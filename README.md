University Management System

Overview

The University Management System is a Java-based application designed to manage university operations, including student records, course management, faculty details, and administrative tasks. It uses MySQL as the database to store and retrieve data efficiently. This system provides a user-friendly interface for administrators, faculty, and students to interact with the university's data.

Features
Student Management: Add, update, delete, and view student records.
Course Management: Manage courses, including adding new courses, updating course details, and assigning courses to students
Faculty Management: Maintain faculty records, including personal details and assigned courses.
Enrollment System: Handle student enrollments in courses.
Admin Dashboard: Centralized interface for administrators to oversee all operations.
Database Integration: Securely stores data in a MySQL database with CRUD (Create, Read, Update, Delete) operations.

Technologies Used
Programming Language: Java (JDK 17 or later)
Database: MySQL 8.0 or later
IDE: Any Java-supported IDE (e.g., IntelliJ IDEA, Eclipse)
JDBC: For database connectivity

Project Structure

university-management-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/university/
│   │   │   │   ├── model/      # Entity classes (e.g., Student, Course)
│   │   │   │   ├── dao/       # Data Access Objects for database operations
│   │   │   │   ├── service/    # Business logic
│   │   │   │   ├── ui/        # User interface (e.g., JavaFX or Swing classes)
│   │   │   │   └── Main.java  # Entry point of the application
│   │   └── resources/
│   │       └── db.properties  # Database configuration
├── database_schema.sql        # SQL file for database schema
├── pom.xml                   # Maven configuration (or build.gradle for Gradle)
└── README.md                 # This file
