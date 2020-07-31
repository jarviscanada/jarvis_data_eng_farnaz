package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class QueueUsingStack {
Stack<Integer> s1;
Stack<Integer> s2;
private int front;
        /** Initialize your data structure here. */
        public QueueUsingStack() {
        s1= new Stack<>();
        s2 = new Stack<>();
        }

        /** Push element x to the back of queue.
         * Time complexity : O(n)
         * Each element, with the exception of the newly arrived, is pushed and popped twice.
         * The last inserted element is popped and pushed once.
         * Therefore this gives 4n+2 operations where n is the queue size.
         * */
        public void push(int x) {
        if(s1.empty())
            front = x;
        while(!s1.isEmpty())
            s2.push(s1.pop());
        s2.push(x);
        while (!s2.isEmpty())
            s1.push(s2.pop());

        }

        /** Removes the element from in front of queue and returns that element. */
        public void pop() {
            s1.pop();
            if (!s1.empty())
                front = s1.peek();
        }

        /** Get the front element. */
        public int peek() {
            int top = -1;
            while (!s1.isEmpty()) {
                top = s1.pop();
                s2.push(top);
            }
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
            return top;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return s1.isEmpty();
        }


    }

