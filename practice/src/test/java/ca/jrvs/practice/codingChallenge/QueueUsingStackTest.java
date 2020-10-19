package ca.jrvs.practice.codingChallenge;

import junit.framework.TestCase;
import org.junit.Test;

public class QueueUsingStackTest extends TestCase {
    QueueUsingStack queueUsingStack= new QueueUsingStack();
    @Test
    public void testPush() {
        queueUsingStack.push(1);
        assertEquals(1,queueUsingStack.peek());
    }
    @Test
    public void testPop() {
        queueUsingStack.push(1);
        queueUsingStack.push(2);
        queueUsingStack.push(3);
        queueUsingStack.pop();
        assertEquals(2,queueUsingStack.peek());
    }
    @Test
    public void testPeek() {
        queueUsingStack.push(1);
        queueUsingStack.push(2);
        queueUsingStack.push(3);
        assertEquals(3,queueUsingStack.peek());
    }
    @Test
    public void testEmpty() {
        queueUsingStack.push(1);
        queueUsingStack.pop();
        assertEquals(true, queueUsingStack.empty());
    }
}