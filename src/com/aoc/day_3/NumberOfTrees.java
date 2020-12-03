package com.aoc.day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NumberOfTrees {
    public static void createNewInputFile() throws IOException {
        Scanner in = new Scanner(new File("resources/inputs/day_3.txt"));
        FileWriter myWriter = new FileWriter("resources/inputs/newInputForDay_3.txt");
        while (in.hasNext()) {
            String line = in.next();
            for (int i = 0; i < 100; i++) {
                myWriter.write(line);
            }
            myWriter.write("\n");

        }
        myWriter.close();
        in.close();
    }

    public static int oneRightTwoDown(int rightMoves, int downMoves) throws FileNotFoundException {
        Scanner readingInput = new Scanner(new File("resources/inputs/newInputForDay_3.txt"));
        int locationOfUsefulChar = 0;
        int count = 0;

        for (int i = 0; i < downMoves - 1; i++)
            readingInput.next();

        while (readingInput.hasNext()) {
            readingInput.next();
            String line = readingInput.next();

            if (line.charAt(locationOfUsefulChar + rightMoves) == '#') {
                count++;
            }

            locationOfUsefulChar = locationOfUsefulChar + rightMoves;
        }
        readingInput.close();

        return count;


    }

    public static int findNumberOfTrees(int rightMoves, int downMoves) throws FileNotFoundException {
        Scanner readingInput = new Scanner(new File("resources/inputs/newInputForDay_3.txt"));
        int locationOfUsefulChar = 0;
        int count = 0;

        for (int i = 0; i < downMoves; i++)
            readingInput.next();

        while (readingInput.hasNext()) {
            String line = readingInput.next();
            if (line.charAt(locationOfUsefulChar + rightMoves) == '#') {
                count++;
            }
            locationOfUsefulChar = locationOfUsefulChar + rightMoves;
        }
        readingInput.close();

        return count;


    }

    public static void main(String[] args) throws Exception {
        createNewInputFile();

        long ansOfPartOne = findNumberOfTrees(3, 1);
        long ansOfPartTwo = 1;
        long one = findNumberOfTrees(1, 1);
        long two = findNumberOfTrees(5, 1);
        long three = findNumberOfTrees(7, 1);
        long four = oneRightTwoDown(1, 2);

        System.out.println(one + " " + ansOfPartOne + " " + two + " " + three + " " + four);

        ansOfPartTwo = one * ansOfPartOne * two * three * four;

        System.out.println("3 Right moves and 1 down move (Part one) =" + ansOfPartOne);
        System.out.println("Part two = " + ansOfPartTwo);

    }
}
