-- language: sql
CREATE DATABASE IF NOT EXISTS sams;
USE sams;

CREATE TABLE IF NOT EXISTS courses (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       code VARCHAR(50) NOT NULL,
    title VARCHAR(200) NOT NULL
    );

CREATE TABLE IF NOT EXISTS subjects (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        code VARCHAR(50) NOT NULL,
    title VARCHAR(200) NOT NULL
    );

CREATE TABLE IF NOT EXISTS lecturers (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(200) NOT NULL,
    email VARCHAR(200)
    );

CREATE TABLE IF NOT EXISTS students (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        reg_no VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(200) NOT NULL,
    course_id INT,
    contact VARCHAR(200),
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE SET NULL
    );

CREATE TABLE IF NOT EXISTS sessions (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        course_id INT,
                                        subject_id INT,
                                        lecturer_id INT,
                                        scheduled_at DATETIME,
                                        FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE SET NULL,
    FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE SET NULL,
    FOREIGN KEY (lecturer_id) REFERENCES lecturers(id) ON DELETE SET NULL
    );

CREATE TABLE IF NOT EXISTS attendance (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          session_id INT,
                                          student_id INT,
                                          present BOOLEAN,
                                          FOREIGN KEY (session_id) REFERENCES sessions(id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
    );

-- sample user: set Database.java to use these credentials or create matching user
INSERT INTO courses(code,title) VALUES('CS', 'Computer Science'),('ENG','Engineering');
INSERT INTO subjects(code,title) VALUES('CS101','Intro to CS'),('MATH101','Calculus I');
INSERT INTO lecturers(name,email) VALUES('Alice Smith','alice@uni.edu'),('Bob Jones','bob@uni.edu');
INSERT INTO students(reg_no,name,course_id,contact) VALUES('REG001','John Doe',1,'john@example.com'),('REG002','Jane Roe',1,'jane@example.com');
