package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class mergeSortedArrayTest  {

    @Test
    public void testMerge() {
        mergeSortedArray myMerge = new mergeSortedArray();
        int[] nums1= {1,2,3,0,0,0};
        int m =  3;
        int n = 3;
        int[] num2 = {2,5,6};
        assertArrayEquals( new int[]{1,2,2,3,5,6}, myMerge.merge (nums1,m,num2,n));
    }
}