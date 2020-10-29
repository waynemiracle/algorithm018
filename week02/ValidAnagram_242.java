package week02;


/*
leetcode-242 有效的字母异位词
https://leetcode-cn.com/problems/valid-anagram/
解释：所谓的异位词就是它字符出现的次数都是一样的，然后顺序不一样

说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */

import java.util.Arrays;

public class ValidAnagram_242 {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] char_s = s.toCharArray();
        char[] char_t = t.toCharArray();

        Arrays.sort(char_s);
        Arrays.sort(char_t);

        return Arrays.equals(char_s, char_t);
    }

    public boolean isAnagram_01(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /*
    我们可以先用计数器表计算 s，然后用 t 减少计数器表中的每个字母的计数器。
    如果在任何时候计数器低于零，我们知道 t 包含一个不在 s 中的额外字母，并立即返回 FALSE
     */
    public boolean isAnagram_02(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];

        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            /*table[t.charAt(i) - 'a']--;
            if ( table[t.charAt(i) - 'a'] < 0) {
                return false;
            }*/
            if ((--table[t.charAt(i) - 'a']) < 0) {
                return false;
            }
        }
        return true;
    }

}
