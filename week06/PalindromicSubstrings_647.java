package week06;
/*
647. 回文子串
https://leetcode-cn.com/problems/palindromic-substrings/
 */
public class PalindromicSubstrings_647 {

    /*
    长度为n的字符串会生成2n - 1组成的回文中心[l1,ri],其中li = i/2, ri = li + (i mod 2)

    时间复杂度：O(n^2)
    空间复杂度：O(1)
     */
    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            int l = i / 2, r = (i / 2) + (i % 2);
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }
}
