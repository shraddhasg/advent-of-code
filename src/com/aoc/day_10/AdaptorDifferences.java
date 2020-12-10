package com.aoc.day_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AdaptorDifferences {
    public static long[] t = new long[94];

    public static ArrayList<Integer> inputExtraction(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        ArrayList<Integer> data = new ArrayList<>();
        data.add(0);
        while (input.hasNextLine()) {
            data.add(input.nextInt());
        }
        return data;
    }

    public static int partOne(ArrayList<Integer> data) {
        Collections.sort(data);
        int largestNumber = data.get(data.size() - 1) + 3;

        int OneJoltDifferences = 0;
        for (int i = 0; i < data.size() - 1; i++) if (data.get(i + 1) - data.get(i) == 1) OneJoltDifferences++;

        int ThreeJoltDifferences = (largestNumber - OneJoltDifferences) / 3;
        return OneJoltDifferences * ThreeJoltDifferences;
    }

    public static long partTwo(ArrayList<Integer> data, int val) {

        if (val == data.size() - 1) return 1;
        if (t[val] != -1) return t[val];

        long ans = 0;
        for (int j = val + 1; j < data.size(); j++) {
            if (data.get(j) - data.get(val) <= 3) ans += partTwo(data, j);
        }
        t[val] = ans;
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> data = inputExtraction(new File("resources/inputs/day_10.txt"));

        int partOne = partOne(data);
        System.out.println("Part one= " + partOne);

        for (int i = 0; i < t.length; i++) t[i] = -1;

        Collections.sort(data);
        long partTwo = partTwo(data, 0);
        System.out.println("Part two= " + partTwo);
    }
}
