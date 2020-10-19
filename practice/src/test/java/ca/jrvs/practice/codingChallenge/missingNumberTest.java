package ca.jrvs.practice.codingChallenge;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class missingNumberTest {
    missingNumber myMissingNumeber = new missingNumber();
    @Test
    public void testFindBySum() {
        assertEquals(8, myMissingNumeber.findBySum(new int[]{9,6,4,2,3,5,7,0,1}));
    }

    @Test
    public void testFindBySort() {
        assertEquals(8, myMissingNumeber.findBySort(new int[]{9,6,4,2,3,5,7,0,1}));
    }

    @Test
    public void testFindByHash() {
        assertEquals(8, myMissingNumeber.findByHash(new int[]{9,6,4,2,3,5,7,0,1}));
    }

}