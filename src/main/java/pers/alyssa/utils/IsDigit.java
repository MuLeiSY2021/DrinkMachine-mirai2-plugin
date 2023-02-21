package pers.alyssa.utils;

import java.util.regex.Pattern;

public class IsDigit {
    public static boolean isDigit(String str) {
        Pattern pattern = Pattern.compile("\\b[0-9]+\\b");
        return str.matches(pattern.pattern());
    }
}
