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


public class AddStockItem extends JPanel{

    private JTextField stockItemNameField;
    private JTextField stockItemQuantityField;
    private JTextField stockItemPriceField;
    private JButton submitButton;
    PreparedStatement pstat = null;
    public AddStockItem() {
        JFrame frame = new JFrame("Add Stock Item");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);
        JLabel stockItemNameLabel = new JLabel("Stock Item Name:");
        add(stockItemNameLabel, gbc);

        gbc.gridy = 1;
        JLabel stockItemQuantityLabel = new JLabel("Stock Item Quantity:");
        add(stockItemQuantityLabel, gbc);

        gbc.gridy = 2;
        JLabel stockItemPriceLabel = new JLabel("Stock Item Price:");
        add(stockItemPriceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        stockItemNameField = new JTextField();
        add(stockItemNameField, gbc);

        gbc.gridy = 1;
        stockItemQuantityField = new JTextField();
        add(stockItemQuantityField, gbc);

        gbc.gridy = 2;
        stockItemPriceField = new JTextField();
        add(stockItemPriceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        submitButton = new JButton("Submit Item");
        add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName = stockItemNameField.getText();
                String itemQuantity = stockItemQuantityField.getText();
                String itemPrice = stockItemPriceField.getText();

                pstat = Main.sql.prepareStatement("INSERT INTO stock (name, quantity, price) VALUES (?, ?, ?)");
                try{
                    pstat.setString(1, itemName);
                    pstat.setString(2, itemQuantity);
                    pstat.setString(3, itemPrice);
                    pstat.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
