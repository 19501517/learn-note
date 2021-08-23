package com.test.base;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public enum RunType {
    Base;

    public String getMethodArgsString(Object[] methodArgs) {
        return Arrays.stream(methodArgs).map(args -> {
            if (args.getClass().isArray()) {
                return ArrayUtils.toString(args);
            } else {
                return Objects.toString(args);
            }
        }).collect(Collectors.joining(", "));
    }

    public String getResultString(Object expectResult) {
        return Objects.toString(expectResult);
    }

    public void assertResult(Object expectResult, Object actualResult) {
        Assertions.assertEquals(expectResult, actualResult);
    }
}
