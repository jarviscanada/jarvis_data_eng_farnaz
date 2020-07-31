package ca.jrvs.practice.codingChallenge;

public class RemoveElement {
    public int remove(int[] numbers,int value){
        int length=0;
        for(int i = 0; i<numbers.length; i++){
            if( value != numbers[i]) {
                numbers[length] = numbers[i];
                length++;
            }
        }
        return length;
    }
}
