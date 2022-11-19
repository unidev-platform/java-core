package com.unidev.platform;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * Extenstion of StringUtils with code used in unidev.
 * @see StringUtils
 * @author denis
 */
@Slf4j
public class Strings extends StringUtils {

    public static String cleanPage(String page) {
        return page.replace("\n", " ").replace("\t", " ").replaceAll(" +", " ").trim();
    }

    /**
     * Remove first value between begin end, if not pattern not found return null
     * @param content Content which should be updated
     * @param begin Begin marker
     * @param end  End marker
     * @param includeBeginEnd Include in removal begin end
     * @return Updated content with removed value
     */
    public static String removeValueBetween(String content, String begin, String end, boolean includeBeginEnd) {
        String valueBetween = substringBetween(content, begin, end);
        if (isBlank(valueBetween)) {
            return null;
        }
        if (includeBeginEnd) {
            return replaceOnce(content, begin + valueBetween + end, "");
        }
        return replaceOnce(content, valueBetween, "");
    }

    /**
     * Generate string unique hash based on provided dictionary
     * @param str
     * @param minLength
     * @param maxLength
     * @param dictionary
     * @return
     */
    @Deprecated
    public static String genHash(String str, int minLength, int maxLength, String dictionary) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random(str.hashCode());
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
    @Deprecated
    public static String genHash(String str, int minLength, int maxLength) {
        return genHash(str, minLength, maxLength, "abcdefghiklmnopqrstxyz0123456789");
    }


    @Deprecated
    public static String genLegacyHash(String string) {
        String result = "";
        Random random = new Random(string.hashCode());
        int length = 8 + random.nextInt(4);
        for (int i = 0; i < length; i++) {
            int k = random.nextInt(2);
            if (k == 0) {
                result += (char)(((int)'A') + random.nextInt(20));
            }
            if (k == 1) {
                result += (char)(((int)'a') + random.nextInt(20));
            }
        }
        return result;
    }
}

