import java.io.*;
import java.util.Scanner;

public class HRManager extends Person {

    private static final String HR_FILE = "hr_accounts.txt";

    public HRManager(int id, String name, String email) {
        super(id, name, email);
    }

    public static boolean validateHR(String enteredUsername, String enteredPassword) {
        try (Scanner scanner = new Scanner(new File(HR_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];

                if (username.equals(enteredUsername) && password.equals(enteredPassword)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void saveHR(String newUsername, String newPassword) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(HR_FILE, true))) {
            String hrAccount = newUsername + "," + newPassword;
            writer.println(hrAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkHRFile() {
        File file = new File(HR_FILE);
        return file.exists() && !file.isDirectory();
    }

    private static void createHRFile() {
        try {
            File file = new File(HR_FILE);
            if (file.createNewFile()) {
                System.out.println("HR accounts file created: " + HR_FILE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean validateHRFile() {
        if (!checkHRFile()) {
            createHRFile();
        }
        return true;
    }
}
