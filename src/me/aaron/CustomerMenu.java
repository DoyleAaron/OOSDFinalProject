package me.aaron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerMenu extends JPanel{
    private JButton addCustomerButton;
    private JButton updateCustomerButton;
    public CustomerMenu() {
        JFrame frame = new JFrame("Menu");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);


        addCustomerButton = new JButton("Add Customer Screen");
        add(addCustomerButton);
        addCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent addCustomerButton) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) addCustomerButton.getSource());
                currentFrame.dispose();
                JFrame frame = new JFrame("Add Customer");
                Customer customer = new Customer();
                frame.add(customer);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        updateCustomerButton = new JButton("Update Customer Screen");
        add(updateCustomerButton);
        updateCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent updateCustomerButton) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) updateCustomerButton.getSource());
                currentFrame.dispose();
                JFrame frame = new JFrame("Update Customer");
                CustomerUpdate updateCustomer = new CustomerUpdate();
                frame.add(updateCustomer);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
