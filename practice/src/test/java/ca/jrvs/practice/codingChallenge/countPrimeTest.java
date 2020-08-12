package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;
import org.junit.Test;

public class countPrimeTest {
    countPrime countPrime = new countPrime();
    @Test
    public void testCount() {
        int number=10;
        assertEquals(4, countPrime.count(number));
    }
}