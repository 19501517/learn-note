package t5_LongestPalindromicSubstring;


import com.test.base.ProxyRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 */
public class LongestPalindromicSubstring {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of("bb", "bb"),
                Arguments.of("babad", "bab"),
                Arguments.of("a", "a"),
                Arguments.of("ac", "a"),
                Arguments.of("abbbbc", "bbbb"),
                Arguments.of("abba", "abba"),
                Arguments.of("12345aba", "aba")
        );
    }

    @ParameterizedTest
    @MethodSource("getParams")
    public void test(Object input, Object expected) {
        ProxyRunner.run(new Object[]{input}, expected, new CentrolExpand());
    }
}