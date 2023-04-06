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


public class ViewStock extends JPanel{
    private JTextField stockIDField;
    private JTextField itemNameField;
    private JTextField itemQuantityField;
    private JTextField itemPriceField;
    private JButton menuButton;
    private JButton selectButton;
    PreparedStatement pstat = null;
    private JComboBox<Integer> stockIDCombobox;
    public ViewStock(){
        JFrame frame = new JFrame("View Stock Info");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);
        JLabel comboBoxLabel = new JLabel("Select the stock item ID you would like to view:");
        add(comboBoxLabel, gbc);
        // Here I am adding the label to the gui

        gbc.gridy = 1;
        JLabel stockIDLabel = new JLabel("Stock ID:");
        add(stockIDLabel, gbc);

        gbc.gridy = 2;
        JLabel itemNameLabel = new JLabel("Item Name:");
        add(itemNameLabel, gbc);

        gbc.gridy = 3;
        JLabel itemQuantityLabel = new JLabel("Item Quantity:");
        add(itemQuantityLabel, gbc);

        gbc.gridy = 4;
        JLabel itemPriceLabel = new JLabel("Item Price:");
        add(itemPriceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        stockIDCombobox = new JComboBox();
        add(stockIDCombobox, gbc);
        // This is the combo box that will show the user the stock ids that exist in the system

        try {
            PreparedStatement stockIDQuery = Main.sql.prepareStatement("SELECT stockID FROM Stock");
            // This is the query to get all the stock IDs
            ResultSet stockIDs = stockIDQuery.executeQuery();
            // This is putting all the stock IDs into a result set

            while (stockIDs.next()) {
                int stockID = stockIDs.getInt("stockID");
                stockIDCombobox.addItem(stockID);
            }
            //This while loop is iterating through the result set and assigning each stock ID into the combo box
        } catch (SQLException stockIDException) {
            stockIDException.printStackTrace();
        }

        gbc.gridy = 1;
        stockIDField = new JTextField();
        stockIDField.setEditable(false);
        add(stockIDField, gbc);

        gbc.gridy = 2;
        itemNameField = new JTextField();
        itemNameField.setEditable(false);
        add(itemNameField, gbc);

        gbc.gridy = 3;
        itemQuantityField = new JTextField();
        itemQuantityField.setEditable(false);
        add(itemQuantityField, gbc);

        gbc.gridy = 4;
        itemPriceField = new JTextField();
        itemPriceField.setEditable(false);
        add(itemPriceField, gbc);

        gbc.gridy = 5;
        selectButton = new JButton("Select Stock Item");
        add(selectButton, gbc);
        // Select Stock Item Button

        gbc.gridy = 6;
        menuButton = new JButton("Return To Menu");
        add(menuButton, gbc);
        // Return to menu button

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent customerViewEvent) {
                pstat = Main.sql.prepareStatement("SELECT * FROM Stock WHERE stockID = ?");
                // Here I am getting all of the customer details from the DB
                try {
                    pstat.setInt(1, (int) stockIDCombobox.getSelectedItem());
                    //This is so the customer ID is the one that is selected in the combo box
                    ResultSet stockDetailsSet = pstat.executeQuery();
                    ResultSetMetaData stockDetailsSetMetaData = stockDetailsSet.getMetaData();
                    int columnAmount = stockDetailsSetMetaData.getColumnCount();
                    // Here I'm getting the amount of columns so that i can assign the value of each column to the correct text field

                    while (stockDetailsSet.next()) {
                        for (int row = 1; row <= columnAmount; row++) {
                            if (row == 1) {
                                stockIDField.setText(stockDetailsSet.getString(row));
                            } else if (row == 2) {
                                itemNameField.setText(stockDetailsSet.getString(row));
                            } else if (row == 3) {
                                itemQuantityField.setText(stockDetailsSet.getString(row));
                            } else if (row == 4) {
                                itemPriceField.setText(stockDetailsSet.getString(row));
                            }
                        }
                        // This while loop is being ussed to iterate through the data from the database and assign into the uneditable text fields that we have in the gui
                        // so that the user can view the customer details
                        JOptionPane.showMessageDialog(ViewStock.this, "Stock Details Selected From Database Successfully.");
                        // This pop up is to let the user know that the customers information has been retrieved from the database successfully
                    }
                } catch (SQLException stockIDBoxQuery) {
                    stockIDBoxQuery.printStackTrace();
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
