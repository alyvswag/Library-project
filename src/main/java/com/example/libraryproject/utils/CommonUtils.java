package com.example.libraryproject.utils;

import com.example.libraryproject.exception.BaseException;

import java.util.regex.Pattern;

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
    public static boolean isValidEmailAddress(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return email != null && pat.matcher(email).matches();
    }
    //ternary operatorunun daha tekmil formasi
    //true gelse exception atilir
}
