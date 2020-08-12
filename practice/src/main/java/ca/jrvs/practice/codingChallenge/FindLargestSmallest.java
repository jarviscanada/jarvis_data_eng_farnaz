package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FindLargestSmallest {
    public int[] findByLoop(int[] numbers){
        int max=0;
        int min= numbers[0];
        for(int i=0; i<numbers.length; i++){
            if(numbers[i]<= min)
                min=numbers[i];
            if (numbers[i]>max)
                max= numbers[i];
        }
        return new int[]{min,max};
    }

    public int[] findByStreamApi(int[] numbers){
        int min,max;
        min= Arrays.stream(numbers).min().orElse(0);
        max= Arrays.stream(numbers).max().orElse(0);
        return new int[]{min, max};
    }

    public int[] findByCollectionApi(int[] numbers) {
        List<Integer> collection= Arrays.stream(numbers).boxed().collect(Collectors.toList());
        int min= Collections.min(collection);
        int max = Collections.max(collection);
        return new int[]{min,max};
    }


}
