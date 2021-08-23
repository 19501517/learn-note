package t20_PrintDimensionalArray;

import com.base.Solution;
import org.apache.commons.lang3.tuple.Pair;

public class PrintDimensionalArraySolution {

    @Solution
    public String print(int[][] input) {
        if (input == null || input.length == 0) {
            return "";
        }
        if (input[0] == null || input[0].length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= input.length / 2; i++) {
            int broad = input.length - i - 1;
            if (i == broad) {
                sb.append(input[i][i]);
            }

            for (int tempX = i; tempX < broad; tempX++) {
                sb.append(input[i][tempX]);
            }
            for (int tempY = i; tempY < broad; tempY++) {
                sb.append(input[tempY][broad]);
            }
            for (int tempX = broad; tempX > i; tempX--) {
                sb.append(input[broad][tempX]);
            }
            for (int tempY = broad; tempY > i; tempY--) {
                sb.append(input[tempY][i]);
            }
        }
        return sb.toString();
    }
}
