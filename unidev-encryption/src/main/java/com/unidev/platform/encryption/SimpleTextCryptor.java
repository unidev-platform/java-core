package com.unidev.platform.encryption;

import com.unidev.platform.common.exception.UnidevRuntimeException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.utils.Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Simple text encryptor
 */
public class SimpleTextCryptor {

    @Getter
    @Setter
    private SecretKeySpec key;

    @Getter
    @Setter
    private IvParameterSpec iv;

    @Getter
    @Setter
    private String transform = "AES/CBC/PKCS5Padding";

    public String encrypt(String input) {

        Properties properties = new Properties();


        final ByteBuffer outBuffer;
        final int bufferSize = 1024;
        final int updateBytes;
        final int finalBytes;

        try (CryptoCipher encipher = Utils.getCipherInstance(transform, properties)) {

            ByteBuffer inBuffer = ByteBuffer.allocateDirect(bufferSize);
            outBuffer = ByteBuffer.allocateDirect(bufferSize);
            inBuffer.put(getUTF8Bytes(input));

            inBuffer.flip(); // ready for the cipher to read it
            // Show the data is there
            System.out.println("inBuffer=" + asString(inBuffer));

            // Initializes the cipher with ENCRYPT_MODE,key and iv.
            encipher.init(Cipher.ENCRYPT_MODE, key, iv);
            // Continues a multiple-part encryption/decryption operation for byte buffer.
            updateBytes = encipher.update(inBuffer, outBuffer);
            System.out.println(updateBytes);

            // We should call do final at the end of encryption/decryption.
            finalBytes = encipher.doFinal(inBuffer, outBuffer);
            System.out.println(finalBytes);

            //return Base64Utils.base64ToString(outBuffer);

        } catch (Exception e) {
            throw new UnidevRuntimeException(e);
        }

        return "";
    }

    public static byte[] getUTF8Bytes(String input) {
        return input.getBytes(StandardCharsets.UTF_8);
    }

    public static String asString(ByteBuffer buffer) {
        final ByteBuffer copy = buffer.duplicate();
        final byte[] bytes = new byte[copy.remaining()];
        copy.get(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

}
