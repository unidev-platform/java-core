package com.unidev.platform.encryption;

import java.util.Base64;

public class Base64Utils {

    public static byte[] base64ToByteArray(String input) {
        return Base64.getDecoder().decode(input);
    }

    public static String byteArrayToBase64(byte[] input) {
        return new String(Base64.getEncoder().encode(input));
    }

}
