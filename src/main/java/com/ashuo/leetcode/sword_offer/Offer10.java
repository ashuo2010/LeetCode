package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年09月27日 14:04
 * 
 * 剑指 Offer 10- I. 斐波那契数列
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * 
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 * 
 * 提示：
 * 0 <= n <= 100
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer10 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            //0、1、1、2、3、5、8、13、21、34
            System.out.println(new Solution().fib(i));
        }
        System.out.println(new Solution().numWays(5));
    }

    //动态规划
    //状态定义： 设 dp 为一维数组，其中 dp[i] 的值代表 斐波那契数列第 i 个数字 。
    //转移方程： dp[i+1]=dp[i]+dp[i−1] ，即对应数列定义 f(n+1)=f(n)+f(n−1) ；
    //初始状态： dp[0]=0,dp[1]=1 ，即初始化前两个数字；
    //返回值： dp[n]即斐波那契数列的第 n 个数字。
    //https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/solution/mian-shi-ti-10-i-fei-bo-na-qi-shu-lie-dong-tai-gui/
    static class Solution {
        public int fib(int n) {
            int a = 0, b = 1, sum = 0;
            for (int i = 0; i < n; i++) {
                sum = (a + b) % 1000000007;
                a = b;
                b = sum;
            }
            return a;
        }

        /**
         * @author AShuo
         * @date 2021/9/27 14:46
         * 
         * 剑指 Offer 10- II. 青蛙跳台阶问题
         * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
         * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
         * 
         * 示例 1：
         * 输入：n = 2
         * 输出：2
         * 
         * 示例 2：
         * 输入：n = 7
         * 输出：21
         * 
         * 示例 3：
         * 输入：n = 0
         * 输出：1
         * 
         * 提示：
         * 0 <= n <= 100
         * 
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        //设跳上 n 级台阶有 f(n) 种跳法。在所有跳法中，青蛙的最后一步只有两种情况： 跳上 1 级或 2 级台阶。
        //当为 1 级台阶： 剩 n-1 个台阶，此情况共有 f(n-1) 种跳法；
        //当为 2 级台阶： 剩 n-2 个台阶，此情况共有 f(n-2) 种跳法。
        //所以共有f(n)=f(n-1)+f(n-2)
        //https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/solution/mian-shi-ti-10-ii-qing-wa-tiao-tai-jie-wen-ti-dong/
        public int numWays(int n) {
            int a = 1, b = 1, sum = 0;
            for (int i = 0; i < n; i++) {
                sum = (a + b) % 1000000007;
                a = b;
                b = sum;
            }
            return a;
        }
    }
}
