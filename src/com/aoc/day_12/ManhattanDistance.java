package com.aoc.day_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ManhattanDistance {
    
    public static ArrayList<String> inputExtraction(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        ArrayList<String> data = new ArrayList<>();
        while (input.hasNextLine()) data.add((input.nextLine()));

        return data;
    }

    public static int partOne(ArrayList<String> data) {
        int currentDirection = 1;
        /*
        0 = north
        1 = east
        2 = south
        3 = west
         */
        int[] result = new int[2];
        int[] dx = {0, 1, 0, -1};//clockwise
        int[] dy = {1, 0, -1, 0};//anti-clockwise

        for (int i = 0; i < data.size(); i++) {
            String direction = data.get(i).charAt(0) + "";
            int number = Integer.parseInt(data.get(i).substring(1));

            if (direction.equals("E")) result[0] += number;
            else if (direction.equals("W")) result[0] -= number;
            else if (direction.equals("N")) result[1] += number;
            else if (direction.equals("S")) result[1] -= number;
            else if (direction.equals("L")) {
                for (int t = 0; t < number / 90; t++) currentDirection = (currentDirection + 3) % 4;
            } else if (direction.equals("R")) {
                for (int t = 0; t < number / 90; t++) currentDirection = (currentDirection + 1) % 4;
            } else if (direction.equals("F")) {
                result[0] += dx[currentDirection] * number;
                result[1] += dy[currentDirection] * number;
            }
        }
        return Math.abs(result[0]) + Math.abs(result[1]);
    }

    public static int partTwo(ArrayList<String> data) {
        int[] result = new int[2];
        int waypointX = 10;
        int waypointY = 1;
        for (int i = 0; i < data.size(); i++) {
            String direction = data.get(i).charAt(0) + "";
            int number = Integer.parseInt(data.get(i).substring(1));

            if (direction.equals("E")) waypointX += number;
            else if (direction.equals("W")) waypointX -= number;
            else if (direction.equals("N")) waypointY += number;
            else if (direction.equals("S")) waypointY -= number;
            else if (direction.equals("L")) {
                for (int t = 0; t < number / 90; t++) {
                    int x = waypointX;
                    int y = waypointY;
                    waypointX = -y;
                    waypointY = x;
                }
            } else if (direction.equals("R")) {
                for (int t = 0; t < number / 90; t++) {
                    int x = waypointX;
                    int y = waypointY;
                    waypointX = y;
                    waypointY = -x;
                }
            } else if (direction.equals("F")) {
                result[0] += waypointX * number;
                result[1] += waypointY * number;
            }
        }
        return Math.abs(result[0]) + Math.abs(result[1]);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> data = inputExtraction(new File("resources/inputs/day_12.txt"));

        int partOne = partOne(data);
        System.out.println("Part One= " + partOne);

        int partTwo = partTwo(data);
        System.out.println("Part Two= " + partTwo);
    }
}
