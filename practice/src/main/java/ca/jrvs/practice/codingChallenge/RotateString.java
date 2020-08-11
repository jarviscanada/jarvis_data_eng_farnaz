package ca.jrvs.practice.codingChallenge;

public class RotateString {

    public boolean rotateString (String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        else {
            //All rotations of s1 are contained in s1+s1.
            // Thus, we can simply check whether s2 is a substring of s1+s1.
            if((s1+s1).contains(s2))
                return true;
            else return false;
            }
        }
    }


