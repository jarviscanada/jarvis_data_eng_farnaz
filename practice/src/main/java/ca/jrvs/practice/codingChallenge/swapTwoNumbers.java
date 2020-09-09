package ca.jrvs.practice.codingChallenge;

public class swapTwoNumbers {
   public int[] swapWithBit (int[] input){
       input[0]= input[0] ^ input[1];
       input[1]= input[0] ^ input[1];
       input[0]= input[0] ^ input[1];
       return input;
   }

    public int[] swapWithArithmeticOperators (int[] input){
        input[0] = input [0] + input [1];
        input[1]= input[0] - input[1];
        input[0] = input [0] - input[1];
        return input;
    }
}
