package me.aaron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteInvoice extends JPanel{
    private JComboBox<Integer> invoiceIDCombobox;
    private JButton deleteButton;
    private JButton menuButton;
    PreparedStatement pstat = null;
    public DeleteInvoice() {
        JFrame frame = new JFrame("Delete Invoice");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);

        JLabel invoiceIDLabel = new JLabel("Select Invoice ID:");
        add(invoiceIDLabel, gbc);
        // This is the label for the invoiceID combo box

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
            // This is putting all the invoce IDs into a result set

            while (invoiceIDs.next()) {
                int invoiceID = invoiceIDs.getInt("invoiceID");
                invoiceIDCombobox.addItem(invoiceID);
            }
            //This while loop is iterating through the result set and assigning each invoice ID into the combo box
        } catch (SQLException invoiceIDException) {
            invoiceIDException.printStackTrace();
        }

        gbc.gridx = 0;
        gbc.gridy = 1;
        deleteButton = new JButton("Delete Invoice");
        add(deleteButton, gbc);
        // Delete Button

        gbc.gridx = 0;
        gbc.gridy = 2;
        menuButton = new JButton("Return To Menu");
        add(menuButton, gbc);
        // Return to menu button

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent deleteInvoiceEvent) {
                pstat = Main.sql.prepareStatement("Delete FROM Invoice WHERE invoiceID = ?");
                //This is the SQL query that is used to delete invoice from the system
                try {
                    pstat.setInt(1, (int) invoiceIDCombobox.getSelectedItem());
                    // Here I am assigning the invoice ID from the combo box to the ID that we want to delete from the database in the SQL query
                    pstat.executeUpdate();
                    JOptionPane.showMessageDialog(DeleteInvoice.this, "Invoice Deleted!");
                    // This is the message that will be displayed when the invoice is deleted from the DB
                } catch (SQLException deleteStockItemException) {
                    deleteStockItemException.printStackTrace();
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
