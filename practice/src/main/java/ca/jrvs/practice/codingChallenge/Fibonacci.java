package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;

/**
 * Ticket URL : https://www.notion.so/Fibonacci-Number-Climbing-Stairs-911e9e42b6df46ab88b79cfdd5a6c28e
 *
 */

public class Fibonacci {
    /**
     * Big-o: O(2^N). This is the slowest way to solve the Fibonacci Sequence because it takes exponential time
     *
     * @param input
     * @return
     */
    public int fib(int input) {
        if (input <= 1)
            return input;
        else
            return fib(input - 1) + fib(input - 2);
    }

    /**
     * Time complexity : O(2N). Each number, starting at 2 up to and including N, is visited, computed and then stored for O(1) access later on.
     * <p>
     * Space complexity : O(N). The size of the stack in memory is proportionate to N.
     *
     * @param n
     * @param memo
     * @return
     */
    public int memoizedfib(int n, int[] memo) {
        if (memo[n] != 0) {
            return memo[n];
        }
        if (n <= 1) {
            int result = n;
            memo[n] = result;
            return result;
        } else {
            int result = memoizedfib(n - 1, memo) + memoizedfib(n - 2, memo);
            memo[n] = result;
            return result;
        }
    }

    /**
     * Big-O = O(N) Since each value from 2 to N will be visited at least once:
     * The time it takes to do this is directly proportionate to N where N is the Fibonacci Number we are looking to compute.
     * @param n
     * @return
     */

    public int dpFib(int n) {
        if (n <= 1) {
            return n;
        } else {
            int[] memo = new int[n + 1];
            memo[0] = 0;
            memo[1] = 1;
            for (int i = 2; i <= n; i++) {
                memo[i] = memo[i - 1] + memo[i - 2];
            }
            return memo[n];
        }
    }


    public int climbStairs(int n) {
        if(n==1)
            return 1;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i <= n; i++)
            dp[i]= dp[i-1] + dp[i-2];

        return dp[n];
    }

}