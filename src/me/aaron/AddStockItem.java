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

    private final JTextField stockItemNameField;
    private final JTextField stockItemQuantityField;
    private final JTextField stockItemPriceField;
    private JButton submitButton;
    private JButton menuButton;
    PreparedStatement pstat = null;
    public AddStockItem() {
        JFrame frame = new JFrame("Add Stock Item");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.ipadx = 5;
        gbc.ipady = 5;
        JLabel stockItemNameLabel = new JLabel("Stock Item Name:");
        add(stockItemNameLabel, gbc);

        gbc.gridy = 1;
        JLabel stockItemQuantityLabel = new JLabel("Stock Item Quantity:");
        add(stockItemQuantityLabel, gbc);

        gbc.gridy = 2;
        JLabel stockItemPriceLabel = new JLabel("Stock Item Price:");
        add(stockItemPriceLabel, gbc);

        //Here I am adding the text fields next to the corresponding labels
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

        //Submit data button
        gbc.gridx = 0;
        gbc.gridy = 5;
        submitButton = new JButton("Submit Item");
        add(submitButton, gbc);

        //Return to menu button
        gbc.gridx = 0;
        gbc.gridy = 6;
        menuButton = new JButton("Return to Menu");
        add(menuButton, gbc);


        //This action listener will submit the data to the database and clear the text fields
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName = stockItemNameField.getText();
                String itemQuantity = stockItemQuantityField.getText();
                String itemPrice = stockItemPriceField.getText();
                // This code is getting the data from the text fields

                pstat = Main.sql.prepareStatement("INSERT INTO stock (itemName, itemQuantity, itemPrice) VALUES (?, ?, ?)");
                // This code is inserting the data into the database
                try{
                    pstat.setString(1, itemName);
                    pstat.setString(2, itemQuantity);
                    pstat.setString(3, itemPrice);
                    pstat.executeUpdate();
                    // This code is assigning the data to strings and executing the query
                    JOptionPane.showMessageDialog(AddStockItem.this, "Data uploaded successfully.");
                    stockItemNameField.setText("");
                    stockItemQuantityField.setText("");
                    stockItemPriceField.setText("");
                    // This code is clearing the text fields after uploading the data
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //This action listener will return the user to the main menu
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
                    currentFrame.dispose();
                    JFrame frame = new JFrame("Stock Menu");
                    StockMenu StockMenu = new StockMenu();
                    frame.add(StockMenu);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
