package ca.jrvs.practice.codingChallenge;

import junit.framework.TestCase;

public class ValidParenthesesTest extends TestCase {

    public void testIsValid() {
        ValidParentheses validParentheses= new ValidParentheses();
        String s= "({(}))";
        assertEquals(false,validParentheses.isValid(s));
    }
}