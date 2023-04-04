package me.aaron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerMenu extends JPanel{
    private JButton addCustomerButton;
    private JButton updateCustomerButton;
    private JButton deleteCustomerButton;
    private JButton viewCustomerButton;
    private JButton returnButton;
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

        deleteCustomerButton = new JButton("Delete Customer Screen");
        add(deleteCustomerButton);

        deleteCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent deleteCustomerButton) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) deleteCustomerButton.getSource());
                currentFrame.dispose();
                JFrame frame = new JFrame("Delete Customer");
                DeleteCustomer DeleteCustomer = new DeleteCustomer();
                frame.add(DeleteCustomer);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        viewCustomerButton = new JButton("View Customer Screen");
        add(viewCustomerButton);
        viewCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent viewCustomerButton) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) viewCustomerButton.getSource());
                currentFrame.dispose();
                JFrame frame = new JFrame("View Customer Information");
                ViewCustomers viewCustomers = new ViewCustomers();
                frame.add(viewCustomers);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        returnButton = new JButton("Main Menu");
        add(returnButton);
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent returnButton) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) returnButton.getSource());
                currentFrame.dispose();
                JFrame frame = new JFrame("Main Menu");
                Menu menu = new Menu();
                frame.add(menu);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
