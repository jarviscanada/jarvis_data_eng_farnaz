package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class TwoMapComparisonTest {

    @Test
    public void compareMapsTest() {
        TwoMapComparison twoMapComparison = new TwoMapComparison();
        Map<Integer, String> firstMap = new HashMap<Integer, String>();
        firstMap.put(1, "A");
        firstMap.put(2, "B");
        firstMap.put(3, "C");
        Map<Integer, String> secondMap = new HashMap<Integer, String>();
        secondMap.put(1, "A");
        secondMap.put(2, "B");
        secondMap.put(3, "C");

        assertEquals(true, twoMapComparison.compareMaps(firstMap, secondMap));
    }
}