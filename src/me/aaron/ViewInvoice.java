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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
public class ViewInvoice extends JPanel{
    private JComboBox<Integer> invoiceIDCombobox;
    private JTextField itemNameField;
    private JTextField itemQuantityField;
    private JTextField itemPriceField;
    private JTextField customerIDField;
    private JTextField invoiceIDField;
    private JButton menuButton;
    private JButton selectButton;
    PreparedStatement pstat = null;
    public ViewInvoice() {
        JFrame frame = new JFrame("View Invoices");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);
        JLabel comboBoxLabel = new JLabel("Select the invoice ID of the invoice you wish to view:");
        add(comboBoxLabel, gbc);
        // Here I am adding the label to the gui

        gbc.gridy = 1;
        JLabel invoiceIDLabel = new JLabel("Invoice ID:");
        add(invoiceIDLabel, gbc);

        gbc.gridy = 2;
        JLabel customerIDLabel = new JLabel("Customer ID:");
        add(customerIDLabel, gbc);

        gbc.gridy = 3;
        JLabel itemNameLabel = new JLabel("Item Name:");
        add(itemNameLabel, gbc);

        gbc.gridy = 4;
        JLabel itemQuantityLabel = new JLabel("Item Quantity:");
        add(itemQuantityLabel, gbc);

        gbc.gridy = 5;
        JLabel itemPriceLabel = new JLabel("Item Price:");
        add(itemPriceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        invoiceIDCombobox = new JComboBox();
        add(invoiceIDCombobox, gbc);
        // This is the combo box that will show the user the invoice ids that exist in the system

        try {
            PreparedStatement invoiceIDQuery = Main.sql.prepareStatement("SELECT invoiceID FROM Invoice");
            // This is the query to get all the invoice IDs
            ResultSet invoiceIDs = invoiceIDQuery.executeQuery();
            // This is putting all the invoice IDs into a result set

            while (invoiceIDs.next()) {
                int invoiceID = invoiceIDs.getInt("invoiceID");
                invoiceIDCombobox.addItem(invoiceID);
            }
            //This while loop is iterating through the result set and assigning each invoice ID into the combo box
        } catch (SQLException invoiceIDException) {
            invoiceIDException.printStackTrace();
        }

        gbc.gridy = 1;
        invoiceIDField = new JTextField();
        invoiceIDField.setEditable(false);
        add(invoiceIDField, gbc);

        gbc.gridy = 2;
        customerIDField = new JTextField();
        customerIDField.setEditable(false);
        add(customerIDField, gbc);

        gbc.gridy = 3;
        itemNameField = new JTextField();
        itemNameField.setEditable(false);
        add(itemNameField, gbc);

        gbc.gridy = 4;
        itemQuantityField = new JTextField();
        itemQuantityField.setEditable(false);
        add(itemQuantityField, gbc);

        gbc.gridy = 5;
        itemPriceField = new JTextField();
        itemPriceField.setEditable(false);
        add(itemPriceField, gbc);

        gbc.gridy = 6;
        selectButton = new JButton("Select Invoice");
        add(selectButton, gbc);
        // Select Invoice button

        gbc.gridy = 7;
        menuButton = new JButton("Return To Menu");
        add(menuButton, gbc);
        // Return to menu button

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent invoiceViewEvent) {
                pstat = Main.sql.prepareStatement("SELECT * FROM Invoice WHERE invoiceID = ?");
                // Here I am getting all of the invoice details from the DB
                try {
                    pstat.setInt(1, (int) invoiceIDCombobox.getSelectedItem());
                    //This is so the customer ID is the one that is selected in the combo box
                    ResultSet invoiceDetailsSet = pstat.executeQuery();
                    ResultSetMetaData invoiceDetailsSetMetaData = invoiceDetailsSet.getMetaData();
                    int columnAmount = invoiceDetailsSetMetaData.getColumnCount();
                    // Here I'm getting the amount of columns so that i can assign the value of each column to the correct text field

                    while (invoiceDetailsSet.next()) {
                        for (int row = 1; row <= columnAmount; row++) {
                            if (row == 1) {
                                invoiceIDField.setText(invoiceDetailsSet.getString(row));
                            } else if (row == 2) {
                                customerIDField.setText(invoiceDetailsSet.getString(row));
                            } else if (row == 3) {
                                itemNameField.setText(invoiceDetailsSet.getString(row));
                            } else if (row == 4) {
                                itemQuantityField.setText(invoiceDetailsSet.getString(row));
                            } else if (row == 5) {
                                itemPriceField.setText(invoiceDetailsSet.getString(row));
                            }
                        }
                        // This for loop is assigning the value of each column to the correct text field
                        JOptionPane.showMessageDialog(ViewInvoice.this, "Invoice Retrieved From Database Successfully.");
                        // This pop up is to let the user know that the invoice has been retrieved from the database successfully
                    }
                } catch (SQLException invoiceIDBoxQuery) {
                    invoiceIDBoxQuery.printStackTrace();
                }
            }
        });

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent stockMenuButtonEvent) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) stockMenuButtonEvent.getSource());
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
