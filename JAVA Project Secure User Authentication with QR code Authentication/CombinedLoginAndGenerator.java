import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CombinedLoginAndGenerator extends JFrame {

    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;

    private JTextField generatorNameField;
    private JTextField generatorUsernameField;
    private JTextField generatorPasswordField;

    public CombinedLoginAndGenerator() {
        super("Combined Login and Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Login System Tab
        JPanel loginPanel = createLoginPanel();
        tabbedPane.addTab("Login System", null, loginPanel, "Login to the system");

        // User Details Generator Tab
        JPanel generatorPanel = createGeneratorPanel();
        tabbedPane.addTab("User Generator", null, generatorPanel, "Generate new user details");

        add(tabbedPane);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        loginUsernameField = new JTextField();
        loginPasswordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginUsernameField.getText();
                char[] passwordChars = loginPasswordField.getPassword();
                String password = new String(passwordChars);

                // Replace the following condition with your secure authentication logic
                if (isValidLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Clear the password field for security reasons
                loginPasswordField.setText("");
            }
        });

        panel.add(usernameLabel);
        panel.add(loginUsernameField);
        panel.add(passwordLabel);
        panel.add(loginPasswordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        return panel;
    }

    private JPanel createGeneratorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        JLabel generatedUsernameLabel = new JLabel("Generated UserID:");
        JLabel generatedPasswordLabel = new JLabel("Generated Password:");

        generatorNameField = new JTextField();
        generatorUsernameField = new JTextField();
        generatorPasswordField = new JTextField();
        generatorUsernameField.setEditable(false);
        generatorPasswordField.setEditable(false);

        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateUserDetails();
            }
        });

        panel.add(nameLabel);
        panel.add(generatorNameField);
        panel.add(generatedUsernameLabel);
        panel.add(generatorUsernameField);
        panel.add(generatedPasswordLabel);
        panel.add(generatorPasswordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(generateButton);

        return panel;
    }

    private boolean isValidLogin(String username, String password) {
        // Replace this with your secure authentication logic
        // In a real-world scenario, you would check against a database and use secure
        // password hashing
        return username.equals("admin") && password.equals("password");
    }

    private void generateUserDetails() {
        String name = generatorNameField.getText();
        String generatedUsername = generateUsername(name);
        String generatedPassword = generatePassword();

        generatorUsernameField.setText(generatedUsername);
        generatorPasswordField.setText(generatedPassword);
    }

    private String generateUsername(String name) {
        // Generate a simple username based on the first letter of the name and a random
        // number
        Random rand = new Random();
        int randomNum = rand.nextInt(1000);
        return name.substring(0, 1).toLowerCase() + randomNum;
    }

    private String generatePassword() {
        // Generate a simple password with a random combination of letters and numbers
        Random rand = new Random();
        int passwordLength = 8;
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            char randomChar = (char) (rand.nextInt(26) + 'a');
            password.append(randomChar);
        }

        return password.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CombinedLoginAndGenerator().setVisible(true);
            }
        });
    }
}
