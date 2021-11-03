package com.ashuo.leetcode.sword_offer;

import java.util.Arrays;

/**
 * @author AShuo
 * @date 2021年10月11日 11:06
 * 
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 * 
 * 示例：
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 * 
 * 提示：
 * 0 <= nums.length <= 50000
 * 0 <= nums[i] <= 10000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer21 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        Arrays.stream(new Solution().exchange(nums)).forEach(System.out::println);
    }

    //让i，j指向数组左右两端，左右开始遍历数组，当i指向的值为奇数时跳过，当j指向的值为偶数时跳过，直到i找到偶数，j找到奇数，此时交换i、j的值，让i的左边都是奇数，j的右边都是偶数
    //https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/solution/mian-shi-ti-21-diao-zheng-shu-zu-shun-xu-shi-qi-4/
    static class Solution {
        public int[] exchange(int[] nums) {
            int i = 0, j = nums.length - 1, tmp;
            while (i < j) {
                //当为奇数时，二进制表示的末尾数字一定是1，与1进行&位运算，结果一定是1，如果与偶数进行&位运算则一定是0
                while (i < j && (nums[i] & 1) == 1) {
                    i++;
                }
                while (i < j && (nums[j] & 1) == 0) {
                    j--;
                }
                tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
            return nums;
        }
    }
}
