package com.aoc.day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ValidPassports {
    public static HashMap createHashmap(ArrayList<String> lines) {
        Object[] arr = lines.toArray();

        String[] str = new String[arr.length];
        System.arraycopy(arr, 0, str, 0, arr.length);

        String newstr = Arrays.toString(str);
        String stringConversionOfArraylist = newstr.replace(",", "");
        stringConversionOfArraylist = stringConversionOfArraylist.replace("[", "");
        stringConversionOfArraylist = stringConversionOfArraylist.replace("]", "");

        String[] passportElements = stringConversionOfArraylist.split(" ");

        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < passportElements.length; i++) {
            String[] elements = passportElements[i].split(":");
            if (elements[0] == "cid") continue;

            if (map.containsKey(elements[0])) {

            } else {
                map.put(elements[0], elements[1]);
            }
        }
        return map;
    }

    public static boolean isValidBirthYear(Object obj) {
        String year = obj + "";
        if (year.length() == 4) {
            int birthyear = Integer.parseInt(year);
            if (birthyear >= 1920 && birthyear <= 2002) return true;
        }
        return false;
    }

    public static boolean isValidIssueYear(Object obj) {
        String year = obj + "";
        if (year.length() == 4) {
            int issueYear = Integer.parseInt(year);
            if (issueYear >= 2010 && issueYear <= 2020) return true;
        }
        return false;
    }

    public static boolean isValidExpirationYear(Object obj) {
        String year = obj + "";
        if (year.length() == 4) {
            int expirationYear = Integer.parseInt(year);
            if (expirationYear >= 2020 && expirationYear <= 2030) return true;
        }
        return false;
    }

    public static boolean isValidHeight(Object obj) {
        String height = obj + "";

        if (height.charAt(height.length() - 2) == 'c' && height.charAt(height.length() - 1) == 'm') {
            int number = Integer.parseInt(height.substring(0, height.length() - 2));
            if (number >= 150 && number <= 193) return true;
        }

        if (height.charAt(height.length() - 2) == 'i' || height.charAt(height.length() - 2) == 'n') {
            int number = Integer.parseInt(height.substring(0, height.length() - 2));
            if (number >= 59 && number <= 76) return true;
        }
        return false;
    }

    public static boolean isValidHairColor(Object obj) {

        String hairColor = obj + "";

        if (hairColor.length() == 7) {

            if (hairColor.charAt(0) == '#') {
                hairColor = hairColor.substring(1);

                if (hairColor.length() == 6) {
                    int flag = 0;
                    for (int i = 0; i < hairColor.length(); i++) {
                        if (hairColor.charAt(i) > 'f') {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) return true;

                } else return false;


            } else return false;

        }
        return false;
    }


    public static boolean isValidEyeColor(Object obj) {
        String eyeColor = obj + "";

        if (eyeColor.equals("amb") || eyeColor.equals("blu") || eyeColor.equals("brn") || eyeColor.equals("gry") || eyeColor.equals("grn") || eyeColor.equals("hzl") || eyeColor.equals("oth"))
            return true;

        return false;
    }

    public static boolean isValidPid(Object obj) {
        String passportID = obj + "";

        if (passportID.length() == 9) return true;

        return false;
    }


    public static boolean isValidForPartTwo(ArrayList<String> lines) {
        HashMap<String, String> map = createHashmap(lines);

        int count = 0;

        Set s = map.entrySet();
        Iterator itr = s.iterator();

        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();


            if (entry.getKey().equals("byr") && isValidBirthYear(entry.getValue())) count++;
            if (entry.getKey().equals("iyr") && isValidIssueYear(entry.getValue())) count++;
            if (entry.getKey().equals("eyr") && isValidExpirationYear(entry.getValue())) count++;
            if (entry.getKey().equals("hgt") && isValidHeight(entry.getValue())) count++;
            if (entry.getKey().equals("hcl") && isValidHairColor(entry.getValue())) count++;
            if (entry.getKey().equals("ecl") && isValidEyeColor(entry.getValue())) count++;
            if (entry.getKey().equals("pid") && isValidPid(entry.getValue())) count++;

        }
        if (count == 7)
            return true;
        return false;
    }


    public static boolean isValidForPartOne(ArrayList<String> lines) {

        HashMap<String, String> map = createHashmap(lines);

        if (map.containsKey("byr") == false || map.containsKey("iyr") == false || map.containsKey("eyr") == false || map.containsKey("hgt") == false || map.containsKey("hcl") == false || map.containsKey("ecl") == false || map.containsKey("pid") == false)
            return false;

        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {

        int countForPartOne = 0;
        int countForPartTwo = 0;

        Scanner input = new Scanner(new File("resources/inputs/day_4.txt"));

        while (input.hasNext()) {
            ArrayList<String> lines = new ArrayList<>();
            String line = null;

            while (!(line = input.nextLine()).isEmpty()) {
                lines.add(line);
            }

            if (isValidForPartOne(lines) == true) {
                countForPartOne++;
                if (isValidForPartTwo(lines) == true) countForPartTwo++;
            }
        }

        System.out.println("Valid Passports=" + countForPartOne);
        System.out.println("Valid Passports with validation =" + countForPartTwo);
    }
}
