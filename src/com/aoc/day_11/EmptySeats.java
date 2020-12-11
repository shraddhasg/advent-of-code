package com.aoc.day_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class EmptySeats {

    public static void printArray(String[][] seatMatrix) {
        for (int i = 0; i < seatMatrix.length; i++) {
            for (int j = 0; j < seatMatrix[i].length; j++) {
                System.out.print(seatMatrix[i][j]);
            }
            System.out.println();
        }
    }

    public static String[][] inputExtraction(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        int row = 0;
        String[][] seatMatrix = new String[91][92];
        
        while (input.hasNextLine()) {
            String[] columns = input.nextLine().split("");
            for (int i = 0; i < columns.length; i++) seatMatrix[row][i] = columns[i];
            row++;
        }

        for (int i = 0; i < seatMatrix.length; i++) {
            for (int j = 0; j < seatMatrix[0].length; j++) {
                if (seatMatrix[i][j].equals("L")) seatMatrix[i][j] = "#";
            }
        }
        return seatMatrix;
    }

    public static String[][] copyArray(String[][] arr) {
        String[][] copy = new String[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }

    public static String[][] makeChangesForPartOne(String[][] seatMatrix, String[][] temp) {

        for (int i = 0; i < seatMatrix.length; i++) {
            for (int j = 0; j < seatMatrix[i].length; j++) {
                if (seatMatrix[i][j].equals("#") || seatMatrix[i][j].equals("L")) {
                    int count = 0;
                    if (i == 0) {
                        if (j == 0) {
                            if (seatMatrix[i][j + 1].equals("#")) count++; // next
                            if (seatMatrix[i + 1][j + 1].equals("#")) count++;//diagonal
                            if (seatMatrix[i + 1][j].equals("#")) count++;//below

                        }
                        if (j == seatMatrix[i].length - 1) {
                            if (seatMatrix[i][j - 1].equals("#")) count++;
                            if (seatMatrix[i + 1][j - 1].equals("#")) count++;//diagonal
                            if (seatMatrix[i + 1][j].equals("#")) count++;//below
                        }
                        if (j > 0 && j < seatMatrix[i].length - 1) {
                            if (seatMatrix[i][j - 1].equals("#")) count++;
                            if (seatMatrix[i][j + 1].equals("#")) count++;
                            if (seatMatrix[i + 1][j - 1].equals("#")) count++;
                            if (seatMatrix[i + 1][j].equals("#")) count++;
                            if (seatMatrix[i + 1][j + 1].equals("#")) count++;
                        }

                        if (seatMatrix[i][j].equals("#") && count >= 4) temp[i][j] = "L";
                        if (seatMatrix[i][j].equals("L") && count == 0) temp[i][j] = "#";
                    }
                    if (i == seatMatrix.length - 1) {
                        if (j == 0) {
                            if (seatMatrix[i - 1][j].equals("#")) count++; // next
                            if (seatMatrix[i - 1][j + 1].equals("#")) count++;//diagonal
                            if (seatMatrix[i][j + 1].equals("#")) count++;//below

                        }
                        if (j == seatMatrix[i].length - 1) {
                            if (seatMatrix[i][j - 1].equals("#")) count++;
                            if (seatMatrix[i - 1][j].equals("#")) count++;//diagonal
                            if (seatMatrix[i - 1][j - 1].equals("#")) count++;//below
                        }
                        if (j > 0 && j < seatMatrix[i].length - 1) {
                            if (seatMatrix[i][j - 1].equals("#")) count++;
                            if (seatMatrix[i][j + 1].equals("#")) count++;
                            if (seatMatrix[i - 1][j].equals("#")) count++;
                            if (seatMatrix[i - 1][j - 1].equals("#")) count++;
                            if (seatMatrix[i - 1][j + 1].equals("#")) count++;
                        }

                        if (seatMatrix[i][j].equals("#") && count >= 4) temp[i][j] = "L";
                        if (seatMatrix[i][j].equals("L") && count == 0) temp[i][j] = "#";
                    }
                    if (i > 0 && i < seatMatrix.length - 1) {
                        if (j == 0) {
                            if (seatMatrix[i - 1][j].equals("#")) count++; // next
                            if (seatMatrix[i + 1][j].equals("#")) count++;//diagonal
                            if (seatMatrix[i][j + 1].equals("#")) count++;//below
                            if (seatMatrix[i - 1][j + 1].equals("#")) count++;
                            if (seatMatrix[i + 1][j + 1].equals("#")) count++;

                        } else if (j == seatMatrix[i].length - 1) {
                            if (seatMatrix[i - 1][j].equals("#")) count++;
                            if (seatMatrix[i + 1][j].equals("#")) count++;//diagonal
                            if (seatMatrix[i][j - 1].equals("#")) count++;//below
                            if (seatMatrix[i - 1][j - 1].equals("#")) count++;
                            if (seatMatrix[i + 1][j - 1].equals("#")) count++;

                        } else {
                            if (seatMatrix[i][j - 1].equals("#")) count++;
                            if (seatMatrix[i][j + 1].equals("#")) count++;
                            if (seatMatrix[i - 1][j].equals("#")) count++;
                            if (seatMatrix[i + 1][j].equals("#")) count++;
                            if (seatMatrix[i - 1][j - 1].equals("#")) count++;
                            if (seatMatrix[i - 1][j + 1].equals("#")) count++;
                            if (seatMatrix[i + 1][j - 1].equals("#")) count++;
                            if (seatMatrix[i + 1][j + 1].equals("#")) count++;
                        }

                        if (seatMatrix[i][j].equals("#") && count >= 4) temp[i][j] = "L";
                        if (seatMatrix[i][j].equals("L") && count == 0) temp[i][j] = "#";

                    }
                }
            }
        }
//        printArray(temp);
        return temp;

    }

    public static boolean isEqual(String[][] a, String[][] b) {
        return Arrays.deepEquals(a, b);
    }

    public static int countOccupiedSeats(String[][] seats) {
        int count = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].equals("#")) count++;
            }
        }
        return count;
    }

    public static int partOne(String[][] seatMatrix) {
        String[][] temp;

        temp = copyArray(seatMatrix);
        temp = makeChangesForPartOne(seatMatrix, temp);

        while (isEqual(seatMatrix, temp) == false) {
            seatMatrix = copyArray(temp);
            temp = makeChangesForPartOne(seatMatrix, temp);
        }

        int ans = countOccupiedSeats(temp);
        return ans;
    }

    public static String[][] makeChangesForPartTwo(String[][] seatMatrix, String[][] temp) {
        for (int i = 0; i < seatMatrix.length; i++) {
            for (int j = 0; j < seatMatrix[i].length; j++) {
                if (seatMatrix[i][j].equals("#") || seatMatrix[i][j].equals("L")) {
                    int count = 0;
                    if (i == 0) {
                        if (j == 0) {
                            if (seatMatrix[i][j + 1].equals("#")) count++;
                            else if (seatMatrix[i][j + 1].equals(".")) {
                                int t = j + 2;
                                while (t < seatMatrix[i].length) {
                                    if (seatMatrix[i][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[i][t].equals("L")) break;
                                    t++;
                                }
                            }

                            if (seatMatrix[i + 1][j + 1].equals("#")) count++;//diagonal
                            else if (seatMatrix[i + 1][j + 1].equals(".")) {
                                int t = j + 2;
                                int k = i + 2;
                                while (t < seatMatrix[i].length && k < seatMatrix.length) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t++;
                                    k++;
                                }
                            }

                            if (seatMatrix[i + 1][j].equals("#")) count++;//below
                            else if (seatMatrix[i + 1][j].equals(".")) {
                                int t = i + 2;
                                while (t < seatMatrix.length) {
                                    if (seatMatrix[t][j].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[t][j].equals("L")) break;
                                    t++;
                                }
                            }
                        }

                        if (j == seatMatrix[i].length - 1) {
                            if (seatMatrix[i][j - 1].equals("#")) count++;
                            else if (seatMatrix[i][j - 1].equals(".")) {
                                int t = j - 2;
                                while (t >= 0) {
                                    if (seatMatrix[i][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[i][t].equals("L")) break;
                                    t--;
                                }
                            }

                            if (seatMatrix[i + 1][j - 1].equals("#")) count++;//diagonal
                            else if (seatMatrix[i + 1][j - 1].equals(".")) {
                                int k = i + 2;
                                int t = j - 2;
                                while (t >= 0 && k < seatMatrix.length) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t--;
                                    k++;
                                }
                            }

                            if (seatMatrix[i + 1][j].equals("#")) count++;//below
                            else if (seatMatrix[i + 1][j].equals(".")) {
                                int t = i + 2;
                                while (t < seatMatrix.length) {
                                    if (seatMatrix[t][j].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[t][j].equals("L")) break;
                                    t++;
                                }
                            }
                        }

                        if (j > 0 && j < seatMatrix[i].length - 1) {
                            if (seatMatrix[i][j - 1].equals("#")) count++;
                            else if (seatMatrix[i][j - 1].equals(".")) {
                                int t = j - 2;
                                while (t >= 0) {
                                    if (seatMatrix[i][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[i][t].equals("L")) break;
                                    t--;
                                }
                            }

                            if (seatMatrix[i][j + 1].equals("#")) count++;
                            else if (seatMatrix[i][j + 1].equals(".")) {
                                int t = j + 2;
                                while (t < seatMatrix[0].length) {
                                    if (seatMatrix[i][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[i][t].equals("L")) break;
                                    t++;
                                }
                            }

                            if (seatMatrix[i + 1][j - 1].equals("#")) count++;
                            else if (seatMatrix[i + 1][j - 1].equals(".")) {
                                int k = i + 2;
                                int t = j - 2;
                                while (k < seatMatrix.length && t >= 0) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t--;
                                    k++;
                                }
                            }

                            if (seatMatrix[i + 1][j].equals("#")) count++;
                            else if (seatMatrix[i + 1][j].equals(".")) {
                                int t = i + 2;
                                while (t < seatMatrix.length) {
                                    if (seatMatrix[t][j].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[t][j].equals("L")) break;
                                    t++;
                                }
                            }

                            if (seatMatrix[i + 1][j + 1].equals("#")) count++;
                            else if (seatMatrix[i + 1][j + 1].equals(".")) {
                                int k = i + 2;
                                int t = j + 2;
                                while (k < seatMatrix.length && t < seatMatrix[0].length) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t++;
                                    k++;
                                }
                            }
                        }

                        if (seatMatrix[i][j].equals("#") && count >= 5) temp[i][j] = "L";
                        if (seatMatrix[i][j].equals("L") && count == 0) temp[i][j] = "#";
                    }

                    if (i == seatMatrix.length - 1) {
                        if (j == 0) {
                            if (seatMatrix[i - 1][j].equals("#")) count++; // next
                            else if (seatMatrix[i - 1][j].equals(".")) {
                                int t = i - 2;
                                while (t >= 0) {
                                    if (seatMatrix[t][j].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[t][j].equals("L")) break;
                                    t--;
                                }
                            }

                            if (seatMatrix[i - 1][j + 1].equals("#")) count++;//diagonal
                            else if (seatMatrix[i - 1][j + 1].equals(".")) {
                                int k = i - 2;
                                int t = j + 2;
                                while (k >= 0 && t < seatMatrix[0].length) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t++;
                                    k--;
                                }
                            }

                            if (seatMatrix[i][j + 1].equals("#")) count++;//below
                            else if (seatMatrix[i][j + 1].equals(".")) {
                                int t = j + 2;
                                while (t < seatMatrix[0].length) {
                                    if (seatMatrix[i][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[i][t].equals("L")) break;
                                    t++;
                                }
                            }

                        }

                        if (j == seatMatrix[i].length - 1) {
                            if (seatMatrix[i][j - 1].equals("#")) count++;
                            else if (seatMatrix[i][j - 1].equals(".")) {
                                int t = j - 2;
                                while (t >= 0) {
                                    if (seatMatrix[i][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[i][t].equals("L")) break;
                                    t--;
                                }
                            }

                            if (seatMatrix[i - 1][j].equals("#")) count++;//diagonal
                            else if (seatMatrix[i - 1][j].equals(".")) {
                                int t = i - 2;
                                while (t >= 0) {
                                    if (seatMatrix[t][j].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[t][j].equals("L")) break;
                                    t--;
                                }
                            }

                            if (seatMatrix[i - 1][j - 1].equals("#")) count++;//below
                            else if (seatMatrix[i - 1][j - 1].equals(".")) {
                                int k = i - 2;
                                int t = j - 2;
                                while (k >= 0 && t >= 0) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("#")) break;
                                    t--;
                                    k--;
                                }
                            }

                        }

                        if (j > 0 && j < seatMatrix[i].length - 1) {
                            if (seatMatrix[i][j - 1].equals("#")) count++;
                            else if (seatMatrix[i][j - 1].equals(".")) {
                                int t = j - 2;
                                while (t >= 0) {
                                    if (seatMatrix[i][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[i][t].equals("L")) break;
                                    t--;
                                }
                            }

                            if (seatMatrix[i][j + 1].equals("#")) count++;
                            else if (seatMatrix[i][j + 1].equals(".")) {
                                int t = j + 2;
                                while (t < seatMatrix[0].length) {
                                    if (seatMatrix[i][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[i][t].equals("L")) break;
                                    t++;
                                }
                            }

                            if (seatMatrix[i - 1][j].equals("#")) count++;
                            else if (seatMatrix[i - 1][j].equals(".")) {
                                int t = i - 2;
                                while (t >= 0) {
                                    if (seatMatrix[t][j].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[t][j].equals("L")) break;
                                    t--;
                                }
                            }

                            if (seatMatrix[i - 1][j - 1].equals("#")) count++;
                            else if (seatMatrix[i - 1][j - 1].equals(".")) {
                                int k = i - 2;
                                int t = j - 2;
                                while (t >= 0 && k >= 0) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t--;
                                    k--;
                                }
                            }

                            if (seatMatrix[i - 1][j + 1].equals("#")) count++;
                            else if (seatMatrix[i - 1][j + 1].equals(".")) {
                                int k = i - 2;
                                int t = j + 2;
                                while (k >= 0 && t < seatMatrix[i].length) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t++;
                                    k--;
                                }
                            }
                        }

                        if (seatMatrix[i][j].equals("#") && count >= 5) temp[i][j] = "L";
                        if (seatMatrix[i][j].equals("L") && count == 0) temp[i][j] = "#";
                    }


                    if (i > 0 && i < seatMatrix.length - 1) {
                        if (j == 0) {
                            if (seatMatrix[i - 1][j].equals("#")) count++;
                            else if (seatMatrix[i - 1][j].equals(".")) {
                                int t = i - 2;
                                while (t >= 0) {
                                    if (seatMatrix[t][j].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[t][j].equals("L")) break;
                                    t--;
                                }
                            }

                            if (seatMatrix[i + 1][j].equals("#")) count++;//diagonal
                            else if (seatMatrix[i + 1][j].equals(".")) {
                                int t = i + 2;
                                while (t < seatMatrix.length) {
                                    if (seatMatrix[t][j].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[t][j].equals("L")) break;
                                    t++;
                                }
                            }

                            if (seatMatrix[i][j + 1].equals("#")) count++;//below
                            else if (seatMatrix[i][j + 1].equals(".")) {
                                int t = j + 2;
                                while (t < seatMatrix[i].length) {
                                    if (seatMatrix[i][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[i][t].equals("L")) break;
                                    t++;
                                }
                            }

                            if (seatMatrix[i - 1][j + 1].equals("#")) count++;
                            else if (seatMatrix[i - 1][j + 1].equals(".")) {
                                int k = i - 2;
                                int t = j + 2;
                                while (k >= 0 && t < seatMatrix[i].length) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    k--;
                                    t++;
                                }
                            }

                            if (seatMatrix[i + 1][j + 1].equals("#")) count++;
                            else if (seatMatrix[i + 1][j + 1].equals(".")) {
                                int k = i + 2;
                                int t = j + 2;
                                while (k < seatMatrix.length && t < seatMatrix[i].length) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t++;
                                    k++;
                                }
                            }

                        } else if (j == seatMatrix[i].length - 1) {
                            if (seatMatrix[i - 1][j].equals("#")) count++;
                            else if (seatMatrix[i - 1][j].equals(".")) {
                                int t = i - 2;
                                while (t >= 0) {
                                    if (seatMatrix[t][j].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[t][j].equals("L")) break;
                                    t--;
                                }
                            }

                            if (seatMatrix[i + 1][j].equals("#")) count++;//diagonal
                            else if (seatMatrix[i + 1][j].equals(".")) {
                                int t = i + 2;
                                while (t < seatMatrix.length) {
                                    if (seatMatrix[t][j].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[t][j].equals("L")) break;
                                    t++;
                                }
                            }

                            if (seatMatrix[i][j - 1].equals("#")) count++;//below
                            else if (seatMatrix[i][j - 1].equals(".")) {
                                int t = j - 2;
                                while (t >= 0) {
                                    if (seatMatrix[i][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[i][t].equals("L")) break;
                                    t--;
                                }
                            }

                            if (seatMatrix[i - 1][j - 1].equals("#")) count++;
                            else if (seatMatrix[i - 1][j - 1].equals(".")) {
                                int t = j - 2;
                                int k = i - 2;
                                while (t >= 0 && k >= 0) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t--;
                                    k--;
                                }
                            }

                            if (seatMatrix[i + 1][j - 1].equals("#")) count++;
                            else if (seatMatrix[i + 1][j - 1].equals(".")) {
                                int t = j - 2;
                                int k = i + 2;
                                while (t >= 0 && k < seatMatrix.length) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t--;
                                    k++;
                                }
                            }

                        } else {
                            if (seatMatrix[i][j - 1].equals("#")) count++;
                            else if (seatMatrix[i][j - 1].equals(".")) {
                                int t = j - 2;
                                while (t >= 0) {
                                    if (seatMatrix[i][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[i][t].equals("L")) break;
                                    t--;
                                }
                            }

                            if (seatMatrix[i][j + 1].equals("#")) count++;
                            else if (seatMatrix[i][j + 1].equals(".")) {
                                int t = j + 2;
                                while (t < seatMatrix[i].length) {
                                    if (seatMatrix[i][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[i][t].equals("L")) break;
                                    t++;
                                }
                            }

                            if (seatMatrix[i - 1][j].equals("#")) count++;
                            else if (seatMatrix[i - 1][j].equals(".")) {
                                int t = i - 2;
                                while (t >= 0) {
                                    if (seatMatrix[t][j].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[t][j].equals("L")) break;
                                    t--;
                                }
                            }

                            if (seatMatrix[i + 1][j].equals("#")) count++;
                            else if (seatMatrix[i + 1][j].equals(".")) {
                                int t = i + 2;
                                while (t < seatMatrix.length) {
                                    if (seatMatrix[t][j].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[t][j].equals("L")) break;
                                    t++;
                                }
                            }

                            if (seatMatrix[i - 1][j - 1].equals("#")) count++;
                            else if (seatMatrix[i - 1][j - 1].equals(".")) {
                                int t = j - 2;
                                int k = i - 2;
                                while (t >= 0 && k >= 0) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t--;
                                    k--;
                                }
                            }

                            if (seatMatrix[i - 1][j + 1].equals("#")) count++;
                            else if (seatMatrix[i - 1][j + 1].equals(".")) {
                                int k = i - 2;
                                int t = j + 2;
                                while (k >= 0 && t < seatMatrix[i].length) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    k--;
                                    t++;
                                }
                            }

                            if (seatMatrix[i + 1][j - 1].equals("#")) count++;
                            else if (seatMatrix[i + 1][j - 1].equals(".")) {
                                int t = j - 2;
                                int k = i + 2;
                                while (t >= 0 && k < seatMatrix.length) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t--;
                                    k++;
                                }
                            }

                            if (seatMatrix[i + 1][j + 1].equals("#")) count++;
                            else if (seatMatrix[i + 1][j + 1].equals(".")) {
                                int t = j + 2;
                                int k = i + 2;
                                while (t < seatMatrix[i].length && k < seatMatrix.length) {
                                    if (seatMatrix[k][t].equals("#")) {
                                        count++;
                                        break;
                                    }
                                    if (seatMatrix[k][t].equals("L")) break;
                                    t++;
                                    k++;
                                }
                            }
                        }

                        if (seatMatrix[i][j].equals("#") && count >= 5) temp[i][j] = "L";
                        if (seatMatrix[i][j].equals("L") && count == 0) temp[i][j] = "#";

                    }
                }
            }
        }
        return temp;
    }

    public static int partTwo(String[][] seatMatrix) {
        String[][] temp;
        temp = copyArray(seatMatrix);
        temp = makeChangesForPartTwo(seatMatrix, temp);

        while (isEqual(seatMatrix, temp) == false) {
            seatMatrix = copyArray(temp);
            temp = makeChangesForPartTwo(seatMatrix, temp);
        }
        int ans = countOccupiedSeats(temp);
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[][] seatMatrix = inputExtraction(new File("resources/inputs/day_11.txt"));

        int partOne = partOne(seatMatrix);
        System.out.println("Part One= " + partOne);

        int partTwo = partTwo(seatMatrix);
        System.out.println("Part Two= " + partTwo);

    }
}