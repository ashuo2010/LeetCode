package com.ashuo.leetcode.sword_offer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author AShuo
 * @date 2021年09月24日 9:55
 * 
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * 
 * 示例 1:
 * 输入：s = "abaccdeff"
 * 输出：'b'
 * 
 * 示例 2:
 * 输入：s = ""
 * 输出：' '
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer50 {
    public static void main(String[] args) {
        String str = "leetcode";
        System.out.println(new Solution().firstUniqChar(str));
    }

    //将字符添加到有序的LinkedHashMap中，键为该字符，值为“是否存在该键”
    //https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/solution/mian-shi-ti-50-di-yi-ge-zhi-chu-xian-yi-ci-de-zi-3/
    static class Solution {
        public char firstUniqChar(String s) {
            Map<Character, Boolean> dic = new LinkedHashMap<>();
            char[] sc = s.toCharArray();
            for (char c : sc) {
                dic.put(c, !dic.containsKey(c));
            }
            for (Map.Entry<Character, Boolean> d : dic.entrySet()) {
                if (d.getValue()) {
                    return d.getKey();
                }
            }
            return ' ';
        }
    }
}
