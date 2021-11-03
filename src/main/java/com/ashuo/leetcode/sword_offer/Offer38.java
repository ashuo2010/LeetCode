package com.ashuo.leetcode.sword_offer;

import java.util.*;

/**
 * @author AShuo
 * @date 2021年10月28日 17:43
 * 
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * 
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * 
 * 限制：
 * 1 <= s 的长度 <= 8
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer38 {


    public static void main(String[] args) {
        Arrays.stream(new Solution().permutation("abc")).forEach(System.out::println);
    }

    static class Solution {
        //首先将字符串转为字符数组，再将数组进行DFS递归，每次递归将当前的首元素与后面的元素进行位置交换，之后索引位置加一再进行递归，直到递归到数组最后一位元素时就是一次字符排列方案，将字符数组添加到列表中
        //在递归的过程中创建一个Set集合，遇到重复的字符时剪枝跳过本次循环
        //在递归到底进行回溯时，需要将数组排序复位为原本的排序
        //https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/solution/mian-shi-ti-38-zi-fu-chuan-de-pai-lie-hui-su-fa-by/
        char[] chars;
        List<String> list = new ArrayList<>();

        public String[] permutation(String s) {
            chars = s.toCharArray();
            //从第一个元素开始递归
            dfs(0);
            return list.toArray(new String[list.size()]);
        }

        public void dfs(int index) {
            //当遍历到了数组最后一个元素时，添加一次方案
            if (index == chars.length - 1) {
                list.add(String.valueOf(chars));
                return;
            }
            Set<Character> set = new HashSet<>();
            for (int i = index; i < chars.length; i++) {
                //使用Set集合剪枝，遇到已经遍历过的字符直接跳过
                if (set.contains(chars[i])) {
                    continue;
                }
                set.add(chars[i]);

                //交换当前index和i的位置上的元素
                swap(index, i);
                //索引加一进行下一个字符的交换
                dfs(index + 1);
                //回溯时恢复素组本来的排序
                swap(i, index);
            }
        }

        public void swap(int a, int b) {
            char temp = chars[a];
            chars[a] = chars[b];
            chars[b] = temp;
        }
    }
}
