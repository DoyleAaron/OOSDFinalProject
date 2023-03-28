package me.aaron;

import me.aaron.SQLConnect;
import me.aaron.Customer;
import javax.swing.JFrame;

public class Main {
    public static SQLConnect sql;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Customer");
        Customer customer = new Customer();
        frame.add(customer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        SQLConnect sql = new SQLConnect();
    }

}
