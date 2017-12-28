#!/bin/sh

# this shell is used to collect jenkins jobs' meta info at huangcun 
# jenkins server
# by zcb, 2017-12-26
# re-arrange the design, 2017-12-27

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

SHELL_NAME=jenkinsmetadata

LOG_NAME=$SHELL_NAME

log="$LOG_DIR/$LOG_NAME-$(date +%Y%m%d%H%M%S).log"
fifo="tmp.fifo"
rm -f ${fifo}
mkfifo ${fifo}
cat ${fifo} | tee ${log} &
exec 1> ${fifo}
exec 2>&1

echo "==============================="
echo "collect jenkins jobs' meta data"
echo "==============================="
echo "init working dirs"
cd $HOME_DIR

# clear previous files before running
if [ -d "$WORKING_DIR" ]; then
  echo "rm old and mk new working dir"
  rm -rf $WORKING_DIR
fi
mkdir $WORKING_DIR
mkdir $BUILD_DIR
# update-2017-12028: no need to mkdir result this time
#mkdir $RESULT_DIR

if [ -d "$LOG_DIR" ]; then
  echo "log dir exist"
else
  mkdir $LOG_DIR
fi

# retrieve all the jobs' name via ls jenkins/lib/jobs, each 
# sub-dir stands for a job, save the jobs' name info to alljobs.txt
echo "-------------------------------"
echo "begin to retrieve jobs info"
ls $JENKINS_JOB_DIR > $ALL_JOBS

# collect each job's build summary file
# var i is used for display only, not function related
touch $JOBS_LAST_BUILD
touch $JOBS_EVER_BUILT
# Note: there are few jobs which name contains space
i=1
cat $ALL_JOBS | while read LINE
do
  JOB_SUMMARY_DIR=$JENKINS_JOB_DIR/$LINE
  # erase the space char in job names
  LINE_FMT=$(echo ${LINE// /})
  if [ -f "$JOB_SUMMARY_DIR/all_builds.mr" ]; then
    cp "$JOB_SUMMARY_DIR"/all_builds.mr $BUILD_DIR/$LINE_FMT.mr
    last_line=$(cat $BUILD_DIR/$LINE_FMT.mr | tail -1)
    echo $LINE_FMT","$last_line >> $JOBS_LAST_BUILD
    echo "Yes "$LINE_FMT >> $JOBS_EVER_BUILT
    if [ $(( $i % 30 )) = '0' ]; then echo -e ".\c"; fi
  else
    echo "No "$LINE_FMT >> $JOBS_EVER_BUILT
    if [ $(( $i % 30 )) = '0' ]; then echo -e ".\c"; fi
  fi
  i=$(($i+1))
done
# erase the space char in alljobs.txt and save as alljobs_fmt.txt
sed "/ /d" $ALL_JOBS > $ALL_JOBS_FMT
echo
jn=$(sed -n '$=' $ALL_JOBS)
echo "total $jn jobs info collected"

echo "-------------------------------"
echo "begin to retrieve views info"
cp $JENKINS_CONFIG $VIEW_CONFIG
mkdir $VIEW_DIR
touch $ALL_VIEWS

# for 1. <listView>
# get <listView> line num
awk '/<listView>/ {print NR}' $VIEW_CONFIG > $WORKING_DIR/lvbln.txt
# get </listView> line num
awk '/<\/listView>/ {print NR}' $VIEW_CONFIG > $WORKING_DIR/lveln.txt
# get total line num, that is, the total num of <listView> sections
n=$(sed -n '$=' $WORKING_DIR/lvbln.txt)
echo "listView sections are "$n

for((i=1;i<=n;i=i+1))
  do
     # get line num of <listView>
     start=$(sed -n "$i p" $WORKING_DIR/lvbln.txt)
     # get line num of </listView>
     end=$(sed -n "$i p" $WORKING_DIR/lveln.txt)
     # get the section between <listView> and </listView>
     sed -n "$start,$end p" $VIEW_CONFIG > $WORKING_DIR/tmp.xml
     # get view name: get the line which contains <name>
     # lvn stands for ListViewName
     lvn=$(grep "<name>" $WORKING_DIR/tmp.xml)
     # erase the left substring before the 1st >
     lvn=$(echo ${lvn#*>})
     # erase the right substring after the last <
     lvn=$(echo ${lvn%<*})
     # erase the space char in the name string
     lvn=$(echo ${lvn// /})
     # rename the view info xml to the view's name, and copy it to view dir
     mv $WORKING_DIR/tmp.xml $VIEW_DIR/$lvn.xml
     echo $lvn >> $ALL_VIEWS
     # retrieve job name included in view
     touch $VIEW_DIR/$lvn.txt
     # job name are defined in the tab <string>
     grep "<string>" $VIEW_DIR/$lvn.xml > $VIEW_DIR/tmp.xml
     # re-format the job name line, erase the tab
     cat $VIEW_DIR/tmp.xml | while read LINE
     do
       jobname=$(echo ${LINE#*>})
       jobname=$(echo ${jobname%<*})
       # erase the space in job names
       jobname=$(echo ${jobname// /})
       echo $jobname >> $VIEW_DIR/$lvn.txt
     done
     rm $VIEW_DIR/tmp.xml
#     echo "view "$lvn" mapped jobs"
  echo -e ".\c"
done
rm $WORKING_DIR/lvbln.txt
rm $WORKING_DIR/lveln.txt
echo

# for 2. <hudson.plugins.view.dashboard.Dashboard>
awk '/<hudson.plugins.view.dashboard.Dashboard/ {print NR}' $VIEW_CONFIG > $WORKING_DIR/dbbln.txt
awk '/<\/hudson.plugins.view.dashboard.Dashboard>/ {print NR}' $VIEW_CONFIG > $WORKING_DIR/dbeln.txt
m=$(sed -n '$=' $WORKING_DIR/dbbln.txt)
#echo "-------------------------"
echo "dashboard sections are "$m
for((j=1;j<=m;j=j+1))
  do
     start=$(sed -n "$j p" $WORKING_DIR/dbbln.txt)
     end=$(sed -n "$j p" $WORKING_DIR/dbeln.txt)
     sed -n "$start,$end p" $VIEW_CONFIG > $WORKING_DIR/tmp.xml
     # dbn stands for DashBoardName
     dbn=$(grep "<name>" $WORKING_DIR/tmp.xml)
     dbn=$(echo ${dbn#*>})
     dbn=$(echo ${dbn%<*})
     dbn=$(echo ${dbn// /})
     mv $WORKING_DIR/tmp.xml $VIEW_DIR/$dbn.xml
     echo $dbn >> $ALL_VIEWS
     touch $VIEW_DIR/$dbn.txt
     grep "<string>" $VIEW_DIR/$dbn.xml > $VIEW_DIR/tmp.xml
     cat $VIEW_DIR/tmp.xml | while read LINE
     do
       jobname=$(echo ${LINE#*>})
       jobname=$(echo ${jobname%<*})
       jobname=$(echo ${jobname// /})
       echo $jobname >> $VIEW_DIR/$dbn.txt
     done
     rm $VIEW_DIR/tmp.xml
     echo -e ".\c"
#     echo "view "$dbn" mapped jobs"
done
echo
rm $WORKING_DIR/dbbln.txt
rm $WORKING_DIR/dbeln.txt

echo "total "$((m+n))" views info collected"

echo "done"

printf "\015"

exit 0
