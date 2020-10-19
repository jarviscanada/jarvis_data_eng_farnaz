package ca.jrvs.practice.codingChallenge;


import static org.junit.Assert.assertEquals;

import ca.jrvs.practice.codingChallenge.middleOfLinkedList.ListNode;
import org.junit.Before;
import org.junit.Test;

public class ReverseLinkedListTest{
    ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
    ListNode head = new ListNode(1);
    ListNode mynode = head;

    @Before
    public void setup() {
        for (int i = 2; i< 4; i++){
            mynode.next = new ListNode(i);
            mynode = mynode.next;
        }
    }

    @Test
    public void testReverseListWithRecrusion() {
       ListNode reverseList = reverseLinkedList.reverseList(head);
       mynode = reverseList;
        for (int i = 3; i >0 ; i--){
            assertEquals (i, mynode.val);
            mynode= mynode.next;
        }
    }

    @Test
    public void testReverseListWithIteration() {
        ListNode reverseList = reverseLinkedList.reverseListWithIteration(head);
        mynode = reverseList;
        for (int i = 3; i >0 ; i--){
            assertEquals (i, mynode.val);
            mynode= mynode.next;
        }
    }
}