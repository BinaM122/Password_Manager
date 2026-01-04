package main;
import javax.swing.*;
import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PasswordManager {
    private static final String DATA_FILE = "passwords.txt"; //Creates the text file that will save all of the website names and the passwords.
    private List<PasswordEntry> store = new ArrayList<>(); //arrayList that has the website and password in it.
    private SecureRandom random = new SecureRandom();  //This is what is going to be used to randomly assign the password.

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordManager().run());//Makes sure that the GUI and the code itself don't overlap and cause errors.
    }

    private void run() {
        loadPasswords();

        String[] options = {"Add Password", "Remove Password", "View All Passwords", "Exit"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(
                null,
                "What would you like to do?",
                "Bina's Password Manager",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
            );
            //each number is representing a button on the program, and will take the user to the corresponding method.
            if (choice == 0) {
                addPassword();
            } else if (choice == 1) {
                removePassword();
            } else if (choice == 2) {
                viewAll();
            } else {
                break;  
            }
        }
        System.exit(0);
    }

    private void loadPasswords() {//opens the password file if it exists`
        File file = new File(DATA_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    store.add(new PasswordEntry(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading passwords: " + e.getMessage());
        }
    }

    private void savePasswords() { //creates the file if it does not already exist.
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (PasswordEntry entry : store) {
                writer.println(entry.website + "," + entry.password);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving passwords: " + e.getMessage());
        }
    }

    private void addPassword() { // lets the user create a new password.
        String site = JOptionPane.showInputDialog(null, "Enter website/app name:");
        if (site == null || site.isBlank()) return;

        String[] options = {"Manual", "Random"};
        int option = JOptionPane.showOptionDialog(
            null,
            "Add password manually or generate randomly?",
            "Choose Mode",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        String pwrd;
        if (option == 0) {
            pwrd = JOptionPane.showInputDialog(null, "Enter password for " + site + ":");
            if (pwrd == null) return;
        } else {
            pwrd = generateRandomPassword(12);
            JOptionPane.showMessageDialog(null, "Generated password:\n" + pwrd);
        }

        store.add(new PasswordEntry(site, pwrd));
        savePasswords();
        JOptionPane.showMessageDialog(null, "Password saved for " + site + ".");
    }

    private void removePassword() {	//removes a password based on the website name the user put in.
        String website = JOptionPane.showInputDialog(null, "Enter website/app name to remove:");
        if (website == null || website.isBlank()) return;

        boolean removed = false;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).website.equals(website)) {
                store.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            savePasswords();
            JOptionPane.showMessageDialog(null, "Removed password for " + website + ".");
        } else {
            JOptionPane.showMessageDialog(null, "No entry found for " + website + ".");
        }
    }

    private void viewAll() {//displays all of the passwords that have been saved to the file.
        if (store.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No passwords stored.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (PasswordEntry entry : store) {
            sb.append(entry.website).append(" : ").append(entry.password).append("\n");
        }
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new java.awt.Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scroll, "All Saved Passwords", JOptionPane.INFORMATION_MESSAGE);
    }

    private String generateRandomPassword(int length) {//this is what can be generated in the random password.
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz"
            + "0123456789"
            + "!@#$%^&*()-_=+";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private static class PasswordEntry {//defines website and password
        String website;
        String password;
        PasswordEntry(String website, String password) {
            this.website = website;
            this.password = password;
        }
    }
}
