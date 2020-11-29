package week06;

/*
64. 最小路径和
https://leetcode-cn.com/problems/minimum-path-sum/
 */
public class MinimumPathSum_64 {

    /*todo 说明：每次只能向下或者向右移动一步。
     行i 0开始
     列j 0开始
     第一行i = 0，只能由向右移动得到
     第一列j = 0，只能由向下移动得到
     其它行列，可以由向下或者向右移动得到

     时间复杂度：O(m*n) ,m、n分别为网格的行列数，需要对整个网格遍历一次，计算dp的每个元素
     空间复杂度：O(m*n),m、n分别为网格的行列数，创建一个二维数组dp和网格大小相同

     空间复杂度的优化，每次只存储上一行的dp值，则可以将空间复杂度优化到O(n)
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[m -1][n - 1];
    }

    //java空间优化后（不修改原数组）
    public int minPathSum_01(int[][] grid) {
        int len = grid[0].length;
        int[] dp = new int[len];
        dp[0] = grid[0][0];
        for (int i = 1; i < len; i++)
            dp[i]=dp[i-1]+grid[0][i];
        for (int i = 1; i < grid.length; i++) {
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < len; j++)
                dp[j] = Math.min(dp[j-1]+grid[i][j], dp[j]+grid[i][j]);
        }
        return dp[len-1];
    }
}
