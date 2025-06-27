package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Question1 extends JFrame {
    private JTextField nameField;
    private JLabel greetingLabel;
    private JButton greetButton;

    public Question1() {
        // Set up the frame
        setTitle("Personalized Greeting");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel nameLabel = new JLabel("Enter your name:");
        nameField = new JTextField(20);
        greetButton = new JButton("Greet");
        greetingLabel = new JLabel("");

        // Set up layout
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Add components to frame
        add(nameLabel);
        add(nameField);
        add(greetButton);
        add(greetingLabel);

        // Add action listener to button
        greetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                if (!name.isEmpty()) {
                    greetingLabel.setText("Hello, " + name + "! Welcome to our application!");
                } else {
                    greetingLabel.setText("Please enter your name!");
                }
            }
        });
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Question1().setVisible(true);
            }
        });
    }
} 