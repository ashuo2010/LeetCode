package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年09月28日 15:38
 * 
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 * 
 * 示例1:
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 
 * 提示：
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer42 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArray(new int[]{1, 2}));
    }

    //动态规划，dp[i] 代表以元素nums[i] 为结尾的连续子数组最大和
    //若 dp[i−1]≤0 ，说明 dp[i−1] 对dp[i] 产生负贡献，即 dp[i−1]+nums[i] 还不如nums[i] 本身大
    //当 dp[i−1]>0 时：执行 dp[i]=dp[i−1]+nums[i]
    //当 dp[i−1]≤0 时：执行 dp[i]=nums[i]
    //https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/solution/mian-shi-ti-42-lian-xu-zi-shu-zu-de-zui-da-he-do-2/
    static class Solution {
        public int maxSubArray(int[] nums) {
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                nums[i] += Math.max(nums[i - 1], 0);
                max = Math.max(max, nums[i]);
            }
            return max;
        }
    }
}
