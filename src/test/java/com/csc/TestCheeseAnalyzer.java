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
        int actual = analyzer.getCheeseCountByMilkTreatment("Pasteurized");
        assertEquals(expected, actual, "Expected Pasteurized Count: " + expected + ", but got: " + actual);
    }

    @Test
    public void testRawCount() {
        CheeseAnalyzer analyzer = new CheeseAnalyzer();
        int expected = 60;
        int actual = analyzer.getCheeseCountByMilkTreatment("Raw Milk");
        assertEquals(expected, actual, "Expected Raw Count: " + expected + ", but got: " + actual);
    }

    @Test
    public void testOrganicMoistureCount() {
        CheeseAnalyzer analyzer = new CheeseAnalyzer();
        int expected = 26;
        int actual = analyzer.OrganicMoistureCount();
        assertEquals(expected, actual, "Expected Organic Moisture Count: " + expected + ", but got: " + actual);
    }

    @Test
    public void testMilkTypeCount() {
        CheeseAnalyzer analyzer = new CheeseAnalyzer();
        Map<String, Integer> actual = analyzer.getMilkTypeCount();
        
        Map<String, Integer> expected = new HashMap<>(actual);

        System.out.println("Actual Milk Type Count: " + actual);

        assertEquals(expected, actual, "Expected Milk Type Count: " + expected + ", but got: " + actual);
    }
}
