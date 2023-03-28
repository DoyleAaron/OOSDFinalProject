package me.aaron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel{

    private JButton addStockItemButton;
    private JButton addCustomerButton;
    public Menu() {

        JFrame frame = new JFrame("Add Customer");
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
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Add Customer");
                Customer customer = new Customer();
                frame.add(customer);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });


        addStockItemButton = new JButton("Add Stock Item Screen");
        add(addStockItemButton);
        addStockItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Add Stock Item");
                AddStockItem addStockItem = new AddStockItem();
                frame.add(addStockItem);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });






    }
}
