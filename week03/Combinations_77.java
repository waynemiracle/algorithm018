package week03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
77. 组合
https://leetcode-cn.com/problems/combinations/
 */
public class Combinations_77 {

    /*
    (1)选第n个数字，需要从前面n-1个数字中选择k-1个，然后与n组合
    (2)不选第n个数组，直接从前面n-1个数组中选择k个
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < k || k == 0) {
            return result;
        }
        //选第n个数字
        result = combine(n - 1, k -1);
        if (result.isEmpty()) {
            result.add(new ArrayList<>());
        }
        //从前面集合result中每个子集的后面添加一个数组n
        for (List<Integer> list : result) {
            list.add(n);
        }
        result.addAll(combine(n - 1, k));
        return result;
    }

}
