package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.middleOfLinkedList.ListNode;
import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
    public boolean hasCycleWithHashTable(ListNode head){
        Set<ListNode> seenNodes = new HashSet<>();
        while (head != null){
            if(seenNodes.contains(head)) {
                return true;
            } else {
                seenNodes.add(head);
            }
            head = head.next;
            }
        return false;
        }


        public boolean hasCycleWithTwoPointers(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast= head.next;
        while (slow != fast){
            if (fast == null || fast.next == null)
                return false;
            slow = slow.next;
            fast= fast.next.next;
        }
        return true;
        }
    }

