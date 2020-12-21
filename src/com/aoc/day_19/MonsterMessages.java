package com.aoc.day_19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MonsterMessages {
    public static ArrayList<String> inputExtractionOfRules(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        ArrayList<String> al = new ArrayList<>();
        String line = "";
        while (!(line = input.nextLine()).isEmpty()) {
            al.add(line);
        }
        return al;
    }

    public static ArrayList<String> inputExtractionOfData(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        ArrayList<String> al = new ArrayList<>();
        String line = "";
        while (!(line = input.nextLine()).isEmpty()) {
        }

        while (input.hasNextLine()) {
            line = input.nextLine();
            if (!Character.isDigit(line.charAt(0))) al.add(line);
        }
        return al;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> EextractedRules = inputExtractionOfRules(new File("resources/inputs/day_19.txt"));
        ArrayList<String> data = inputExtractionOfData(new File("resources/inputs/day_19.txt"));

        ArrayList<ArrayList<String>> rules = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < EextractedRules.size(); i++) {
            String[] temp = EextractedRules.get(i).split(": ");
            ArrayList<String> a = new ArrayList<>();
            for (int j = 0; j < temp.length; j++) a.add(temp[j]);
            rules.add(a);
        }

        for (int i = 0; i < rules.size(); i++) {
            ArrayList<String> al = rules.get(i);
            String str = al.get(1);
            String[] temp = str.split(" ");
            for (int j = 0; j < temp.length; j++) {
                if (temp[j].equals("92")) temp[j] = " b ";
                if (temp[j].equals("110")) temp[j] = " a ";
            }

            String s = "";
            for (int j = 0; j < temp.length; j++) {
                s += temp[j];
            }
            al.set(1, s);
            rules.set(i, al);
        }
        System.out.println(rules);
    }
}
