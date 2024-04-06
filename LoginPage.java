import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel panel;

    public LoginPage() {
        setTitle("Login Page");
        setSize(1000, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the image
        ImageIcon backgroundImage = new ImageIcon("Login Image.jpeg");

        // Create a JLabel and set its bounds to cover the entire frame
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // Set layout to null to allow free positioning of components
        getContentPane().setLayout(null);

        // Add the background label to the content pane at index 0
        getContentPane().add(backgroundLabel, 0);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set the background color to light beige
                Color beigeColor = new Color(223,204,166);
                g.setColor(beigeColor);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);
        panel.setBounds(0, 0, getWidth(), getHeight()); // Set panel bounds to cover the entire frame
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {

        add(panel);
        int panelWidth = panel.getWidth();

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds((panelWidth - 80) / 2 - 125, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds((panelWidth - 165) / 2, 20, 180, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds((panelWidth - 80) / 2 - 125, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds((panelWidth - 165) / 2, 50, 180, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.GREEN); // Set background color to green
        JButton createHRButton = new JButton("Create HR Account");
        createHRButton.setBackground(new Color(0, 0, 139)); // Dark blue: RGB(0, 0, 139)
        createHRButton.setForeground(Color.WHITE); // White text color

        loginButton.setBounds((panelWidth - 80) / 2 - 125, 80, 80, 25);
        createHRButton.setBounds((panelWidth - 165) / 2 + 14, 80, 165, 25);

        panel.add(loginButton);
        panel.add(createHRButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        createHRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createHRAccount();
            }
        });
    }

    private void performLogin() {
        String enteredUsername = usernameField.getText();
        String enteredPassword = new String(passwordField.getPassword());

        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty");
            return;
        }

        if (HRManager.validateHR(enteredUsername, enteredPassword)) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(LoginPage.this, "Login Successful as HR");
                new HRDashboard().setVisible(true);
                dispose(); // Close the login page
            });
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }

    private void createHRAccount() {
        String newUsername = JOptionPane.showInputDialog("Enter new HR username:");
        String newPassword = JOptionPane.showInputDialog("Enter new HR password:");

        HRManager.saveHR(newUsername, newPassword);
        JOptionPane.showMessageDialog(this, "HR Account created successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage());
    }
}
