package t4_MedianOfTwoSortedArrays;

import com.test.base.Solution;
import org.apache.commons.lang3.ArrayUtils;

import java.util.LinkedList;

/**
 * 双指针法，走到中位数所在位置
 */
public class DoublePoint {

    @Solution
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (ArrayUtils.isEmpty(nums1) && ArrayUtils.isEmpty(nums2)) {
            return 0;
        }
        int p1 = 0;
        int p2 = 0;
        int mid = (nums1.length + nums2.length) / 2 + 1;
        int index = 0;
        LinkedList<Integer> nums = new LinkedList<>();
        while (index < mid) {
            if (p1 == nums1.length) {
                nums.addLast(nums2[p2++]);
                if (nums.size() > 2) {
                    nums.removeFirst();
                }
                index++;
                continue;
            }
            if (p2 == nums2.length) {
                nums.addLast(nums1[p1++]);
                if (nums.size() > 2) {
                    nums.removeFirst();
                }
                index++;
                continue;
            }
            if (nums1[p1] <= nums2[p2]) {
                nums.addLast(nums1[p1++]);
            } else {
                nums.addLast(nums2[p2++]);
            }
            if (nums.size() > 2) {
                nums.removeFirst();
            }
            index++;
        }

        if ((nums1.length + nums2.length) % 2 == 0) {
            return (nums.getFirst() + nums.getLast()) / 2.0;
        } else {
            return nums.getLast();
        }
    }
}
