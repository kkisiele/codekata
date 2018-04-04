package com.kkisiele.minesweeper.gui;

import javax.swing.*;
import java.awt.*;

public class Minesweeper {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Minesweeper");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

//            frame.setLayout(new FlowLayout());
            frame.setLayout(new GridLayout(5, 5));
            for(int i = 0; i < 25; i++)
                frame.add(new JButton("a"));

            frame.setVisible(true);
        });
    }
}