package t3_LongestSubstringWithoutRepeatingCharacters;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
class LongestSubstringSolutionTest {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of("abcabcbb", 3),
                Arguments.of("bbbbb", 1),
                Arguments.of("pwwkew", 3)
        );
    }

    @ParameterizedTest
    @MethodSource("getParams")
    @DisplayName("exhaustion solution")
    void bitsetSolutionTest(String s, int expectedLength) {
        assertAndPrint(s, expectedLength, new LongestSubstringBitSetSolution());
    }

    @ParameterizedTest
    @MethodSource("getParams")
    @DisplayName("brute force solution")
    void bruteForceSolutionTest(String s, int expectedLength) {
        assertAndPrint(s, expectedLength, new LongestSubstringBruteForceSolution());
    }

    private void assertAndPrint(String s, int expectedLength, ILongestSubstringSolution solution) {
        long start = System.currentTimeMillis();
        int actualLength = solution.lengthOfLongestSubstring(s);
        long end = System.currentTimeMillis();
        System.out.println("----------------------------------------");
        System.out.println("given s : " + s);
        System.out.println("actual result : " + actualLength);
        System.out.println("expect result : " + expectedLength);
        System.out.println("time : " + (end - start));

        Assertions.assertEquals(expectedLength, actualLength);
    }
}