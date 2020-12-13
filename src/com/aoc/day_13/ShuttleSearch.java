package com.aoc.day_13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ShuttleSearch {

    public static ArrayList<ArrayList<Integer>> inputExtractionForPartOne(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> earliestTimestamp = new ArrayList<>();
        earliestTimestamp.add(Integer.parseInt(input.nextLine()));

        ArrayList<Integer> busIds = new ArrayList<>();
        String[] candidate = input.nextLine().split(",");
        for (int i = 0; i < candidate.length; i++) {
            if (!candidate[i].equals("x")) busIds.add(Integer.parseInt(candidate[i]));
        }
        data.add(earliestTimestamp);
        data.add(busIds);

        return data;
    }

    public static int partOne(File file) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> data = inputExtractionForPartOne(file);
        int earliestTimestamp = data.get(0).get(0);
        ArrayList<Integer> busIds = data.get(1);


        HashMap<Integer, Integer> distance = new HashMap<>();

        for (int i = 0; i < busIds.size(); i++) {
            int number = busIds.get(i);
            int temp = earliestTimestamp / number;
            int result = number * temp;
            while (result < earliestTimestamp) result += number;
            distance.put(result, number);
        }

        ArrayList<Map.Entry<Integer, Integer>> result = new ArrayList<>(distance.entrySet());
        Collections.sort(result, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                Integer i1 = o1.getKey();
                Integer i2 = o2.getKey();
                return i1.compareTo(i2);
            }
        });
        return (result.get(0).getKey() - earliestTimestamp) * result.get(0).getValue();
    }

    public static ArrayList<ArrayList<String>> inputExtractionForPartTwo(File file) throws FileNotFoundException {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        Scanner input = new Scanner(file);
        input.nextLine();
        ArrayList<String> busIds = new ArrayList<>();
        ArrayList<String> diff = new ArrayList<>();
        String[] temp = input.nextLine().split(",");
        int count = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].equals("x")) count++;
            else {
                busIds.add(temp[i] + "-" + count);
                count = 0;
                diff.add(i + "");
            }
        }
        result.add(busIds);
        result.add(diff);
        return result;
    }

    public static long partTwo(File file) throws FileNotFoundException {
        ArrayList<ArrayList<String>> extractedInput = inputExtractionForPartTwo(file);

        ArrayList<String> al = extractedInput.get(0);
        ArrayList<String> differences = extractedInput.get(1);

        int[] diff = new int[differences.size()];
        for (int i = 0; i < differences.size(); i++) diff[i] = Integer.parseInt(differences.get(i));

        long[] result = new long[al.size()];
        for (int i = 0; i < al.size(); i++) {
            String[] temp = al.get(i).split("-");
            result[i] = Integer.parseInt(temp[0]);
        }
        long minVal = 0L;
        long productSoFar = 1L;
        for (int i = 0; i < diff.length; i++) {
            long k = result[i];
            long v = diff[i];
            while ((minVal + v) % k != 0) minVal += productSoFar;
            productSoFar *= k;
        }
        return minVal;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int partOne = partOne(new File("resources/inputs/day_13.txt"));
        System.out.println("Part One= " + partOne);

        long partTwo = partTwo(new File("resources/inputs/day_13.txt"));
        System.out.println("Part Two= " + partTwo);

    }
}
