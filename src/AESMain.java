import aes.AES;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class AESMain {
    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);
        AES aes = new AES();
//        while (true) {
//            System.out.println("Enter your text : (enter q or Q for exit)");
//            String s = scn.nextLine();
//            if (s.equals("q") || s.equals("Q")) {
//                System.out.println("terminated!");
//                break;
//            }
//            String e = aes.encryption(s);
//            System.out.println(e);
//            String d = aes.decryption(e);
//            System.out.println(d + "\n");
//        }
//        File path = new File("C:\\Users\\Baha2r\\Desktop\\Texts", "k");
//        String k = aes.readBinaryFile(path);
//        System.out.println(k);
    }
}
