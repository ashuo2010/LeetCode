package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年09月07日 10:43
 * 
 * 剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 * 
 * 限制：
 * 0 <= s 的长度 <= 10000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer05 {
    public static void main(String[] args) {
        System.out.println(new Solution().replaceSpace("We are happy."));
    }

    //字符串先转成数组，遍历数组找到空格并进行替换
    //https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/solution/mian-shi-ti-05-ti-huan-kong-ge-ji-jian-qing-xi-tu-/
    static class Solution {
        public String replaceSpace(String s) {
            StringBuilder stringBuilder = new StringBuilder();
            char[] chars = s.toCharArray();
            for (char c : chars) {
                if (c == ' ') {
                    stringBuilder.append("%20");
                } else {
                    stringBuilder.append(c);
                }
            }
            return stringBuilder.toString();
        }
    }
}
