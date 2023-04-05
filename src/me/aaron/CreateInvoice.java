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
public class CreateInvoice extends JPanel {
    private JComboBox<Integer> CustomerIDBox;
    private JTextField itemNameField;
    private JTextField itemQuantityField;
    private JTextField itemPriceField;
    private JButton submitButton;
    private JButton menuButton;
    PreparedStatement pstat = null;
    public CreateInvoice() {
        JFrame frame = new JFrame("Create Invoice");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);

        JLabel custIDLabel = new JLabel("Select Customer ID:");
        add(custIDLabel, gbc);

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
        CustomerIDBox = new JComboBox();
        add(CustomerIDBox, gbc);

        try {
            PreparedStatement customerIDQuery = Main.sql.prepareStatement("SELECT CustomerID FROM Customer");
            // This is the query to get all of the customer IDs
            ResultSet customerIDs = customerIDQuery.executeQuery();
            // This is putting all of the customer IDs into a result set

            while (customerIDs.next()) {
                int customerID = customerIDs.getInt("CustomerID");
                CustomerIDBox.addItem(customerID);
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

        gbc.gridy = 6;
        submitButton = new JButton("Submit");
        add(submitButton, gbc);
        // Submit button

        gbc.gridy = 7;
        menuButton = new JButton("Return To Menu");
        add(menuButton, gbc);
        // Return to menu button

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent sqlUpdateEvent) {
                String itemName = itemNameField.getText();
                String itemPrice = itemPriceField.getText();
                String itemQuantity = itemQuantityField.getText();
                //This is getting the values from the text fields and assigning them to variables
                pstat = Main.sql.prepareStatement("INSERT INTO Invoice (itemName, itemPrice, itemQuantity) VALUES (?, ?, ?)");
                //This is the SQL query for updating the customer data in the customer table
                try {
                    pstat.setString(1, itemName);
                    pstat.setString(2, itemPrice);
                    pstat.setString(3, itemQuantity);
                    pstat.setInt(4, (int) CustomerIDBox.getSelectedItem());
                    pstat.executeUpdate();
                    //This is assigning the values from the text fields for the SQL query
                    JOptionPane.showMessageDialog(CreateInvoice.this, "Invoice created successfully.");
                    itemNameField.setText("");
                    itemPriceField.setText("");
                    itemQuantityField.setText("");
                    //This is clearing the text fields after the data has been updated
                } catch (SQLException sqlInsertException) {
                    sqlInsertException.printStackTrace();
                }
            }
        });
    }
}
