import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

public class SecureAuthenticationWithOTP_GUI {

    private static String senderEmail = "rushikesh11223344@gmail.com";
    private static String senderPassword = "rushi@112";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Secure Authentication with OTP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        placeComponents(panel);
        frame.add(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel emailLabel = new JLabel("Enter your email:");
        emailLabel.setBounds(10, 20, 150, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(160, 20, 200, 25);
        panel.add(emailText);

        JButton sendOTPButton = new JButton("Send OTP");
        sendOTPButton.setBounds(10, 50, 150, 25);
        panel.add(sendOTPButton);

        JLabel otpLabel = new JLabel("Enter OTP:");
        otpLabel.setBounds(10, 80, 150, 25);
        panel.add(otpLabel);

        JTextField otpText = new JTextField(20);
        otpText.setBounds(160, 80, 200, 25);
        panel.add(otpText);

        JButton authenticateButton = new JButton("Authenticate");
        authenticateButton.setBounds(10, 110, 150, 25);
        panel.add(authenticateButton);

        sendOTPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = emailText.getText();
                String otp = generateOTP();
                sendOTPEmail(userEmail, otp);
                JOptionPane.showMessageDialog(null, "OTP sent to your email.");
            }
        });

        authenticateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = emailText.getText();
                String userInputOTP = otpText.getText();
                String generatedOTP = retrieveGeneratedOTP(userEmail);

                if (userInputOTP.equals(generatedOTP)) {
                    JOptionPane.showMessageDialog(null, "Authentication successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Authentication failed. Invalid OTP.");
                }
            }
        });
    }

    private static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private static void sendOTPEmail(String userEmail, String otp) {
        // Similar to the previous example
        // Implement the send email functionality using JavaMail API
        // (See the sendOTPEmail method in the previous example)
    }

    private static String retrieveGeneratedOTP(String userEmail) {
        // In a real-world scenario, you would store the generated OTPs
        // in a secure way, such as in a database, instead of a simple method
        // like this. For demonstration purposes, this example returns a fixed value.
        return "123456";
    }
}
