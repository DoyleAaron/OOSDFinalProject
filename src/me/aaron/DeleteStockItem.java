package me.aaron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteStockItem extends JPanel{
    private JComboBox<Integer> stockIDcombobox;
    private JButton deleteButton;
    private JButton menuButton;
    PreparedStatement pstat = null;
    public DeleteStockItem(){
        JFrame frame = new JFrame("Delete Stock Item");
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 10, 0);

        JLabel stockIDLabel = new JLabel("Select Customer ID:");
        add(stockIDLabel, gbc);
        // This is the label for the stockID combo box

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        stockIDcombobox = new JComboBox();
        add(stockIDcombobox, gbc);
        // This is the combo box that will show the user the stock ids that exist in the system

        try {
            PreparedStatement stockIDQuery = Main.sql.prepareStatement("SELECT stockID FROM Stock");
            // This is the query to get all the stock IDs
            ResultSet stockIDs = stockIDQuery.executeQuery();
            // This is putting all the stock IDs into a result set

            while (stockIDs.next()) {
                int stockID = stockIDs.getInt("stockID");
                stockIDcombobox.addItem(stockID);
            }
            //This while loop is iterating through the result set and assigning each stock ID into the combo box
        } catch (SQLException stockIDException) {
            stockIDException.printStackTrace();
        }

        gbc.gridx = 0;
        gbc.gridy = 1;
        deleteButton = new JButton("Delete Stock Item");
        add(deleteButton, gbc);
        // Submit button

        gbc.gridx = 0;
        gbc.gridy = 2;
        menuButton = new JButton("Return To Menu");
        add(menuButton, gbc);
        // Return to menu button

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent deleteCustomerEvent) {
                pstat = Main.sql.prepareStatement("Delete FROM Stock WHERE stockID = ?");
                //This is the SQL query that is used to delete the stock item from the system
                try {
                    pstat.setInt(1, (int) stockIDcombobox.getSelectedItem());
                    // Here I am assigning the stock ID from the combo box to the ID that we want to delete from the database in the SQL query
                    pstat.executeUpdate();
                    JOptionPane.showMessageDialog(DeleteStockItem.this, "Stock Item Deleted!");
                    // This is the message that will be displayed when the stock item is deleted from the DB
                } catch (SQLException deleteStockItemException) {
                    deleteStockItemException.printStackTrace();
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
