package com.aoc.day_16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketTranslation {
    public static ArrayList<Integer> extractYourTickets(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        String line = null;
        ArrayList<Integer> yourTickets = new ArrayList<>();

        while (input.hasNextLine()) {
            if ((line = input.nextLine()).equals("your ticket:")) {
                String[] tickets = input.nextLine().split(",");
                for (int i = 0; i < tickets.length; i++) yourTickets.add(Integer.parseInt(tickets[i]));
            }
        }
        return yourTickets;
    }

    public static ArrayList<ArrayList<Integer>> extractNearbyTickets(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        String line = null;
        ArrayList<ArrayList<Integer>> nearbyTickets = new ArrayList<ArrayList<Integer>>();

        while (input.hasNextLine()) {
            if ((line = input.nextLine()).equals("nearby tickets:")) {
                while (input.hasNextLine()) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    String[] tickets = input.nextLine().split(",");
                    for (int i = 0; i < tickets.length; i++) temp.add(Integer.parseInt(tickets[i]));
                    nearbyTickets.add(temp);
                }
            }
        }
        return nearbyTickets;
    }

    public static ArrayList<String> validFieldsForPartOne(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        String line = null;
        ArrayList<String> validValues = new ArrayList<>();

        while (!(line = input.nextLine()).isEmpty()) {
            String[] temp = line.split(" ");
            for (int i = 0; i < temp.length; i++) {
                if (Character.isDigit(temp[i].charAt(0))) {
                    validValues.add(temp[i]);
                }
            }
        }
        return validValues;
    }

    public static long partOne(ArrayList<ArrayList<Integer>> nearbyTickets, ArrayList<String> validFeilds) {
        long result = 0L;
        for (int i = 0; i < nearbyTickets.size(); i++) {
            ArrayList<Integer> candidateArray = nearbyTickets.get(i);
            for (int j = 0; j < candidateArray.size(); j++) {
                int candidate = candidateArray.get(j);
                if (!isValidNearbyTicket(candidate, validFeilds)) result += candidate;
            }
        }
        return result;
    }

    public static long partTwo(ArrayList<ArrayList<Integer>> nearbyTickets, ArrayList<String> validFeilds, ArrayList<Integer> yourTickets) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> discardInvalidTickets = discardInvalidTickets(nearbyTickets, validFeilds);
        ArrayList<ArrayList<String>> validFeildsForPartTwo = validFieldsForPartTwo(new File("resources/inputs/day_16.txt"));
        ArrayList<String> result = findLocationsOfFields(discardInvalidTickets, validFeildsForPartTwo);

        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            String[] temp = result.get(i).split(":=");
            if (temp[0].equals("departurelocation") || temp[0].equals("departurestation") || temp[0].equals("departureplatform") || temp[0].equals("departurerack") || temp[0].equals("departuredate") || temp[0].equals("departuretime")) {
                index.add(Integer.parseInt(temp[1]));
            }
        }
        long ans = 1L;
        for (int i = 0; i < index.size(); i++) {
            ans *= (long) yourTickets.get(index.get(i));
        }
        return ans;
    }

    public static boolean isValidNearbyTicket(int candidate, ArrayList<String> validFeilds) {
        for (int i = 0; i < validFeilds.size(); i++) {
            String[] limits = validFeilds.get(i).split("-");
            int start = Integer.parseInt(limits[0]);
            int end = Integer.parseInt(limits[1]);
            if (candidate >= start && candidate <= end) return true;
        }
        return false;
    }

    public static String isNearByTicketPosCorrect(ArrayList<Integer> candidates, ArrayList<ArrayList<String>> validFeilds) {
        int i = 0;
        String result = "";
        for (i = 0; i < validFeilds.size(); i++) {
            ArrayList<String> a = validFeilds.get(i);
            String validField = "";
            for (int k = 0; k < a.size(); k++) validField += a.get(k) + " ";
            result = a.get(0);

            Pattern p = Pattern.compile("-?\\d+");
            ArrayList<String> al = new ArrayList<>();
            Matcher m = p.matcher(validField);
            while (m.find()) {
                al.add(m.group());
            }
            int start1 = Integer.parseInt(al.get(0));
            int end1 = -(Integer.parseInt(al.get(1)));
            int start2 = Integer.parseInt(al.get(2));
            int end2 = -(Integer.parseInt(al.get(3)));

            int count = 0;
            for (int t = 0; t < candidates.size(); t++) {
                int candidate = candidates.get(t);
                if (candidate >= start1 && candidate <= end1) count++;
                else if (candidate >= start2 && candidate <= end2) count++;
                else break;
            }
            if (count == candidates.size()) return result;
        }
        return "no";
    }

    public static ArrayList<ArrayList<String>> validFieldsForPartTwo(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        String line = null;
        ArrayList<ArrayList<String>> validValues = new ArrayList<ArrayList<String>>();

        while (!(line = input.nextLine()).isEmpty()) {
            String[] temp = line.split(" ");
            ArrayList<String> al = new ArrayList<>();
            for (int i = 0; i < temp.length; i++) {
                if (Character.isDigit(temp[i].charAt(0))) {
                    if (!temp[i - 1].equals("or"))
                        al.add(temp[i - 1]);
                    al.add(temp[i]);
                }
            }
            validValues.add(al);
        }
        return validValues;
    }

    public static ArrayList<ArrayList<Integer>> discardInvalidTickets(ArrayList<ArrayList<Integer>> nearbyTickets, ArrayList<String> validFields) {
        for (int i = 0; i < nearbyTickets.size(); i++) {
            ArrayList<Integer> candidates = nearbyTickets.get(i);
            for (int j = 0; j < candidates.size(); j++) {
                int candidate = candidates.get(j);
                if (!isValidNearbyTicket(candidate, validFields)) {
                    nearbyTickets.remove(i);
                    i--;
                }
            }
        }
        return nearbyTickets;
    }

    public static int findIndexInValidFields(String str, ArrayList<ArrayList<String>> validFields) {
        for (int i = 0; i < validFields.size(); i++) {
            if (validFields.get(i).get(0).equals(str)) return i;
        }
        return 0;
    }

    public static ArrayList<String> findLocationsOfFields(ArrayList<ArrayList<Integer>> nearbyTickets, ArrayList<ArrayList<String>> validFeilds) {

        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < nearbyTickets.get(0).size(); i++) {
            ArrayList<Integer> candidates = new ArrayList<>();
            int j = 0;
            for (j = 0; j < nearbyTickets.size(); j++) {
                candidates.add(nearbyTickets.get(j).get(i));
            }
            String ckeck = isNearByTicketPosCorrect(candidates, validFeilds);
            if (!ckeck.equals("no")) {
                result.add(ckeck + "=" + i);
                int index = findIndexInValidFields(ckeck, validFeilds);
                validFeilds.remove(index);
            }
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> validFeilds = validFieldsForPartOne(new File("resources/inputs/day_16.txt"));
        ArrayList<Integer> yourTickets = extractYourTickets(new File("resources/inputs/day_16.txt"));
        ArrayList<ArrayList<Integer>> nearbyTickets = extractNearbyTickets(new File("resources/inputs/day_16.txt"));

        long partOne = partOne(nearbyTickets, validFeilds);
        System.out.println("Part One= " + partOne);

        long partTwo = partTwo(nearbyTickets, validFeilds, yourTickets);
        System.out.println("Part Two = " + partTwo);


    }
}
