package AES;

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

    //iv file path
    private final File ivPathDirectory = new File("assets");
    private final File ivPath = new File(ivPathDirectory.getAbsolutePath(), "iv");

    //k file path
    private final File kPathDirectory = new File("assets");
    private final File kPath = new File(kPathDirectory.getAbsolutePath(), "iv");

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

    public File fileEncryption(File directory, String fileName) throws IOException {
        if (!directory.isDirectory()) {
            System.err.println("This is not a directory!");
            return null;
        }
        File file = null;
        File[] files = directory.listFiles();
        assert files != null;
        for (File value : files) {
            if (value.getName().equals(fileName)) {
                file = value;
                break;
            }
        }
        if (file == null) {
            System.err.println("There is no file with this information in this directory!");
            return null;
        }
        String text;
        if (isTextFile(file)) {
            text = readFile(file);
            if (text.equals("")) {
                System.err.println("file is empty!");
                return null;
            }
            fileName = "Encrypted_" + fileName;
            System.out.println("Your file is encrypted now!\n");
            File EF = writeFile(directory, fileName, encryption(text));
            EF.setReadOnly();
            return EF;
        }
        if (file.getName().contains(".bin")) {
            text = readBinaryFile(file);
            if (text.equals("")) {
                System.err.println("file is empty!");
                return null;
            }
            fileName = "Encrypted_" + fileName;
            System.out.println("Your file is encrypted now!\n");
            File EF = writeBinaryFile(directory, fileName, encryption(text));
            EF.setReadOnly();
            return EF;
        }
        return null;
    }

    public File fileDecryption(File directory, String fileName) throws IOException {
        String tempName = fileName;
        fileName = "Encrypted_" + fileName;
        if (!directory.isDirectory()) {
            System.err.println("This is not a directory!");
            return null;
        }
        File encryptedFile = null;
        File[] files = directory.listFiles();
        assert files != null;
        for (File value : files) {
            if (value.getName().equals(fileName)) {
                encryptedFile = value;
                break;
            }
        }
        if (encryptedFile == null) {
            System.err.println("There is no file with this information in this directory!");
            return null;
        }
        String encrypted;
        if (isTextFile(encryptedFile)) {
            encrypted = readFile(encryptedFile);
            if (encrypted.equals("")) {
                System.err.println("file is empty!");
                return null;
            }
            String finalName = "Decrypted_" + tempName;
            System.out.println("Your file is decrypted now!\n");
            return writeFile(directory, finalName, decryption(encrypted));
        }
        if (isBinaryFile(encryptedFile)) {
            encrypted = readBinaryFile(encryptedFile);
            if (encrypted.equals("")) {
                System.err.println("file is empty!");
                return null;
            }
            String finalName = "Decrypted_" + tempName;
            System.out.println("Your file is decrypted now!\n");
            return writeFile(directory, finalName, decryption(encrypted));
        }
        return null;
    }
}