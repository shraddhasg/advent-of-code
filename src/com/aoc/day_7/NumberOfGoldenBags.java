package com.aoc.day_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NumberOfGoldenBags {
    public static int count = 0;
//    public static int ans = 0;

    public static ArrayList<ArrayList<String>> inputExtraction(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);

        ArrayList<ArrayList<String>> bags = new ArrayList<ArrayList<String>>();

        while (input.hasNext()) {
            String line = input.nextLine();

            String[] wordsOfLine = line.split(" ");

            ArrayList<String> bag = new ArrayList<>();
            bag.add(wordsOfLine[0] + wordsOfLine[1]);

            for (int i = 2; i < wordsOfLine.length - 2; i++) {
                if (Character.isDigit(wordsOfLine[i].charAt(0))) {
                    bag.add(wordsOfLine[i + 1] + wordsOfLine[i + 2]);
                }
            }
            bags.add(bag);
        }
        return bags;
    }

    public static ArrayList<ArrayList<String>> removeBags(ArrayList<ArrayList<String>> bags, ArrayList<String> directHolderOfBag) {
        for (int i = 0; i < bags.size(); i++) {
            for (int j = 0; j < directHolderOfBag.size(); j++) {
                if (bags.get(i).size() > 0 && bags.get(i).get(0).equals(directHolderOfBag.get(j))) {
                    bags.remove(i);
                    i--;
                    break;
                }
            }
        }
        return bags;
    }

    public static ArrayList<String> holderBags(ArrayList<ArrayList<String>> bags, ArrayList<String> bag) {

        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < bags.size(); i++) {
            if (bags.get(i).size() > 0) {
                ArrayList<String> list = new ArrayList<>(bags.get(i));
                String temp = bags.get(i).get(0);

                if (temp.equals("shinygold") != true) {
                    bags.get(i).remove(0);

                    for (int j = 0; j < bag.size(); j++) {
                        if (bags.get(i).contains(bag.get(j))) {
                            count++;
                            al.add(temp);
                            break;
                        }
                    }
                }

                bags.remove(i);
                bags.add(i, list);
            }
        }
        return al;
    }

    public static void partOne() throws FileNotFoundException {
        ArrayList<ArrayList<String>> bags = inputExtraction(new File("resources/inputs/day_7.txt"));

        ArrayList<String> bag = new ArrayList<>();
        bag.add("shinygold");

        while (bag.size() > 0) {
            ArrayList<String> al = holderBags(bags, bag);
            bag.clear();
            bag.addAll(al);
            bags = removeBags(bags, bag);
        }

        System.out.println("Part one=" + count);

    }

    public static ArrayList<ArrayList<String>> inputExtractionWithNumber(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);

        ArrayList<ArrayList<String>> bags = new ArrayList<ArrayList<String>>();

        while (input.hasNext()) {
            String line = input.nextLine();

            String[] wordsOfLine = line.split(" ");

            ArrayList<String> bag = new ArrayList<>();
            bag.add(wordsOfLine[0] + wordsOfLine[1]);

            for (int i = 2; i < wordsOfLine.length - 2; i++) {
                if (Character.isDigit(wordsOfLine[i].charAt(0))) {
                    bag.add(wordsOfLine[i + 1] + wordsOfLine[i + 2] + "-" + wordsOfLine[i]);
                }
            }
            bags.add(bag);
        }
        return bags;
    }

    public static ArrayList<String> findBag(ArrayList<ArrayList<String>> bags, String color) {
        for (int i = 0; i < bags.size(); i++) {
            if (bags.get(i).get(0).equals(color)) {
                return bags.get(i);
            }
        }
        return bags.get(0);
    }


    public static void partTow() throws FileNotFoundException {
        ArrayList<ArrayList<String>> bags = inputExtractionWithNumber(new File("resources/inputs/day_7.txt"));
        int partTwo = finalPartTwo(bags, "shinygold");
        System.out.println("Part two=" + partTwo);
    }

    public static int finalPartTwo(ArrayList<ArrayList<String>> bags, String color) {
        ArrayList<String> innerBags = findBag(bags, color);
        int ans = 0;
        if (innerBags.size() <= 1) {
            return 0;
        }
        innerBags.remove(0);
        HashMap<String, Integer> map = new HashMap<>();

        for (String innerBag : innerBags) {
            String[] split = innerBag.split("-");
            String c = split[0];
            int i = Integer.parseInt(split[1]);
            ans += i;
            map.put(c, i);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ans += entry.getValue() * finalPartTwo(bags, entry.getKey());
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        partOne();
        partTow();
    }
}

