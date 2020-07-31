package ca.jrvs.practice.codingChallenge;

import junit.framework.TestCase;

public class RemoveElementTest extends TestCase {
RemoveElement removeElement= new RemoveElement();
    public void testRemove() {
        int[] numbers = {1,2,2,2,2,4};
        int value= 2;
        assertEquals(2,removeElement.remove(numbers,value));
    }
}