package t22_SubSequenceOfStack;

import com.base.ProxyRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如序列1，2，3，4，5是某栈的压栈序列，序列4，5，3，2，1是该栈的一个弹出序列，但4，3，5，1，2就不是该栈的弹出序列。
 */
public class SubSequenceOfStack {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1}, true),
                Arguments.of(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2}, false),
                Arguments.of(new int[]{1,2,3,4,5}, new int[]{1,2,3,4,5}, true),
                Arguments.of(new int[]{1,2,3,4,5}, new int[]{5,4,3,2,1}, true)
        );
    }

    @ParameterizedTest
    @MethodSource("getParams")
    public void test(int[] input, int[] subsequence, boolean expect) {
        ProxyRunner.run(new Object[]{input, subsequence}, expect, new SubSequenceOfStackSolution());
    }
}
