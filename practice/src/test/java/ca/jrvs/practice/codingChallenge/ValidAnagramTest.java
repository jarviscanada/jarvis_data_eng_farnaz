package ca.jrvs.practice.codingChallenge;

import junit.framework.TestCase;

public class ValidAnagramTest extends TestCase {
    ValidAnagram validAnagram = new ValidAnagram();

    public void testIsValidBySorting() {
        String s1 = "abcd";
        String s2 = "bdac";
        assertEquals(true,validAnagram.isValidBySorting(s1,s2));
    }


    public void testIsValidByTable() {
        String s1 = "abcd";
        String s2 = "bdac";
        assertEquals(true,validAnagram.isValidByHashTable(s1,s2));
    }
}