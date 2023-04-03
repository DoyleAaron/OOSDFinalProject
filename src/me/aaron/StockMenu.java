package me.aaron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StockMenu extends JPanel {
    private JButton addStockButton;
    private JButton updateStockButton;
    public StockMenu() {
        addStockButton = new JButton("Add Stock Item");
        add(addStockButton);
        addStockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent addStockButton) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) addStockButton.getSource());
                currentFrame.dispose();
                JFrame frame = new JFrame("Stock");
                AddStockItem addStockItem = new AddStockItem();
                frame.add(addStockItem);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        updateStockButton = new JButton("Update Stock Item");
        add(updateStockButton);

        updateStockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent updateStockButton) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) updateStockButton.getSource());
                currentFrame.dispose();
                JFrame frame = new JFrame("Update Stock Item");
                StockUpdate StockUpdate = new StockUpdate();
                frame.add(StockUpdate);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
