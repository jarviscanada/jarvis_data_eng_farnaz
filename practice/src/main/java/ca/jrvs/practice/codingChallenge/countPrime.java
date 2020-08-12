package ca.jrvs.practice.codingChallenge;

public class countPrime {
    public int count(int number){
        int prime[] = new int[number];
        int counter=0;
        if (number == 1)
            return 0;
        for(int i=2; i < number; i++){
            if(isPrime(i))
                counter++;
        }
        return counter;
    }

    public boolean isPrime(int n){
        int root= (int) Math.sqrt(n);
        for(int i=2; i<= root; i++){
            if(n % i ==0)
                return false;
        }
        return true;
    }
}
