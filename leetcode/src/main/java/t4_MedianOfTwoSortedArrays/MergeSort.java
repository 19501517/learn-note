package t4_MedianOfTwoSortedArrays;

import com.test.base.Solution;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 选择排序后，查找中位数
 * 可以通用双指针法，走到中位数所在位置，不过写起来有点烦，只是占用空间小了，速度并没有快
 */
public class MergeSort {

    @Solution
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (ArrayUtils.isEmpty(nums1) && ArrayUtils.isEmpty(nums2)) {
            return 0;
        }
        int[] merge = new int[nums1.length + nums2.length];
        int p1 = 0;
        int p2 = 0;
        int index = 0;
        while (index < merge.length) {
            if (p1 == nums1.length) {
                merge[index++] = nums2[p2++];
                continue;
            }
            if (p2 == nums2.length) {
                merge[index++] = nums1[p1++];
                continue;
            }
            if (nums1[p1] <= nums2[p2]) {
                merge[index++] = nums1[p1++];
            } else {
                merge[index++] = nums2[p2++];
            }
        }

        if (merge.length % 2 == 0) {
            return (merge[merge.length / 2] + merge[merge.length / 2 - 1]) / 2.0;
        } else {
            return merge[merge.length / 2];
        }
    }
}
