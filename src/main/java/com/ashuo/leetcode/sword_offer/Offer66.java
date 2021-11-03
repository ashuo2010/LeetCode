package com.ashuo.leetcode.sword_offer;

import java.util.Arrays;

/**
 * @author AShuo
 * @date 2021年10月25日 9:09
 * 
 * 剑指 Offer 66. 构建乘积数组
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 * 
 * 示例:
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 * 
 * 提示：
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer66 {
    public static void main(String[] args) {
        Arrays.stream(new Solution().constructArr(new int[]{1, 2, 3, 4, 5})).forEach(System.out::println);
    }

    static class Solution {
        //因为B[i]是指数组A中除了i以外的所有元素的乘积，所以可以把乘积分为两部分，如下
        //当A={1,2,3}
        //        A[1]        A[2]         A[3]

        // B[1]=  1      *    A[2]    *    A[3]
        // B[2]=  A[1]   *      1     *    A[3]
        // B[3]=  A[1]   *    A[2]    *      1

        //所以B[i]就等于左下部分的*右上部分的乘积
        //https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/solution/mian-shi-ti-66-gou-jian-cheng-ji-shu-zu-biao-ge-fe/
        public int[] constructArr(int[] a) {
            int[] ans = new int[a.length];
            int tmp = 1;
            //计算左下部分的乘积
            for (int i = 0; i < ans.length; i++) {
                ans[i] = tmp;
                tmp *= a[i];
            }
            tmp = 1;
            //计算右上部分的乘积并乘以左下部分的乘积
            for (int i = ans.length - 1; i >= 0; i--) {
                ans[i] *= tmp;
                tmp *= a[i];
            }
            return ans;
        }
    }
}
