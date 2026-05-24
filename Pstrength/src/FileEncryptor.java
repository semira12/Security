import java.util.Scanner;

public class FileEncryptor {

    public static String encrypt(String text, int shift) {

        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            result.append((char)(c + shift));
        }

        return result.toString();
    }

    public static String decrypt(String text, int shift) {

        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            result.append((char)(c - shift));
        }

        return result.toString();
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        int shift = 3;

        String encrypted = encrypt(text, shift);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, shift);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
