package week02;

public class UglyNumber_Offer49 {

    //1、暴力枚举，

    //2、
    public int nthUglyNumber(int n) {
        int p1 = 0, p2 = 0, p3 = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[p1] * 2;
            int n3 = dp[p2] * 3;
            int n5 = dp[p3] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) p1++;
            if (dp[i] == n3) p2++;
            if (dp[i] == n5) p3++;
        }

        return dp[n - 1];
    }


}
