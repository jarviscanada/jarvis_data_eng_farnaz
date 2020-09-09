package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.middleOfLinkedList.ListNode;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode reverse = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reverse;
    }


    public ListNode reverseListWithIteration (ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null){
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev= curr;
            curr = nextTemp;
        }
        return prev;
    }
}
