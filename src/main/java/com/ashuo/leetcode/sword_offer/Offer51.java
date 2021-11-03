package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年11月01日 16:19
 * 
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 
 * 示例 1:
 * 输入: [7,5,6,4]
 * 输出: 5
 * 
 * 限制：
 * 0 <= 数组长度 <= 50000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer51 {

    public static void main(String[] args) {
        System.out.println(new Solution().reversePairs(new int[]{7, 5, 6, 4}));
    }

    static class Solution {
        //归并排序，在每次合并时找到不是按照不是按照从大到小排列的i，j位置指向的元素，计算[i,m]区间元素的数量即是j前面共有多少个逆序对
        //因为i后面的元素经过归并排序之后，i一定小于i后面的元素，所以当i>j时，[i,m]区间的元素都一定大于j指向的元素
        //每轮统计这种情况即可
        //https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
        int[] tmp;

        public int reversePairs(int[] nums) {
            tmp = new int[nums.length];
            return mergeSort(nums, 0, nums.length - 1);
        }

        public int mergeSort(int[] nums, int l, int r) {
            if (l >= r) {
                return 0;
            }
            int m = (l + r) / 2;
            int res = mergeSort(nums, l, m) + mergeSort(nums, m + 1, r);
            int i = l, j = m + 1;
            for (int k = l; k <= r; k++) {
                tmp[k] = nums[k];
            }

            for (int k = l; k <= r; k++) {
                if (i > m) {
                    nums[k] = tmp[j++];
                } else if (j > r || tmp[i] <= tmp[j]) {
                    nums[k] = tmp[i++];
                } else {
                    nums[k] = tmp[j++];
                    res = res + m - i + 1;
                }
            }
            return res;
        }


        //归并排序
        public void sort(int[] arr, int left, int right) {
            if (left < right) {
                int mid = (left + right) / 2;
                //递归将元素分到最后一个
                sort(arr, left, mid);
                sort(arr, mid + 1, right);
                //合并
                merge(arr, left, mid, right);
            }
        }

        public void merge(int[] arr, int left, int mid, int right) {
            int[] temp = new int[arr.length];
            //i指向数组左半部分首元素，j指向右半部分首元素
            int i = left, j = mid + 1;
            //将[left,right]区间内的元素先复制出来一份，待会根据条件将复制出来的数组添加到区间内的元素
            for (int k = left; k <= right; k++) {
                temp[k] = arr[k];
            }
            //根据条件将复制出来的数组添加到区间[left,right]内的元素
            for (int k = left; k <= right; k++) {
                //如果左边的指针走到了右区间，则表明左边的元素都已经添加到到数组中了，直接将右边剩下的元素进行添加
                if (i > mid) {
                    arr[k] = temp[j++];
                } else if (j > right) { //如果右边的指针超过了有边界，代表右边的元素都已经添加了，直接将左边剩下的元素进行添加
                    arr[k] = temp[i++];
                } else if (temp[i] <= temp[j]) {//如果两个指针都在区间[left,right]内，则判断i，j指针指向元素的大小,来确定将那个元素添加到数组中
                    arr[k] = arr[i++];
                } else {
                    arr[k] = arr[j++];
                }
            }
        }
    }
}
