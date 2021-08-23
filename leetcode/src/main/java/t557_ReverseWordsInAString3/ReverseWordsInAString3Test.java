package t557_ReverseWordsInAString3;


import com.test.base.ProxyRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 *
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * 示例：
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *
 * 提示：
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */
public class ReverseWordsInAString3Test {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of("Let's take LeetCode contest", "s'teL ekat edoCteeL tsetnoc")
        );
    }

    @ParameterizedTest
    @MethodSource("getParams")
    public void reverseWordsInAString3ArrayTest(String input, String expect) {
        ProxyRunner.run(input, expect, new ReverseWordsInAStringArrayListSolution());
    }
}