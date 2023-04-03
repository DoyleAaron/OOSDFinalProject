package me.aaron;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class StockUpdate extends JPanel{
    private JComboBox<Integer> stockIDField;
    private JTextField itemNameField;
    private JTextField itemQuantityField;
    private JTextField itemPriceField;
    private JButton submitButton;
    private JButton menuButton;
    PreparedStatement pstat = null;

    public StockUpdate(){
        JFrame frame = new JFrame("Update Stock Details");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);

        JLabel stockIDLabel = new JLabel("Select Stock Item ID:");
        add(stockIDLabel, gbc);

        gbc.gridy = 1;
        JLabel itemNameLabel = new JLabel("Item Name:");
        add(itemNameLabel, gbc);

        gbc.gridy = 2;
        JLabel itemQuantityLabel = new JLabel("Item Quantity:");
        add(itemQuantityLabel, gbc);

        gbc.gridy = 3;
        JLabel itemPriceLabel = new JLabel("Item Price:");
        add(itemPriceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        stockIDField = new JComboBox();
        add(stockIDField, gbc);

        try {
            PreparedStatement stockIDQuery = Main.sql.prepareStatement("SELECT stockID FROM Stock");
            // This query is used to get all the stock IDs from the stock table
            ResultSet stockIDs = stockIDQuery.executeQuery();
            // This is putting all the stock IDs into a result set called stockIDs

            while (stockIDs.next()) {
                int stockID = stockIDs.getInt("stockID");
                stockIDField.addItem(stockID);
            }
            //This while loop is iterating through the result set and assigning each stock ID into the combo box
        } catch (SQLException stockIDQuery) {
            stockIDQuery.printStackTrace();
        }

        gbc.gridy = 1;
        itemNameField = new JTextField();
        add(itemNameField, gbc);

        gbc.gridy = 2;
        itemQuantityField = new JTextField();
        add(itemQuantityField, gbc);

        gbc.gridy = 3;
        itemPriceField = new JTextField();
        add(itemPriceField, gbc);

        gbc.gridy = 4;
        submitButton = new JButton("Submit");
        add(submitButton, gbc);
        // Submit button

        gbc.gridy = 5;
        menuButton = new JButton("Return To Menu");
        add(menuButton, gbc);
        // Return to menu button

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent stockUpdateEvent) {
                String itemName = itemNameField.getText();
                String itemQuantity = itemQuantityField.getText();
                String itemPrice = itemPriceField.getText();
                //This is getting the values from the text fields and assigning them to variables
                pstat = Main.sql.prepareStatement("UPDATE Stock SET itemName = ?, itemQuantity = ?, itemPrice = ? WHERE stockID = ?");
                //This is the SQL query for updating the stock information in the table
                try {
                    pstat.setString(1, itemName);
                    pstat.setString(2, itemQuantity);
                    pstat.setString(3, itemPrice);
                    pstat.setInt(4, (int) stockIDField.getSelectedItem());
                    pstat.executeUpdate();
                    //This is assigning the values from the text fields for the SQL query
                    JOptionPane.showMessageDialog(StockUpdate.this, "Stock Data updated successfully.");
                    itemNameField.setText("");
                    itemQuantityField.setText("");
                    itemPriceField.setText("");
                    //This is clearing the text fields after the data has been updated
                } catch (SQLException stockUpdateException) {
                    stockUpdateException.printStackTrace();
                }
            }
        });

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent stockMenuButtonEvent) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) stockMenuButtonEvent.getSource());
                currentFrame.dispose();
                JFrame frame = new JFrame("Stock Menu");
                StockMenu StockMenu = new StockMenu();
                frame.add(StockMenu);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
            // This is the action button for the menu button which closes the current window and loads the stock menu
        });
    }
}
