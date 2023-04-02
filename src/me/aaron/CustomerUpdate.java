package me.aaron;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerUpdate extends JPanel {

    private JComboBox<Integer> CustomerIDField;
    private JTextField firstNameField;
    private JTextField secondNameField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JTextField emailAddressField;
    private JButton submitButton;
    private JButton menuButton;
    PreparedStatement pstat = null;
    public CustomerUpdate() {
        JFrame frame = new JFrame("Update Customer Details");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);

        JLabel custIDLabel = new JLabel("Select Customer ID:");
        add(custIDLabel, gbc);

        gbc.gridy = 1;
        JLabel firstNameLabel = new JLabel("First Name:");
        add(firstNameLabel, gbc);

        gbc.gridy = 2;
        JLabel secondNameLabel = new JLabel("Last Name:");
        add(secondNameLabel, gbc);

        gbc.gridy = 3;
        JLabel addressLabel = new JLabel("Address:");
        add(addressLabel, gbc);

        gbc.gridy = 4;
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        add(phoneNumberLabel, gbc);

        gbc.gridy = 5;
        JLabel emailAddressLabel = new JLabel("Email Address:");
        add(emailAddressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        CustomerIDField = new JComboBox();
        add(CustomerIDField, gbc);

        gbc.gridy = 1;
        firstNameField = new JTextField();
        add(firstNameField, gbc);

        gbc.gridy = 2;
        secondNameField = new JTextField();
        add(secondNameField, gbc);

        gbc.gridy = 3;
        addressField = new JTextField();
        add(addressField, gbc);

        gbc.gridy = 4;
        phoneNumberField = new JTextField();
        add(phoneNumberField, gbc);

        gbc.gridy = 5;
        emailAddressField = new JTextField();
        add(emailAddressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        submitButton = new JButton("Submit");
        add(submitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        menuButton = new JButton("Return To Menu");
        add(menuButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent sqlUpdateEvent) {
                String firstName = firstNameField.getText();
                String secondName = secondNameField.getText();
                String address = addressField.getText();
                String phoneNumber = phoneNumberField.getText();
                String emailAddress = emailAddressField.getText();
                pstat = Main.sql.prepareStatement("UPDATE customer (firstName, secondName, address, phoneNumber, emailAddress) VALUES (?, ?, ?, ?, ?) WHERE customerID = ?");
                try {
                    
                    pstat.setString(1, firstName);
                    pstat.setString(2, secondName);
                    pstat.setString(3, address);
                    pstat.setString(4, phoneNumber);
                    pstat.setString(5, emailAddress);
                    pstat.executeUpdate();
                    JOptionPane.showMessageDialog(CustomerUpdate.this, "Customer Data updated successfully.");
                    firstNameField.setText("");
                    secondNameField.setText("");
                    addressField.setText("");
                    phoneNumberField.setText("");
                    emailAddressField.setText("");
                } catch (SQLException sqlInsertException) {
                    sqlInsertException.printStackTrace();
                }
            }
        });

    }
}
