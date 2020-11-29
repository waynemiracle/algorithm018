package week06;

import java.awt.*;

/*
221. 最大正方形
https://leetcode-cn.com/problems/maximal-square/
 */
public class MaximalSquare_221 {

    /*
    暴力
    遍历矩阵，遇到1，就将它视为正方形的左上角；
    每次在下方新增一行以及右边新增一列，判断新增的行和列是否满足所有元素都是1

    时间复杂度：O(mn * min(m,n)^2)
    空间复杂度：O(1)
     */
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    //遇到1作为正方形的左上角
                    maxSide = Math.max(maxSide, 1);//可能的最大正方形
                    //计算可能的最大正方形边长
                    int curMaxSide = Math.min(rows - i, columns - j);
                    for (int k = 1; k < curMaxSide; k++) {
                        boolean flag = true;
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }
                        for (int m = 0; m < k; m++) {
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            maxSide = Math.max(maxSide, k + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        //int maxSqure = maxSide * maxSide;
        return maxSide * maxSide;
    }

    /*
    动态规划
    对每个位置的(i,j),检查在矩阵中的该位置的值
    如果该位置的值是0，则当前位置不会出现在由1组成的正方形中，dp[i][j] = 0;
    如果该位置的值是1，则dp[i][j]由其上方，左方，左上的三个相邻位置的dp值决定，
        具体而言，当前位置的元素值等于三个相邻位置的元素中的最小值加1，
        状态转移方程dp[i][j] = min(min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;

        还需要考虑边界条件。如果i和 j中至少有一个为 0，
        则以位置 (i,j) 为右下角的最大正方形的边长只能是 1，因此dp(i,j)=1。【其实就是第0行、第0列和原矩阵一样输出】

        时间复杂度：O(m*n)
        空间复杂度：O(m*n),存储dp状态矩阵， 由于dp(i,j)是由与它三个相邻位置的dp决定，因此可以使用两个一维数组进行状态转移，空间复杂度优化到O(n)
     */
    public int maximalSquare_01(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
