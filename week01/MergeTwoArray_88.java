package week01;

/*
给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。



说明：

    初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
    你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。



示例：

输入：
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

输出：[1,2,2,3,5,6]



提示：

    -10^9 <= nums1[i], nums2[i] <= 10^9
    nums1.length == m + n
    nums2.length == n
 */
public class MergeTwoArray_88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m];
        System.arraycopy(nums1,0,temp,0,m);
        int p1 = 0;
        int p2 = 0;
        int cur = 0;
        while (p1 < m && p2 < n) {
            if (temp[p1] <= nums2[p2]) {
                nums1[cur++] = temp[p1++];
            } else {
                nums1[cur++] = nums2[p2++];
            }
        }
        if (p1 == m) {
            System.arraycopy(nums2,p2,nums1,cur,n - p2);
        }
        if (p2 == n) {
            System.arraycopy(temp,p1,nums1,cur,m - p1);
        }
    }

    //由于在从头改变nums1的值时，需要把nums1中的元素存放在其他位置。
    //如果我们从结尾开始改写 nums1 的值
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int cur = m + n - 1;
        while ((p1 >= 0) && (p2 >= 0)) {
            if (nums1[p1] < nums2[p2]) {
                nums1[cur--] = nums2[p2--];
            } else {
                nums1[cur--] = nums1[p1--];
            }
        }
        System.arraycopy(nums2,0,nums1,0,p2+1);
    }
}
