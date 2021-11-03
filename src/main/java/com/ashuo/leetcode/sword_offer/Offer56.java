package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月21日 11:13
 * 
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * 
 * 示例 1：
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 
 * 示例 2：
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 * 
 * 限制：
 * 2 <= nums.length <= 10000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer56 {
    public static void main(String[] args) {
        //Arrays.stream(singleNumbers(new int[]{1, 2, 10, 4, 1, 4, 3, 3})).forEach(System.out::println);
        System.out.println(new Solution().singleNumber(new int[]{9, 1, 7, 9, 7, 9, 7}));
    }

    //1.如果数组中只有一个元素只出现一次，其他的元素都出现两次，那么当数组全部元素进行异或时，根据异或同0或同1则为0的特征，全部异或后的结果既是只出现了一次的元素
    //2.当数组中有x,y只出现一次，其他都出现两次时，全部异或后的结果既是x^y,其中一定x!=y，那么x^y一定会有一个位数为1的，找到这个位数为1的位置，再与原数组中的元素都进行异或
    //就能将原数组中的元素分为一个包含有x的、另一个包含有y的数组（因为x和y的这个位置的位数一定不相同），这就化为了“1”中的解决思路
    //https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/jian-zhi-offer-56-i-shu-zu-zhong-shu-zi-tykom/
    static class Solution {
        public int[] singleNumbers(int[] nums) {
            int n = 0, m = 1, x = 0, y = 0;
            //获得x^Y的结果
            for (int num : nums) {
                n = n ^ num;
            }
            //根据x^y与一个数进行与运算，找到x和y不相同的右边第一个位数位置，用于下面区分x和y
            while ((n & m) == 0) {
                m = m << 1;
            }
            for (int num : nums) {
                if ((num & m) == 0) {
                    x = x ^ num;
                } else {
                    y = y ^ num;
                }
            }
            return new int[]{x, y};
        }

        /**
         * @author AShuo
         * @date 2021/10/21 11:55
         * 
         * 剑指 Offer 56 - II. 数组中数字出现的次数 II
         * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
         * 
         * 示例 1：
         * 输入：nums = [3,4,3,3]
         * 输出：4
         * 
         * 示例 2：
         * 输入：nums = [9,1,7,9,7,9,7]
         * 输出：1
         * 
         * 限制：
         * 1 <= nums.length <= 10000
         * 1 <= nums[i] < 2^31
         * 
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */

        //对于出现三次的数字，各二进制位出现的次数都是3的倍数,因此，统计所有数字的各二进制位中1的出现次数，并进行3求余，结果则为只出现一次的数字
        //如：
        //3=0 0 1 1
        //3=0 0 1 1
        //3=0 0 1 1
        //5=0 1 0 1
        //1的总和：0 1 3 4
        //求余：   0 1 0 1
        //恢复成10进制=5
        //所以可以通过统计每一位1的个数来找到只出现一次的数字
        //https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4/
        public int singleNumber(int[] nums) {
            //用于保存32位里面每一个位出现1的次数
            int[] count = new int[32];
            int res = 0;
            //统计对应位置上1的位数
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < 32; j++) {
                    //获得最后一位位数
                    count[j] += nums[i] & 1;
                    //左移
                    nums[i] >>>= 1;
                }
            }
            //将二进制转换为十进制
            for (int i = count.length - 1; i >= 0; i--) {
                res <<= 1;
                //使用%3获取不是3倍数的位数，即只出现一次的位数
                res = res | (count[i] % 3);
            }
            return res;
        }
    }
}
