package com.ashuo.leetcode.sword_offer;

import java.util.Arrays;

/**
 * @author AShuo
 * @date 2021年10月29日 17:13
 * 
 * 剑指 Offer 60. n个骰子的点数
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * 
 * 示例 1:
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 
 * 示例 2:
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 * 
 * 限制：
 * 1 <= n <= 11
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer60 {
    public static void main(String[] args) {
        Arrays.stream(new Solution().dicesProbability(2)).forEach(System.out::println);
    }

    static class Solution {
        //dp[]用于存储n个骰子点数的概率，当骰子有n个时，骰子的点数就有（6*i-i）+1种
        //假如dp代表n-1个骰子点数的概率，那么可以创建一个tmp（长度为：6*n-n+1）临时数组存放n个骰子的点数概率
        //让dp经过对应处理处理之后存放到临时的tmp数组中，
        //https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/solution/jian-zhi-offer-60-n-ge-tou-zi-de-dian-sh-z36d/
        public double[] dicesProbability(int n) {
            double[] dp = new double[6];
            Arrays.fill(dp, 1.0 / 6.0);
            //从第二个骰子开始，每增加一个骰子循环一次
            for (int i = 2; i <= n; i++) {
                //创建存放当前骰子所有点数的可能性数组
                double[] tmp = new double[6 * i - i + 1];   //最大-最小+1
                //循环遍历dp数组即上一个骰子的所有可能性数组
                for (int j = 0; j < dp.length; j++) {
                    for (int k = 0; k < 6; k++) {
                        //遍历dp将dp乘以 1/6 赋值给对应的点数
                        //比如只有1个骰子时，dp[1]是代表当骰子点数之和为2时的概率，它会对当有2个骰子时的点数之和为3、4、5、6、7、8产生影响
                        //因为当有一个骰子的值为2时，另一个骰子的值可以为1~6，产生的点数之和相应的就是3~8；
                        //比如dp[2]代表点数之和为3，它会对有2个骰子时的点数之和为4、5、6、7、8、9产生影响
                        //所以k在这里就是对应着第i个骰子出现时可能出现六种情况
                        tmp[j + k] += dp[j] * (1.0 / 6.0);
                    }
                }
                dp = tmp;
            }
            return dp;
        }
    }
}
