#!/bin/sh
#
# this shell is used to calculate & collect the statistics of 
# Jenkins jobs status
# this script is expected to be run after jenkinsmetadata.sh
# by zcb, 2017-12-26
# re-arrange the design, 2017-12-28

# define the env vars
JENKINS_DIR=/export/jenkins
JENKINS_JOB_DIR=$JENKINS_DIR/lib/jobs
JENKINS_CONFIG=$JENKINS_DIR/lib/config.xml

HOME_DIR=/home/test
WORKING_DIR=$HOME_DIR/working
BUILD_DIR=$WORKING_DIR/builds
VIEW_DIR=$WORKING_DIR/views
RESULT_DIR=$HOME_DIR/result
LOG_DIR=$HOME_DIR/log

ALL_JOBS=$WORKING_DIR/alljobs.txt
ALL_JOBS_FMT=$WORKING_DIR/alljobs_fmt.txt
ALL_VIEWS=$WORKING_DIR/allviews.txt
VIEW_CONFIG=$WORKING_DIR/config.xml

JOBS_LAST_BUILD=$WORKING_DIR/jlb.txt
JOBS_EVER_BUILT=$WORKING_DIR/jeb.txt

SHELL_NAME=failrate7d

META_RESULT_DIR=$WORKING_DIR/metaresult
FAILRATE_DIR=$META_RESULT_DIR/$SHELL_NAME
RESULTS=$RESULT_DIR/$SHELL_NAME.txt
RESULTS_NAME=$SHELL_NAME

LOG_NAME=$SHELL_NAME

log="$LOG_DIR/$LOG_NAME-$(date +%Y%m%d%H%M%S).log"
fifo="tmp.fifo"
rm -f ${fifo}
mkfifo ${fifo}
cat ${fifo} | tee ${log} &
exec 1> ${fifo}
exec 2>&1

echo "========================================="
echo "calc jenkins Views weekly job static rate"
echo "========================================="
echo "check working dirs"
cd $HOME_DIR

# clear previous files before running
if [ -d "$WORKING_DIR" -a -d "$BUILD_DIR" -a -d "$VIEW_DIR"\
 -a -d "$LOG_DIR" ]; then
  echo "working dir exist"
else
  echo "working dir not exist!"
  echo "please run jenkinsmetadata.sh first!"
  exit 1
fi

if [ -r "$ALL_JOBS_FMT" -a -r "$ALL_VIEWS" -a -r "$JOBS_EVER_BUILT" ]; then
  echo "metadata exist"
else
  echo "metadata not exist!"
  echo "please run jenkinsmetadata.sh first!"
  exit 1
fi

if [ -d "$RESULT_DIR" ]; then
  echo "previous results dir exist"
else
  echo "mkdir results dir"
  mkdir $RESULT_DIR
fi

if [ -d "$META_RESULT_DIR" ]; then
  echo "previous meta result dir exist"
else
  echo "mkdir metaresult dir"
  mkdir $META_RESULT_DIR
fi

if [ -d "$FAILRATE_DIR" ]; then
  echo "previous index dir exist"
else
  echo "mkdir index dir"
  mkdir $FAILRATE_DIR
fi

# backup previous result
if [ -f "$RESULTS" ]; then
  echo "backup the previous result"
  fileoffset=$(stat $RESULTS | grep Modify | awk '{print $2 $3}\
' | cut -d"." -f1 | sed -e 's/-//g' -e 's/://g')
  mv $RESULTS $RESULT_DIR/$RESULTS_NAME-$fileoffset.txt
fi
touch $RESULTS
echo "View Fail Skip Unst Pass Total FailRate UnstRate PassRate" > $RESULTS

timeoffset=$(date "+%Y%m%d%H%M")
# there couldn't be the same dir exist
this_result_dir=$FAILRATE_DIR/$timeoffset
mkdir $this_result_dir

# accoring to allviews.txt, each line stands for a view, accoring to each
# line(view) to search in views dir, get the [viewname].txt which store the 
# jobs belong to this view, then according to each line(job) to search in 
# jeb.txt, to tell if the job had ever been built. if no, skip this job to
# the next job; if yes, then search in builds dir, get the job's build 
# summary file [jobname].mr, then according to the time period, retrieve the
# build times valid for the statistic duration, then at last calculate the 
# statistic index
# update(2017-12-28): change the logic to tell if job ever been built

# go through allviews
cat $ALL_VIEWS | while read EACHVIEW
do
  view2jobs=$VIEW_DIR/$EACHVIEW.txt
  viewresult=$this_result_dir/$EACHVIEW.txt
  touch $viewresult
# go through each jobs
  cat $view2jobs | while read jobname
  do
# first tell if the job had ever been built
  #  jeb=$(grep $jobname $JOBS_EVER_BUILT | awk '{print $1')
  #  if [ $jeb = "Yes" ]; then
  # no need to read jeb.txt, just tell if $jobname.mr exist is OK
  # by zcb, 2017-12-28
    if [ -r "$BUILD_DIR/$jobname.mr" ]; then
# go through this job's builds summary to retrieve data
      job2builds=$BUILD_DIR/$jobname.mr
#      echo $job2builds
      awk 'BEGIN {
#                   print "====="mr"=====";
                   FS=",";
                   cmd1="date +%s";
                   cmd1 | getline today;
#                   print "today is: "today;
                   weekago=(today-7*24*3600)*1000;
#                   print "weekago is: "weekago;
                 }
          {
#            print "------------"
#            print $1,$2,$3,$4;
            time=$2;
            statu=$4;
#            print "time is: "time;
#            print "statu is: "statu;
#            print "weekago inside awk main is: "weekago;
            if(time>weekago){print $4}
          }' $job2builds >> $viewresult
    fi
  done
  # according to viewresult to calculate the static index
  total=$(sed -n '$=' $viewresult)
  if [[ ! -s $viewresult ]]; then
    echo "$EACHVIEW 0 0 0 0 0 0 0 0" >> $RESULTS
  else
    fail=$(grep -o 'FAILURE' $viewresult | wc -l)
    skip=$(grep -o 'ABORTED' $viewresult | wc -l)
    unst=$(grep -o 'UNSTABLE' $viewresult | wc -l)
    pass=$(grep -o 'SUCCESS' $viewresult | wc -l)
    # calculate the rate
    failrate=$(echo "scale=3;$fail/($total-$skip)" | bc -l)
    unstrate=$(echo "scale=3;$unst/($total-$skip)" | bc -l)
    passrate=$(echo "scale=3;$pass/($total-$skip)" | bc -l)
    echo "$EACHVIEW $fail $skip $unst $pass $total $failrate \
$unstrate $passrate" >> $RESULTS
  fi
  echo -e ".\c" 
done
echo
echo "done"
ln1=$(sed -n '$=' $ALL_VIEWS)
ln2=$(sed -n '$=' $RESULTS)
ln2=$(($ln2 - 1 ))
if [ $ln1 = $ln2 ]; then
  echo "Weekly Jobs' statistic rate for each of $ln1 Views can be found at:"
else
  echo "checksum failed, please check the result:"
fi
echo $RESULTS
printf "\015"
exit 0
