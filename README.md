# University Management System

A desktop-based **University Management System** built entirely in **Java (Swing)** with backend connectivity to a **MySQL database**. This project was developed as part of our Database Management Systems course.

## 🛠 Tech Stack

- **Frontend & Backend:** Java (Swing Framework)
- **Database:** MySQL
- **IDE Used:** IntelliJ IDEA / NetBeans / Eclipse (any Java IDE)
- **Database Connectivity:** JDBC through a custom `Conn` class

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Ahsan654584/University-Management-System.git
cd University-Management-System
```

### 2. Set Up the MySQL Database

- Open the `mysql_commands.txt` file located in the root of this repository.
- Run the SQL commands in your MySQL environment to create the necessary database and tables.

### 3. Configure the Database Connection

- The database connection is handled in the `Conn.java` class.
- Make sure to update the MySQL credentials in that file if your username or password is different.

### 4. Run the Application

- Start the application by running the `Login.java` file.
- **Default Credentials**:
  - **Email:** `admin`
  - **Password:** `admin`

> These credentials are also mentioned in `mysql_commands.txt`.

## 📁 Project Structure

| Class Name                  | Description                                                |
|----------------------------|------------------------------------------------------------|
| `Login`                    | Login interface to access the system                       |
| `Project`                  | Main dashboard with buttons to navigate to all modules     |
| `AddStudent`, `UpdateStudent` | Add or update student records                          |
| `AddTeacher`, `UpdateTeacher` | Add or update teacher records                          |
| `StudentDetails`, `TeacherDetails` | View all student or teacher data                  |
| `StudentAttendance`, `TeacherAttendance` | Mark daily attendance                       |
| `StudentAttendanceDetails`, `TeacherAttendanceDetails` | View attendance logs   |
| `EnterMarks`, `Marks`, `ExaminationDetails` | Manage and view exam results           |
| `StudentFeeForm`, `FeeStructure` | Handle student fee information                     |
| `Conn`                     | Manages the MySQL database connection                      |

## 👥 Team Members

- **Muhammad Ahsan Kareem** *(me)*
- **Sibgha Mursaleen**
- **Abdul Moiz Barlas**

## 🤝 Contribution

Contributions are welcome!  
Feel free to fork the repository and open a pull request.

## 📜 License

This project is for academic purposes. If you'd like to use or contribute, just give proper credit.

---

### 🔗 GitHub Repository

[University Management System](https://github.com/Ahsan654584/University-Management-System)

