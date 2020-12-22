package com.aoc.day_22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class CrabCombat {

    public static ArrayList<ArrayList<Integer>> inputExtraction(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        String line = "";
        ArrayList<Integer> player1 = new ArrayList<>();
        ArrayList<Integer> player2 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();

        input.nextLine();
        while (!(line = input.nextLine()).isEmpty()) {
            player1.add(Integer.parseInt(line));
        }
        input.nextLine();
        while (input.hasNextLine()) player2.add(Integer.parseInt(input.nextLine()));

        data.add(player1);
        data.add(player2);
        return data;
    }

    public static int playGame(List<Integer> p1d, List<Integer> p2d) {
        Set<String> states = new HashSet<>();
        while (p1d.size() > 0 && p2d.size() > 0) {
            String state = getState(p1d, p2d);
            if (!states.add(state)) {
                return 1;
            }

            int a = p1d.remove(0);
            int b = p2d.remove(0);

            int winner = 0;
            if (a <= p1d.size() && b <= p2d.size()) {
                winner = playGame(new LinkedList<>(p1d.subList(0, a)), new LinkedList<>(p2d.subList(0, b)));
            } else {
                winner = (a > b) ? 1 : 2;
            }

            if (winner == 1) {
                p1d.add(a);
                p1d.add(b);
            } else {
                p2d.add(b);
                p2d.add(a);
            }
        }

        int rv = 0;
        if (p1d.size() == 0) {
            rv = 2;
        } else {
            rv = 1;
        }

        return rv;
    }

    private static String getState(List<Integer> p1d, List<Integer> p2d) {
        return p1d.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(",")) + "@"
                + p2d.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(","));
    }

    public static int partOne(ArrayList<Integer> player1, ArrayList<Integer> player2) {
        while (player1.size() > 0 && player2.size() > 0) {
            int p1 = player1.remove(0);
            int p2 = player2.remove(0);
            if (p1 > p2) {
                player1.add(p1);
                player1.add(p2);
            } else if (p2 > p1) {
                player2.add(p2);
                player2.add(p1);
            }
        }

        int ans = 0;
        if (player1.size() > player2.size()) {
            for (int i = 0, j = player1.size(); i < player1.size() && j > 0; i++, j--) ans = ans + (player1.get(i) * j);
        } else {
            for (int i = 0, j = player2.size(); i < player2.size() && j > 0; i++, j--) ans = ans + (player2.get(i) * j);
        }
        return ans;
    }

    public static long partTwo(List<Integer> p1d, List<Integer> p2d) {
        int winner = playGame(p1d, p2d);

        List<Integer> d = null;
        if (winner == 1) {
            d = p1d;
        } else {
            d = p2d;
        }

        long tot = 0;
        int s = d.size();
        for (int i = 0; i < d.size(); i++) {
            tot += d.get(i) * (s - i);
        }

//        System.out.println(d + "***************");
//        System.out.println(tot);
        return tot;
    }

    public static ArrayList<Integer> getArrayList(ArrayList<Integer> data) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) al.add(data.get(i));
        return al;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> data = inputExtraction(new File("resources/inputs/day_22.txt"));
        ArrayList<Integer> player1 = getArrayList(data.get(0));
        ArrayList<Integer> player2 = getArrayList(data.get(1));
        List<Integer> p1d = getArrayList(data.get(0));
        List<Integer> p2d = getArrayList(data.get(1));

        int partOne = partOne(player1, player2);
        System.out.println("Part One= " + partOne);
        long partTwo = partTwo(p1d, p2d);
        System.out.println("Part Two= " + partTwo);
    }
}

