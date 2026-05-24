import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SecureLogin {

    static final String USER_FILE = "users.txt";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println(" 1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    register();
                    break;

                case "2":
                    login();
                    break;

                case "3":
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }


    static void register() {

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String hashedPassword = hashPassword(password);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {

            writer.write(username + "," + hashedPassword);
            writer.newLine();

            System.out.println("User registered.");

        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }


    static void login() {

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String hashedPassword = hashPassword(password);

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (username.equals(data[0]) && hashedPassword.equals(data[1])) {
                    System.out.println("Login successful.");
                    return;
                }
            }

            System.out.println("Invalid login.");

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }


    static String hashPassword(String password) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}