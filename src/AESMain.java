import java.util.Scanner;

public class AESMain {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        AES aes = new AES();
        while (true) {
            System.out.println("Enter your text : (enter q or Q for exit)");
            String s = scn.nextLine();
            if (s.equals("q") || s.equals("Q")) {
                System.out.println("terminated!");
                break;
            }
            String e = aes.encryption(s);
            System.out.println(e);
            String d = aes.decryption(e);
            System.out.println(d + "\n");
        }
    }
}
