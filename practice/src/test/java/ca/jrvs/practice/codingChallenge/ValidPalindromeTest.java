package ca.jrvs.practice.codingChallenge;

import junit.framework.TestCase;

public class ValidPalindromeTest extends TestCase {

    public void testIsValid() {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        String s= "A   b c Cba";
        assertEquals(true,validPalindrome.isValid(s));
    }

    public void testRecrussionIsValid() {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        String s= "A   b c Cba";
        assertEquals(true,validPalindrome.isPalindromeWithRecrussion(s));
    }
}