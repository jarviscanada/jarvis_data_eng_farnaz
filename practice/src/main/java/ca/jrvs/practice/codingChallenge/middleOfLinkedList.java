package ca.jrvs.practice.codingChallenge;
import java.util.*;

public class middleOfLinkedList {
    public ListNode find(ListNode head) {
        ListNode[] myList = new ListNode[100];
        int lenght = 0;
        while (head.next != null){
            myList[lenght++] = head;
            head=head.next;
        }
        return myList[lenght/2];
    }

    public static class ListNode {

        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

}
