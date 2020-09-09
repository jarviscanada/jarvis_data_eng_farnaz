package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class missingNumber {
    public int findBySum(int[] input){
        int expectedSum= ((input.length) * (input.length +1))/2;
        int sum = 0;
        for (int i= 0; i<input.length; i++){
            sum= input[i] + sum;
        }
        return expectedSum-sum;
    }

    public int findBySort(int[] input){
        Arrays.sort(input);
        for(int i=0; i< input.length;i++){
            if (input[i]+1 != input[i+1])
                return input[i]+1;
        }
        return -1;
    }

    public int findByHash(int[] input){
        Set<Integer> mySet= new HashSet<Integer>();
        for(int i:input)
            mySet.add(i);
        int expectedLength = input.length + 1;
        for(int j=0 ; j<expectedLength; j++){
            if(!mySet.contains(j))
                return j;
        }
        return -1;
    }
}
