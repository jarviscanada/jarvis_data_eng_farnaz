package ca.jrvs.practice.codingChallenge;

public class PrintLetterWithNumber {
    public String print(String s){
        char[] myArray = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i< s.length(); i++){
            stringBuilder.append(myArray[i]);
            if (myArray[i] >= 'a' && myArray[i] <='z')
                stringBuilder.append((int) myArray[i]- 'a' + 1);
            else
                stringBuilder.append((int) myArray[i]- 'A' + 27);
        }
        return stringBuilder.toString();
    }
}
