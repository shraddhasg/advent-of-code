package com.aoc.day_2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ValidPassword {

    public static boolean isValid(String limit, String ch, String data) {

        String[] startAndEnd = limit.split("-");
        int start = Integer.parseInt(startAndEnd[0]);
        int end = Integer.parseInt(startAndEnd[1]);
        // System.out.println(start + "----" + end);

        char checkChar = ch.charAt(0);

        int count = 0;

        for (int i = 0; i < data.length(); i++) {
            // count = 0;
            if (data.charAt(i) == checkChar)
                count++;
        }

        if (count >= start && count <= end)
            return true;
        return false;

    }

    public static int validPasswords(List<List<String>> listwords) {
        int count = 0;
        for (int i = 0; i < listwords.size(); i++) {
            List<String> list = listwords.get(i);
            String limit = list.get(0);
            String ch = list.get(1);
            String data = list.get(2);
            // System.out.println(limit + "-" + ch + "-" + data);
            if (isValid(limit, ch, data))
                count++;
        }
        return count;
    }

    public static void main(String[] args) throws Exception {

        // File file = new File("input.txt");
        // BufferedReader br = new BufferedReader(new FileReader(file));
        // String st;
        // while ((st = br.readLine()) != null) {
        // System.out.println(st);
        // }

        Scanner in = new Scanner(new File("resources/inputs/day_2.txt"));
        List<List<String>> listwords = new ArrayList<>();

        while (in.hasNext()) {
            listwords.add(Arrays.asList(in.nextLine().split(" ")));
        }

        // System.out.println(listwords);

        int ans = validPasswords(listwords);
        System.out.println(ans);
    }
}

