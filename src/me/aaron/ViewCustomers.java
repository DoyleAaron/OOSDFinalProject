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
    private JTextField customerIDField;
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
        JLabel customerIDLabel = new JLabel("Customer ID:");
        add(customerIDLabel, gbc);

        gbc.gridy = 6;
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
        customerIDField = new JTextField();
        customerIDField.setEditable(false);
        add(customerIDField, gbc);

        gbc.gridy = 6;
        emailAddressField = new JTextField();
        emailAddressField.setEditable(false);
        add(emailAddressField, gbc);
        // These are the text fields so the user can view the details of the customer, I set them to non-editable as this is only the view screen and not update

        gbc.gridy = 7;
        selectButton = new JButton("Select Customer");
        add(selectButton, gbc);
        // Select Customer Button

        gbc.gridy = 8;
        menuButton = new JButton("Return To Menu");
        add(menuButton, gbc);
        // Return to menu button

        selectButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent customerViewEvent){
               pstat = Main.sql.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");
               // Here I am getting all of the customer details from the DB
               try {
                   pstat.setInt(1, (int) CustomerIDBox.getSelectedItem());
                   //This is so the customer ID is the one that is selected in the combo box
                     ResultSet customerDetailsSet = pstat.executeQuery();
                     ResultSetMetaData customerDetailsSetMetaData = customerDetailsSet.getMetaData();
                     int columnAmount = customerDetailsSetMetaData.getColumnCount();
                     // Here I'm getting the amount of columns so that i can assign the value of each column to the correct text field

                   while (customerDetailsSet.next()) {
                       for (int row = 1; row <= columnAmount; row++) {
                           if (row == 1) {
                               firstNameField.setText(customerDetailsSet.getString(row));
                           } else if (row == 2) {
                               secondNameField.setText(customerDetailsSet.getString(row));
                           } else if (row == 3) {
                               addressField.setText(customerDetailsSet.getString(row));
                           } else if (row == 4) {
                               phoneNumberField.setText(customerDetailsSet.getString(row));
                           } else if (row == 5) {
                               customerIDField.setText(customerDetailsSet.getString(row));
                           } else if (row == 6) {
                               emailAddressField.setText(customerDetailsSet.getString(row));
                           }
                       }
                   }
                   // This while loop is being ussed to iterate through the data from the database and assign into the uneditable text fields that we have in the gui
                   // so that the user can view the customer details
                   JOptionPane.showMessageDialog(ViewCustomers.this, "Customer Selected From Database Successfully.");
                   // This pop up is to let the user know that the customers information has been retrieved from the database successfully
               } catch (SQLException customerIDBoxQuery) {
                   customerIDBoxQuery.printStackTrace();
               }
           }
        });

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent MenuButtonEvent) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) MenuButtonEvent.getSource());
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
