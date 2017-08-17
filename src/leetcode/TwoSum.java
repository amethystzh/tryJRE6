package leetcode;

import java.util.*;

// 1. Two Sum
// Given an array of integers, return indices of the two numbers such that they add up to a specific target.
//
//You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//Example:
//Given nums = [2, 7, 11, 15], target = 9,
//
//Because nums[0] + nums[1] = 2 + 7 = 9,
//return [0, 1].

public class TwoSum {
	
	public static ArrayList<Integer> randomIntArrayList() {
		
		ArrayList<Integer> alInt = new ArrayList<Integer>();
		
		// randomly generated a initial size as 100 integer arrayList, then randomly drop some members, to get a random arrayList
		int randomSeed = 100;
		Random random = new Random();
		int size = random.nextInt(randomSeed);
//		System.out.println("initial random size="+size);
		
		Random randomDrop = new Random(System.currentTimeMillis()); 
		
		for (int i=0; i<=size; i++) {
			int randomDropInt = randomDrop.nextInt();
			if ( (randomDropInt % 2) == 0) {
				alInt.add(i);
			} 
		}
		
//		printArrayList(alInt);
//		
//		System.out.println("arrayList size="+alInt.size()+"\n");
		
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
	
	public static int[] twoSum(int[] arrayInt, int twoSum) {
		
		int[] arrayResult = new int[2];
		
		for (int i=0; i<arrayInt.length; i++) {
			for (int j=i+1; j<arrayInt.length; j++) {
				if (arrayInt[i] + arrayInt[j] == twoSum ) {
					arrayResult[0] = i;
					arrayResult[1] = j;
					return arrayResult;
				}
			}
		}
		return arrayResult;
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
	
	public static void printArray(int[] alInt) {
		System.out.print("[");
		for (int i=0; i<alInt.length; i++) {
			
			if (i != alInt.length-1 ) {
				System.out.print(alInt[i]+", ");
			}
			else {
				System.out.print(alInt[alInt.length-1]);
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
//		System.out.println("index1="+index1+", index2="+index2);
		int num1=alInt.get(index1);
		int num2=alInt.get(index2);
//		System.out.println("al["+index1+"]:"+num1+", al["+index2+"]:"+num2);
		int twoSum = num1+num2;
//		System.out.println("two sum is: "+twoSum+"\n");
		
		// first get all the answer pairs fulfill the two sum calculations
		ArrayList<Integer> alResult = new ArrayList<Integer>();
		alResult = twoSum(alInt, twoSum);
		
//		System.out.print("[");
//		for (int i = 0 ; i < ( alResult.size() -1 ) ; i++ ) {
//			if ( i %2 == 0) {
//				System.out.print(alResult.get(i)+", ");
//			}
//			else {
//				System.out.print(alResult.get(i)+"] [");
//			}
//		}
//		System.out.print(alResult.get(alResult.size()-1)+"]"+"\n");
		
		// mark those mappings not as index1/2 as null. can't remove, or arrayList itself will changed
		for (int i = 0 ; i < alResult.size() ; i++ ) {

			if ( alResult.get(i) == index1 || alResult.get(i) == index2 ) {

			}
			else {
				alInt.set((alResult.get(i)),null);
				
			}
		}
		
		// re-print the alInt
//		System.out.println("the final array is: ");
//		printArrayList(alInt);
		
		// transfer the arrayList to array, exclude those null values
		int[] arrayInt = new int[( alInt.size() - alResult.size() +2 )];
		int s = 0;
		for (int i=0; i<alInt.size(); i++) {
//			System.out.print("i="+i+", al[i]="+alInt.get(i));
			if (alInt.get(i)!=null) {
				arrayInt[s] = alInt.get(i);
				s++;
//				System.out.println(" , !null, save to array["+(s-1)+"]");
			}
			else {
				// do nothing
//				System.out.println(" , null, do not save");
			}
		}
		// the significant array:
		printArray(arrayInt);
		
		int[] arrayResult = twoSum(arrayInt, twoSum);
		
		System.out.print("sum="+twoSum+", the positons are: ");
		printArray(arrayResult);
		System.out.println("["+arrayResult[0]+"]="+arrayInt[arrayResult[0]]+", ["+arrayResult[1]+"]="+arrayInt[arrayResult[1]]+", sum="
							+(arrayInt[arrayResult[0]]+arrayInt[arrayResult[1]]) );
		
	}

}
