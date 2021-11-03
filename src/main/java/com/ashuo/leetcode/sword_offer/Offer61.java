package com.ashuo.leetcode.sword_offer;

import java.util.Arrays;

/**
 * @author AShuo
 * @date 2021年10月14日 14:03
 * 
 * 剑指 Offer 61. 扑克牌中的顺子
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * 
 * 示例 1:
 * 输入: [1,2,3,4,5]
 * 输出: True
 * 
 * 示例 2:
 * 输入: [0,0,1,2,5]
 * 输出: True
 * 
 * 限制：
 * 数组长度为 5
 * 数组的数取值为 [0, 13]
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer61 {
    public static void main(String[] args) {
        System.out.println(new Solution().isStraight(new int[]{1, 2, 3, 4, 5}));
        System.out.println(new Solution().isStraight(new int[]{0, 0, 0, 2, 5}));
    }

    //顺子条件：1.除大小王外，所有牌无重复；2.最大牌-最小牌需要小于等于四（小于是因为大小王可以当成任意牌），排序遍历，获取最大值和除了大小王之外的下标，进行相减校验
    //https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/solution/mian-shi-ti-61-bu-ke-pai-zhong-de-shun-zi-ji-he-se/
    static class Solution {
        public boolean isStraight(int[] nums) {
            Arrays.sort(nums);
            int joker = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == 0) {
                    //记录大小王的数量，得到排序后第一个不是大小王的索引
                    joker++;
                } else if (nums[i] == nums[i + 1]) {
                    //如果不是大小王且相等，肯定不是顺子，提前返回false
                    return false;
                }
            }
            //小于5是因为大小王可以当成任意的牌，所以牌有可能是0,0,0,2,5也算是顺子
            return nums[nums.length - 1] - nums[joker] < 5;
        }
    }
}
