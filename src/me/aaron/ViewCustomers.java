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


public class ViewCustomers extends JPanel{
    private JTextField firstNameField;
    private JTextField secondNameField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JTextField emailAddressField;
    private JButton menuButton;
    private JButton selectButton;
    PreparedStatement pstat = null;
    private JComboBox<Integer> CustomerIDBox;
    public ViewCustomers() {
        JFrame frame = new JFrame("View Customer Info");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);
        JLabel comboBoxLabel = new JLabel("Select the customer you would like to view:");
        add(comboBoxLabel, gbc);
        // Here I am adding the label to the gui

        gbc.gridy = 1;
        JLabel firstNameLabel = new JLabel("First Name:");
        add(firstNameLabel, gbc);

        gbc.gridy = 2;
        JLabel secondNameLabel = new JLabel("Second Name:");
        add(secondNameLabel, gbc);

        gbc.gridy = 3;
        JLabel addressLabel = new JLabel("Address:");
        add(addressLabel, gbc);

        gbc.gridy = 4;
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        add(phoneNumberLabel, gbc);

        gbc.gridy = 5;
        JLabel emailAddressLabel = new JLabel("Email Address:");
        add(emailAddressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        CustomerIDBox = new JComboBox();
        add(CustomerIDBox, gbc);
        // Here I am adding the combo box that will contain the customer ids to the gui

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
        } catch (SQLException customerIDBoxQuery) {
            customerIDBoxQuery.printStackTrace();
        }

        gbc.gridy = 1;
        firstNameField = new JTextField();
        firstNameField.setEditable(false);
        add(firstNameField, gbc);

        gbc.gridy = 2;
        secondNameField = new JTextField();
        secondNameField.setEditable(false);
        add(secondNameField, gbc);

        gbc.gridy = 3;
        addressField = new JTextField();
        addressField.setEditable(false);
        add(addressField, gbc);

        gbc.gridy = 4;
        phoneNumberField = new JTextField();
        phoneNumberField.setEditable(false);
        add(phoneNumberField, gbc);

        gbc.gridy = 5;
        emailAddressField = new JTextField();
        emailAddressField.setEditable(false);
        add(emailAddressField, gbc);
        // These are the text fields so the user can view the details of the customer, I set them to non-editable as this is only the view screen and not update

        gbc.gridy = 6;
        selectButton = new JButton("Select Customer");
        add(selectButton, gbc);
        // Select Customer Button

        gbc.gridy = 7;
        menuButton = new JButton("Return To Menu");
        add(menuButton, gbc);
        // Return to menu button

        selectButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent customerViewEvent){
               pstat = Main.sql.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");
               try {
                   pstat.setInt(1, (int) CustomerIDBox.getSelectedItem());
                     ResultSet customerDetailsSet = pstat.executeQuery();
                        ResultSetMetaData customerDetailsSetMetaData = customerDetailsSet.getMetaData();
               } catch (SQLException customerIDBoxQuery) {
                   customerIDBoxQuery.printStackTrace();
               }
           }
        });

    }
}
