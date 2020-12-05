package com.aoc.day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HighestBordingPassId {

    public static int findRow(String line) {
        int start = 0;
        int end = 127;

        int row = 0;

        for (int i = 0; i < 7; i++) {

            int mid = start + (end - start) / 2;

            if (line.charAt(i) == 'F') {
                end = mid - 1;
            }

            if (line.charAt(i) == 'B') {
                start = mid + 1;
            }

            if (start == end) {
                row = start;
                break;
            }
        }

        return row;
    }


    public static int findColumn(String line) {

        int start = 0;
        int end = 7;

        int column = 0;

        for (int i = 7; i < 10; i++) {

            int mid = start + (end - start) / 2;

            if (line.charAt(i) == 'L') {
                end = mid - 1;
            }

            if (line.charAt(i) == 'R') {
                start = mid + 1;
            }

            if (start == end) {
                column = start;
                break;
            }
        }

        return column;
    }

    public static ArrayList<Map.Entry<Integer, Integer>> sortMap(HashMap<Integer, Integer> map) {

        ArrayList<Map.Entry<Integer, Integer>> arrayListOfMap = new ArrayList<>(map.entrySet());
        Collections.sort(arrayListOfMap, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                Integer i1 = o1.getKey();
                Integer i2 = o2.getKey();
                return i1.compareTo(i2);
            }
        });

        return arrayListOfMap;
    }

    public static HashMap<Integer, Integer> createHashMap(ArrayList<Integer> al) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < al.size(); i++) {
            if (map.containsKey(al.get(i))) {
                int val = map.get(al.get(i));
                map.put(al.get(i), val + 1);
            } else map.put(al.get(i), 1);
        }

        return map;
    }


    public static int findYourSeatId(ArrayList<Integer> al) {

        HashMap<Integer, Integer> map = createHashMap(al);

        ArrayList<Map.Entry<Integer, Integer>> arrayListOfMap = sortMap(map);
        int ans = 0;
        for (int i = 1; i < arrayListOfMap.size() - 1; i++) {
            if (arrayListOfMap.get(i).getValue() != 6 && arrayListOfMap.get(i).getValue() != 4 && arrayListOfMap.get(i).getValue() != 2) {
                ans = arrayListOfMap.get(i).getKey();
                break;
            }
        }

        return ans;
    }


    public static int findSeatId(String line) {

        int row = findRow(line);
        int column = findColumn(line);
        int result = (row * 8) + column;

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File("resources/inputs/day_5.txt"));

        ArrayList<Integer> result = new ArrayList<>();

        while (input.hasNext()) {
            String line = input.nextLine();

            result.add(findSeatId(line));
        }

        Collections.sort(result);
        System.out.println("All Boarding pass ID's=" + result);
        System.out.println("***************************************************************");
        System.out.println("Heighest id=" + result.get(result.size() - 1));

        int yourSeatId = findYourSeatId(result);
        System.out.println("Your seat id=" + yourSeatId);
    }
}
