package ca.jrvs.practice.codingChallenge;

import junit.framework.TestCase;
import org.junit.Test;

public class StringToIntegerTest extends TestCase {

    @Test
    public void testParsing() {
        StringToInteger stringToInteger= new StringToInteger();
        String input= "-1234";
        assertEquals(-1234, stringToInteger.parsing(input));
    }

    @Test
    public void testMyatoi() {
        StringToInteger stringToInteger= new StringToInteger();
        String input= "-1234Hello";
        assertEquals(-1234, stringToInteger.myAtoi(input));
    }
}