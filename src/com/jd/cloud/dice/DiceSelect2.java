package com.jd.cloud.dice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

public class DiceSelect2 {
	
	// 数据结构：
	
	// 候选人列表：名字，以及星级
	private HashMap<String, Integer> candidates = new HashMap<String, Integer>();
	
	// 参加分赃的各小组名字，以及各自需要的人数，这是需要初始输入设置的内容
	protected HashMap<String, Integer> groupInfo = new HashMap<String, Integer>();
	
	// 想象用一个集合存放分配到各组的人名列表的结果: HashSet<String> nameList = new HashSet<String>();
	// 因为没有初始化groupInfo之前不知道各小组的名字，所以无法在此声明；即各小组都应该有其对应的namelist存放结果
	// 所以需用一个HashMap存放小组对应的分配结果，key是小组组名，value是nameList: 
	private HashMap< String, HashSet<String> > randResult
		= new HashMap< String, HashSet<String> >();
	
	int errorCode;
	
	// 为了能让HashSet<String>能被访问，必须声明其为全局变量
	// 但是又因为没有初始化groupInfo之前不知道有多少个小组的数目，所以只能声明一个可变长度的ArrayList来储存HashSet<String>nameList：
//	public ArrayList<HashSet<String>> nameList = new ArrayList<HashSet<String>>();
	
	// 设置candidates内容
	public int setCandidates(String name, Integer star) {
		errorCode = 0;
		String reg = "[\\u4e00-\\u9fa5]+";
		
		if ( name == null || name.isEmpty() || name.trim().isEmpty() || !name.matches(reg) ) {
			errorCode = -1;
		}
		else {
			if ( star == null || star.intValue() <= 0 || star.intValue() > 5 ) {
				errorCode = -2;
			}
			else {
				candidates.put(name, star);
			}
		}
		return errorCode;
	}
	
	// 输出candidates内容
	public void getCandidates() {
		System.out.println("候选人名单列表：");
		printHM(candidates);
	}
	
	// 设置groupInfo内容
	public void setGroupInfo(String name, Integer reqs) {
		groupInfo.put(name, reqs);
	}
	
	// 获取groupInfo的项数
	public int getGroupNum(HashMap<String, Integer> groupInfo) {
		return groupInfo.size();
	}
	
	// 输出groupInfo内容
	public void getGroupInfo() {
		System.out.println("参加抽签的组别和需求人数：");
		printHM(groupInfo);
	}
	
	// 初始化randResult
	public void initRandResult(HashMap<String, Integer> hmGroupInfo/*, ArrayList<HashSet<String>> nameList*/) {
		
		// 遍历hmGroupInfo，获取其key，并与namelist构成randResult
		for (Entry<String, Integer>entry : hmGroupInfo.entrySet() ) {
			
			HashSet<String> hse = new HashSet<String> (); 
//			nameList.add(hse);
			
			randResult.put(entry.getKey(), hse);
			
		}
	}
	
	// 指定分配人员
	public void assignRandResult(String groupName, String candidateName) {
		// 首先判断输入的组名是否属于groupInfo，候选人名不用判断
		if ( getGroupNum(groupInfo) < 1 ) {
			System.out.println("there's no group to assign candidates");
			return;
		}
		else {
			if ( !groupInfo.containsKey(groupName) ) {
				System.out.println("not a valid group name, please check it again");
				return;
			}
			else {
				randResult.get(groupName).add(candidateName);
			}
		}
	}
	
	// 分配randResult
	public void setRandResult(HashMap<String, Integer> hmCandidate, HashMap<String, Integer> hmGroupInfo
			, HashMap<String, HashSet<String>> hmRandResult) {
		// foreach遍历候选人列表，逐个对候选人做随机分配
		// 对每轮分配，首先统计参加分配的小组：
		// 首先铜鼓randResult.key即组名，得到randResult.value，即存放结果的set
		// 然后取set的大小，再与groupInfo.key对应的value比较，是否小于后者，则说明还未取满，则可以参加此轮抽签
		// 统计共有几个小组参加抽签，然后取小组的组数作为随机区间，产生随机种子，进行抽签
		// 最后将此次的候选人名字加入抽中小组的set中

		// 创建辅助类validGroup，存放每轮参与抽签的有名额的小组的组名
		HashSet<String> validGroup = new HashSet<String>();
		
		// 创建辅助类ArrayList candidates，存放候选人名单。以便后面利用数组的下标来取随机
		ArrayList<String> candidates = new ArrayList<String>();
		for (Entry<String, Integer>entry0 : hmCandidate.entrySet()) {
			candidates.add(entry0.getKey());
		}
		printArrayList(candidates);
		
		while ( ! candidates.isEmpty() ) {
			int arrayLength = candidates.size();
			
			// 随机取候选人，以避免排在后面的候选人没有抽签的机会的问题
			Random rand = new Random();
			int key = rand.nextInt(arrayLength);
			String entry0 = candidates.get(key);
			
			validGroup.clear();
			
			//首先依次遍历randResult中各组对应的nameList[]，取nameList[i]的大小，与groupinfo。value对比，判断是否已经取满
			for (Entry<String, HashSet<String>>entry1 : hmRandResult.entrySet()) {
				
				int currentSize;
				if (entry1.getValue().isEmpty()) {
					currentSize = 0;
				}
				else {
					currentSize = entry1.getValue().size();
				}
				
				int targetSize = hmGroupInfo.get(entry1.getKey());
				
				if (currentSize < targetSize) {
					// 此小组还有名额，则参与抽签
					validGroup.add(entry1.getKey());
				} 
				else {
					// 此小组名额已满，不参与抽签
				}
			}
			//至此得到了还有名额可以参与抽签的小组组名的set：validGroup
			
			if (validGroup.size()==0) {
				System.out.println("各组均已无名额，分配结束\n");
				return;
			}
			else {
				// go on
			}
			
			// 做个hashset到String[]的转换
			int randNum = validGroup.size();
			String[] group = new String[randNum];
			int j=0;
			for (String str: validGroup){
				group[j]=str;
				j++;
			}
			
			// 取随机
			Random rand1 =new Random();
			int k=rand1.nextInt(randNum);
			// 所以中签的小组名即为group[k]
			// 再根据组名，将此轮抽签的人名存入set中
			hmRandResult.get(group[k]).add(entry0);
			
			candidates.remove(key);
			
		}
		
	}
	
	// 获取并输出分配结果
	public void getRandResult(HashMap<String, HashSet<String>>hmRandResult) {
		
		for ( Entry<String, HashSet<String>>entry : hmRandResult.entrySet() ) {
			System.out.print( entry.getKey() + ": ");
			for (String str : entry.getValue() ) {
				System.out.print(str + ", ");
			}
			System.out.print("\n");
		}
	}
	
	@SuppressWarnings("rawtypes")
	static public void printHM(HashMap hm) {
		Iterator ihm = hm.keySet().iterator();
		while (ihm.hasNext()) {
			Object key = ihm.next();
			Object value = hm.get(key);
			System.out.print(key+":"+value+"  ");
		}
		System.out.print("\n\n");
	}
	
	static public void printArrayList(ArrayList<String> al){
		for (int i = 0 ; i < al.size(); i++ ) {
			System.out.print(al.get(i) + " ");
		}
		System.out.print("\n\n");
	}
	

	public static void main(String[] args) {
		
		DiceSelect2 ds = new DiceSelect2();
		
		// 初始化候选人信息
//		ds.setCandidates("马宁宁", 5);
		ds.setCandidates("邓诗琪", 4);
		ds.setCandidates("王桂林", 4);
		ds.setCandidates("黄山", 4);
		ds.setCandidates("沙怡", 4);
//		ds.setCandidates("于薇", 4);
//		ds.setCandidates("庄田", 4);
		ds.setCandidates("龙思", 4);
		ds.setCandidates("高原", 4);
//		ds.setCandidates("王思琪", 3);
//		ds.setCandidates("孟林海", 3);
		ds.setCandidates("张超", 3);
		ds.setCandidates("赵亚东", 3);
		
		ds.getCandidates();
	
		// 初始化小组信息
		ds.setGroupInfo("赤兵", 7 );
		ds.setGroupInfo("汪晔", 2 );
		ds.setGroupInfo("玉芝", 2 );
		ds.setGroupInfo("彭畅", 2 );
		
		ds.getGroupInfo();
		
		// 初始化存放结果的randResult
		ds.initRandResult(ds.groupInfo/*, ds.nameList*/);
		
		// 已经预定了的人员
		ds.assignRandResult("赤兵", "马宁宁");
		ds.assignRandResult("彭畅", "孟林海");
		ds.assignRandResult("玉芝", "王思琪");
		ds.assignRandResult("汪晔", "于薇");
		ds.assignRandResult("汪晔", "庄田");
		
		// 调用分配函数
		ds.setRandResult(ds.candidates, ds.groupInfo, ds.randResult);
		
		// 获得分配结果
		ds.getRandResult(ds.randResult);

	}
	
}