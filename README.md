# Advanced Java Project

This project contains a collection of Java Swing applications.

## Project Structure

The project is organized as follows:

- `src/main/java/com/example/Question1.java`: Simple Java program
- `src/main/java/com/example/Question2.java`: Calculator application
- `src/main/java/com/example/Question3.java`: Number operations application
- `src/main/java/com/example/Question4.java`: Student Management System
- `src/main/java/com/example/StudentCRUDGUI.java`: Student Management GUI

## Requirements

- Java 17 or higher
- MySQL Server
- Maven

## Setup

1. Clone the repository
2. Configure MySQL database
3. Update database connection details in the application
4. Build the project using Maven:
   ```bash
   mvn clean install
   ```

## Running the Applications

Each application can be run independently:

1. Question1: Simple Java program
2. Question2: Calculator with GUI
3. Question3: Number operations with mouse events
4. Question4: Student Management System
5. StudentCRUDGUI: Student Management with GUI

## Database Configuration

The project uses MySQL for data storage. Make sure to:
1. Have MySQL server running
2. Create the necessary database and tables
3. Update the connection details in the application

## Building and Running

1. Clean and build the project:
   ```bash
   mvn clean install
   ```

2. Run the desired application using IntelliJ IDEA or from the command line.

## Notes

This is a pure Java Swing project and does not require any web server or servlet container.
