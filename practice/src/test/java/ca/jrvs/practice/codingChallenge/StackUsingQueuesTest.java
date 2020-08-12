package ca.jrvs.practice.codingChallenge;

import junit.framework.TestCase;
import org.junit.Test;

public class StackUsingQueuesTest extends TestCase {
    StackUsingQueues stackUsingQueues= new StackUsingQueues();
    @Test
    public void testPush() {
        stackUsingQueues.push(1);
        assertEquals(1,stackUsingQueues.top());
    }
    @Test
    public void testPop() {
        stackUsingQueues.push(1);
        stackUsingQueues.push(2);
        stackUsingQueues.push(3);
        stackUsingQueues.pop();
        assertEquals(2,stackUsingQueues.top());
    }
    @Test
    public void testTop() {
        stackUsingQueues.push(1);
        stackUsingQueues.push(2);
        stackUsingQueues.push(3);
        assertEquals(3,stackUsingQueues.top());
    }
    @Test
    public void testEmpty() {
        stackUsingQueues.push(1);
        stackUsingQueues.pop();
        assertEquals(true, stackUsingQueues.empty());
    }
}