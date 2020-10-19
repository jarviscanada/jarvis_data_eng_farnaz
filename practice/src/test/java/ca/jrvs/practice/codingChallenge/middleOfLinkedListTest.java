package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import ca.jrvs.practice.codingChallenge.middleOfLinkedList.ListNode;
import org.junit.Test;

public class middleOfLinkedListTest {
middleOfLinkedList mylist = new middleOfLinkedList();


    @Test
    public void testFind() {
        ListNode head = new ListNode(1);
        ListNode newList = head;
        newList.next= new ListNode(2);
        newList = newList.next;
        newList.next= new ListNode(3);

        assertEquals(2,mylist.find(head).val);

        newList = newList.next;
        newList.next= new ListNode(4);
        newList = newList.next;
        newList.next= new ListNode(5);

        assertEquals(3,mylist.find(head).val);

    }
}