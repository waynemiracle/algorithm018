package week01;

/*
 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]

示例 2:

输入: [-1,-100,3,99] 和 k = 2
输出: [3,99,-1,-100]
解释:
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]

说明:

    尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
    要求使用空间复杂度为 O(1) 的 原地 算法。

 */
public class RotateArray_189 {

    //暴力求解
    //外层循环控制移动次数，内存循环每次从最后一个开始每相邻两个两两交换
    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = nums.length - 1; j > 0; j--) {
                int temp = nums[j - 1];
                nums[j - 1] = nums[j];
                nums[j] = temp;
            }
        }

    }

    //k次移动后
    //前k个元素是原数组的后k个元素,后nums.length -k个是原数组的前nums.length-k个元素
    //todo 借助数组，空间复杂度O(n)
    public void rotate1(int[] nums, int k) {
        int[] temp = new int[nums.length];
        int cur = 0;
        k = k % nums.length;
        for (int i = nums.length - k; i < nums.length; i++) {
            temp[cur++] = nums[i];
        }

        for (int j = 0; j < nums.length - k; j++) {
            temp[cur++] = nums[j];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }
    //简化rotae1代码
    // 原本数组里下标为i的我们把它放到 (i+k)%nums.length 的位置
    public void rotate2(int[] nums, int k) {
        int[] temp = new int[nums.length];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            temp[(i+k)%n] = nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }

    //反转数组
    public void rotate3(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums,0, nums.length - 1);

        reverse(nums, 0, k - 1);

        reverse(nums,k, nums.length - 1);

    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}
