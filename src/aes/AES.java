package aes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AES extends  {

    private static final String ALGORITHM = "aes.AES/CBC/PKCS5PADDING";
    private static Key KEY;
    private static IvParameterSpec IV;

    public AES() {
        final int blockSize = 128;
        final int n = 16;
        SecureRandom keyRand = new SecureRandom();
        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance("aes.AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert generator != null;
        generator.init(blockSize, keyRand);
        KEY = generator.generateKey();
        SecureRandom ivRand = new SecureRandom();
        byte[] iv = new byte[n];
        ivRand.nextBytes(iv);
        IV = new IvParameterSpec(iv);
    }

   public String encryption(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, KEY, IV);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   public String decryption(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, KEY, IV);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(cipherText));
            return new String(original);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}