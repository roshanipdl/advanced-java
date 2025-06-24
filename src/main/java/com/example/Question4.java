package com.example;

import java.sql.*;
import java.util.Scanner;

public class Question4 {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";  // Updated password
    private static Connection connection;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect to database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database successfully!");

            // Create table
            createTable();

            while (true) {
                System.out.println("\n=== Student Management System ===");
                System.out.println("1. Insert Student");
                System.out.println("2. Display All Students");
                System.out.println("3. Update Student Name");
                System.out.println("4. Delete Student");
                System.out.println("5. Display Students Age > 20");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        insertStudent();
                        break;
                    case 2:
                        displayAllStudents();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        displayStudentsAbove20();
                        break;
                    case 6:
                        System.out.println("Exiting program...");
                        connection.close();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Students (" +
                              "id INT PRIMARY KEY, " +
                              "name VARCHAR(50), " +
                              "age INT, " +
                              "phone VARCHAR(15))";
        
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Students table created successfully!");
        }
    }

    private static void insertStudent() throws SQLException {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter student phone: ");
        String phone = scanner.nextLine();

        String insertSQL = "INSERT INTO Students (id, name, age, phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setString(4, phone);
            pstmt.executeUpdate();
            System.out.println("Student inserted successfully!");
        }
    }

    private static void displayAllStudents() throws SQLException {
        String selectSQL = "SELECT * FROM Students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            
            System.out.println("\nAll Students:");
            System.out.println("ID\tName\tAge\tPhone");
            System.out.println("----------------------------------------");
            
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                                 rs.getString("name") + "\t" +
                                 rs.getInt("age") + "\t" +
                                 rs.getString("phone"));
            }
        }
    }

    private static void updateStudent() throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();

        String updateSQL = "UPDATE Students SET name = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("No student found with ID: " + id);
            }
        }
    }

    private static void deleteStudent() throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();

        String deleteSQL = "DELETE FROM Students WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("No student found with ID: " + id);
            }
        }
    }

    private static void displayStudentsAbove20() throws SQLException {
        String selectSQL = "SELECT * FROM Students WHERE age > 20";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            
            System.out.println("\nStudents above 20 years:");
            System.out.println("ID\tName\tAge\tPhone");
            System.out.println("----------------------------------------");
            
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                                 rs.getString("name") + "\t" +
                                 rs.getInt("age") + "\t" +
                                 rs.getString("phone"));
            }
        }
    }
} 