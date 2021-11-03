package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月29日 16:03
 * 
 * 剑指 Offer 49. 丑数
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 * 
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 
 * 说明:
 * 1 是丑数。
 * n 不超过1690。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chou-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer49 {
    public static void main(String[] args) {
        System.out.println(new Solution().nthUglyNumber(10));
    }

    static class Solution {
        //每一个丑数都可以由2或3或5来乘以前一个数得到，所以从1开始，每个数都要分别乘以2、3和5才能得到全部丑数
        //直接让每个数循环乘以可能2、3和5的话可能得到的丑数顺序不是从小到大的，即先算2的所有丑数，再算3的所有丑数，最后算所有5的丑数
        //会产生 2*2>1*3的情况，导致不是有序排列的
        //解决方法是用三个指针分别指向上一次乘以该数的位置
        //如用a-1记录上一次乘以2得到的丑数的索引，当计算出下一个丑数是由dp[a-1]*2得到的时候，a进行加一，表明a-1位置的丑数已经被乘过了，下一次再遇到同样的情况的时候以a为索引计算
        //同理b代表*3的索引，c代表*5的索引
        //（计算的丑数应该是离上一次计算得到的丑数最近的，即上一次计算得到的丑数分别乘以2、3和5的最小值）
        //https://leetcode-cn.com/problems/chou-shu-lcof/solution/mian-shi-ti-49-chou-shu-dong-tai-gui-hua-qing-xi-t/
        public int nthUglyNumber(int n) {
            int[] dp = new int[n];
            int a = 0, b = 0, c = 0;
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                int n2 = dp[a] * 2;
                int n3 = dp[b] * 3;
                int n5 = dp[c] * 5;
                //找出最近的丑数
                dp[i] = Math.min(n2, Math.min(n3, n5));
                //如果是n1乘以2得到的，那么n1位置的索引的数据已经被计算过了，向前移动一次
                if (dp[i] == n2) {
                    a++;
                }
                if (dp[i] == n3) {
                    b++;
                }
                if (dp[i] == n5) {
                    c++;
                }
            }
            return dp[n - 1];
        }
    }
}
