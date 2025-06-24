package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Question3 extends JFrame {
    private JTextField firstNumberField;
    private JTextField secondNumberField;
    private JLabel resultLabel;
    private JLabel firstLabel;
    private JLabel secondLabel;
    private JLabel resultTextLabel;

    public Question3() {
        // Set up the frame
        setTitle("Number Operations");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        firstLabel = new JLabel("First Number:");
        secondLabel = new JLabel("Second Number:");
        resultTextLabel = new JLabel("Result:");
        resultLabel = new JLabel("0");  // Initialize with 0
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));  // Make result more visible

        firstNumberField = new JTextField(10);
        secondNumberField = new JTextField(10);

        // Create panel for components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(firstLabel, gbc);
        gbc.gridx = 1;
        panel.add(firstNumberField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(secondLabel, gbc);
        gbc.gridx = 1;
        panel.add(secondNumberField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(resultTextLabel, gbc);
        gbc.gridx = 1;
        panel.add(resultLabel, gbc);

        // Add mouse listener to the panel
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                calculateSum();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                calculateDifference();
            }
        });

        // Add instruction label
        JLabel instructionLabel = new JLabel("Press mouse for sum, release for difference");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set up layout
        setLayout(new BorderLayout());
        add(instructionLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        // Add padding
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void calculateSum() {
        try {
            int num1 = Integer.parseInt(firstNumberField.getText());
            int num2 = Integer.parseInt(secondNumberField.getText());
            int sum = num1 + num2;
            resultLabel.setText(String.valueOf(sum));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input");
        }
    }

    private void calculateDifference() {
        try {
            int num1 = Integer.parseInt(firstNumberField.getText());
            int num2 = Integer.parseInt(secondNumberField.getText());
            int difference = num1 - num2;
            resultLabel.setText(String.valueOf(difference));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Question3().setVisible(true));
    }
} 