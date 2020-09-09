package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class containsDuplicate {
    public boolean contains(int[] numbers){
        Arrays.sort(numbers);
        for(int i=0; i<numbers.length; i++){
            if (numbers[i] == numbers[i+1])
                return true;
        }
        return false;
    }

    public boolean findBySet(int[] numbers){
        Set<Integer> table = new HashSet<Integer>();
        for(int num : numbers){
            if (table.contains(num))
                return true;
            table.add(num);
        }
        return false;
    }

}
