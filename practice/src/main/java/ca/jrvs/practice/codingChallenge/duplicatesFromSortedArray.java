package ca.jrvs.practice.codingChallenge;

public class duplicatesFromSortedArray {
    public int findLength (int[] nums){
        if (nums.length == 0)
            return 0;
        int lenght=1;
        for(int i=0; i<nums.length-1;i++){
            if (nums[i] != nums[i+1])
                lenght++;
        }
        return lenght;
    }
}
