package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueues {
Queue<Integer> q1;
Queue<Integer> q2;
    /** Initialize your data structure here. */

    public StackUsingQueues() {
        q1= new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q1.add(x);

    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int top = -1;
        while (!q1.isEmpty()) {
            top = q1.remove();
            if (!q1.isEmpty()) {
                q2.add(top);
            }
        }
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return top;

    }

    /** Get the top element. */
    public int top() {
        int top = -1;
        while (!q1.isEmpty()) {
            top = q1.remove();
            q2.add(top);
        }
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }

}
