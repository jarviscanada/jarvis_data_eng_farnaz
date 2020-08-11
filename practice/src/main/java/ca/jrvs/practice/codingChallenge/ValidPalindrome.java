package ca.jrvs.practice.codingChallenge;

public class ValidPalindrome {
    public boolean isValid(String s){
        s=s.toLowerCase();
        s = s. replaceAll("\\s","");
        int i=0;
        int j= s.length()-1;

        while(i<j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    static boolean ispalRec (String s,int i, int j){
        if(i==j)
            return true;
        if(s.charAt(i) != s.charAt(j))
            return false;
        if(i<j+1)
            return ispalRec(s,i+1,j-1);

        return true;

    }
    public boolean isPalindromeWithRecrussion(String s){
        s=s.toLowerCase();
        s = s. replaceAll("\\s","");
        int n = s.length();
        if (n==0)
            return true;
        return ispalRec(s,0,n-1);

    }
}
