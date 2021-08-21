package t03_SearchInTwoDimensionalArray;

import com.base.Solution;
import org.apache.commons.lang3.tuple.Pair;

public class SearchInTwoDimensionalArraySolution {

    @Solution
    public Pair<Integer, Integer> getIndex(int[][] input, int target) {
        if (input == null || input.length == 0) {
            return null;
        }
        if (input[0] == null || input[0].length == 0) {
            return null;
        }
        int x = input[0].length - 1;
        int y = 0;

        while (x >= 0 && y < input.length) {
            if (target == input[y][x]) {
                return Pair.of(y, x);
            }
            if (target > input[y][x]) {
                y++;
            } else {
                x--;
            }
        }
        return null;
    }
}
