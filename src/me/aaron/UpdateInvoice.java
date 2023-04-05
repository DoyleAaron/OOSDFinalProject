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


public class UpdateInvoice extends JPanel{
    private JComboBox<Integer> invoiceIDBox;
    private JTextField itemNameField;
    private JTextField itemPriceField;
    private JTextField itemQuantityField;
    private JButton submitButton;
    private JButton menuButton;
    PreparedStatement pstat = null;
    public UpdateInvoice() {
        JFrame frame = new JFrame("Update Invoice");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);

        JLabel invoiceIDLabel = new JLabel("Select Invoice ID:");
        add(invoiceIDLabel, gbc);

        gbc.gridy = 1;
        JLabel itemNameLabel = new JLabel("Item Name:");
        add(itemNameLabel, gbc);

        gbc.gridy = 2;
        JLabel itemPriceLabel = new JLabel("Item Price:");
        add(itemPriceLabel, gbc);

        gbc.gridy = 3;
        JLabel itemQuantityLabel = new JLabel("Item Quantity:");
        add(itemQuantityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        invoiceIDBox = new JComboBox();
        add(invoiceIDBox, gbc);

        try {
            PreparedStatement invoiceIDQuery = Main.sql.prepareStatement("SELECT invoiceID FROM Invoice");
            // This is the query to get all of the invoice IDs
            ResultSet invoiceIDs = invoiceIDQuery.executeQuery();
            // This is putting all of the invoice IDs into a result set

            while (invoiceIDs.next()) {
                int invoiceID = invoiceIDs.getInt("invoiceID");
                invoiceIDBox.addItem(invoiceID);
            }
            //This while loop is iterating through the result set and assigning each of them into the combo box
        } catch (SQLException custIDQuery) {
            custIDQuery.printStackTrace();
        }

        gbc.gridy = 1;
        itemNameField = new JTextField();
        add(itemNameField, gbc);

        gbc.gridy = 2;
        itemPriceField = new JTextField();
        add(itemPriceField, gbc);

        gbc.gridy = 3;
        itemQuantityField = new JTextField();
        add(itemQuantityField, gbc);

        gbc.gridy = 4;
        submitButton = new JButton("Submit");
        add(submitButton, gbc);
        // Submit button

        gbc.gridy = 5;
        menuButton = new JButton("Return To Menu");
        add(menuButton, gbc);
        // Return to menu button

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent sqlUpdateEvent) {
                String itemName = itemNameField.getText();
                String itemPrice = itemPriceField.getText();
                String itemQuantity = itemQuantityField.getText();
                //This is getting the values from the text fields and assigning them to variables
                pstat = Main.sql.prepareStatement("UPDATE Invoice SET itemName = ?, itemPrice = ?, itemQuantity = ? WHERE invoiceID = ?");
                //This is the SQL query for updating the invoice data in the Invoice table
                try {
                    pstat.setString(1, itemName);
                    pstat.setString(2, itemPrice);
                    pstat.setString(3, itemQuantity);
                    pstat.setInt(4, (int) invoiceIDBox.getSelectedItem());
                    pstat.executeUpdate();
                    //This is assigning the values from the text fields for the SQL query
                    JOptionPane.showMessageDialog(UpdateInvoice.this, "Invoice info updated successfully.");
                    itemNameField.setText("");
                    itemPriceField.setText("");
                    itemQuantityField.setText("");
                    //This is clearing the text fields after the data has been updated
                } catch (SQLException sqlInsertException) {
                    sqlInsertException.printStackTrace();
                }
            }
        });

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent invoiceMenuButtonEvent) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) invoiceMenuButtonEvent.getSource());
                currentFrame.dispose();
                JFrame frame = new JFrame("Invoice Menu");
                InvoiceMenu InvoiceMenu = new InvoiceMenu();
                frame.add(InvoiceMenu);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
            // This is the action button for the menu button which closes the current window and loads the invoice menu
        });
    }
}
