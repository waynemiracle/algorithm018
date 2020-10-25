package week01;

import java.util.Stack;

/*
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

示例 1：

输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

示例 2：

输入：height = [4,2,0,3,2,5]
输出：9

提示：

    n == height.length
    0 <= n <= 3 * 104
    0 <= height[i] <= 105
 */
public class TrappingRainWater_42 {

    public int trap(int[] height) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int cur = stack.peek();
                stack.pop();
                if (stack.isEmpty()) break;

                int l = stack.peek();
                int r = i;
                int h = Math.min(height[r],height[l]) - height[cur];
                ans += ((r - l) - 1)*h;
            }
            stack.push(i);
        }
        return ans;
    }

}
