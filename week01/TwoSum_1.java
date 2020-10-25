package week01;

/*
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。



示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
 */

import java.util.HashMap;
import java.util.Map;

public class TwoSum_1 {

    //暴力求解
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }


    //哈希表
    //时间复杂O(n),空间复杂O(n)
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer,Integer> tempMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (tempMap.containsKey(target - nums[i])) {
                return new int[]{tempMap.get(target - nums[i]), i};
            }else {
                tempMap.put(nums[i], i);
            }
        }
        return new int[0];
    }

}
