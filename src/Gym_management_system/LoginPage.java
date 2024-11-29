package Gym_Management_system;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {
    JLabel titleLabel, userLabel, passwordLabel;
    JTextField userTextField;
    JPasswordField passwordField;
    JPanel p1, p2;
    JButton loginButton, cancelButton;
    Font f, f1;
    Connection connection;

    public LoginPage() {
        super("Login");

        setLocation(50, 50);
        setSize(400, 200);

        f = new Font("Arial", Font.BOLD, 20);
        f1 = new Font("Arial", Font.BOLD, 15);

        titleLabel = new JLabel("Welcome To Mussi Fitness Point");
        userLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.RED);
        userLabel.setForeground(Color.BLUE);
        passwordLabel.setForeground(Color.BLUE);

        titleLabel.setFont(f);
        userLabel.setFont(f1);
        passwordLabel.setFont(f1);

        userTextField = new JTextField();
        passwordField = new JPasswordField();

        userTextField.setFont(f1);
        passwordField.setFont(f1);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Gym_Management_system/Local/login_2.png"));
        Image img2 = img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imgLabel = new JLabel(img3);

        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);

        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);

        loginButton.setFont(f1);
        cancelButton.setFont(f1);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(3, 2, 10, 10));
        p1.add(userLabel);
        p1.add(userTextField);
        p1.add(passwordLabel);
        p1.add(passwordField);
        p1.add(loginButton);
        p1.add(cancelButton);

        setLayout(new BorderLayout(30, 30));

        add(titleLabel, BorderLayout.NORTH);
        add(imgLabel, BorderLayout.EAST);
        add(p1, BorderLayout.CENTER);

        // Initialize the connection
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = userTextField.getText();
        String pass = new String(passwordField.getPassword());
        if (e.getSource() == loginButton) {
            try {
                String q = "SELECT * FROM login WHERE Username = ? AND Password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(q);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, pass);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    new Home_page().setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Not found any data");
                }

                resultSet.close();
                preparedStatement.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == cancelButton) {
            JOptionPane.showMessageDialog(null, "Are you sure to cancel?");
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginPage().setVisible(true);
        });
    }
}
