package me.aaron;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Customer extends JPanel{
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JTextField emailAddressField;
    private JButton submitButton;
    PreparedStatement pstat = null;
    Connection connection = null;

    public Customer() {
        JFrame frame = new JFrame("Add Customer");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);
        JLabel firstNameLabel = new JLabel("First Name:");
        add(firstNameLabel, gbc);

        gbc.gridy = 1;
        JLabel lastNameLabel = new JLabel("Last Name:");
        add(lastNameLabel, gbc);

        gbc.gridy = 2;
        JLabel addressLabel = new JLabel("Address:");
        add(addressLabel, gbc);

        gbc.gridy = 3;
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        add(phoneNumberLabel, gbc);

        gbc.gridy = 4;
        JLabel emailAddressLabel = new JLabel("Email Address:");
        add(emailAddressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        firstNameField = new JTextField();
        add(firstNameField, gbc);

        gbc.gridy = 1;
        lastNameField = new JTextField();
        add(lastNameField, gbc);

        gbc.gridy = 2;
        addressField = new JTextField();
        add(addressField, gbc);

        gbc.gridy = 3;
        phoneNumberField = new JTextField();
        add(phoneNumberField, gbc);

        gbc.gridy = 4;
        emailAddressField = new JTextField();
        add(emailAddressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        submitButton = new JButton("Submit");
        add(submitButton, gbc);

    submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String address = addressField.getText();
                String phoneNumber = phoneNumberField.getText();
                String emailAddress = emailAddressField.getText();
                pstat = Main.sql.prepareStatement("INSERT INTO customer (firstName, lastName, address, phoneNumber, emailAddress) VALUES (?, ?, ?, ?, ?)");
                try {
                    pstat.setString(1, firstName);
                    pstat.setString(2, lastName);
                    pstat.setString(3, address);
                    pstat.setString(4, phoneNumber);
                    pstat.setString(5, emailAddress);
                    pstat.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

}