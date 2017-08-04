package com.jd.cloud.testinput;

import java.util.*;

public class TwoSum {
	
	public static ArrayList<Integer> randomIntArrayList() {
		
		ArrayList<Integer> alInt = new ArrayList<Integer>();
		
		int randomSeed = 100;
		Random random = new Random();
		int size = random.nextInt(randomSeed);
		System.out.println("original size="+size);
		
		Random randomDrop = new Random(System.currentTimeMillis()); 
		
		for (int i=0; i<=size; i++) {
			int randomDropInt = randomDrop.nextInt();
			if ( (randomDropInt % 2) == 0) {
				alInt.add(i);
			} 
		}
		
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
		System.out.println("size after drop="+alInt.size());
		
		return alInt;
	}
	
	public static int[] twoSum(ArrayList<Integer> alInt, int twoSum) {
		int[] resultArray = new int[2];
		
		for (int i=0; i<alInt.size(); i++){
			for (int j=i+1; j<alInt.size(); j++) {
				if (alInt.get(i)+alInt.get(j)==twoSum){
					resultArray[0]=i;
					resultArray[1]=j;
				}
			}
		}

		return resultArray;
	}

	public static void main(String[] args) {
		
		ArrayList<Integer> alInt = randomIntArrayList();
		
		while (alInt.isEmpty() || alInt.size()==0 ) {
			alInt = randomIntArrayList();
		}
		
		int alSize = alInt.size();
		
		Random random = new Random();
		int index1 = random.nextInt(alSize);
		int index2 = random.nextInt(alSize);
		while (index2==index1){
			index2 = random.nextInt(alSize);
		}
		System.out.println("index1="+index1+", index2="+index2);
		System.out.println("al["+index1+"]:"+alInt.get(index1)+", al["+index2+"]:"+alInt.get(index2));
		int twoSum = alInt.get(index1) + alInt.get(index2);
		System.out.println("two sum is: "+twoSum);
		
		int[] resultArray = new int[2];
		resultArray = twoSum(alInt, twoSum);
		
		System.out.println("["+resultArray[0]+", "+resultArray[1]+"]");
		
		

	}

}
