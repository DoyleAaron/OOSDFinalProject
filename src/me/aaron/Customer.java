package me.aaron;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;


public class Customer extends JPanel{
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField DOBField;
    public Customer() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridLayout(0, 1));
        firstNameField = new JTextField(10);
        String firstName = firstNameField.getText();
        add(firstNameField);

    }

}