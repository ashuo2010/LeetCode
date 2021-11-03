package com.ashuo.leetcode.sword_offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author AShuo
 * @date 2021年10月11日 15:12
 * 
 * 剑指 Offer 57. 和为s的两个数字
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 * 
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * 
 * 示例 2：
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 * 
 * 限制：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer57 {
    public static void main(String[] args) {
        Arrays.stream(new Solution().twoSum(new int[]{10, 26, 30, 31, 47, 60}, 40)).forEach(System.out::println);
        int[][] continuousSequence = new Solution().findContinuousSequence(15);
        for (int[] i : continuousSequence) {
            for (int j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    //让i，j指向数组左右两端，i向后移动，j向前移动，当i=j时退出，移动时计算i、j指向位置的值的和与target值的大小，如果target更大，i向后移动，
    //消去(nums[i],nums[i+1]).....(nums[i],nums[j]).因为数组有序递增，所以这些都和都比target小，调整到(i+1,j)区间
    //同理如果target更小，j向前移动
    //https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof/solution/mian-shi-ti-57-he-wei-s-de-liang-ge-shu-zi-shuang-/
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            int i = 0, j = nums.length - 1;
            while (i < j) {
                if (target > nums[i] + nums[j]) {
                    i++;
                } else if (target < nums[i] + nums[j]) {
                    j--;
                } else {
                    return new int[]{nums[i], nums[j]};
                }
            }
            return new int[0];
        }

        /**
         * @author AShuo
         * @date 2021/10/25 15:51
         * 
         * 剑指 Offer 57 - II. 和为s的连续正数序列
         * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
         * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
         * 
         * 示例 1：
         * 输入：target = 9
         * 输出：[[2,3,4],[4,5]]
         * 
         * 示例 2：
         * 输入：target = 15
         * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
         * 
         * 限制：
         * 1 <= target <= 10^5
         * 
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */

        //滑动窗口，从数组左边出发，定义一个可以调整长度的窗口进行遍历
        //定义左边界i，有边界j，边界内的元素和s，目标值target，当s<target时，j++，右边界向后，数组元素增加一个，相当于扩大了窗口
        //当s>target时，i++，左边界向后，数组元素减少一个，相当于减小了窗口
        //当s=target时，记录[i,j]之间的元素
        //https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/solution/jian-zhi-offer-57-ii-he-wei-s-de-lian-xu-t85z/
        public int[][] findContinuousSequence(int target) {
            int i = 1, j = 2, s = 3;
            List<int[]> res = new ArrayList<>();
            while (i < j) {
                if (s > target) {
                    s -= i;
                    i++;
                } else if (s < target) {
                    j++;
                    s += j;
                } else {
                    int[] ans = new int[j - i + 1];
                    for (int k = i; k <= j; k++) {
                        ans[k - i] = k;
                    }
                    res.add(ans);
                    s -= i;
                    i++;
                }
            }
            return res.toArray(new int[0][]);
        }
    }
}
