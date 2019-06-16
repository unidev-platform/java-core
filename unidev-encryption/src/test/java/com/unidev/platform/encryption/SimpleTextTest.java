package com.unidev.platform.encryption;

import org.junit.jupiter.api.Test;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static com.unidev.platform.encryption.SimpleTextCryptor.getUTF8Bytes;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SimpleTextTest {

    @Test
    public void testEncryption() {

        SimpleTextCryptor simpleTextCryptor = new SimpleTextCryptor();
        simpleTextCryptor.setKey(new SecretKeySpec(getUTF8Bytes("1234567890123456"), "AES"));
        simpleTextCryptor.setIv(new IvParameterSpec(getUTF8Bytes("1234567890123456")));

        String input = "encryption test " + System.currentTimeMillis();

        String encryptedValue = simpleTextCryptor.encrypt(input);
        String decryptedValue = simpleTextCryptor.decrypt(encryptedValue);

        assertEquals(input, decryptedValue);
    }

}
