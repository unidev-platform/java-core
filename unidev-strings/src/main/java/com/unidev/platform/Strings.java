package com.unidev.platform;

import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Apache string utils with extensions used in U development
 * @author denis
 */
public class Strings  extends StringUtils{

    /**
     * Generate string unique hash based on provided dictionary
     * @param str
     * @param minLength
     * @param maxLength
     * @param dictionary
     * @return
     */
    public static String genHash(String str, int minLength, int maxLength, String dictionary) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new SecureRandom(str.getBytes());
        int length = minLength + random.nextInt(maxLength - minLength);
        for (int i = 0; i < length; i++) {
            int id = random.nextInt(dictionary.length());
            stringBuilder.append(dictionary.charAt(id));
        }
        return stringBuilder.toString();
    }

    /**
     * Generate string unique hash based on provided dictionary
     * @param str
     * @param minLength
     * @param maxLength
     * @return
     */
    public static String genHash(String str, int minLength, int maxLength) {
        return genHash(str, minLength, maxLength, "abcdefghiklmnopqrstxyz0123456789");
    }
}

