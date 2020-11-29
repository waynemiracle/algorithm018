package week06;
/*
91. 解码方法
https://leetcode-cn.com/problems/decode-ways/
 */

public class DecodeWays_91 {

    /*
    dp过程可以从左往右，也可以从右往左，这里以从右往左
    dp[i] += dp[j],j > i dp[i]表示从第i+1个数到第n个数的所有方案数
    (1)只有26种状态，且没有哪一种状态是以0开头，只有10、20是以0结尾的。所以遇到0开头的情况可以跳过该记录
    (2)中间的遍历循环由于记录的num值最大只有26.即两位，所以我们可以添加判断条件缩短内存循环的次数，j - i < 2
    (2)最多只有两层内存循环，根据记录的num值来判断是否超过了上限26，没有就能够进行状态转移

    时间复杂度：O(n)，内层循环能控制到不超过2次
    空间复杂度：O(n)

     */
    public int numDecodings(String s) {
        char[] nums = s.toCharArray();
        int n = nums.length;
        //dp[i]表示从第i+1个数到第n个数的所有方案数
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            //当开始位为0字符时不满足任意一个字母的解析，跳过
            if (nums[i] == '0') continue;
            int num = 0;
            for (int j = i; j < n && j - i < 2; j++) {
                num = num * 10 + (nums[j] - '0');
                //对子状态dp[j+1]为0开头的也可以进行添加吗，因为没有赋值为dp[j+1]为0
                if (num <= 26) {
                    dp[i] += dp[j + 1];
                }
            }
        }
        return dp[0];
    }
}
