package com.aoc.day_17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class ConwayCubes {

   
    public static String[][] inputEctraction(File file) throws FileNotFoundException {
        int row = 8;
        int column = 8;
        String[][] result = new String[row][column];
        Scanner input = new Scanner(file);
        for (int i = 0; i < row && input.hasNextLine(); i++) {
            String rowData = input.nextLine();
            String[] colData = rowData.split("");

            for (int j = 0; j < colData.length && j < column; j++) {
                result[i][j] = colData[j];
            }
        }
        return result;
//        for (int i = 0; i < result.length; i++) {
//            for (int j = 0; j < result[i].length; j++) {
//                System.out.print(result[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[][] input = inputEctraction(new File("resources/inputs/day_17.txt"));


        HashSet<Integer> on = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j].equals("#")) on.add(i + j + 0);
            }
        }
        int nbr = 0;
        for (int i = 0; i < 6; i++) {
            HashSet<Integer> h = new HashSet<>();
            for (int x = -15; x <= 15; x++) {
                for (int y = -15; y <= 15; y++) {
                    for (int z = -15; z <= 15; z++) {
                        nbr = 0;
                    }
                }
            }
        }


    }
}
