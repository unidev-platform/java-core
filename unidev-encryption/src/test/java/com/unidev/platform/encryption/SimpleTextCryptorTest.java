package com.unidev.platform.encryption;

import org.junit.jupiter.api.Test;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static com.unidev.platform.encryption.SimpleTextCryptor.getUTF8Bytes;

public class SimpleTextCryptorTest {

    @Test
    public void testEncryption() {

        SimpleTextCryptor simpleTextCryptor = new SimpleTextCryptor();
        simpleTextCryptor.setKey(new SecretKeySpec(getUTF8Bytes("1234567890123456"), "AES"));
        simpleTextCryptor.setIv(new IvParameterSpec(getUTF8Bytes("1234567890123456")));

        simpleTextCryptor.encrypt("potato");

    }

}
