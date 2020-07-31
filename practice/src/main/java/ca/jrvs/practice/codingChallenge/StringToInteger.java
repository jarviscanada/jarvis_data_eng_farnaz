package ca.jrvs.practice.codingChallenge;

public class StringToInteger {
    public int parsing (String input){
        //Ths approach doesn't work when the input has white spaces.
        try {
            return Integer.parseInt(input);
        }
    catch (NumberFormatException e){
        throw new IllegalArgumentException("Unexpected input");
    }
    }


 public int myAtoi(String input) {
        int res = 0;
        int negative = 1;
        int start = 0;

        for(start = 0; start < input.length(); start++){
            if(input.charAt(start) != ' '){
                break;
            }
        }


        if(input.charAt(start) == '-'){
            negative = -1;
            start++;
        }
        else if(input.charAt(start) == '+'){
            negative = 1;
            start++;
        }


        for(int i = start; i < input.length(); i++){
            if(input.charAt(i) >= '0' && input.charAt(i) <= '9'){
                int temp = input.charAt(i) - '0';

                if(res*negative > Integer.MAX_VALUE/10 || (res*negative == Integer.MAX_VALUE/10 && temp >= 7))
                    return Integer.MAX_VALUE;
                else if(res*negative < Integer.MIN_VALUE/10 || (res*negative == Integer.MIN_VALUE/10 && temp >= 8))
                    return Integer.MIN_VALUE;

                res = (res * 10) + (temp);
            }
            else
                break;
        }

        return res * negative;
    }

}
