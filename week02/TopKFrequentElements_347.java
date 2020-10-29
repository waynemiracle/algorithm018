package week02;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
347. 前 K 个高频元素
https://leetcode-cn.com/problems/top-k-frequent-elements/

提示：

    你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
    你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
    题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
    你可以按任意顺序返回答案。


 */
public class TopKFrequentElements_347 {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> maxheap = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        int[] res = new int[k];
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i],0) + 1);
        }

        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            maxheap.offer(m);
        }

        for (int i = 0; i < k; i++) {
            res[i] = maxheap.poll().getKey();
        }
        return res;
    }
}
