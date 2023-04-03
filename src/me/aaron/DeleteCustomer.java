package me.aaron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteCustomer extends JPanel {
    private JComboBox<Integer> CustomerIDBox;
    private JButton deleteButton;
    private JButton menuButton;
    PreparedStatement pstat = null;
    public DeleteCustomer(){
        JFrame frame = new JFrame("Delete Customer");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);

        JLabel custIDLabel = new JLabel("Select Customer ID:");
        add(custIDLabel, gbc);
        // This is the label for the customer ID combo box

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        CustomerIDBox = new JComboBox();
        add(CustomerIDBox, gbc);
        // This is the combo box that displays the customer ids for the user to select which customer they want to delete

        try {
            PreparedStatement customerIDQuery = Main.sql.prepareStatement("SELECT CustomerID FROM Customer");
            // This is the query to get all of the customer IDs
            ResultSet custIDs = customerIDQuery.executeQuery();
            // This is putting all of the customer IDs into a result set

            while (custIDs.next()) {
                int customerID = custIDs.getInt("CustomerID");
                CustomerIDBox.addItem(customerID);
            }
            //This while loop is iterating through the result set and assigning each of them into the combo box
        } catch (SQLException customerIDException) {
            customerIDException.printStackTrace();
        }

        gbc.gridx = 0;
        gbc.gridy = 1;
        deleteButton = new JButton("Delete Customer");
        add(deleteButton, gbc);
        // Submit button

        gbc.gridx = 0;
        gbc.gridy = 2;
        menuButton = new JButton("Return To Menu");
        add(menuButton, gbc);
        // Return to menu button

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent deleteCustomerEvent) {
                pstat = Main.sql.prepareStatement("Delete FROM Customer WHERE customerID = ?");
                //This is the SQL query for deleting the customer data from the customer table
                try {
                    pstat.setInt(1, (int) CustomerIDBox.getSelectedItem());
                    // Here i am assigning the customer ID from the combo box to the ID that we want to delete from the database in the SQL query
                    pstat.executeUpdate();
                    JOptionPane.showMessageDialog(DeleteCustomer.this, "Customer Data Deleted Successfully.");
                    // This is the message that will be displayed when the customer data is deleted
                } catch (SQLException deleteCustomerException) {
                    deleteCustomerException.printStackTrace();
                }
            }
        });

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent customerMenuButtonEvent) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) customerMenuButtonEvent.getSource());
                currentFrame.dispose();
                JFrame frame = new JFrame("Customer Menu");
                CustomerMenu CustomerMenu = new CustomerMenu();
                frame.add(CustomerMenu);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
            // This is the action button for the menu button which closes the current window and loads the customer menu
        });
    }
}
