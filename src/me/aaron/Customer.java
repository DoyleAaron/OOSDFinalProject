package me.aaron;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import javax.swing.*;


public class Customer {
    public Customer(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        JTextField firstNameField = new JTextField();
        String firstName = firstNameField.getText();


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Customer");
        frame.pack();
        frame.setVisible(true);

    }

}