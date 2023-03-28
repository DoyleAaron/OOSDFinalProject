package me.aaron;

import java.awt.*;
import javax.swing.*;

public class BaseFrame extends JFrame {

    public BaseFrame()  {
        JPanel panel = new JPanel();
        setLayout(new GridLayout(0, 1));
        add(panel, BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
