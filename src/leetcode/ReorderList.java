package leetcode;

import java.util.ArrayList;

// 143. reorder list
//Given a singly linked list L: L0?L1?…?Ln-1?Ln,
//reorder it to: L0?Ln?L1?Ln-1?L2?Ln-2?…
//
//You must do this in-place without altering the nodes' values.
//
//For example,
//Given {1,2,3,4}, reorder it to {1,4,2,3}.
public class ReorderList {
	
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	
    public static ListNode reorderList1(ListNode head) {
    	
    	if ( head == null || head.next == null ) {
    		return head;
    	}
    	
    	ArrayList <Integer> ali = new ArrayList<Integer>();
    	ListNode currentNode = head;
    	ListNode temp = null;
    	while ( currentNode.next != null ) {
    		ali.add(currentNode.val);
    		temp = currentNode.next;
    		currentNode = temp;
    	}
    	ali.add(currentNode.val);
    	
    	int size = ali.size();
    	int[] array = new int[size];
    	for ( int i=0; i<(size/2); i++ ) {
    		array[i*2] = ali.get(i);
    		array[i*2+1] = ali.get(size-1-i);
    	}
    	if (size%2!=0) {
    		array[size-1] = ali.get(size/2);
    	} 
    	head = buildListNode(array);
    	return head;

    }
    
    public static void reorderList(ListNode head) {
    	
    	if (head == null || head.next == null ) {
    		return;
    	}
    	
    	ListNode pos=head.next;
//    	ListNode tail = pos;
    	ListNode leftTail = head;
//    	ListNode temp = null;
    	
    	while (pos.next != null ) {
    		leftTail = pos;
    		pos = leftTail.next;
    	}
//    	tail = pos;
    	pos = head.next;
    	
    	while (pos.next != null ) {
    		
    	}
    }
    
    private static ListNode buildListNode(int[] array) {
    	ListNode head = null;
    	ListNode tail = null;
    	ListNode newNode = null;
    	
    	if ( array == null || array.length == 0 ) {
    		return head;
    	}
    	
    	int num = array.length;
    	for (int i=0; i<num; i++ ) {
    		newNode = new ListNode(array[i]);
    		
    		if ( head == null ) {
    			head = newNode;
    			tail = newNode;
    		}
    		else {
    			tail.next = newNode;
    			tail = newNode;
    		}
    	}
    	
    	return head;
    }
    
    private static void printListNode(ListNode head) {
    	ListNode currentNode = head;
    	ListNode temp = null;
    	
    	if ( currentNode == null ) {
    		System.out.println("null");
    		return;
    	}
    	
    	while ( currentNode.next != null) {
    		System.out.print(currentNode.val+"->");
    		temp = currentNode.next;
    		currentNode = temp;
    	}
    	System.out.println(currentNode.val);
    }

	public static void main(String[] args) {
		

//		int[] array = {};
//		int[] array = {1};
//		int[] array = {1,2};
//		int[] array = {1,2,3};
//		int[] array = {1,2,3,4};
		int[] array = {1,2,3,4,5,6,7};
		
		ListNode head = buildListNode(array);
		
		printListNode(head);
		head=reorderList1(head);
		printListNode(head);

	}

}
