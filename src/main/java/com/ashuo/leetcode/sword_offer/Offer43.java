package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年11月02日 11:11
 * 
 * 剑指 Offer 43. 1～n 整数中 1 出现的次数
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 * 
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 * 
 * 示例 1：
 * 输入：n = 12
 * 输出：5
 * 
 * 示例 2：
 * 输入：n = 13
 * 输出：6
 * 
 * 限制：
 * 1 <= n < 2^31
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer43 {
    public static void main(String[] args) {
        System.out.println(new Solution().countDigitOne(12));
    }

    static class Solution {
        //找到规律：另数字变成为高位(high)+当前位(cur)+低位(low)以及当前位的所在位数(digit)的形式，让cur从个位数(digit=1)开始出发，即最后一位，由此有规律如下
        //当cur=0时,即在个位数上时有：  1的出现次数=high×digit
        //当cur=1时，即在十位数上时： 1的出现次数=high×digit+low+1
        //其它情况： 1的出现次数=(high+1)×digit
        //所以让cur从个位数上开始出发，直到high和cur都不为0时停止（代表已经遍历技术），统计cur在每一位上出现的次数
        //https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/
        public int countDigitOne(int n) {
            int low = 0, cur = n % 10, high = n / 10, digit = 1, res = 0;
            while (high != 0 || cur != 0) {
                //规律计算
                if (cur == 0) {
                    res += high * digit;
                } else if (cur == 1) {
                    res += high * digit + low + 1;
                } else {
                    res += (high + 1) * digit;
                }
                //移动位置，直到high和cur都已经为0时退出
                low += cur * digit;
                cur = high % 10;
                high = high / 10;
                digit *= 10;
            }
            return res;
        }
    }
}
