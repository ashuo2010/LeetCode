package com.ashuo.leetcode.sword_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AShuo
 * @date 2021年10月26日 11:07
 * 
 * 剑指 Offer 20. 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 数值（按顺序）可以分成以下几个部分：
 * 若干空格
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 
 * 小数（按顺序）可以分成以下几个部分：
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 
 * 整数（按顺序）可以分成以下几个部分：
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 
 * 部分数值列举如下：
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 部分非数值列举如下：
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 * 
 * 示例 1：
 * 输入：s = "0"
 * 输出：true
 * 
 * 示例 2：
 * 输入：s = "e"
 * 输出：false
 * 
 * 示例 3：
 * 输入：s = "."
 * 输出：false
 * 
 * 示例 4：
 * 输入：s = "    .1  "
 * 输出：true
 * 
 * 提示：
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer20 {
    public static void main(String[] args) {
        System.out.println(new Solution().isNumber("5.2e-3 "));
    }

    //有限状态机：
    //状态转移表、当前状态、状态转移循环
    //https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/solution/mian-shi-ti-20-biao-shi-shu-zhi-de-zi-fu-chuan-y-2/
    static class Solution {
        public boolean isNumber(String s) {
            Map[] states = {
                    new HashMap<Character, Integer>() {{
                        put(' ', 0);
                        put('s', 1);
                        put('d', 2);
                        put('.', 4);
                    }},
                    new HashMap<Character, Integer>() {{
                        put('d', 2);
                        put('.', 4);
                    }},
                    new HashMap<Character, Integer>() {{
                        put('d', 2);
                        put('.', 3);
                        put('e', 5);
                        put(' ', 8);
                    }},
                    new HashMap<Character, Integer>() {{
                        put('d', 3);
                        put('e', 5);
                        put(' ', 8);
                    }},
                    new HashMap<Character, Integer>() {{
                        put('d', 3);
                    }},
                    new HashMap<Character, Integer>() {{
                        put('s', 6);
                        put('d', 7);
                    }},
                    new HashMap<Character, Integer>() {{
                        put('d', 7);
                    }},
                    new HashMap<Character, Integer>() {{
                        put('d', 7);
                        put(' ', 8);
                    }},
                    new HashMap<Character, Integer>() {{
                        put(' ', 8);
                    }},
            };
            int p = 0;
            char t;
            //遍历字符串
            for (char c : s.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    t = 'd';
                } else if (c == '+' || c == '-') {
                    t = 's';
                } else if (c == 'e' || c == 'E') {
                    t = 'e';
                } else if (c == '.' || c == ' ') {
                    t = c;
                } else {
                    t = '?';
                }
                //如果当前遍历的字符在map中不存在，则不可能是数值
                if (!states[p].containsKey(t)) {
                    return false;
                }
                //0->map(0)=a->map(a)=b->b
                p = (int) states[p].get(t);
            }
            return p == 2 || p == 3 || p == 7 || p == 8;
        }
    }
}
