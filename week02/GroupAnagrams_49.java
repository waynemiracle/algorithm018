package week02;

/*
49. 字母异位词分组
https://leetcode-cn.com/problems/group-anagrams/

说明：

    所有输入均为小写字母。
    不考虑答案输出的顺序。


 */
import java.util.*;

public class GroupAnagrams_49 {


    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }

        String[] temp = Arrays.copyOf(strs, strs.length);
        for (int i = 0; i < temp.length; i++) {
            char[] str = temp[i].toCharArray();
            Arrays.sort(str);
            temp[i] = new String(str);
        }

        Map<String,List<String>> map = new HashMap<>();
        for (int i = 0; i < temp.length; i++) {
            List<String> list = map.get(temp[i]);
            if (list == null) {
                list = new ArrayList<>();

            }
            list.add(strs[i]);
            map.put(temp[i],list);
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;

    }
    //简化代码
    public List<List<String>> groupAnagrams_01(String[] strs) {

        if (strs == null || strs.length == 0) {
            return new ArrayList();
        }
        Map<String,List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] char_s = s.toCharArray();
            Arrays.sort(char_s);
            String key = String.valueOf(char_s);
            if (!map.containsKey(key)) map.put(key, new ArrayList());
            map.get(key).add(s);
        }

        return new ArrayList(map.values());
    }

    /*
    题目中说了所有输入均为小写字母，所以还可以只用一个数组，统计字符串中每个字符的个数，
    最终会生成一个新的字符串，如果生成新的字符串相同，说明他们是字母异位词
     */
    public List<List<String>> groupAnagrams_03(String[] strs) {

        if (strs == null || strs.length == 0) {
            return new ArrayList();
        }
        Map<String,List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = new char[26];

            for (char c : s.toCharArray()) {
                ca[c - 'a']++;
            }

            String key = String.valueOf(ca);

            if (!map.containsKey(key)) map.put(key, new ArrayList());
            map.get(key).add(s);
        }

        return new ArrayList(map.values());
    }


}
