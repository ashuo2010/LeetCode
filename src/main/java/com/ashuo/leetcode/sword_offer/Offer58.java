package com.ashuo.leetcode.sword_offer;


/**
 * @author AShuo
 * @date 2021年09月07日 10:54
 * 
 * 剑指 Offer 58 - II. 左旋转字符串
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * 
 * 示例 1：
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 * 
 * 示例 2：
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 * 
 * 限制：
 * 1 <= k < s.length <= 10000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer58 {
    public static void main(String[] args) {
        System.out.println(new Solution().reverseLeftWords("Hello World ", 6));
        System.out.println(new Solution().reverseWords("hello,this is  my world  "));
    }

    static class Solution {
        //从截取的最末尾开始遍历字符串，进行取模获取字符并添加到新的字符串中
        //https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/solution/mian-shi-ti-58-ii-zuo-xuan-zhuan-zi-fu-chuan-qie-p/
        public String reverseLeftWords(String s, int n) {
            StringBuilder res = new StringBuilder();
            for (int i = n; i < s.length() + n; i++) {
                res.append(s.charAt(i % s.length()));
            }
            return res.toString();
        }

        //直接使用String.substring方法
        public String reverseLeftWords2(String s, int n) {
            return s.substring(n) + s.substring(0, n);
        }

        /**
         * @author AShuo
         * @date 2021/10/11 15:55
         * 
         * 剑指 Offer 58 - I. 翻转单词顺序
         * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
         * 
         * 示例 1：
         * 输入: "the sky is blue"
         * 输出: "blue is sky the"
         * 
         * 示例 2：
         * 输入: "  hello world!  "
         * 输出: "world! hello"
         * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
         * 
         * 示例 3：
         * 输入: "a good   example"
         * 输出: "example good a"
         * 
         * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
         * 说明：
         * 无空格字符构成一个单词。
         * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
         * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
         * 
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */

        //让i和j指向字符串末尾，i向前移动，直到遇到空格停止，此时添加(i+1,j)之间的字符，之后让i=j再重复调用次用，直到i指向第一个字符
        //https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/solution/mian-shi-ti-58-i-fan-zhuan-dan-ci-shun-xu-shuang-z/
        public String reverseWords(String s) {
            s = s.trim();
            int i = s.length() - 1;
            int j = i;
            StringBuilder sb = new StringBuilder();
            while (i >= 0) {
                while (i >= 0 && s.charAt(i) != ' ') {
                    i--;
                }
                //substring(i,j) 截取下标i~j-1之间的字符
                sb.append(s.substring(i + 1, j + 1) + " ");
                while (i >= 0 && s.charAt(i) == ' ') {
                    i--;
                }
                j = i;
            }
            return sb.toString().trim();
        }

    }
}
