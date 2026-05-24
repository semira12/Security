import java.util.Scanner;
import java.util.regex.Pattern;

public class PasswordChecker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        int score = 0;

        if (password.length() >= 8) {
            score++;
        }

        if (Pattern.compile("[A-Z]").matcher(password).find()) {
            score++;
        }

        if (Pattern.compile("[a-z]").matcher(password).find()) {
            score++;
        }

        if (Pattern.compile("[0-9]").matcher(password).find()) {
            score++;
        }

        if (Pattern.compile("[^A-Za-z0-9]").matcher(password).find()) {
            score++;
        }

        if (score <= 2) {
            System.out.println("Weak Password");
        }
        else if (score <= 4) {
            System.out.println("Medium Password");
        }
        else {
            System.out.println("Strong Password");
        }
    }
}