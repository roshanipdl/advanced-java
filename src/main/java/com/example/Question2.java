package com.example;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class Question2 extends JFrame {
    private JTextPane displayPane;
    private double firstNumber = 0;
    private String currentOperation = "";
    private boolean isNewNumber = true;
    private StringBuilder expression = new StringBuilder();
    private String firstOperand = "";
    private String secondOperand = "";
    private boolean operatorPressed = false;

    public Question2() {
        // Set up the frame
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create display pane
        displayPane = new JTextPane();
        displayPane.setEditable(false);
        displayPane.setFont(new Font("Arial", Font.PLAIN, 20));
        displayPane.setBackground(Color.WHITE);
        displayPane.setMargin(new Insets(10, 10, 10, 10));
        displayPane.setPreferredSize(new Dimension(260, 70));
        setRightAlignment("");

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
        add(displayPane, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Add padding
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void setRightAlignment(String text) {
        StyledDocument doc = displayPane.getStyledDocument();
        SimpleAttributeSet right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        doc.setParagraphAttributes(0, doc.getLength(), right, false);
        try {
            displayPane.setText("");
            doc.insertString(0, text, right);
        } catch (BadLocationException e) {
            displayPane.setText(text);
        }
    }

    private class NumberButtonListener implements ActionListener {
        private String number;

        public NumberButtonListener(String number) {
            this.number = number;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isNewNumber) {
                setRightAlignment(number);
                expression.setLength(0);
                expression.append(number);
                firstOperand = number;
                secondOperand = "";
                operatorPressed = false;
                isNewNumber = false;
            } else {
                setRightAlignment(displayPane.getText() + number);
                expression.append(number);
                if (!operatorPressed) {
                    firstOperand += number;
                } else {
                    secondOperand += number;
                }
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
            if (!operatorPressed && !firstOperand.isEmpty()) {
                firstNumber = Double.parseDouble(firstOperand);
                currentOperation = operation;
                isNewNumber = false;
                operatorPressed = true;
                expression.append(" " + operation + " ");
                setRightAlignment(expression.toString());
            }
        }
    }

    private class EqualsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!firstOperand.isEmpty() && operatorPressed && !secondOperand.isEmpty()) {
                double result = 0;
                double secondNumber = 0;
                try {
                    secondNumber = Double.parseDouble(secondOperand);
                } catch (Exception ex) {
                    setRightAlignment(expression.toString() + "\n= Error");
                    isNewNumber = true;
                    return;
                }
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
                            setRightAlignment(expression.toString() + "\n= Error: Division by zero");
                            isNewNumber = true;
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
                setRightAlignment(expression.toString() + "\n= " + formattedResult);
                isNewNumber = true;
            } else {
                // Don't show error for incomplete expressions
                setRightAlignment(expression.toString());
            }
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setRightAlignment("");
            firstNumber = 0;
            currentOperation = "";
            isNewNumber = true;
            expression.setLength(0);
            firstOperand = "";
            secondOperand = "";
            operatorPressed = false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Question2().setVisible(true));
    }
} 