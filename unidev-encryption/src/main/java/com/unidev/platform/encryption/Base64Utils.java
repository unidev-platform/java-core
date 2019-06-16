package com.unidev.platform.encryption;

import java.util.Base64;

public class Base64Utils {

    public static String stringToBase64(String input) {
        return Base64.getEncoder().withoutPadding().encodeToString(input.getBytes());
    }

    public static String base64ToString(String input) {
        return new String(Base64.getDecoder().decode(input));
    }

}
