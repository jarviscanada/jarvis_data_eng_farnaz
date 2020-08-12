package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Ticket URL : https://www.notion.so/Two-Sum-39e4ee872a754202b4ea10ade46a7e24
 */

public class twoSum {
    /**
     *  Big-O : O(N^2)
     */
    public int[] twosum(int[] numbers, int target){
        for (int i=0; i< numbers.length;i++){
            for(int j=i+1; j<numbers.length;j++) {
                if (target - numbers[i] == numbers[j]) {
                    return new int[]{i, j};
                }
            }
    }
        throw new IllegalArgumentException("No number found");
    }

    public int[] twoSumBruteForce(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (target - array[i] == array[j]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No solution found");
    }


    public int[] twoSumWithSorting(int[] numbers, int target) {
        /**
         * Big-O: O(n*logn)
         */
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length; i++) {
           int secondIndex = Arrays.binarySearch(numbers,i+1,numbers.length,target);
           return new int []{i,secondIndex};
        }
        throw new IllegalArgumentException("No number found");
    }


    public int[] twoSumWithHash(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }
        for (int j = 0; j < numbers.length; j++) {
            int secondNumber = target - numbers[j];
            if (map.containsKey(secondNumber) && map.get(secondNumber) != j) {
                return new int[] { j, map.get(secondNumber) };
            }
        }
        throw new IllegalArgumentException("No number found");
    }


}




