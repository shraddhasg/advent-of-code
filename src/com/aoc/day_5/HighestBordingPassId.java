package com.aoc.day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HighestBordingPassId {

    public static int findRow(String line) {
        int start = 0;
        int end = 127;

        int row = 0;

        for (int i = 0; i < 7; i++) {

            int mid = start + (end - start) / 2;

            if (line.charAt(i) == 'F') {
                end = mid;
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
                end = mid;
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

    public static int findYourSeatId(ArrayList<Integer> al) {

        int result = 0;
        for (int i = 1; i < al.size(); i++) {
            if (al.get(i) - al.get(i - 1) != 1) result = al.get(i) - 1;
        }
        return result;
    }

    public static int findSeatId(String line) {
        int row = findRow(line);
        int column = findColumn(line);

        return (row * 8) + column;
    }


    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File("resources/inputs/day_5.txt"));

        ArrayList<Integer> result = new ArrayList<>();

        while (input.hasNext()) {
            String line = input.nextLine();

            result.add(findSeatId(line));
        }

        Collections.sort(result);
        System.out.println("Heighest id=" + result.get(result.size() - 1));

        int yourSeatId = findYourSeatId(result);
        System.out.println("Your seat id=" + yourSeatId);
    }
}

