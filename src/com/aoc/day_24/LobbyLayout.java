package com.aoc.day_24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class LobbyLayout {
    public static int[][] lobby = new int[51][51];

    public static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) System.out.print(matrix[i][j]);
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        for (int[] row : lobby)
            Arrays.fill(row, 2);

        int count = 0;

        Scanner input = new Scanner(new File("resources/inputs/day_24.txt"));
        while (input.hasNextLine()) {
            int startX = 26;
            int startY = 26;
            String south = "", north = "";

            String line = input.nextLine();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'e') startY++;
                else if (line.charAt(i) == 'w') startY--;

                else if (line.charAt(i) == 's') {
                    south = line.charAt(i) + line.charAt(i + 1) + "";
                    i++;
                } else if (line.charAt(i) == 'n') {
                    north = line.charAt(i) + line.charAt(i + 1) + "";
                    i++;
                }
                if (south.equals("se")) {
                    south = "";
                    startX++;
                    startY++;
                }
                if (south.equals("sw")) {
                    south = "";
                    startX++;
                    startY--;
                }
                if (north.equals("ne")) {
                    north = "";
                    startX++;
                    startY--;
                }
                if (north.equals("nw")) {
                    south = "";
                    startX--;
                    startY--;
                }
            }
            if (lobby[startX][startY] == 1 || lobby[startX][startY] == 2) {
                lobby[startX][startY] = 0;
//                count++;
            } else if (lobby[startX][startY] == 0 || lobby[startX][startY] == 2) {
                lobby[startX][startY] = 1;
//                count++;
            }
        }
        print(lobby);
        System.out.println("Count= " + count);
        count = 0;

        for (int i = 0; i < lobby.length; i++) {
            for (int j = 0; j < lobby[i].length; j++) {
                if (lobby[i][j] == 0) count++;
            }
        }
        System.out.println(count);
    }

}


//
//DIRECTIONS = {"e": [2, 0], "se": [1, -1], "sw": [-1, -1],
//        "w": [-2, 0], "nw": [-1, 1], "ne": [1, 1]}
//
//
//        def readData(inpath="input.txt"):
//        with open(inpath, "r") as infile:
//        paths = []
//        for line in infile.read().splitlines():
//        instructions = []
//        curr = ""
//        for i in line:
//        if curr + i in DIRECTIONS.keys():
//        instructions.append(curr + i)
//        curr = ""
//        else:
//        curr = i
//        paths.append(instructions)
//        return paths
//
//
//        def initBoard(paths):
//        flipped = set()
//        for path in paths:
//        coords = [0, 0]
//        for inst in path:
//        curr = DIRECTIONS[inst]
//        for i in range(len(coords)):
//        coords[i] += curr[i]
//        tuped = tuple(coords)
//        if tuped not in flipped:
//        flipped.add(tuped)
//        else:
//        flipped.remove(tuped)
//        return flipped
//
//
//        def part1(data):
//        return len(initBoard(data))
//
//
//        def part2(data):
//        black = initBoard(data)
//        for _ in range(100):
//        neighbored = {}
//        for tile in black:
//        if tile not in neighbored:
//        neighbored[tile] = 0
//        for token in DIRECTIONS.keys():
//        curr = list(tile)
//        for j in range(len(curr)):
//        curr[j] += DIRECTIONS[token][j]
//        curr = tuple(curr)
//        if curr not in neighbored.keys():
//        neighbored[curr] = 1
//        else:
//        neighbored[curr] += 1
//        newBlack = set()
//        for tile in neighbored:
//        if (tile in black and neighbored[tile] in [1, 2]) or \
//        (tile not in black and neighbored[tile] == 2):
//        newBlack.add(tile)
//        black = newBlack
//        return len(black)
//
//
//        def main():
//        data = readData()
//        print(part1(data))
//        print(part2(data))
//        # Part 2: {part2(data)}")
//
//
//        main()