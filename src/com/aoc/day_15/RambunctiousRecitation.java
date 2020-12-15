package com.aoc.day_15;

import java.util.HashMap;

public class RambunctiousRecitation {
    public static HashMap<Integer, String> map = new HashMap<>();

    public static void putValuesInMap(int val, int index) {
        if (map.containsKey(val)) {
            String[] temp = map.get(val).split("-");
            String indexes = temp[0];
            int valCount = Integer.parseInt(temp[1]) + 1;
            map.put(val, index + "," + indexes + "-" + valCount);
        } else map.put(val, index + "-" + 1);
    }

    public static int partOne(int[] input, int limit, int previousNumber) {
        for (int i = 0; i < input.length; i++) {
            previousNumber = input[i];
            putValuesInMap(input[i], i + 1);
        }

        int lastElement = 0;
        for (int i = map.size(); i <= limit; i++) {
            lastElement = previousNumber;
            if (map.containsKey(lastElement)) {
                String[] temp = map.get(lastElement).split("-");
                String index = temp[0];
                int count = Integer.parseInt(temp[1]);
                if (count == 1) {
                    previousNumber = 0;
                    putValuesInMap(0, i + 1);
                } else if (count > 1) {
                    String[] indexes = index.split(",");
                    int val = Integer.parseInt(indexes[0]) - Integer.parseInt(indexes[1]);
                    previousNumber = val;
                    putValuesInMap(val, i + 1);
                }
            }
        }
        return lastElement;
    }

    public static void findSolution(int[] input, int limit) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for (i = 0; i < input.length; i++) map.put(input[i], i + 1);
        System.out.println(map);
        int number = 0;
        int prevNum = 0;
        for (i = input.length + 1; i <= limit; i++) {
            if (map.containsKey(number)) {
                prevNum = i - map.get(number);
                map.put(number, i);
                number = prevNum;
                System.out.println(map);
            } else {
                map.put(0, i);
                number = 0;
            }
        }
        System.out.println(number);
    }

    public static int solution(int[] input, int limit) {
        var history = new long[limit];

        for (int i = 0; i < input.length; i++) {
            // put i + 1 into the lowest 32 bits
            history[input[i]] = i + 1L;
        }

        var number = input[input.length - 1];

        for (int i = input.length + 1; i <= limit; i++) {
            var numberHistory = history[number];

            // if the highest 32 bits are not set then this number has only been seen once
            // if the number has been seen twice, subtract the highest 32 bits from the lowest 32 bits
            number = (numberHistory >> Integer.SIZE) == 0L ? 0 :
                    (int) ((numberHistory & ~0) - (numberHistory >> Integer.SIZE));

            // shift the lowest 32 bits into the highest 32 bits
            // then put i into the lowest 32 bits
            history[number] = (history[number] << Integer.SIZE) | i;
        }
        return number;
    }

    public static void main(String[] args) {
        int[] input = {1, 20, 11, 6, 12, 0};
//        findSolution(input, 2020);

        int partOne = partOne(input, 2020, 0);
        System.out.println("Part One= " + partOne);

//        int partOne = solution(input, 2020);
//        System.out.println("Part One= " + partOne);

        int partTwo = solution(input, 30000000);
        System.out.println("Part Two= " + partTwo);
    }
}
