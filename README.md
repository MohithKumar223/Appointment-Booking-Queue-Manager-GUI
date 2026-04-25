
 Appointment Booking & Queue Manager

A simple **desktop application** built using **Java Swing + JDBC + MySQL** to manage appointments and track queue status in real-time.



 Overview

This project allows users to:

* Book appointments
* View all appointments in a table
* Manage queue status (WAITING / DONE)

It demonstrates core concepts of:

* Java GUI development (Swing)
* Database connectivity using JDBC
* CRUD operations with MySQL



 Tech Stack

* **Java (Swing)** – GUI development
* **JDBC** – Database connectivity
* **MySQL** – Backend database



 Features

*  Add new appointments
*  View all appointments in a table
*  Refresh appointment list
*  Mark appointments as DONE
*  Input validation (prevents empty/wrong data)



 Project Structure


AppointmentManager/
│
├── DBConnection.java      # Database connection setup
├── Appointment.java       # Model class
├── AppointmentDAO.java    # Database operations (CRUD)
├── MainUI.java            # Swing GUI
```

---

 Setup Instructions

 Clone the repository


git clone https://github.com/MohithKumar223/Appointment-Booking-Queue-Manager-GUI.git
cd appointment-manager
```



 Setup MySQL Database

Open MySQL and run:


CREATE DATABASE appointment_db;

USE appointment_db;

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(15),
    appointment_time DATETIME,
    status VARCHAR(20) DEFAULT 'WAITING'
);




 Configure Database Connection

Edit `DBConnection.java`:


private static final String USER = "root";
private static final String PASSWORD = "your_password";



 Add MySQL JDBC Driver

Download:

* MySQL Connector/J

Add `.jar` to your project:

* Right-click project → Build Path → Add External JARs



 Run the Application

Run:


MainUI.java




Usage

Enter details in the form:


Name: John
Phone: 9999999999
Time: 2026-04-23 10:30:00


Click:

* **Add Appointment** → Adds to database
* **Refresh** → Reloads table
* **Mark Done** → Updates status






 Common Issues

 Access denied (MySQL)

Check username/password in `DBConnection.java`

 Incorrect datetime error

Use format:


YYYY-MM-DD HH:MM:SS


JDBC not working

Make sure MySQL Connector/J is added

 Compilation errors

Delete `module-info.java` if using Java 9+



 Future Improvements

*  Date & Time picker UI
*  Token-based queue system
*  Multi-user support
*  Dashboard & analytics
*  Web version (Spring Boot + React)

---

Author

**Mohith Kumar H M**
 Bengaluru, India

* GitHub: https://github.com/MohithKumar223
* LinkedIn: https://www.linkedin.com/in/mohithkumar-dev/

---

 Support

If you like this project, consider giving it a ⭐ on GitHub!
