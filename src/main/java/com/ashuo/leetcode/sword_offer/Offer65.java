package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月21日 10:14
 * 
 * 剑指 Offer 65. 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 * 
 * 示例:
 * 输入: a = 1, b = 1
 * 输出: 2
 * 
 * 提示：
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer65 {
    public static void main(String[] args) {
        System.out.println(new Solution().add(3, 4));
    }

    //sum=非进位和+进位 = a+b = n+c = a^b+(a&b)<< 1;
    //https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/solution/mian-shi-ti-65-bu-yong-jia-jian-cheng-chu-zuo-ji-7/
    static class Solution {
        public int add(int a, int b) {
            if (b == 0) {
                return a;
            }
            return add(a ^ b, (a & b) << 1);
        }
    }
}
