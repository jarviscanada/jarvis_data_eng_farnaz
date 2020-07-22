package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

public class FibonacciTest {

    @Test
    public void fibTest() {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fib(10));
    }

    @Test
    public void memoizedfibTest() {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.memoizedfib(2, new int[11]));
    }

    @Test
    public void dpFibTest() {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.dpFib(2));
    }

}