package me.aaron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel{

    private JButton stockButton;
    private JButton customerButton;
    private JButton updateCustomerButton;
    public Menu() {

        JFrame frame = new JFrame("Menu");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);

        customerButton = new JButton("Customer");
        add(customerButton);
customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent customerButton){
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) customerButton.getSource());
                currentFrame.dispose();
                JFrame frame = new JFrame("Customer Menu");
                CustomerMenu customerMenu = new CustomerMenu();
                frame.add(customerMenu);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        stockButton = new JButton("Stock");
        add(stockButton);
        stockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent addStockButton) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) addStockButton.getSource());
                currentFrame.dispose();
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
