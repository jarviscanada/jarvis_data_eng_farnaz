package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PrintLetterWithNumberTest {
PrintLetterWithNumber printLetterWithNumber= new PrintLetterWithNumber();
    @Test
    public void testPrint() {
        String s= "abcee";
        assertEquals("a1b2c3e5e5", printLetterWithNumber.print(s));
    }
}