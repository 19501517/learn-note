package townum;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.AssertUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * <p>
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [2, 7].
 *
 * 这里为了结果直观，把原来的返回数组下标改成返回当前数值
 */
class TwoSumTest {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of(new int[]{2, 4, 6, 7}, 6, new int[]{2, 4}),
                Arguments.of(new int[]{1, 4, 6, 7}, 10, new int[]{4, 6}));
    }

    @ParameterizedTest
    @MethodSource("getParams")
    @DisplayName("exhaustion solution")
    void forceTest(int[] numArray, int target, int[] expectedResult) {
        assertAndPrint(numArray, target, expectedResult, new TwoSumExhaustionSolution());
    }

    @ParameterizedTest
    @MethodSource("getParams")
    @DisplayName("one map solution")
    void mapTest(int[] numArray, int expectedResult, int[] result) {
        assertAndPrint(numArray, expectedResult, result, new TwoSumMapSolution());
    }

    private void assertAndPrint(int[] numArray, int target, int[] expectedResult, ITwoSumSolution solution) {
        long start = System.currentTimeMillis();
        int[] actualResult = solution.getTwoSumResult(numArray, target);
        long end = System.currentTimeMillis();
        System.out.println("----------------------------------------");
        System.out.println("given num arrays : " + Arrays.toString(numArray));
        System.out.println("target : " + target);
        System.out.println("expect result : " + Arrays.toString(expectedResult));
        System.out.println("result : " + Arrays.toString(actualResult));
        System.out.println("time : " + (end - start));

        AssertUtils.assertArrayElementEquals(expectedResult, actualResult);
    }
}
