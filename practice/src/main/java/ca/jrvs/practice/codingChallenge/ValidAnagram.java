package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;

public class ValidAnagram {
    /**
     * Time complexity : O(nlogn). Assume that n is the length of s,
     * sorting costs O(nlogn) and comparing two strings costs O(n).
     * Sorting time dominates and the overall time complexity is O(nlogn).
     */
    public boolean isValidBySorting(String s1, String s2){
        if (s1.length() != s2.length())
            return false;
        char[] A = s1.toCharArray();
        char[] B = s2.toCharArray();
        Arrays.sort(A);
        Arrays.sort(B);
        return Arrays.equals(A,B);
    }

    public boolean isValidByHashTable (String s1, String s2){
        /**
         * To examine if t is a rearrangement of s,
         *  we can count occurrences of each letter in the two strings and compare them.
         *  Since both s and t contain only letters from a−z, a simple counter table of size 26 is suffice.
         *
         *  Time complexity : O(n). Time complexity is O(n) because accessing the counter table is a constant time operation.
         *
         * Space complexity : O(1). Although we do use extra space, the space complexity is O(1)
         * because the table's size stays constant no matter how large n is.
         */

        if (s1.length() != s2.length())
            return false;
        int[] table = new int[26];
        for (int i=0; i<s1.length(); i++){
            table[s1.charAt(i)- 'a']++;
        }
        for (int i=0; i<s2.length(); i++){
            table[s2.charAt(i)- 'a']--;
            if(table[s2.charAt(i)- 'a']<0) {
                return false;
            }
        }

        return true;
    }


}
