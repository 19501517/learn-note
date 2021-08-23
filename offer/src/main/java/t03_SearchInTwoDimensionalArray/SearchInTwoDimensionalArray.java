package t03_SearchInTwoDimensionalArray;

import com.test.base.ProxyRunner;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SearchInTwoDimensionalArray {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of(new int[][]{{1,2,3,4},{5,6,7,8},{10,11,12,13},{14,15,16,17}}, 7, Pair.of(1, 2)),
                Arguments.of(new int[][]{{1,2,3,4},{5,6,7,8},{10,11,12,13},{14,15,16,17}}, 9, null),
                Arguments.of(new int[][]{{1,2,3,4},{5,6,7,8},{10,11,12,13},{14,15,16,17}}, -1, null),
                Arguments.of(new int[][]{{1,2,3,4},{5,6,7,8},{10,11,12,13},{14,15,16,17}}, 100, null),
                Arguments.of(new int[][]{{1,2,3,4},{5,6,7,8},{10,11,12,13},{14,15,16,17}}, 17, Pair.of(3, 3)),
                Arguments.of(new int[][]{{1,2,3,4},{5,6,7,8},{10,11,12,13},{14,15,16,17}}, 4, Pair.of(0, 3))
        );
    }

    @ParameterizedTest
    @MethodSource("getParams")
    public void searchInTwoDimensionalArrayTest(int[][] input, int target, Pair<Integer, Integer> expect) {
        ProxyRunner.run(new Object[]{input, target}, expect, new SearchInTwoDimensionalArraySolution());
    }
}
