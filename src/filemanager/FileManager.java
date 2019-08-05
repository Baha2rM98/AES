package filemanager;

import java.io.*;
import java.util.Scanner;

public abstract class FileManager {

    protected FileManager() {
    }

    private File creatFile(File directory, String fileName) throws IOException {
        if (!directory.isDirectory()) {
            System.err.println("This is not a directory!");
            return null;
        }
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    private File creatBinaryFile(File directory, String fileName) throws IOException {
        if (!directory.isDirectory()) {
            System.err.println("This is not a directory!");
            return null;
        }
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    protected String readInnerBinaryFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        final int size = ois.available();
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            bytes[i] = ois.readByte();
        }
        byte[] buff = CharacterHider.show(bytes);
        ois.close();
        fis.close();
        return new String(buff);
    }

    protected File writeBinaryFile(File directory, String fileName, String text) throws IOException {
        File file = creatBinaryFile(directory, fileName);
        assert file != null;
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.write(text.getBytes());
        os.flush();
        os.close();
        fos.flush();
        fos.close();
        return file;
    }

    protected String readBinaryFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        final int size = ois.available();
        byte[] buff = new byte[size];
        for (int i = 0; i < size; i++) {
            buff[i] = ois.readByte();
        }
        ois.close();
        fis.close();
        return new String(buff);
    }

    protected File writeFile(File directory, String fileName, String text) throws IOException {
        File file = creatFile(directory, fileName);
        assert file != null;
        FileWriter fw = new FileWriter(file);
        fw.write(text);
        fw.flush();
        fw.close();
        return file;
    }

    protected String readFile(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        StringBuilder textBuilder = new StringBuilder();
        while (reader.hasNextLine()) {
            textBuilder.append(reader.nextLine());
        }
        return textBuilder.toString();
    }

//    public void deleteFile(File file) {
//        if (!file.exists()) {
//            System.err.println("There is nothing to delete!");
//            return;
//        }
//        if (file.exists())
//            file.delete();
//    }

    protected boolean isTextFile(File file) {
        return file.getName().contains(".txt");
    }

    protected boolean isBinaryFile(File file) {
        return file.getName().contains(".bin");
    }
}
