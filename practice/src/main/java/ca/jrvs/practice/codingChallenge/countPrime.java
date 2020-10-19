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

    public int countPrimes(int n) {
    boolean[] isPrime = new boolean[n];
       for (int i = 2; i < n; i++) {
        isPrime[i] = true;
    }
    // Loop's ending condition is i * i < n instead of i < sqrt(n)
    // to avoid repeatedly calling an expensive function sqrt().
       for (int i = 2; i * i < n; i++) {
        if (!isPrime[i]) continue;
        for (int j = i * i; j < n; j += i) {
            isPrime[j] = false;
        }
    }
    int count = 0;
       for (int i = 2; i < n; i++) {
        if (isPrime[i]) count++;
    }
       return count;

}

}
