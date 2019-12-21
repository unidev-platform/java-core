package com.unidev.platform.encryption;

import com.unidev.platform.common.exception.UnidevRuntimeException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.utils.Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Simple text encryptor
 */
@Slf4j
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

        try (CryptoCipher encipher = Utils.getCipherInstance(transform, properties)) {

            byte[] data = getUTF8Bytes(input);
            int size = Math.round(data.length / 1024f) * 1024 + 1024;
            ByteBuffer inBuffer = ByteBuffer.allocateDirect(size);
            outBuffer = ByteBuffer.allocateDirect(size);
            inBuffer.put(data);
            inBuffer.flip();
            encipher.init(Cipher.ENCRYPT_MODE, key, iv);
            encipher.update(inBuffer, outBuffer);
            encipher.doFinal(inBuffer, outBuffer);
            outBuffer.flip();
            byte[] array = new byte[outBuffer.remaining()];
            outBuffer.get(array);

            return Base64Utils.byteArrayToBase64(array);

        } catch (Exception e) {
            log.error("Failed to do encoding", e);
            throw new UnidevRuntimeException(e);
        }
    }

    /**
     * Decode base64 string
     * @param string
     * @return
     */
    public String decrypt(String string) {
        byte[] data = Base64Utils.base64ToByteArray(string);
        int size = Math.round(data.length / 1024f) * 1024 + 1024;
        ByteBuffer inBuffer = ByteBuffer.allocateDirect(size);
        inBuffer.put(data);
        inBuffer.flip();
        Properties properties = new Properties();
        try (CryptoCipher decipher = Utils.getCipherInstance(transform, properties)) {
            decipher.init(Cipher.DECRYPT_MODE, key, iv);
            ByteBuffer decoded = ByteBuffer.allocateDirect(size);
            decipher.update(inBuffer, decoded);
            decipher.doFinal(inBuffer, decoded);
            decoded.flip();
            return asString(decoded);
        } catch (Exception e) {
            log.error("Failed to decode", e);
            throw new UnidevRuntimeException(e);
        }
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
