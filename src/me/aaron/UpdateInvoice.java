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
import java.sql.*;


public class UpdateInvoice extends JPanel{
    private JComboBox<Integer> CustomerIDField;
    private JTextField itemNameField;
    private JTextField itemNameField;
    private JTextField addressField;
    private JButton submitButton;
    private JButton menuButton;
    PreparedStatement pstat = null;
    public UpdateInvoice() {

    }
}
