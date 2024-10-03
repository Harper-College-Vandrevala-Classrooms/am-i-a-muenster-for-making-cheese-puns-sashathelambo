package com.csc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CheeseAnalyzer {

    public static void main(String[] args) {
        String csvFile = "cheese_data.csv";
        String outputFile = "output.txt";
        
        int pasteurizedCount = 0;
        int rawCount = 0;
        int organicMoistureCount = 0;
        Map<String, Integer> milkTypeCount = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String header = br.readLine();
            String[] columns = header.split(",");
            
            int milkTreatmentIndex = getColumnIndex(columns, "MilkTreatmentTypeEn");
            int organicIndex = getColumnIndex(columns, "Organic");
            int moisturePercentIndex = getColumnIndex(columns, "MoisturePercent");
            int milkTypeIndex = getColumnIndex(columns, "MilkTypeEn");

            String line;
            while ((line = br.readLine()) != null) {
                String[] cheeseData = line.split(",");
                
                if (cheeseData.length <= Math.max(Math.max(milkTreatmentIndex, organicIndex), Math.max(moisturePercentIndex, milkTypeIndex))) {
                    continue;
                }

                String milkTreatment = cheeseData[milkTreatmentIndex];
                if (milkTreatment.equalsIgnoreCase("Pasteurized")) {
                    pasteurizedCount++;
                } else if (milkTreatment.equalsIgnoreCase("Raw Milk")) {
                    rawCount++;
                }

                String organic = cheeseData[organicIndex];
                String moisturePercent = cheeseData[moisturePercentIndex];
                if (organic.equals("1") && !moisturePercent.isEmpty() && Double.parseDouble(moisturePercent) > 41.0) {
                    organicMoistureCount++;
                }

                String milkType = cheeseData[milkTypeIndex];
                milkTypeCount.put(milkType, milkTypeCount.getOrDefault(milkType, 0) + 1);
            }

            String mostCommonMilkType = milkTypeCount.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("Unknown");

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
                bw.write("Number of cheeses with pasteurized milk: " + pasteurizedCount + "\n");
                bw.write("Number of cheeses with raw milk: " + rawCount + "\n");
                bw.write("Number of organic cheeses with moisture percentage > 41.0%: " + organicMoistureCount + "\n");
                bw.write("Most common milk type in Canada: " + mostCommonMilkType + "\n");
            }

            System.out.println("Number of cheeses with pasteurized milk: " + pasteurizedCount);
            System.out.println("Number of cheeses with raw milk: " + rawCount);
            System.out.println("Number of organic cheeses with moisture percentage > 41.0%: " + organicMoistureCount);
            System.out.println("Most common milk type in Canada: " + mostCommonMilkType);

        } catch (IOException e) {
            System.err.println("An error occurred while processing the cheese data: " + e.getMessage());
        }
    }

    private static int getColumnIndex(String[] columns, String columnName) {
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    public int getPasteurizedCount() {
        return getCheeseCountByMilkTreatment("Pasteurized");
    }

    public int getRawCount() {
        return getCheeseCountByMilkTreatment("Raw Milk");
    }

    public int getOrganicMoistureCount() {
        return getCheeseCountByCondition("Organic", "MoisturePercent", 41.0);
    }

    public Map<String, Integer> getMilkTypeCount() {
        Map<String, Integer> milkTypeCount = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("cheese_data.csv"))) {
            String line;
            String[] headers = br.readLine().split(",");
            int milkTypeIndex = getColumnIndex(headers, "MilkTypeEn");
            while ((line = br.readLine()) != null) {
                String[] cheeseData = line.split(",");
                String milkType = cheeseData[milkTypeIndex];
                milkTypeCount.put(milkType, milkTypeCount.getOrDefault(milkType, 0) + 1);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the cheese data: " + e.getMessage());
        }
        return milkTypeCount;
    }

    private int getCheeseCountByMilkTreatment(String milkTreatmentType) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("cheese_data.csv"))) {
            String line;
            String[] headers = br.readLine().split(",");
            int milkTreatmentIndex = getColumnIndex(headers, "MilkTreatmentTypeEn");
            while ((line = br.readLine()) != null) {
                String[] cheeseData = line.split(",");
                String milkTreatment = cheeseData[milkTreatmentIndex];
                if (milkTreatment.equalsIgnoreCase(milkTreatmentType)) {
                    count++;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while processing the cheese data: " + e.getMessage());
        }
        return count;
    }

    private int getCheeseCountByCondition(String organicCol, String moistureCol, double minMoisture) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("cheese_data.csv"))) {
            String line;
            String[] headers = br.readLine().split(",");
            int organicIndex = getColumnIndex(headers, organicCol);
            int moistureIndex = getColumnIndex(headers, moistureCol);
            while ((line = br.readLine()) != null) {
                String[] cheeseData = line.split(",");
                boolean isOrganic = cheeseData[organicIndex].equals("1");
                double moisturePercent = Double.parseDouble(cheeseData[moistureIndex]);
                if (isOrganic && moisturePercent > minMoisture) {
                    count++;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while processing the cheese data: " + e.getMessage());
        }
        return count;
    }
}
