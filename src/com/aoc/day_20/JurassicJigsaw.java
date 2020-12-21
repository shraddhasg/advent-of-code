package com.aoc.day_20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JurassicJigsaw {
    public static ArrayList<String> findEdges(String[][] matrix, String name) {
        ArrayList<String> result = new ArrayList<>();
        result.add(name);

        String up = "";
        String right = "";
        String down = "";
        String left = "";

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0) up += matrix[i][j];
                if (i == matrix.length - 1) down += matrix[i][j];
                if (j == 0) left += matrix[i][j];
                if (j == matrix[i].length - 1) right += matrix[i][j];
            }
        }

        result.add(up);
        result.add(reverseString(up));
        result.add(right);
        result.add(reverseString(right));
        result.add(down);
        result.add(reverseString(down));
        result.add(left);
        result.add(reverseString(left));
        return result;
    }

    public static String reverseString(String str) {
        String rev = "";
        for (int i = str.length() - 1; i >= 0; i--) rev += str.charAt(i);
        return rev;
    }

    public static void print(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) System.out.print(matrix[i][j]);
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<String>> inputExtraction(File file) throws FileNotFoundException {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        int row = 10;
        int column = 10;
        String[][] matrix = new String[row][column];
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (!line.isEmpty() && line.charAt(0) == 'T') {
                String name = line.substring(5, line.length() - 1);

                for (int i = 0; i < row && input.hasNextLine(); i++) {
                    String rowData = input.nextLine();
                    String[] colData = rowData.split("");
                    for (int j = 0; j < colData.length && j < column; j++) {
                        matrix[i][j] = colData[j];
                    }
                }
                ArrayList<String> temp = findEdges(matrix, name);
                data.add(temp);
            }
        }
        return data;
    }

    public static long partOne(ArrayList<ArrayList<String>> data) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ArrayList<String> temp = data.get(i);
            String name = temp.get(0);
            int count = 0;
            for (int j = 1; j < temp.size(); j++) {
                String check = temp.get(j);
                for (int k = 0; k < data.size(); k++) {
                    if (k == i) continue;
                    ArrayList<String> a = data.get(k);
                    if (!a.contains(check)) continue;
                    count++;
                }
            }
            if (count == 4) result.add(name);
        }

        long ans = 1L;
        for (int i = 0; i < result.size(); i++) ans *= Long.parseLong(result.get(i));
        return ans;

    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<ArrayList<String>> data = inputExtraction(new File("resources/inputs/day_20.txt"));

        long partOne = partOne(data);
        System.out.println("Part One= " + partOne);
    }
}
