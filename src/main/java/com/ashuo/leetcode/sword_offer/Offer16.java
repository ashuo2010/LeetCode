package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月20日 15:23
 * 
 * 剑指 Offer 16. 数值的整数次方
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 * 
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * 
 * 提示：
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer16 {
    public static void main(String[] args) {
        System.out.println(new Solution().myPow(2, 10));
    }

    //降低时间复杂度
    //https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/mian-shi-ti-16-shu-zhi-de-zheng-shu-ci-fang-kuai-s/
    static class Solution {
        public double myPow(double x, int n) {
            if (x == 0) {
                return x;
            }
            long b = n;
            if (b < 0) {
                b = -b;
                x = 1 / x;
            }
            double res = 1.0;
            while (b > 0) {
                if ((b & 1) == 1) {       //即b%2==1
                    res *= x;
                }
                x *= x;
                b >>= 1;     //即b=b/2
            }
            return res;
        }
    }
}
