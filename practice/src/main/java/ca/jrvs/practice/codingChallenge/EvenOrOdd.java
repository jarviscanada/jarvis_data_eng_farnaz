package ca.jrvs.practice.codingChallenge;

/**
 Ticket URL : https://www.notion.so/Sample-Check-if-a-number-is-even-or-odd-939ba6da59024468964a1958a56d6f0f
 Big-O : O(1) Since it is one line arithmetic operation
 */

public class EvenOrOdd {
    public String checking(int input) {
        if (input % 2 == 0)
            return "Even";
        else
            return "Odd";
    }
}
