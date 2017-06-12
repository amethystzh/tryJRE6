package com.jd.cloud.dice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class DiceSelect {
/*the very 1st version*/	

	public static void main(String[] args) {
		
		// 候选人
		HashMap<Integer, String> hsCandidate = new HashMap<Integer, String> ();
		hsCandidate.put(0, "马宁宁");
		hsCandidate.put(1, "邓诗琪");
		hsCandidate.put(2, "王桂林");
		hsCandidate.put(3, "黄山");
		hsCandidate.put(4, "沙怡");
		hsCandidate.put(5, "于薇");
		hsCandidate.put(6, "庄田");
		hsCandidate.put(7, "龙思");
		hsCandidate.put(8, "高原");
		hsCandidate.put(9, "王思琪");
		hsCandidate.put(10, "孟林海");
		hsCandidate.put(11, "张超");
		hsCandidate.put(12, "赵亚东");
		
		int num = hsCandidate.size();
		System.out.println("候选人个数："+num);
		
		for (Entry<Integer, String> entry: hsCandidate.entrySet() ) {
			System.out.print(entry.getKey() + entry.getValue() + "  ");
		}
		System.out.print("\n\n");
		
		// 各组需要的人数
		short cbLimit = 7;
		short wyLimit = 2;
		short yzLimit = 2;
		short pcLimit = 2;
		
		ArrayList<String> cb = new ArrayList<String>();
		ArrayList<String> wy = new ArrayList<String>();
		ArrayList<String> yz = new ArrayList<String>();
		ArrayList<String> pc = new ArrayList<String>();
		
		// 按序号取随机
		ArrayList<Integer> randResult = new ArrayList<Integer>(); 
		Random rand = new Random();
		while ( randResult.isEmpty() || randResult.size() < hsCandidate.size() ) {
			int thisRound = rand.nextInt(num);
			if (randResult.contains(thisRound)) {
				
			}
			else {
				randResult.add(thisRound);
			}
		}
		
		System.out.print("随机排位结果：");
		
		for (int i = 0; i < randResult.size(); i++){
			System.out.print(randResult.get(i) + " ");
		}
		System.out.print("\n\n");
		
		// 按随机结果依次分配到各组中
		for (int i=0; i < randResult.size(); i++){
			int t=randResult.get(i);
			switch ((i+4)%4){
			case 1:
				if (wy.size() < wyLimit) {
					wy.add(hsCandidate.get(t));
				}
				break;
			case 2:
				if (yz.size() < yzLimit) {
					yz.add(hsCandidate.get(t));
				}
				break;
			case 3:
				if (pc.size() < pcLimit) {
					pc.add(hsCandidate.get(t));
				}
				break;
			default:
				cb.add(hsCandidate.get(t));
			}
		}
	
		// 剩余的人的处理
		for (int i=0; i< randResult.size(); i++){
			int t=randResult.get(i);

			if ( wy.contains(hsCandidate.get(t)) || yz.contains(hsCandidate.get(t)) 
					|| pc.contains(hsCandidate.get(t)) || cb.contains(hsCandidate.get(t)) ) {
				continue;
			}
			else {
				if (cb.size() <= cbLimit){
					cb.add(hsCandidate.get(t));
				}
			}
		}
		
		System.out.print("赤兵组结果：");
		printArrayList(cb);
		
		System.out.print("汪晔组结果：");
		printArrayList(wy);
		
		System.out.print("玉芝组结果：");
		printArrayList(yz);
		
		System.out.print("彭畅组结果：");
		printArrayList(pc);
	}
	
	static public void printArrayList(ArrayList<String> al){
		for (int i = 0 ; i < al.size(); i++ ) {
			System.out.print(al.get(i) + " ");
		}
		System.out.print("\n");
	}

}
