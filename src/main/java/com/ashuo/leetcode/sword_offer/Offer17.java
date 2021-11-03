package com.ashuo.leetcode.sword_offer;

import java.util.Arrays;

/**
 * @author AShuo
 * @date 2021年11月01日 10:32
 * 
 * 剑指 Offer 17. 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * 
 * 示例 1:
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 * 
 * 说明：
 * 用返回一个整数列表来代替打印
 * n 为正整数
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer17 {
    public static void main(String[] args) {
        Arrays.stream(new Solution().printNumbers(1)).forEach(System.out::print);
    }

    static class Solution {
        char[] loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder res = new StringBuilder();

        public void dfs(int x, int n, char[] chars) {
            //当数组长度不满足时，递归遍历
            if (x == n) {
                res.append(String.valueOf(chars)).append(",");
                return;
            }

            for (char c : loop) {
                chars[x] = c;
                dfs(x + 1, n, chars);
            }

        }

        //创建一个长度为n的数组，从数组第一位开始遍历到n的位置，每一个位置递归从0到9，当递归到n位置时，记录本次递归得到的值
        //在处理好之后对对应字符串进行处理即可
        //https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/solution/mian-shi-ti-17-da-yin-cong-1-dao-zui-da-de-n-wei-2/
        public int[] printNumbers(int n) {
            //递归添加数组
            dfs(0, n, new char[n]);
            //除去首个为0的元素
            String[] splits = res.substring(n + 1, res.length() - 1).split(",");
            return Arrays.stream(splits).mapToInt(Integer::valueOf).toArray();

        /* 没有考虑大数的解法
        int x = (int) (Math.pow(10, n) - 1);
        int num[] = new int[x];
        for (int i = 0; i < x; i++) {
            num[i] = i+1;
        }
        return num;*/
        }
    }
}
