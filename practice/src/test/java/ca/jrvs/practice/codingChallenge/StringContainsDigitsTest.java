package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;
import org.junit.Test;

public class StringContainsDigitsTest {
    StringContainsDigits stringContainsDigits = new StringContainsDigits();

    @Test
    public void testCheckByAscii() {
        String input= "1234";
        assertEquals(true , stringContainsDigits.checkByAscii(input));
    }

    @Test
    public void testCheckByApi() {
        String input= "1234";
        assertEquals(true , stringContainsDigits.checkByApi(input));
    }
    @Test
    public void testCheckByRegex() {
        String input= "1234";
        assertEquals(true , stringContainsDigits.checkByRegex(input));
    }


}