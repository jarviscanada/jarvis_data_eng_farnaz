/*
package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

public class duplicateChar {
    //public char[] find(String input){
    public StringBuilder find(String input){
        input= input.toLowerCase();
        input = input. replaceAll("\\s","");
        char[] mychar = input.toCharArray();
        Arrays.sort(mychar);
        //char [] output = new char[input.length()];
        //int counter = 0;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i< mychar.length-1; i++){
            if(mychar[i] == mychar[i+1]) {
                */
/*if (counter != 0) {
                    if (output[counter - 1] != mychar[i]) {
                        output[counter] = mychar[i];
                        counter++;
                    }
                } else {
                    output[counter] = mychar[i];
                    counter++;
                }*//*


                str.append (mychar[i]);
                char [] ou
            }

        }
       // return output;
        return str;
    }
}
*/
