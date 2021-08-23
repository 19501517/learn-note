package t20_PrintDimensionalArray;

import com.base.ProxyRunner;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * 顺时针打印矩阵
 */
public class PrintDimensionalArray {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}}, "12348121615141395671110"),
                Arguments.of(new int[][]{{1,2,3},{4,5,6},{7,8,9}}, "123698745")
        );
    }

    @ParameterizedTest
    @MethodSource("getParams")
    public void test(int[][] input, String expect) {
        ProxyRunner.run(new Object[]{input}, expect, new PrintDimensionalArraySolution());
    }
}
