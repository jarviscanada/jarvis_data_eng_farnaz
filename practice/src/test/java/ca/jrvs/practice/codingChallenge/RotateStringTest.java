package ca.jrvs.practice.codingChallenge;

import junit.framework.TestCase;

public class RotateStringTest extends TestCase {
    /**
     * Time Complexity:
     * O(N^ 2), where N is the length of S.
     * Space Complexity: O(N), the space used building s1+s1.
     */

    public void testRotateString() {
        RotateString rotateString= new RotateString();
        String s1= "abcd";
        String s2="abcd";
        assertEquals(true, rotateString.rotateString(s1,s2));
    }
}