package com.jd.cloud.testinput;

import java.util.*;

public class TwoSum {
	
	public static ArrayList<Integer> randomIntArrayList() {
		
		ArrayList<Integer> alInt = new ArrayList<Integer>();
		
		// randomly generated a initial size as 100 integer arrayList, then randomly drop some members, to get a random arrayList
		int randomSeed = 100;
		Random random = new Random();
		int size = random.nextInt(randomSeed);
		System.out.println("initial random size="+size);
		
		Random randomDrop = new Random(System.currentTimeMillis()); 
		
		for (int i=0; i<=size; i++) {
			int randomDropInt = randomDrop.nextInt();
			if ( (randomDropInt % 2) == 0) {
				alInt.add(i);
			} 
		}
		
		printArrayList(alInt);
		
		System.out.println("arrayList size="+alInt.size()+"\n");
		
		return alInt;
	}
	
	public static ArrayList<Integer> twoSum(ArrayList<Integer> alInt, int twoSum) {
		
		ArrayList<Integer> alResult = new ArrayList<Integer>();
		
		for (int i=0; i<alInt.size(); i++){
			for (int j=i+1; j<alInt.size(); j++) {
				if (alInt.get(i)+alInt.get(j)==twoSum){
					alResult.add(i);
					alResult.add(j);
				}
			}
		}
		return alResult;
	}
	
	public static void printArrayList(ArrayList<Integer> alInt) {
		System.out.print("[");
		for (int i=0; i<alInt.size(); i++) {
			if (i != alInt.size()-1 ) {
				System.out.print(alInt.get(i)+", ");
			}
			else {
				System.out.print(alInt.get(i));
			}
			
		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		
		// initialize a random arrayList 
		ArrayList<Integer> alInt = randomIntArrayList();
		
		// if the target arrayList is null, only 1 number, re-init it
		while (alInt.isEmpty() || alInt.size()<2 ) {
			alInt = randomIntArrayList();
		}
		
		// randomly select two position, and calculate their sum
		int alSize = alInt.size();
		
		Random random = new Random();
		int index1 = random.nextInt(alSize);
		int index2 = random.nextInt(alSize);
		// make sure index1 & index2 be unique
		while (index2==index1){
			index2 = random.nextInt(alSize);
		}
		System.out.println("index1="+index1+", index2="+index2);
		int num1=alInt.get(index1);
		int num2=alInt.get(index2);
		System.out.println("al["+index1+"]:"+num1+", al["+index2+"]:"+num2);
		int twoSum = num1+num2;
		System.out.println("two sum is: "+twoSum+"\n");
		
//		// drop those mappings duplicated
//		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
//		for (int i=0; i<alInt.size(); i++){
//			for (int j=i+1; j<alInt.size(); j++) {
//				if (alInt.get(i)+alInt.get(j)==twoSum && ( (i==index1 && j == index2) || (i==index2 && j==index1) )  ){
//					hm.put(i, j);
//				}
//			}
//		}
//		
//		Iterator<Entry<Integer, Integer>> hmi = hm.entrySet().iterator();
//		while (hmi.hasNext()) {
//			Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) hmi.next();
//			Integer key = entry.getKey();
//			Integer val = entry.getValue();
//			if (hm.containsValue(key)) {
//				hm.remove(key,val);
//			}
//		}
//		
//		Iterator<Entry<Integer, Integer>> hmj = hm.entrySet().iterator();
//		while (hmj.hasNext()) {
//			Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) hmj.next();
//			Integer key = entry.getKey();
//			Integer val = entry.getValue();
//			alInt.remove(key);
//			alInt.remove(val);
//		}	
//		
//		System.out.println("size after drop duplicated="+alInt.size());
//		printArrayList(alInt);
//		
//		// re-print the new indexs and numbers
//		index1=alInt.indexOf(alInt.get(index1));
//		index2=alInt.indexOf(alInt.get(index2));
//		System.out.println("index1="+index1+", index2="+index2);
//		System.out.println("al["+index1+"]:"+alInt.get(index1)+", al["+index2+"]:"+alInt.get(index2));
		
		ArrayList<Integer> alResult = new ArrayList<Integer>();
		alResult = twoSum(alInt, twoSum);
		
		System.out.print("[");
		for (int i = 0 ; i < ( alResult.size() -1 ) ; i++ ) {
			if ( i %2 == 0) {
				System.out.print(alResult.get(i)+", ");
			}
			else {
				System.out.print(alResult.get(i)+"] [");
			}
		}
		System.out.print(alResult.get(alResult.size()-1)+"]");
		
	}

}
