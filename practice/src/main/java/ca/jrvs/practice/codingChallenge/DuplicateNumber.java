package ca.jrvs.practice.codingChallenge;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DuplicateNumber {
    /**
     * Time complexity : O(nlogn)
     */
    public int findBySorting(int[] numbers){
        Arrays.sort(numbers);
        for(int i=0; i<numbers.length; i++){
            if (numbers[i] == numbers[i+1])
                return numbers[i];
        }
        return -1;
    }

    /**
     * we iterate over the array and insert each element into seen.
     * Before inserting it, we check whether it is already there.
     * If it is, then we found our duplicate, so we return it.
     *
     * Time complexity : O(n)
     */

    public int findBySet(int[] numbers){
        Set<Integer> table = new HashSet<Integer>();
        for(int num : numbers){
            if (table.contains(num))
                return num;
            table.add(num);
        }
    return -1;
    }
}
