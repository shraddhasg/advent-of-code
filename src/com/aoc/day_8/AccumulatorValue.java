package com.aoc.day_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccumulatorValue {

    public static ArrayList<String> inputExtraction(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        ArrayList<String> data = new ArrayList<>();
        while (input.hasNext()) {
            data.add(input.nextLine() + " 1");
        }
        return data;
    }

    public static int accumulatorValueForPartOne(ArrayList<String> data) {
        int result = 0;
        int accumulator = 0;

        for (int i = 0; i < data.size(); ) {
            String[] instructionLine = data.get(i).split(" ");
            String instruction = instructionLine[0];
            char behaviour = instructionLine[1].charAt(0);
            int number = Integer.parseInt(instructionLine[1].substring(1));
            int count = Integer.parseInt(instructionLine[2]);

            if (count == 2) {
                result = accumulator;
                break;
            } else {
                count += 1;
                data.set(i, instruction + " " + behaviour + number + " " + count);
                switch (instruction) {
                    case "acc": {
                        if (behaviour == '-') accumulator -= number;
                        if (behaviour == '+') accumulator += number;
                        i++;
                        break;
                    }
                    case "jmp": {
                        if (behaviour == '-') i = i - number;
                        if (behaviour == '+') i = i + number;
                        break;
                    }
                    case "nop": {
                        i = i + 1;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static int findAccumulatorForPartTwo(ArrayList<String> data) {

        for (int i = 0; i < data.size(); i++) {
            String[] instructionLine = data.get(i).split(" ");
            String instruction = instructionLine[0];
            char behaviour = instructionLine[1].charAt(0);
            int number = Integer.parseInt(instructionLine[1].substring(1));
            int count = 0;

            data.set(i, instruction + " " + behaviour + number + " " + count);
        }

        int accumulator = 0;

        for (int i = 0; i < data.size(); ) {
            String[] instructionLine = data.get(i).split(" ");
            String instruction = instructionLine[0];
            char behaviour = instructionLine[1].charAt(0);
            int number = Integer.parseInt(instructionLine[1].substring(1));

            int count = Integer.parseInt(instructionLine[2]);
            if (count > 1) return 0;
            count += 1;

            data.set(i, instruction + " " + behaviour + number + " " + count);

            switch (instruction) {
                case "acc": {
                    if (behaviour == '-') accumulator -= number;
                    if (behaviour == '+') accumulator += number;
                    i++;
                    break;
                }
                case "jmp": {
                    if (behaviour == '-') i = i - number;
                    if (behaviour == '+') i = i + number;
                    break;
                }
                case "nop": {
                    i = i + 1;
                    break;
                }
            }
        }
        return accumulator;
    }


    public static int secondPart(ArrayList<String> data) {
        int result = 0;

        for (int i = 0; i < data.size(); i++) {
            String[] instructionLine = data.get(i).split(" ");
            String instruction = instructionLine[0];
            char behaviour = instructionLine[1].charAt(0);
            int number = Integer.parseInt(instructionLine[1].substring(1));

            if (instruction.equals("acc")) continue;

            if (instruction.equals("nop") && number != 0) {
                data.set(i, "jmp" + " " + behaviour + number + " " + "1");
                result = findAccumulatorForPartTwo(data);

                if (result != 0) return result;
                data.set(i, "nop" + " " + behaviour + number + " " + "1");
            } else if (instruction.equals("jmp") && number != 0) {
                data.set(i, "nop" + " " + behaviour + number + " " + "1");
                result = findAccumulatorForPartTwo(data);

                if (result != 0) return result;
                data.set(i, "jmp" + " " + behaviour + number + " " + "1");
            }
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> data = inputExtraction(new File("resources/inputs/day_8.txt"));

        System.out.println("Part one= " + accumulatorValueForPartOne(data));
        System.out.println("Part two = " + secondPart(data));
    }
}
