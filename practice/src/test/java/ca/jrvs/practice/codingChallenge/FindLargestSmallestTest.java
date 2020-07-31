package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindLargestSmallestTest {
    FindLargestSmallest findLargestSmallest = new FindLargestSmallest();
    @Test
    public void testFindByLoop() {
        int[] numbers= {10,2,1,3,4,5,2};
        assertEquals(10,findLargestSmallest.findByLoop(numbers)[1]);
        assertEquals(1,findLargestSmallest.findByLoop(numbers)[0]);
    }

    @Test
    public void testFindByStreamApi() {
        int[] numbers= {10,2,1,3,4,5,2};
        assertEquals(10,findLargestSmallest.findByStreamApi(numbers)[1]);
        assertEquals(1,findLargestSmallest.findByStreamApi(numbers)[0]);
    }

    @Test
    public void testFindByCollectionApi() {
        int[] numbers= {10,2,1,3,4,5,2};
        assertEquals(10,findLargestSmallest.findByCollectionApi(numbers)[1]);
        assertEquals(1,findLargestSmallest.findByCollectionApi(numbers)[0]);
    }
}