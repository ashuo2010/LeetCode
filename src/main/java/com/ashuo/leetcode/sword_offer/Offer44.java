package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年11月02日 16:32
 * 
 * 剑指 Offer 44. 数字序列中某一位的数字
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 * 请写一个函数，求任意第n位对应的数字。
 * 
 * 示例 1：
 * 输入：n = 3
 * 输出：3
 * 
 * 示例 2：
 * 输入：n = 11
 * 输出：0
 * 
 * 限制：
 * 0 <= n < 2^31
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer44 {
    public static void main(String[] args) {
        System.out.println(new Solution().findNthDigit(3));
    }

    static class Solution {
        //确定n所在数字的位数==>根据位数找到位置n对应的数字==>返回数字中对应n位置的数字
        //https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/solution/mian-shi-ti-44-shu-zi-xu-lie-zhong-mou-yi-wei-de-6/
        public int findNthDigit(int n) {
            int digit = 1;
            long start = 1, count = 9;
            //确定n所在数字的位数digit和起始位置start
            while (n > count) {
                n -= count;
                start *= 10;
                digit += 1;
                count = digit * start * 9;
            }
            //根据start和dight与num之间的规律找到n处对应完整的数字
            long num = start + (n - 1) / digit;
            //根据规律找到数字num内n指向的数字
            return Long.toString(num).charAt((n - 1) % digit) - '0';
        }
    }
}
