package base;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public enum RunType {
    Base;

    public String getMethodArgsString(Object[] methodArgs) {
        return Arrays.stream(methodArgs).map(Objects::toString).collect(Collectors.joining(", "));
    }

    public String getResultString(Object expectResult) {
        return Objects.toString(expectResult);
    }

    public void assertResult(Object expectResult, Object actualResult) {
        Assertions.assertEquals(expectResult, actualResult);
    }
}
