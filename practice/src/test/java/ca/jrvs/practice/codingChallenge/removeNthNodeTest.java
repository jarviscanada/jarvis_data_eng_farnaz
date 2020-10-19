package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.middleOfLinkedList.ListNode;
import junit.framework.TestCase;
import org.junit.Test;

public class removeNthNodeTest extends TestCase {
    removeNthNode removeNthNode = new removeNthNode();
    ListNode head;
    ListNode node2;
    ListNode node3;
    ListNode node4;
    ListNode node5;

    @Test
    public void testRemove() {

        head = new ListNode(1);
        node2 = new ListNode(2);
        node3 = new ListNode(3);
        node4 = new ListNode(4);
        node5 = new ListNode(5);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        head = removeNthNode.remove(head, 2);
        assertEquals(node3.next, node5);

    }
}