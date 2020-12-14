package com.aoc.day_14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DockingData {

    public static ArrayList<String> inputExtraction(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        ArrayList<String> data = new ArrayList<>();
        while (input.hasNextLine()) data.add(input.nextLine());
        return data;
    }

    public static long partOne(ArrayList<String> data) {
        ArrayList<Long> resultData = new ArrayList<>();

        long finalSum = 0;
        HashMap<Integer, Long> map = new HashMap<>();
        ArrayList<Integer> fixIndexes = new ArrayList<>();
        ArrayList<String> mask = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).charAt(1) == 'a') {
                mask.clear();
                fixIndexes.clear();
                String[] temp = data.get(i).split(" ");
                for (int k = 0; k < temp[2].length(); k++) {
                    if (temp[2].charAt(k) != 'x' && temp[2].charAt(k) != 'X') {
                        fixIndexes.add(k);
                        mask.add(temp[2].charAt(k) + "");
                    } else mask.add(0 + "");
                }
            }
            if (data.get(i).charAt(2) == 'm') {
                String[] temp = data.get(i).split("=");
                int memoryLocation = Integer.parseInt(temp[0].substring(4, temp[0].length() - 2));
                int number = Integer.parseInt(temp[1].substring(1));
                String binaryString = Integer.toBinaryString(number);
                int len = binaryString.length();
                String abc = "";
                for (int l = 0; l < mask.size() - len; l++) abc += "0";
                binaryString = abc + binaryString;
                for (int t = 0; t < binaryString.length(); t++) {
                    if (fixIndexes.contains(t)) continue;
                    mask.set(t, binaryString.charAt(t) + "");
                }
                String str = "";
                for (int s = 0; s < mask.size(); s++) str += mask.get(s);
                long decimalNumber = Long.parseLong(str, 2);
                map.put(memoryLocation, decimalNumber);
            }
        }
        for (Map.Entry mapElement : map.entrySet()) {
            finalSum += (long) mapElement.getValue();
        }
        return finalSum;
    }

    public static String convertIntoBinaryString(int number) {
        String binaryString = Integer.toBinaryString(number);
        int len = binaryString.length();
        String abc = "";
        for (int l = 0; l < 36 - len; l++) abc += "0";
        binaryString = abc + binaryString;

        return binaryString;
    }

    public static String compareMaskAndAddress(String mask, String address) {
        String result = "";
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == '0') result += address.charAt(i);
            else if (mask.charAt(i) == 'X' || mask.charAt(i) == 'x') result += "X";
            else if (mask.charAt(i) == '1') result += '1';
        }
        return result;
    }

    public static ArrayList<Long> floatingBits(String str) {
        ArrayList<Long> result = new ArrayList<>();
        int numberOfX = 0;
        int firstxLocation = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'X') numberOfX++;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'X') {
                firstxLocation = str.length() - 1 - i;
                break;
            }
        }
        String newStr = "";
        for (int t = 0; t < str.length(); t++) {
            if (str.charAt(t) == 'X') newStr += '0';
            else newStr += str.charAt(t);
        }
        int numberForAddition = (int) Math.pow(2, firstxLocation);
        int number = (int) Math.pow(2, numberOfX) / 2;
        long firstNumber = Long.parseLong(newStr, 2);
        int count = 1;
        while (count <= 2) {
            if (count == 2) {
                long num = firstNumber + numberForAddition;
                for (int k = 0; k < number; k++) {
                    result.add(num);
                    num += 1;
                }
            } else {
                long num = firstNumber;
                for (int k = 0; k < number; k++) {
                    result.add(num);
                    num += 1;
                }
            }
            count++;
        }
        return result;
    }

    public static long partTwo(ArrayList<String> data) {
        long finalSum = 0L;
        HashMap<Long, Long> map = new HashMap<>();
        String mask = "";

        for (int i = 0; i < data.size(); i++) {
            char ch = data.get(i).charAt(1);

            switch (ch) {
                case 'a': {
                    mask = "";
                    String[] temp = data.get(i).split(" ");
                    mask = temp[2];
                    break;
                }
                case 'e': {
                    String[] temp = data.get(i).split(" ");
                    int memoryLocation = Integer.parseInt(temp[0].substring(4, temp[0].length() - 1));
                    long number = Long.parseLong(temp[2]);
                    String binaryString = convertIntoBinaryString(memoryLocation);
                    String result = compareMaskAndAddress(mask, binaryString);
                    ArrayList<Long> floatingBits = floatingBits(result);
                    for (int t = 0; t < floatingBits.size(); t++) map.put(floatingBits.get(t), number);
                    break;
                }
            }
        }
        for (Map.Entry mapElement : map.entrySet()) {
            finalSum += (long) mapElement.getValue();
        }
        return finalSum;
    }


    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> data = inputExtraction(new File("resources/inputs/day_14.txt"));

        long partOne = partOne(data);
        System.out.println("Part One= " + partOne);
        long partTwo = partTwo(data);
        System.out.println("Part Two= " + partTwo);

        System.out.println("*************** Day2 original ans= 2737766154126 ***************");
    }
}
