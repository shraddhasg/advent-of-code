package com.aoc.day_2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ValidPasswordsWithGivenPossition {
    public static boolean isValid(String limit, String ch, String data) {

        String[] startAndEnd = limit.split("-");
        int start = Integer.parseInt(startAndEnd[0]);
        int end = Integer.parseInt(startAndEnd[1]);

        char checkChar = ch.charAt(0);

        if (data.charAt(start - 1) != data.charAt(end - 1)) {
            if (data.charAt(start - 1) == checkChar || data.charAt(end - 1) == checkChar)
                return true;
        }
        return false;

    }

    public static int validPasswords(List<List<String>> listwords) {
        int count = 0;
        for (int i = 0; i < listwords.size(); i++) {
            List<String> list = listwords.get(i);
            String limit = list.get(0);
            String ch = list.get(1);
            String data = list.get(2);
            if (isValid(limit, ch, data))
                count++;
        }
        return count;
    }

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("resources/inputs/day_2.txt"));
        List<List<String>> listwords = new ArrayList<>();

        while (in.hasNext()) {
            listwords.add(Arrays.asList(in.nextLine().split(" ")));
        }

        int ans = validPasswords(listwords);
        System.out.println(ans);
    }

}
