package com.crave.edu.util;

public class AssertUtils {
    public static void requireTrue(boolean value, String msg) {
        if (!value) {
            throw new RuntimeException(msg);
        }
    }
}
