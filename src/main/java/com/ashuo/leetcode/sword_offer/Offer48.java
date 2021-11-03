package com.ashuo.leetcode.sword_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AShuo
 * @date 2021年10月09日 15:58
 * 
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * 
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 
 * 提示：
 * s.length <= 40000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer48 {
    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("pwwkew"));
    }

    //动态规划，用哈希表保存相同元素最后一次出现的位置，当新元素出现时，判断该新元素与最后一次相同元素的间隔与当前遍历不重复的字符串的长度，
    //如果长度小于间隔，则不重复字符串加上该新元素，否则让新的不重复字符串为 该新元素与最后一次相同的元素
    //https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/solution/mian-shi-ti-48-zui-chang-bu-han-zhong-fu-zi-fu-d-9/
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int res = 0;
            int temp = 0;
            for (int i = 0; i < s.length(); i++) {
                int j = map.getOrDefault(s.charAt(i), -1);
                map.put(s.charAt(i), i);
                temp = temp < i - j ? temp + 1 : i - j;
                res = Math.max(res, temp);
            }
            return res;
        }
    }
}
