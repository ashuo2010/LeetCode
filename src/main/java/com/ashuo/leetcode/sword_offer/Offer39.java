package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月22日 15:01
 * 
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 
 * 示例 1:
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 * 
 * 限制：
 * 1 <= 数组长度 <= 50000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer39 {
    public static void main(String[] args) {
        System.out.println(new Solution().majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }

    //摩尔投票法，一开始选一个数作为被投票的数，遍历数组，当遇到与这个数相同的投票加一，否则投票减一，当减为0时，重新选取一个数投票，因为有一个数一定占大于数组的一半
    //所以投票最后获得的数一定是这个大于数组一半的数
    //https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/solution/mian-shi-ti-39-shu-zu-zhong-chu-xian-ci-shu-chao-3/
    static class Solution {
        public int majorityElement(int[] nums) {
            int vote = 0;
            int x = 0;
            for (int n : nums) {
                if (vote == 0) {
                    x = n;
                }
                vote = vote + (x == n ? 1 : -1);
            }
            return x;
        }
    }
}
