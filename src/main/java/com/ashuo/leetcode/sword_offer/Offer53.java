package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年09月23日 15:54
 * 
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * 统计一个数字在排序数组中出现的次数。
 * 
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 * 
 * 提示：
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer53 {
    public static void main(String[] args) {
        int[] arr1 = {5, 7, 7, 8, 8, 10};
        int[] arr2 = {0, 1, 2, 3, 4, 5, 6, 7, 9};
        int target = 8;
        System.out.println("数据为" + target + "的数据出现次数为" + new Solution().search(arr1, target));
        System.out.println("丢失的数据是" + new Solution().missingNumber(arr2));
    }

    static class Solution {
        //二分查找获取target数据的数组索引，再进行遍历，节约时间
        //https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/solution/mian-shi-ti-53-i-zai-pai-xu-shu-zu-zhong-cha-zha-5/
        public int search(int[] nums, int target) {
            int count = 0;
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (nums[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            while (left < nums.length && nums[left++] == target) {
                count++;
            }
            return count;
        }


        /**
         * @author AShuo
         * @date 2021年09月23日 17:27
         * 
         * 剑指 Offer 53 - II. 0～n-1中缺失的数字
         * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
         * 
         * 示例 1:
         * 输入: [0,1,3]
         * 输出: 2
         * 
         * 示例 2:
         * 输入: [0,1,2,3,4,5,6,7,9]
         * 输出: 8
         * 
         * 限制：
         * 1 <= 数组长度 <= 10000
         * 
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        //使用二分查找"右子数组的首位元素"
        //https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/solution/mian-shi-ti-53-ii-0n-1zhong-que-shi-de-shu-zi-er-f/
        public int missingNumber(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == mid) {
                    //如果下标与对应数据一致，则表示迷失的数据在右侧
                    left = mid + 1;
                } else {
                    // 迷失的数据在左侧
                    right = mid - 1;
                }
                //如果左侧索引超过了右侧索引，则找到了迷失的数据
            }
            return left;
        }
    }
}

