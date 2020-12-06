package com.aoc.day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NumberOfPersons {

    public static HashMap<Character, Integer> createMap(String str) {

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                int val = map.get(str.charAt(i));
                map.put(str.charAt(i), val + 1);
            } else map.put(str.charAt(i), 1);
        }
        return map;
    }

    public static HashMap<Character, Integer> insertEntry(HashMap<Character, Integer> map, String str) {

        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                int val = map.get(str.charAt(i));
                map.put(str.charAt(i), val + 1);
            } else map.put(str.charAt(i), 1);
        }
        return map;
    }

    public static int yesCount(HashMap<Character, Integer> map, int lineCount) {

        int count = 0;
        Collection c = map.values();
        Iterator itr = c.iterator();

        while (itr.hasNext()) {
            if ((int) itr.next() == lineCount) count++;
        }
        return count;
    }


    public static void main(String[] args) throws FileNotFoundException {
        int partOne = 0;
        int partTwo = 0;

        Scanner input = new Scanner(new File("resources/inputs/day_6.txt"));

        while (input.hasNext()) {
            int lineCount = 0;
            ArrayList<String> lines = new ArrayList<>();
            String line = null;

            while (!(line = input.nextLine()).isEmpty()) {
                lineCount++;
                lines.add(line);
            }
            HashMap<Character, Integer> map = createMap(lines.get(0));
            for (int i = 1; i < lines.size(); i++) {
                map = insertEntry(map, lines.get(i));
            }

            partOne += map.size();
            partTwo += yesCount(map, lineCount);
        }

        System.out.println("Part One=" + partOne);
        System.out.println("Part two=" + partTwo);
    }
}

