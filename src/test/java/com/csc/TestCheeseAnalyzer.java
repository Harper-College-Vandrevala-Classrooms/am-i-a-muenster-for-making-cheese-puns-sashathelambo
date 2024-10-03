package com.csc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestCheeseAnalyzer {

    @Test
    public void testPasteurizedCount() {
        CheeseAnalyzer analyzer = new CheeseAnalyzer();
        int expected = 430;
        int actual = analyzer.getPasteurizedCount();
        System.out.println("Expected Pasteurized Count: " + expected);
        System.out.println("Actual Pasteurized Count: " + actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testRawCount() {
        CheeseAnalyzer analyzer = new CheeseAnalyzer();
        int expected = 60;
        int actual = analyzer.getRawCount();
        System.out.println("Expected Raw Count: " + expected);
        System.out.println("Actual Raw Count: " + actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testOrganicMoistureCount() {
        CheeseAnalyzer analyzer = new CheeseAnalyzer();
        int expected = 26;
        int actual = analyzer.getOrganicMoistureCount();
        System.out.println("Expected Organic Moisture Count: " + expected);
        System.out.println("Actual Organic Moisture Count: " + actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testMilkTypeCount() {
        CheeseAnalyzer analyzer = new CheeseAnalyzer();
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Cow", 1); // Assuming "Cow" is the most common milk type
        Map<String, Integer> actual = analyzer.getMilkTypeCount();
        System.out.println("Expected Milk Type Count: " + expected);
        System.out.println("Actual Milk Type Count: " + actual);
        assertEquals(expected, actual);
    }
}
