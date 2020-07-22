package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EvenOrOddTest {

    @Test
    public void checking() {
        EvenOrOdd evenOrOdd = new EvenOrOdd();
        assertEquals("Even",evenOrOdd.checking(16));
    }
}