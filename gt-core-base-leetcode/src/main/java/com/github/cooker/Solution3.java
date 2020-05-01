package com.github.cooker;

import java.util.HashSet;
import java.util.Set;

/**
 * grant
 * 23/4/2020 9:24 上午
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution3 {

    public int lengthOfLongestSubstring(String s) {
        Set<String> sets = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char ch : chars){
            if (!sb.toString().isEmpty()){
                if (sets.contains(ch + "")){

                }else {
                    sets.add(ch + "");
                    sb.append(ch + "");
                }
            }else {
                sb.append(ch + "");
            }

        }

        int max = 0;
        for (String str : sets){
            max = Math.max(max, str.length());
        }

        return max;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution3.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution3.lengthOfLongestSubstring("pwwkew"));
    }
}
