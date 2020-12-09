package com.aoc.day_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class XMasEncryption {

    public static HashMap<Long, Long> pair = new HashMap<>();

    public static ArrayList<Long> inputExtraction(File file, int count) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        ArrayList<Long> data = new ArrayList<>();

        int val = 0;
        while (input.hasNext()) {
            val++;
            count++;
            long number = Long.parseLong(input.nextLine());
            if (val <= 25 && count <= 25) {
                data.add(number);
                if (count == 25) break;
            }
            if (val > 25 && count > 25) data.add(number);
        }
        return data;
    }

    public static boolean isValid(long candidate, ArrayList<Long> preambleData) {

        for (int i = 0; i < preambleData.size() - 1; i++) {
            long first = preambleData.get(i);
            for (int j = i + 1; j < preambleData.size(); j++) {
                long second = preambleData.get(j);
                if (first + second == candidate) {
                    if (pair.containsKey(first) && pair.get(first) == second) continue;
                    if (pair.containsKey(second) && pair.get(second) == first) continue;
                    pair.put(first, second);
                    return true;
                }
            }
        }
        return false;
    }

    public static long partOne(ArrayList<Long> preambleData, ArrayList<Long> data) {
        long ans = 0l;

        for (int i = 0; i < data.size(); i++) {
            boolean result = isValid(data.get(i), preambleData);
            if (!result) {
                ans = data.get(i);
                break;
            } else {
                preambleData.add(data.get(i));
                preambleData.remove(0);
            }
        }
        return ans;
    }

    public static ArrayList<String> continiousSubArrayWithGivenSum(ArrayList<Long> data, long sum) {
        int i, j;
        long currentSum;

        ArrayList<String> result = new ArrayList<>();

        for (i = 0; i < data.size(); i++) {
            currentSum = data.get(i);
            for (j = i + 1; j <= data.size(); j++) {
                if (currentSum == sum) {
                    result.add(i + "");
                    result.add(j + "");
                    return result;
                }
                if (currentSum > sum || j == data.size()) break;
                currentSum = currentSum + data.get(j);
            }
        }

        return result;
    }

    public static long partTwo(ArrayList<String> index, ArrayList<Long> data) {
        int start = Integer.parseInt(index.get(0));
        int end = Integer.parseInt(index.get(1));
        ArrayList<Long> subArray = new ArrayList<>();

        for (int i = start; i <= end; i++) subArray.add(data.get(i));

        Collections.sort(subArray);

        return subArray.get(subArray.size() - 1) + subArray.get(0);
    }


    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Long> preambleData = inputExtraction(new File("resources/inputs/day_9.txt"), 0);
        ArrayList<Long> data = inputExtraction(new File("resources/inputs/day_9.txt"), 25);

        long partOneAns = partOne(preambleData, data);
        System.out.println("Part one= " + partOneAns);

        ArrayList<String> subArrayIndexes = continiousSubArrayWithGivenSum(data, partOneAns);
        long partTwoAns = partTwo(subArrayIndexes, data);
        System.out.println("Part two = " + partTwoAns);
    }
}


