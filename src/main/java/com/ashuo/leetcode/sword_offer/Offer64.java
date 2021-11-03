package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月19日 14:11
 * 
 * 剑指 Offer 64. 求1+2+…+n
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * 
 * 示例 1：
 * 输入: n = 3
 * 输出: 6
 * 
 * 示例 2：
 * 输入: n = 9
 * 输出: 45
 * 
 * 限制：
 * 1 <= n <= 10000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qiu-12n-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer64 {
    public static void main(String[] args) {
        System.out.println(new Solution().sumNums(9));
    }

    //使用递归和短路运算符结束运算
    //https://leetcode-cn.com/problems/qiu-12n-lcof/submissions/
    static class Solution {
        public int sumNums(int n) {
            boolean b = (n > 0) && (n += sumNums(n - 1)) > 0;
            return n;
        }
    }
}
