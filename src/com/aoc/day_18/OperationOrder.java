package com.aoc.day_18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class OperationOrder {
    public static ArrayList<String> inputExtraction(File file) throws FileNotFoundException {
        ArrayList<String> result = new ArrayList<>();
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            result.add(line);
        }
        return result;
    }

    public static long findSolution(String str) {
        String rev = "";
        for (int i = str.length() - 1; i >= 0; i--) rev += str.charAt(i);

        rev = rev.replaceAll("\\s", "");
        Stack<String> s = new Stack<>();
        for (int i = 0; i < rev.length(); i++) {
            if (rev.charAt(i) != '(') s.push(rev.charAt(i) + "");
            if (rev.charAt(i) == '(') {
                long result = Long.parseLong(s.pop() + "");
                while (!s.peek().equals(")")) {
                    String ch = s.pop();
                    if (ch.equals("+")) result = result + Long.parseLong(s.pop() + "");
                    else if (ch.equals("*")) result = result * Long.parseLong(s.pop() + "");
                }
                s.pop();
                s.push(result + "");
            }
        }
        while (s.size() > 1) {
            long result = Long.parseLong(s.pop() + "");
            String ch = s.pop();
            if (ch.equals("+")) result = result + Long.parseLong(s.pop() + "");
            else if (ch.equals("*")) result = result * Long.parseLong(s.pop() + "");
            s.push(result + "");
        }
        return Long.parseLong(s.peek() + "");
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> input = inputExtraction(new File("resources/inputs/day_18.txt"));

        long ans = 0L;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            ans += findSolution(line);
        }
        System.out.println("Part one= " + ans);

    }

}
