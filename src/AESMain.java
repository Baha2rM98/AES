import AES.AES;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AESMain {
    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);
        AES aes = new AES();
        System.out.println("Welcome!\nThis program uses for encrypting files!\n");
        System.out.println("For using string mod enter 1, For using file mod enter 2, For exit enter 0.");
        String ans = scn.next();
        if (ans.equals("1")) {
            while (true) {
                System.out.println("Enter Text : (enter q or Q for exit)");
                String text = scn.nextLine();
                if (text.equals("q") || text.equals("Q")) {
                    System.out.println("Terminated!");
                    break;
                } else {
                    String e = aes.encryption(text);
                    System.out.println(e);
                    String d = aes.decryption(e);
                    System.out.println(d + "\n");
                }
            }
        }
        if (ans.equals("2")) {
            while (true) {
                System.out.println("Enter the path of your file's directory (for example C:\\\\User\\\\...)\nEnter q or Q for exit");
                String way = scn.next();
                if (way.equals("q") || way.equals("Q")) {
                    System.out.println("Terminated!");
                    break;
                } else {
                    File path = new File(way);
                    System.out.println("Enter your file name with suffix (for example .txt .bin and...)");
                    String name = scn.next();
                    aes.fileEncryption(path, name);
                    System.out.println("Do you wanna decrypt this file?\nPress y or n...");
                    String res = scn.next();
                    if (res.equals("y") || res.equals("Y")) {
                        aes.fileDecryption(path, name);
                    } else {
                        System.out.println("Ops! you haven't chosen y!\nTerminated...");
                        break;
                    }
                }
            }
        }
    }
}