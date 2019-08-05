import aes.AES;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AESMain {
    private static File path;
    private static String name;

    public static void main(String[] args) throws IOException {
        AES aes = new AES();
        Scanner scn = new Scanner(System.in);
        System.out.println("Welcome!\nThis program uses for encrypting files!\n");
        while (true) {
            System.out.println("Choose one the options below:\n1) encryption mode\n2) decryption mode\n3) exit");
            String ans = scn.next();
            if (ans.equals("1")) {
                System.out.println("welcome to encryption mode!");
                System.out.println("Enter the path of your file's directory (for example C:\\\\User\\\\...)\nEnter q to exit");
                String way = scn.next();
                if (way.toLowerCase().equals("q"))
                    exit();
                path = new File(way);
                System.out.println("Enter your file name with suffix (for example .txt .bin and etc)\nEnter q to exit");
                name = scn.next();
                if (name.toLowerCase().equals("q"))
                    exit();
                aes.fileEncryption(path, name);
            }
            if (ans.equals("2")) {
                System.out.println("welcome to decryption mode!");
                System.out.println("Enter the path of your encrypted file's directory (for example C:\\\\User\\\\...)\nEnter q to exit");
                String way = scn.next();
                if (way.toLowerCase().equals("q"))
                    exit();
                path = new File(way);
                System.out.println("Enter your original file name with suffix (for example .txt .bin and etc)\nEnter q to exit");
                name = scn.next();
                if (name.toLowerCase().equals("q"))
                    exit();
                aes.fileDecryption(path, name);
            }
            if (ans.equals("3"))
                exit();
        }
    }

    private static void exit() {
        System.exit(-1);
    }
}