package t4_MedianOfTwoSortedArrays;


import base.ProxyRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 *
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MedianOfTwoSortedArraysTest {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of(new int[]{1, 3}, new int[]{2}, 2d),
                Arguments.of(new int[]{1, 2}, new int[]{3, 4}, 2.5d),
                Arguments.of(new int[]{1}, new int[]{2}, 1.5d),
                Arguments.of(new int[]{0, 0}, new int[]{0, 0}, 0d),
                Arguments.of(new int[]{1}, new int[]{}, 1d),
                Arguments.of(new int[]{}, new int[]{2}, 2d)
        );
    }

    @ParameterizedTest
    @MethodSource("getParams")
    public void mergetSortTest(int[] nums1, int[] nums2, double expected) {
        ProxyRunner.run(new Object[]{nums1, nums2}, expected, new MergeSort());
    }

    @ParameterizedTest
    @MethodSource("getParams")
    public void doublePointTest(int[] nums1, int[] nums2, double expected) {
        ProxyRunner.run(new Object[]{nums1, nums2}, expected, new DoublePoint());
    }
}