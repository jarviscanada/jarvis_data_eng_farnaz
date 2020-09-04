package ca.jrvs.practice.codingChallenge;
/**
 * Ticket URL : https://www.notion.so/How-to-compare-two-maps-72460f68655b40bba5d69fdb2de3aa3f
 * Big-O : O(N) Since the code iterates through the whole map
 */

import java.util.Map;

public class TwoMapComparison {
    public <K, V> boolean compareMaps(Map<K, V> m1, Map<K, V> m2) {

        if (m1.equals(m2))
            return true;
        else
            return false;
    }
}