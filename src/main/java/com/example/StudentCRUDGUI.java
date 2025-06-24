package com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentCRUDGUI extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private Connection connection;

    // Components for Insert
    private JTextField idField, nameField, ageField, phoneField;
    private JButton insertButton;

    // Components for Update
    private JTextField updateIdField, updateNameField;
    private JButton updateButton;

    // Components for Delete
    private JTextField deleteIdField;
    private JButton deleteButton;

    // Components for Display
    private JButton displayAllButton, displayAbove20Button;
    private JTable table;
    private DefaultTableModel tableModel;

    public StudentCRUDGUI() {
        setTitle("Student CRUD Application");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Connect to DB
        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC driver loaded successfully");
            System.out.println("Attempting to connect to database...");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection successful");
            createTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }

        // Top Panel for Insert
        JPanel insertPanel = new JPanel(new FlowLayout());
        insertPanel.setBorder(BorderFactory.createTitledBorder("Insert Student"));
        idField = new JTextField(5);
        nameField = new JTextField(10);
        ageField = new JTextField(5);
        phoneField = new JTextField(10);
        insertButton = new JButton("Insert");
        insertPanel.add(new JLabel("ID:")); insertPanel.add(idField);
        insertPanel.add(new JLabel("Name:")); insertPanel.add(nameField);
        insertPanel.add(new JLabel("Age:")); insertPanel.add(ageField);
        insertPanel.add(new JLabel("Phone:")); insertPanel.add(phoneField);
        insertPanel.add(insertButton);

        // Middle Panel for Update
        JPanel updatePanel = new JPanel(new FlowLayout());
        updatePanel.setBorder(BorderFactory.createTitledBorder("Update Student"));
        updateIdField = new JTextField(5);
        updateNameField = new JTextField(10);
        updateButton = new JButton("Update Name");
        updatePanel.add(new JLabel("ID:")); updatePanel.add(updateIdField);
        updatePanel.add(new JLabel("New Name:")); updatePanel.add(updateNameField);
        updatePanel.add(updateButton);

        // Middle Panel for Delete
        JPanel deletePanel = new JPanel(new FlowLayout());
        deletePanel.setBorder(BorderFactory.createTitledBorder("Delete Student"));
        deleteIdField = new JTextField(5);
        deleteButton = new JButton("Delete");
        deletePanel.add(new JLabel("ID to Delete:")); deletePanel.add(deleteIdField);
        deletePanel.add(deleteButton);

        // Bottom Panel for Display
        JPanel displayPanel = new JPanel(new FlowLayout());
        displayAllButton = new JButton("Display All");
        displayAbove20Button = new JButton("Display Age > 20");
        displayPanel.add(displayAllButton);
        displayPanel.add(displayAbove20Button);

        // Table for displaying students
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Age", "Phone"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);

        // Add panels to frame
        JPanel topPanel = new JPanel(new GridLayout(4, 1));
        topPanel.add(insertPanel);
        topPanel.add(updatePanel);
        topPanel.add(deletePanel);
        topPanel.add(displayPanel);
        add(topPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);

        // Action Listeners
        insertButton.addActionListener(e -> insertStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        displayAllButton.addActionListener(e -> displayAllStudents());
        displayAbove20Button.addActionListener(e -> displayStudentsAbove20());

        // Load initial data
        displayAllStudents();
    }

    private void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Students (" +
                "id INT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "age INT, " +
                "phone VARCHAR(15))";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        }
    }

    private void insertStudent() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String phone = phoneField.getText().trim();
            String insertSQL = "INSERT INTO Students (id, name, age, phone) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setInt(3, age);
                pstmt.setString(4, phone);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student inserted successfully!");
                idField.setText(""); nameField.setText(""); ageField.setText(""); phoneField.setText("");
                displayAllStudents();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Insert failed: " + ex.getMessage());
        }
    }

    private void updateStudent() {
        try {
            int id = Integer.parseInt(updateIdField.getText().trim());
            String newName = updateNameField.getText().trim();
            String updateSQL = "UPDATE Students SET name = ? WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
                pstmt.setString(1, newName);
                pstmt.setInt(2, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Student updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "No student found with ID: " + id);
                }
                updateIdField.setText(""); updateNameField.setText("");
                displayAllStudents();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Update failed: " + ex.getMessage());
        }
    }

    private void deleteStudent() {
        try {
            int id = Integer.parseInt(deleteIdField.getText().trim());
            String deleteSQL = "DELETE FROM Students WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "No student found with ID: " + id);
                }
                deleteIdField.setText("");
                displayAllStudents();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Delete failed: " + ex.getMessage());
        }
    }

    private void displayAllStudents() {
        tableModel.setRowCount(0);
        String selectSQL = "SELECT * FROM Students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("phone")
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Display failed: " + ex.getMessage());
        }
    }

    private void displayStudentsAbove20() {
        tableModel.setRowCount(0);
        String selectSQL = "SELECT * FROM Students WHERE age > 20";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("phone")
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Display failed: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentCRUDGUI().setVisible(true));
    }
} 