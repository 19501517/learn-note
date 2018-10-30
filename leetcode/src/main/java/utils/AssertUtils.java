package utils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.fail;

public class AssertUtils {

    public static void assertArrayElementEquals(int[] o1, int[] o2) {
        if (o1 == o2) {
            return;
        }

        if (o1 == null || o2 == null) {
            fail();
        }

        int[] copy1 = Arrays.copyOf(o1, o1.length);
        int[] copy2 = Arrays.copyOf(o2, o2.length);
        Arrays.sort(copy1);
        Arrays.sort(copy2);

        if (!Arrays.equals(copy1, copy2)) {
            fail();
        }
    }

    public static void assertArrayElementEquals(Object[] o1, Object[] o2) {
        if (o1 == o2) {
            return;
        }

        if (o1 == null || o2 == null) {
            fail();
        }

        Object[] copy1 = Arrays.copyOf(o1, o1.length);
        Object[] copy2 = Arrays.copyOf(o2, o2.length);
        Arrays.sort(copy1);
        Arrays.sort(copy2);

        if (!Arrays.equals(copy1, copy2)) {
            fail();
        }
    }
}
