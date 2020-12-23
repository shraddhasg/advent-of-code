package com.aoc.day_23;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CrabCups {
    public static class Node {
        public Long value;
        public Node next;
        public Node previous;

        public Node(Long value) {
            this.value = value;
        }
    }

    ;

    public static Node move(Node head, Map<Long, Node> m) {
        Node current = head;
        Node first = current.next;
        Node last = current.next.next.next;
        current.next = last.next;

        long target = current.value - 1;
        while (target < 1 || target == first.value ||
                target == first.next.value || target == last.value) {
            target = (target < 1) ? 1000000 : target - 1;
        }
        Node targetNode = m.get(target);
        last.next = targetNode.next;
        targetNode.next = first;

        return current.next;
    }

    public static long partTwo(String input) {
        long highest = Long.parseLong(input.substring(0, 1));
        Map<Long, Node> m = new HashMap<Long, Node>();

        Node head = null;
        Node current = head;

        for (String s : input.split("")) {
            long value = Long.parseLong(s);
            if (current == null) {
                current = new Node(value);
                head = current;
            } else {
                current.next = new Node(value);
                current = current.next;
            }
            m.put(value, current);
            current.next = head;

            if (value > highest) highest = value;
        }


        for (long i = highest + 1; i <= 1000000; ++i) {
            current.next = new Node(i);
            current = current.next;
            m.put(i, current);
        }
        current.next = head;

        long move = 0;
        current = head;
        while (move < 10000000) {
            current = move(current, m);
            ++move;
        }
        return m.get(1L).next.value * m.get(1L).next.next.value;

    }

    public static ArrayList<Integer> inputExtraction(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        ArrayList<Integer> al = new ArrayList<>();
        while (input.hasNextLine()) {
            String[] data = input.nextLine().split("");
            for (int i = 0; i < data.length; i++) al.add(Integer.parseInt(data[i]));
        }
        return al;
    }

    public static int findDestination(int currentElement, ArrayList<Integer> pickUp, ArrayList<Integer> data) {
        int destination = currentElement - 1;
        if (!pickUp.contains(destination)) return destination;
        else {
            while (destination > 1) {
                destination -= 1;
                if (!pickUp.contains(destination)) return destination;
            }
        }
        int max = data.get(0);
        for (int i = 1; i < data.size(); i++) max = Math.max(max, data.get(i));

        return max;
    }

    public static ArrayList<Integer> getDataOfPartOne(ArrayList<Integer> data, int currentIndex) {
        int currentElement = data.get(currentIndex);
//        System.out.println("Current index= " + currentElement);
        ArrayList<Integer> pickUp = new ArrayList<>();
        for (int i = 1; i <= 3; i++) pickUp.add(data.get((currentIndex + i) % data.size()));
//        System.out.println("pickup= " + pickUp);
        for (int i : pickUp) data.remove((Integer) i);
//        System.out.println("after renoving = " + data);

        int destination = findDestination(currentElement, pickUp, data);
//        System.out.println("Destination =" + destination);

        int index = data.indexOf(destination);
        for (int i : pickUp) data.add(++index, i);

        return data;
    }

    public static String partOne(ArrayList<Integer> input) {
        int index = 0;
        String before = "", after = "";
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) == 1) {
                index = i;
                break;
            }
        }
        for (int i = 0; i < input.size(); i++) {
            if (i < index) before += input.get(i) + "";
            else if (i > index) after += input.get(i) + "";
        }
        return before + after;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> input = inputExtraction(new File("resources/inputs/day_23.txt"));
        for (int i = 0; i < 10; i++) input = getDataOfPartOne(input, i % input.size());
        String partOne = partOne(input);
        System.out.println("Part One= " + partOne);

        String testInput = "784235916";
        String inputForTwo = testInput;

        long partTwo = partTwo(inputForTwo);
        System.out.println("Part Two= " + partTwo);
    }
}