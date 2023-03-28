package me.aaron;

import javax.swing.JFrame;

public class Main {
    public static SQLConnect sql;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Menu");
        Menu menu = new Menu();
        frame.add(menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        sql = new SQLConnect();
        sql.connect();
    }

}
