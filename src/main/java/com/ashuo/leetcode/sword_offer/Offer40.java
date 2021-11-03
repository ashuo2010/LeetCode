package com.ashuo.leetcode.sword_offer;

import java.util.Arrays;

/**
 * @author AShuo
 * @date 2021年10月15日 10:01
 * 
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * 
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 * 
 * 限制：
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer40 {
    public static void main(String[] args) {
        Arrays.stream(new Solution().getLeastNumbers(new int[]{0, 1, 2, 1}, 1)).forEach(System.out::println);
    }

    //利用快速排序，当快排的基准大于k时，向左遍历，否则向右遍历，直到当基准等于k时退出
    //https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/jian-zhi-offer-40-zui-xiao-de-k-ge-shu-j-9yze/
    static class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k >= arr.length) {
                return arr;
            }
            int left = 0, right = arr.length - 1;
            return quickSort(arr, left, right, k);
        }

        private int[] quickSort(int[] arr, int left, int right, int k) {
            int i = left, j = right;
            while (i < j) {
                while (i < j && arr[left] <= arr[j]) {
                    j--;
                }
                while (i < j && arr[left] >= arr[i]) {
                    i++;
                }
                swap(arr, i, j);
            }
            swap(arr, left, i);
            if (i < k) {
                quickSort(arr, i + 1, right, k);
            }
            if (i > k) {
                quickSort(arr, left, i - 1, k);
            }
            return Arrays.copyOf(arr, k);
        }




   /* 快速排序
    public void quickSort(int[] arr, int left, int right) {
        //如果左边界大于等于右边界，表明遍历结束或者只有一个元素了，遍历结束
        if (left >= right) {
            return;
        }
        //让i和j分别从左边和右边遍历，分别找到比基准大和小的第一个元素，并交换这两个元素的位置，遍历结束之后数组：[基准,比基准小的,..i,j..,比基准大的...]
        //再让基准和i交换，即基准左边数组的都比基准小，右边的数组都比基准大，再递归重复遍历，直到只有一个元素，此时数组既是有序的
        int i = left, j = right;
        while (i < j) {
            //一定要加上等于，要不然会一直停在这里不断交换
            while (i < j && arr[left] <= arr[j]) {
                j--;
            }
            while (i < j && arr[left] >= arr[i]) {
                i++;
            }
            //交换i,j索引位置的元素
            swap(arr, i, j);
        }
        //将left和i交换，基准指向i索引位置的元素
        swap(arr, i, left);
        //递归左右子数组
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }*/

        public void swap(int[] arr, int x, int y) {
            int temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }
    }
}
