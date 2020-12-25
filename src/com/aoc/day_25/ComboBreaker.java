package com.aoc.day_25;

public class ComboBreaker {
    public static long partOne(long input1, long input2) {
        int i = 1, j = 1;
        int count1 = 0, count2 = 0;

        while (i != input1) {
            i *= 7;
            i %= 20201227;
            count1 += 1;
        }
        while (j != input2) {
            j *= 7;
            j %= 20201227;
            count2 += 1;
        }
        long ans = 1L;
        for (int k = 0; k < count2; k++) {
            ans *= input1;
            ans %= 20201227;
        }
        return ans;
    }

    public static void main(String[] args) {
        long input1 = 335121L;
        long input2 = 363891L;

        long partOne = partOne(input1, input2);
        System.out.println("Part One= " + partOne);
    }
}
