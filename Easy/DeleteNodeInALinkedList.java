/*
Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class Solution {
	public void deleteNode(ListNode node){
		if(node==null||node.next==null){
			return;
		}
		ListNode next = node.next;
		node.val=next.val; 				// copy node data to the next node
		node.next=next.next; 			// copy node reference to the next node, which is deleting the current node
	}   								// 把next值copy给node然后删掉next
}

/*
CC150 2-3
*/