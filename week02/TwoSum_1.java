package week02;

import java.util.HashMap;
import java.util.Map;

/*
    1、两数之和
    https://leetcode-cn.com/problems/two-sum/

    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 */
public class TwoSum_1 {

    //暴力求解
    public int[] twoSum_01(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSum_02(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    public int[] twoSum_03(int[] nums, int target) {

        if (nums == null || nums.length < 2) return new int[0];

        int[] res = new int[2];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer idx = map.get(target - nums[i]);
            if (idx == null) {
                map.put(nums[i], i);
            } else {
                res[0] = i;
                res[1] = idx;
                break;
            }
        }

        return res;
    }

}
