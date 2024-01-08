package com.personalfinance.common;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComboBoxWithButton {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ComboBox with Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Option 1");
        comboBox.addItem("Option 2");
        comboBox.addItem("Option 3");
      //  comboBox.setEditable(true); // Allow typing in the combo box

        JButton button = new JButton("Open ComboBox");
        button.addActionListener(e -> {
            JPopupMenu popupMenu = new JPopupMenu();
            popupMenu.add(comboBox);
            popupMenu.show(button, 0, button.getHeight());
        });

        frame.add(button);

        frame.setVisible(true);
    }
}
