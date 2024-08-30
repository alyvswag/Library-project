package com.example.libraryproject.utils;

import com.example.libraryproject.exception.BaseException;

public class CommonUtils {
    @FunctionalInterface
    public interface Checker {
        boolean check();
    }

    public static void throwIf(Checker checker, BaseException ex) {
        if (checker.check()) {
            throw ex;
        }
    }
}
