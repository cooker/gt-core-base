package com.github.cooker;

/**
 * grant
 * 6/5/2020 2:22 下午
 * 描述：
 */
public class Solution {
    public int[] swapNumbers(int[] numbers) {
        return new int[]{numbers[1], numbers[0]};
    }

    public static void main(String[] args) {
        int[] aa = new int[]{1,2};
        System.out.println(aa[0]^aa[1]);
        System.out.println(aa[1]^aa[1]);
    }
}
