package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertArrayEquals;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;

public class twoSumTest {
    twoSum twoSumTest = new twoSum();


    @Test
    public void testTwoSum() {
        twoSum twoSum = new twoSum();
        int[]numbers= {1,2,3,5};
        int target=8;

        assertArrayEquals(new int[]{2, 3}, twoSum.twosum(numbers, target));

    }

    @Test
    public void testTwoSumWithSorting() {
        twoSum twoSum = new twoSum();
        int[]numbers= {1,2,3,5};
        int target= 3;
       assertArrayEquals(twoSum.twoSumWithSorting(numbers,target), new int[]{0,1});
    }

    @Test
    public void testTwoSumWithHashmap() {
        twoSum twoSum = new twoSum();
        int[]numbers= {1,2,3,5};
        int target= 3;
        assertArrayEquals(new int[]{0,1}, twoSum.twoSumWithHash(numbers,target));
    }
}