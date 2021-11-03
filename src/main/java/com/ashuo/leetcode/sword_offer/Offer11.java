package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年09月26日 9:44
 * 
 * 剑指 Offer 11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 * 
 * 示例 1：
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 
 * 示例 2：
 * 输入：[2,2,2,0,1]
 * 输出：0
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer11 {
    public static void main(String[] args) {
        int[] arr = {2, 2, 2, 0, 1};
        System.out.println(new Solution().minArray(arr));
    }

    //二分查找，每轮获取中间的值，如果中间的值大于最右边的值，说明那个点在右边，如果中间的值小于最右边的值，说明点在左边或者刚好是在中间
    //如果相等说明点在左右或者中间都有可能，但是右边的值有中间的值代替，所以可以将右边的值减一，缩小范围
    //https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/mian-shi-ti-11-xuan-zhuan-shu-zu-de-zui-xiao-shu-3/
    static class Solution {
        public int minArray(int[] numbers) {
            int left = 0, right = numbers.length - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (numbers[mid] > numbers[right]) {
                    //如果中间的值大于最右边的值，说明那个点在右边
                    left = mid + 1;
                } else if (numbers[mid] < numbers[right]) {
                    //如果中间的值小于最右边的值，说明点在左边或者刚好是在中间
                    right = mid;
                } else {
                    //如果相等说明点在左右或者中间都有可能，但可以缩小范围
                    right = right - 1;
                }
            }
            return numbers[right];
        }
    }
}
