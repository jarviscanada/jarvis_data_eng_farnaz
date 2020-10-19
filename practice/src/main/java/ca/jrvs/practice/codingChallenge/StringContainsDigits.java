package ca.jrvs.practice.codingChallenge;

public class StringContainsDigits {
    public boolean checkByAscii(String input){
        for(int i=0; i < input.length(); i++){
            if(input.charAt(i)<48 || input.charAt(i) >57)
                return false;
        }
        return true;
    }
    public boolean checkByApi(String s){
        try {
            Integer.valueOf(s);
        } catch (NumberFormatException ex) {
            return false;
        }
      return true;
    }

    public boolean checkByRegex(String s){
        if(s.matches("\\d+"))
            return true;
        return false;
    }
}
