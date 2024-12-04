package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class LoginApp extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";

    public LoginApp() {
        setTitle("Login Screen");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        // Email Label and Text Field
        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        // Password Label and Password Field
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());
        panel.add(loginButton);

        add(panel);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword()); // Password is ignored for validation

            String userName = authenticateUser(email, password);
            if (userName != null) {
                JOptionPane.showMessageDialog(null, "Welcome, " + userName + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "User not found.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//    private String authenticateUser(String email) {
//        String userName = null;
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
//            String query = "SELECT name FROM User WHERE Email = ?";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, email);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                userName = rs.getString("Name");
//            }
//            rs.close();
//            stmt.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return userName;
//    }

//    public String authenticateUser(String email, String password) {
//        String userName = null;
//
//        Map<String, String> users = new HashMap<>();
//        users.put("johndoe@example.com", "John Doe");
//        users.put("janesmith@example.com", "Jane Smith");
//        users.put("mikejohnson@example.com", "Mike Johnson");
//        users.put("alicebrown@example.com", "Alice Brown");
//        users.put("tomclark@example.com", "Tom Clark");
//
//        try {
//            if (users.containsKey(email)) {
////                userName = users.get(email);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return userName;
//    }

    public static String authenticateUser(String email, String password) {
        String userName = null;

        // Hardcoded user data
        Map<String, String[]> users = new HashMap<>();
        users.put("johndoe@example.com", new String[]{"John Doe", "password123"});
        users.put("janesmith@example.com", new String[]{"Jane Smith", "password456"});
        users.put("mikejohnson@example.com", new String[]{"Mike Johnson", "password789"});
        users.put("alicebrown@example.com", new String[]{"Alice Brown", "password101"});
        users.put("tomclark@example.com", new String[]{"Tom Clark", "password202"});

        // Validate email and password input
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            System.err.println("Email and password cannot be null or empty.");
            return null;
        }

        // Validate email format
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            System.err.println("Invalid email format.");
            return null;
        }

        // Authentication logic with hardcoded data
        if (users.containsKey(email)) {
            String[] userData = users.get(email);
            String storedPassword = userData[1];
            userName = userData[0];

            if (!storedPassword.equals(password)) {
                System.err.println("Password does not match.");
                return null;
            }
        } else {
            System.err.println("No user found with the provided email.");
            return null;
        }

        return userName;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginApp loginApp = new LoginApp();
            loginApp.setVisible(true);
        });
    }
}
