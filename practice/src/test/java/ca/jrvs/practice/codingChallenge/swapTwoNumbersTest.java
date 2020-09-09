package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertArrayEquals;

import junit.framework.TestCase;
import org.junit.Test;

public class swapTwoNumbersTest {
    swapTwoNumbers myswapTwoNumbers = new swapTwoNumbers();

    @Test
    public void testSwapWithABitOperators() {
        int[] input = new int[]{8,12};
        int[] output = new int[]{12,8};
        assertArrayEquals(input,myswapTwoNumbers.swapWithBit(output));
    }

    @Test
    public void testSwapWithArithmeticOperators() {
        int[] input = new int[]{8,12};
        int[] output = new int[]{12,8};
        assertArrayEquals(input,myswapTwoNumbers.swapWithArithmeticOperators(output));
    }
}