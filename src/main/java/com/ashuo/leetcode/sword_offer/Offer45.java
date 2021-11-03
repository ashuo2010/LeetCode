package com.ashuo.leetcode.sword_offer;

import java.util.Arrays;

/**
 * @author AShuo
 * @date 2021年10月14日 10:57
 * 
 * 剑指 Offer 45. 把数组排成最小的数
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 
 * 示例 1:
 * 输入: [10,2]
 * 输出: "102"
 * 
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 * 
 * 提示:
 * 0 < nums.length <= 100
 * 
 * 说明:
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer45 {
    public static void main(String[] args) {
        System.out.println(new Solution().minNumber(new int[]{3, 30, 34, 5, 9}));
    }

    static class Solution {
        //对于数组的任意数字x,y，要求组成字符串最小时，当接字符串 x+y>y+x时，x应当在y后边才组成最小,反之也成立，故重写数组的排序方法就行
        //https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/solution/mian-shi-ti-45-ba-shu-zu-pai-cheng-zui-xiao-de-s-4/
        public String minNumber(int[] nums) {
            String[] strNums = new String[nums.length];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nums.length; i++) {
                strNums[i] = String.valueOf(nums[i]);
            }
            Arrays.stream(strNums).sorted((x, y) -> (x + y).compareTo(y + x)).forEach(sb::append);
            return sb.toString();
//        Arrays.stream(nums).mapToObj(String::valueOf).sorted((x, y) -> (x + y).compareTo(y + x)).forEach(sb::append);
        }
    }
}
