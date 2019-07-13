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

    //give to this iv file path
    private final File ivPath = new File("C:\\Users\\Baha2r\\IdeaProjects\\AES\\src\\assets", "iv");

    //give to this k file path
    private final File kPath = new File("C:\\Users\\Baha2r\\IdeaProjects\\AES\\src\\assets", "k");

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
//        ivPath.setReadable(true, true);
//        kPath.setReadable(true, true);
        ivPath.setReadOnly();
        kPath.setReadOnly();
        IV = new IvParameterSpec(ivReader().getBytes(StandardCharsets.UTF_8));
        KEY = new SecretKeySpec(keyReader().getBytes(StandardCharsets.UTF_8), "AES");
    }

    private String ivReader() throws IOException {
        return readInnerBinaryFile(ivPath);
    }

    private String keyReader() throws IOException {
        return readInnerBinaryFile(kPath);
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