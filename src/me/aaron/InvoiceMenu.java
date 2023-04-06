package me.aaron;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceMenu extends JPanel {
    private JButton addInvoiceButton;
    private JButton updateInvoiceButton;
    private JButton deleteInvoiceButton;
    private JButton viewInvoiceButton;
    private JButton returnButton;

    public InvoiceMenu() {
            addInvoiceButton = new JButton("Create Invoice");
            add(addInvoiceButton);
            addInvoiceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent addInvoiceButton) {
                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) addInvoiceButton.getSource());
                    currentFrame.dispose();
                    JFrame frame = new JFrame("Create Invoice");
                    CreateInvoice CreateInvoice = new CreateInvoice();
                    frame.add(CreateInvoice);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
            });

            updateInvoiceButton = new JButton("Update Invoice");
            add(updateInvoiceButton);

            updateInvoiceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent updateInvoiceButton) {
                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) updateInvoiceButton.getSource());
                    currentFrame.dispose();
                    JFrame frame = new JFrame("Update Invoice");
                    UpdateInvoice UpdateInvoice = new UpdateInvoice();
                    frame.add(UpdateInvoice);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
            });

            deleteInvoiceButton = new JButton("Delete Invoice");
            add(deleteInvoiceButton);

            deleteInvoiceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent deleteInvoiceButton) {
                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) deleteInvoiceButton.getSource());
                    currentFrame.dispose();
                    JFrame frame = new JFrame("Delete Invoice");
                    DeleteInvoice DeleteInvoice = new DeleteInvoice();
                    frame.add(DeleteInvoice);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
            });

            viewInvoiceButton = new JButton("View Invoice");
            add(viewInvoiceButton);

            viewInvoiceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent viewInvoiceButton) {
                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) viewInvoiceButton.getSource());
                    currentFrame.dispose();
                    JFrame frame = new JFrame("View Invoice");
                    ViewInvoice ViewInvoice = new ViewInvoice();
                    frame.add(ViewInvoice);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
            });

            returnButton = new JButton("Main Menu");
            add(returnButton);
            returnButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent returnButton) {
                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) returnButton.getSource());
                    currentFrame.dispose();
                    JFrame frame = new JFrame("Main Menu");
                    Menu menu = new Menu();
                    frame.add(menu);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
            });
    }
}

