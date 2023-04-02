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

    private JComboBox CustomerIDField;
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
    }
}
