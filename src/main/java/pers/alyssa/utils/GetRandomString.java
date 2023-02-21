package pers.alyssa.utils;

import java.util.Random;

public class GetRandomString {
    public static String getRandomString(int length,int charBound_min,int charBound_max) {
        StringBuilder sb = new StringBuilder();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < length; i++) {
            sb.append((char)(charBound_min + r.nextInt(charBound_max - charBound_min + 1)));
        }
        return sb.toString();
    }
}
