package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月25日 10:34
 * 
 * 剑指 Offer 14- I. 剪绳子
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 
 * 提示：
 * 2 <= n <= 58
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer14 {
    public static void main(String[] args) {
        System.out.println(new Solution().cuttingRope(10));
        System.out.println(new Solution().cuttingRope2(10));
        System.out.println(new Solution().cuttingRope3(10));
    }

    static class Solution {
        //尽可能将绳子以长度3等分为多段时，乘积最大
        //最优：3。把绳子尽可能切为多个长度为 3 的片段，留下的最后一段绳子的长度可能为 0,1,2 三种情况。
        //次优：2。若最后一段绳子长度为 2；则保留，不再拆为 1+1 。
        //最差：1。若最后一段绳子长度为 1；则应把一份 3 + 1 替换为 2 + 2，因为 2×2>3×1。
        //https://leetcode-cn.com/problems/jian-sheng-zi-lcof/solution/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by/
        public int cuttingRope(int n) {
            if (n <= 3) {
                return n - 1;
            }
            int a = n / 3;
            int b = n % 3;
            if (b == 0) {
                return (int) Math.pow(3, a);
            } else if (b == 1) {
                return (int) Math.pow(3, a - 1) * 4;
            } else {
                return (int) Math.pow(3, a) * 2;
            }
        }


        //使用动态规划，用dp[len]剪存储长度为len的最大长度乘积
        public int cuttingRope2(int n) {
            if (n <= 3) {
                return n - 1;
            }
            int[] dp = new int[n + 1]; //n+1是因为长度为n,且n不能为0
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 3;
            //获取前一段绳子长度
            for (int len = 4; len <= n; len++) {
                //获取后一段绳子长度， len/2，是因为[1,3]和[3,1]是乘积一样的
                for (int k = 1; k <= len / 2; k++) {
                    dp[len] = Math.max(dp[len], dp[k] * dp[len - k]);
                }
            }
            return dp[n];
        }

        /**
         * @author AShuo
         * @date 2021/11/2 9:58
         * 
         * 剑指 Offer 14- II. 剪绳子 II
         * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
         * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
         * 
         * 示例 1：
         * 输入: 2
         * 输出: 1
         * 解释: 2 = 1 + 1, 1 × 1 = 1
         * 
         * 示例 2:
         * 输入: 10
         * 输出: 36
         * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
         * 
         * 提示：
         * 2 <= n <= 1000
         * 
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */

        //贪心算法，根据推测有n=n1+n2-->n1+n2<n1*n2; n=n1+n2+n3<n1*n2*n3
        //所以合理的切分方案可以带来更大的乘积
        //若切分方案合理，绳子段切分的越多，乘积越大
        //为使乘积最大，只有长度为 2 和 3 的绳子不应再切分，且 3 比 2 更优
        //其中当为4时2*2>1*3,其余为3*3*3...的格式，最后一位只可能4、3或2，直接相乘最大
        //https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/solution/mian-shi-ti-14-ii-jian-sheng-zi-iitan-xin-er-fen-f/
        public int cuttingRope3(int n) {
            if (n <= 3) {
                return n - 1;
            }
            long res = 1;
            while (n > 4) {
                res = res * 3 % 1000000007;
                n -= 3;
            }
            return (int) (res * n % 1000000007);
        }
    }
}
