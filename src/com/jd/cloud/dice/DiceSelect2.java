package com.jd.cloud.dice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

public class DiceSelect2 {
	
	// ���ݽṹ��
	
	// ��ѡ���б����֣��Լ��Ǽ�
	private HashMap<String, Integer> candidates = new HashMap<String, Integer>();
	
	// �μӷ��ߵĸ�С�����֣��Լ�������Ҫ��������������Ҫ��ʼ�������õ�����
	protected HashMap<String, Integer> groupInfo = new HashMap<String, Integer>();
	
	// ������һ�����ϴ�ŷ��䵽����������б�Ľ��: HashSet<String> nameList = new HashSet<String>();
	// ��Ϊû�г�ʼ��groupInfo֮ǰ��֪����С������֣������޷��ڴ�����������С�鶼Ӧ�������Ӧ��namelist��Ž��
	// ��������һ��HashMap���С���Ӧ�ķ�������key��С��������value��nameList: 
	private HashMap< String, HashSet<String> > randResult
		= new HashMap< String, HashSet<String> >();
	
	int errorCode;
	
	// Ϊ������HashSet<String>�ܱ����ʣ�����������Ϊȫ�ֱ���
	// ��������Ϊû�г�ʼ��groupInfo֮ǰ��֪���ж��ٸ�С�����Ŀ������ֻ������һ���ɱ䳤�ȵ�ArrayList������HashSet<String>nameList��
//	public ArrayList<HashSet<String>> nameList = new ArrayList<HashSet<String>>();
	
	// ����candidates����
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
	
	// ���candidates����
	public void getCandidates() {
		System.out.println("��ѡ�������б�");
		printHM(candidates);
	}
	
	// ����groupInfo����
	public void setGroupInfo(String name, Integer reqs) {
		groupInfo.put(name, reqs);
	}
	
	// ��ȡgroupInfo������
	public int getGroupNum(HashMap<String, Integer> groupInfo) {
		return groupInfo.size();
	}
	
	// ���groupInfo����
	public void getGroupInfo() {
		System.out.println("�μӳ�ǩ����������������");
		printHM(groupInfo);
	}
	
	// ��ʼ��randResult
	public void initRandResult(HashMap<String, Integer> hmGroupInfo/*, ArrayList<HashSet<String>> nameList*/) {
		
		// ����hmGroupInfo����ȡ��key������namelist����randResult
		for (Entry<String, Integer>entry : hmGroupInfo.entrySet() ) {
			
			HashSet<String> hse = new HashSet<String> (); 
//			nameList.add(hse);
			
			randResult.put(entry.getKey(), hse);
			
		}
	}
	
	// ָ��������Ա
	public void assignRandResult(String groupName, String candidateName) {
		// �����ж�����������Ƿ�����groupInfo����ѡ���������ж�
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
	
	// ����randResult
	public void setRandResult(HashMap<String, Integer> hmCandidate, HashMap<String, Integer> hmGroupInfo
			, HashMap<String, HashSet<String>> hmRandResult) {
		// foreach������ѡ���б�����Ժ�ѡ�����������
		// ��ÿ�ַ��䣬����ͳ�Ʋμӷ����С�飺
		// ����ͭ��randResult.key���������õ�randResult.value������Ž����set
		// Ȼ��ȡset�Ĵ�С������groupInfo.key��Ӧ��value�Ƚϣ��Ƿ�С�ں��ߣ���˵����δȡ��������ԲμӴ��ֳ�ǩ
		// ͳ�ƹ��м���С��μӳ�ǩ��Ȼ��ȡС���������Ϊ������䣬����������ӣ����г�ǩ
		// ��󽫴˴εĺ�ѡ�����ּ������С���set��

		// ����������validGroup�����ÿ�ֲ����ǩ���������С�������
		HashSet<String> validGroup = new HashSet<String>();
		
		// ����������ArrayList candidates����ź�ѡ���������Ա��������������±���ȡ���
		ArrayList<String> candidates = new ArrayList<String>();
		for (Entry<String, Integer>entry0 : hmCandidate.entrySet()) {
			candidates.add(entry0.getKey());
		}
		printArrayList(candidates);
		
		while ( ! candidates.isEmpty() ) {
			int arrayLength = candidates.size();
			
			// ���ȡ��ѡ�ˣ��Ա������ں���ĺ�ѡ��û�г�ǩ�Ļ��������
			Random rand = new Random();
			int key = rand.nextInt(arrayLength);
			String entry0 = candidates.get(key);
			
			validGroup.clear();
			
			//�������α���randResult�и����Ӧ��nameList[]��ȡnameList[i]�Ĵ�С����groupinfo��value�Աȣ��ж��Ƿ��Ѿ�ȡ��
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
					// ��С�黹�����������ǩ
					validGroup.add(entry1.getKey());
				} 
				else {
					// ��С�������������������ǩ
				}
			}
			//���˵õ��˻���������Բ����ǩ��С��������set��validGroup
			
			if (validGroup.size()==0) {
				System.out.println("�������������������\n");
				return;
			}
			else {
				// go on
			}
			
			// ����hashset��String[]��ת��
			int randNum = validGroup.size();
			String[] group = new String[randNum];
			int j=0;
			for (String str: validGroup){
				group[j]=str;
				j++;
			}
			
			// ȡ���
			Random rand1 =new Random();
			int k=rand1.nextInt(randNum);
			// ������ǩ��С������Ϊgroup[k]
			// �ٸ��������������ֳ�ǩ����������set��
			hmRandResult.get(group[k]).add(entry0);
			
			candidates.remove(key);
			
		}
		
	}
	
	// ��ȡ�����������
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
		
		// ��ʼ����ѡ����Ϣ
//		ds.setCandidates("������", 5);
		ds.setCandidates("��ʫ��", 4);
		ds.setCandidates("������", 4);
		ds.setCandidates("��ɽ", 4);
		ds.setCandidates("ɳ��", 4);
//		ds.setCandidates("��ޱ", 4);
//		ds.setCandidates("ׯ��", 4);
		ds.setCandidates("��˼", 4);
		ds.setCandidates("��ԭ", 4);
//		ds.setCandidates("��˼��", 3);
//		ds.setCandidates("���ֺ�", 3);
		ds.setCandidates("�ų�", 3);
		ds.setCandidates("���Ƕ�", 3);
		
		ds.getCandidates();
	
		// ��ʼ��С����Ϣ
		ds.setGroupInfo("���", 7 );
		ds.setGroupInfo("����", 2 );
		ds.setGroupInfo("��֥", 2 );
		ds.setGroupInfo("��", 2 );
		
		ds.getGroupInfo();
		
		// ��ʼ����Ž����randResult
		ds.initRandResult(ds.groupInfo/*, ds.nameList*/);
		
		// �Ѿ�Ԥ���˵���Ա
		ds.assignRandResult("���", "������");
		ds.assignRandResult("��", "���ֺ�");
		ds.assignRandResult("��֥", "��˼��");
		ds.assignRandResult("����", "��ޱ");
		ds.assignRandResult("����", "ׯ��");
		
		// ���÷��亯��
		ds.setRandResult(ds.candidates, ds.groupInfo, ds.randResult);
		
		// ��÷�����
		ds.getRandResult(ds.randResult);

	}
	
}