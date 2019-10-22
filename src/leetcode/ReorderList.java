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
    	
    	ArrayList <Integer> ali = new ArrayList<>();
    	ListNode currentNode = head;
    	ListNode temp;
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
    
    private static void reorderList(ListNode head) {
    	
    	if (head == null || head.next == null || head.next.next==null ) { // list with 0, 1, 2 node all return as it is
    		return;
    	}
    	
    	ListNode pos=head;
    	ListNode temp = pos.next;
    	ListNode beforeTail = getBeforeTailNode(pos);
    	
    	do {
    		
//    		System.out.println("0: pos="+pos.val+", pos.next="+pos.next.val+", beforeTail="+beforeTail.val+", beforeTail.next="+beforeTail.next.val+", temp="+temp.val);
    		
    		pos.next = beforeTail.next;
    		
//    		System.out.println("1: pos="+pos.val+", pos.next="+pos.next.val+", beforeTail="+beforeTail.val+", beforeTail.next="+beforeTail.next.val+", temp="+temp.val);
    		
    		beforeTail.next.next = temp;
    		
//    		System.out.println("2: pos="+pos.val+", pos.next="+pos.next.val+", beforeTail="+beforeTail.val+", beforeTail.next="+beforeTail.next.val+", temp="+temp.val);
    		
    		
    		beforeTail.next = null;
    		
//    		System.out.println("3: pos="+pos.val+", pos.next="+pos.next.val+", beforeTail="+beforeTail.val+", beforeTail.next=null, temp="+temp.val);
    		
    		pos = temp;
    		
    		if ( pos.next != null ) {
    			temp = pos.next;
//    			System.out.println("4: pos="+pos.val+", pos.next="+pos.next.val+", beforeTail="+beforeTail.val+", beforeTail.next=null, temp="+temp.val);
    		}
//    		else {
//    			System.out.println("4: pos.next=null");
//    		}
    		
    		
    		
    		
    		beforeTail = getBeforeTailNode(pos);
    		
//    		System.out.println("5: pos="+pos.val+", pos.next="+pos.next.val+", beforeTail="+beforeTail.val+", beforeTail.next="+beforeTail.next.val+", temp="+temp.val);

//    		printListNode(head);
    	} while ( pos.next != null && beforeTail.next != temp );
    	
    	head=pos;

    }
    
    public static void reorderList2(ListNode head) {
        //Tricky part is hard to trace tail and then tail.prev
        //solution: reverse the back half so we can iterater from tail -> tail.next
        
        //find mid 
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        //reverse the part after mid
        ListNode cur = slow.next;
        while(cur != null && cur.next != null){
            ListNode tempN = cur.next;
            cur.next = tempN.next;
            tempN.next = slow.next;
            slow.next = tempN;
        }

        //relink 
        ListNode iter1 = head;       //head
        ListNode iter2 = slow.next;  //head of reversed part
        slow.next = null;            //break 2 lists to avoid cycle
        while(iter1 != null && iter2 != null){
            ListNode next1 = iter1.next;
            ListNode next2 = iter2.next;
            iter1.next = iter2;
            iter2.next = next1;
            iter1 = next1;
            iter2 = next2;
        }
        
    }
    
    private static ListNode getBeforeTailNode(ListNode head) {
    	
    	if (head == null || head.next == null) return null;
    	
    	if (head.next.next == null ) return head; 
    		
    	ListNode beforeTail = head.next;
    	
    	while (beforeTail.next.next != null) {
    		beforeTail = beforeTail.next;
    	}
    	return beforeTail;
    }
    
    private static ListNode buildListNode(int[] array) {
    	ListNode head = null;
    	ListNode tail = null;
    	ListNode newNode;
    	
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
    	ListNode temp;
    	
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
//		int[] array = {1,2,3,4,5};
//		int[] array = {1,2,3,4,5,6};
//		int[] array = {1,2,3,4,5,6,7};
		int[] array = {1,2,3,4,5,6,7,8};
		
		ListNode head = buildListNode(array);
		
		printListNode(head);
//		head=reorderList1(head);
		reorderList(head);
		printListNode(head);

	}

}
