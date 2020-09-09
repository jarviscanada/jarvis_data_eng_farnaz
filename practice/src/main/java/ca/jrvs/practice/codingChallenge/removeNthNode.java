package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.middleOfLinkedList.ListNode;

public class removeNthNode {
    public ListNode remove(ListNode head, int n){
        ListNode dummy= new ListNode(0);
        dummy.next = head;
        int length= 0;
        ListNode first = head;
        while (first != null){
            length++;
            first=first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0){
            length--;
            first=first.next;
        }
        first.next=first.next.next;
        return dummy.next;
    }
}

