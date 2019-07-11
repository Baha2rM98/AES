package aes;

import fileManager.FileManager;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Baha2r
 **/

public class AES extends FileManager {

    private final String ALGORITHM = "AES/CBC/PKCS5PADDING";
    private final File ivPath = new File("C:\\Users\\Baha2r\\IdeaProjects\\AES\\src\\assets", "iv.bin");
    private final File kPath = new File("C:\\Users\\Baha2r\\IdeaProjects\\AES\\src\\assets", "k.bin");
    private SecretKeySpec KEY;
    private IvParameterSpec IV;

//    /**
//     * This constructor is used for generating different Key and ivVector
//     **/
//    public AES() {
//        super();
//        final int blockSize = 128;
//        final int n = 16;
//        SecureRandom keyRand = new SecureRandom();
//        KeyGenerator generator = null;
//        try {
//            generator = KeyGenerator.getInstance("AES");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        assert generator != null;
//        generator.init(blockSize, keyRand);
//        KEY = generator.generateKey();
//        SecureRandom ivRand = new SecureRandom();
//        byte[] iv = new byte[n];
//        ivRand.nextBytes(iv);
//        IV = new IvParameterSpec(iv);
//    }

    /**
     * This constructor is used for preparing Key and ivVector thad are already exist
     **/
    public AES() throws IOException {
        IV = new IvParameterSpec(ivReader().getBytes(StandardCharsets.UTF_8));
        KEY = new SecretKeySpec(keyReader().getBytes(StandardCharsets.UTF_8), ALGORITHM);
    }

    public String ivReader() throws IOException {
        return readBinaryFile(ivPath);
    }

    public String keyReader() throws IOException {
        return readBinaryFile(kPath);
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