package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Question2 extends JFrame {
    private JTextField displayField;
    private double firstNumber = 0;
    private String currentOperation = "";
    private boolean isNewNumber = true;

    public Question2() {
        // Set up the frame
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create display field
        displayField = new JTextField();
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        // Create number buttons
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"  // Clear button
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            buttonPanel.add(button);

            if (label.matches("[0-9.]")) {
                button.addActionListener(new NumberButtonListener(label));
            } else if (label.matches("[+\\-*/]")) {
                button.addActionListener(new OperationButtonListener(label));
            } else if (label.equals("=")) {
                button.addActionListener(new EqualsButtonListener());
            } else if (label.equals("C")) {
                button.addActionListener(new ClearButtonListener());
            }
        }

        // Set up layout
        setLayout(new BorderLayout(10, 10));
        add(displayField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Add padding
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private class NumberButtonListener implements ActionListener {
        private String number;

        public NumberButtonListener(String number) {
            this.number = number;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isNewNumber) {
                displayField.setText(number);
                isNewNumber = false;
            } else {
                displayField.setText(displayField.getText() + number);
            }
        }
    }

    private class OperationButtonListener implements ActionListener {
        private String operation;

        public OperationButtonListener(String operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            firstNumber = Double.parseDouble(displayField.getText());
            currentOperation = operation;
            isNewNumber = true;
        }
    }

    private class EqualsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double secondNumber = Double.parseDouble(displayField.getText());
            double result = 0;

            switch (currentOperation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        displayField.setText("Error: Division by zero");
                        return;
                    }
                    break;
            }

            // Format the result to remove trailing zeros after decimal point
            String formattedResult;
            if (result == (long) result) {
                formattedResult = String.format("%d", (long) result);
            } else {
                formattedResult = String.format("%s", result);
            }
            displayField.setText(formattedResult);
            isNewNumber = true;
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayField.setText("");
            firstNumber = 0;
            currentOperation = "";
            isNewNumber = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Question2().setVisible(true));
    }
} 