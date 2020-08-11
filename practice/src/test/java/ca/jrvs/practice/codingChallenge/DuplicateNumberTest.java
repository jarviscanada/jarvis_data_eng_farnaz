package ca.jrvs.practice.codingChallenge;

import junit.framework.TestCase;

public class DuplicateNumberTest extends TestCase {
DuplicateNumber duplicateNumber= new DuplicateNumber();
    public void testFind() {
        int[] numbers= {1,3,3,2};
        assertEquals(3,duplicateNumber.findBySorting(numbers));
    }

    public void testFindByset() {
        int[] numbers= {1,3,3,2};
        assertEquals(3,duplicateNumber.findBySet(numbers));
    }
}