// language: java
package com.sams;

import com.sams.dao.*;
import com.sams.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final CourseDao courseDao = new CourseDao();
    private static final StudentDao studentDao = new StudentDao();
    private static final LecturerDao lecturerDao = new LecturerDao();
    private static final SubjectDao subjectDao = new SubjectDao();
    private static final ClassSessionDao sessionDao = new ClassSessionDao();
    private static final AttendanceDao attendanceDao = new AttendanceDao();

    public static void main(String[] args) {
        System.out.println("SAMS - Terminal");
        while (true) {
            System.out.println("\n1. Courses 2. Students 3. Lecturers 4. Subjects 5. Sessions 6. Attendance 7. Reports 0. Exit");
            System.out.print("> ");
            String cmd = in.nextLine().trim();
            switch (cmd) {
                case "1" -> coursesMenu();
                case "2" -> studentsMenu();
                case "3" -> lecturersMenu();
                case "4" -> subjectsMenu();
                case "5" -> sessionsMenu();
                case "6" -> attendanceMenu();
                case "7" -> reportsMenu();
                case "0" -> { System.out.println("bye"); return; }
                default -> System.out.println("unknown");
            }
        }
    }

    private static void coursesMenu() {
        System.out.println("Courses: 1-add 2-list 3-delete 0-back");
        String c = in.nextLine().trim();
        if ("1".equals(c)) {
            System.out.print("code: "); String code = in.nextLine().trim();
            System.out.print("title: "); String title = in.nextLine().trim();
            courseDao.create(new Course(0, code, title));
            System.out.println("created");
        } else if ("2".equals(c)) {
            List<Course> cs = courseDao.listAll();
            cs.forEach(System.out::println);
        } else if ("3".equals(c)) {
            System.out.print("id to delete: "); int id = Integer.parseInt(in.nextLine().trim());
            courseDao.delete(id); System.out.println("deleted");
        }
    }

    private static void studentsMenu() {
        System.out.println("Students: 1-add 2-list 3-delete 0-back");
        String c = in.nextLine().trim();
        if ("1".equals(c)) {
            System.out.print("regNo: "); String reg = in.nextLine().trim();
            System.out.print("name: "); String name = in.nextLine().trim();
            System.out.print("courseId: "); int courseId = Integer.parseInt(in.nextLine().trim());
            System.out.print("contact: "); String contact = in.nextLine().trim();
            studentDao.create(new Student(0, reg, name, courseId, contact));
            System.out.println("created");
        } else if ("2".equals(c)) {
            studentDao.listAll().forEach(System.out::println);
        } else if ("3".equals(c)) {
            System.out.print("id to delete: "); int id = Integer.parseInt(in.nextLine().trim());
            studentDao.delete(id); System.out.println("deleted");
        }
    }

    private static void lecturersMenu() {
        System.out.println("Lecturers: 1-add 2-list 0-back");
        String c = in.nextLine().trim();
        if ("1".equals(c)) {
            System.out.print("name: "); String name = in.nextLine().trim();
            System.out.print("email: "); String email = in.nextLine().trim();
            lecturerDao.create(new Lecturer(0, name, email));
            System.out.println("created");
        } else if ("2".equals(c)) {
            lecturerDao.listAll().forEach(System.out::println);
        }
    }

    private static void subjectsMenu() {
        System.out.println("Subjects: 1-add 2-list 0-back");
        String c = in.nextLine().trim();
        if ("1".equals(c)) {
            System.out.print("code: "); String code = in.nextLine().trim();
            System.out.print("title: "); String title = in.nextLine().trim();
            subjectDao.create(new Subject(0, code, title));
            System.out.println("created");
        } else if ("2".equals(c)) {
            subjectDao.listAll().forEach(System.out::println);
        }
    }

    private static void sessionsMenu() {
        System.out.println("Sessions: 1-schedule 2-list 0-back");
        String c = in.nextLine().trim();
        if ("1".equals(c)) {
            System.out.print("courseId: "); int courseId = Integer.parseInt(in.nextLine().trim());
            System.out.print("subjectId: "); int subjectId = Integer.parseInt(in.nextLine().trim());
            System.out.print("lecturerId: "); int lecturerId = Integer.parseInt(in.nextLine().trim());
            System.out.print("YYYY-MM-DDTHH:MM (e.g. 2026-04-01T10:00): "); String dt = in.nextLine().trim();
            LocalDateTime when = LocalDateTime.parse(dt);
            sessionDao.create(new ClassSession(0, courseId, subjectId, lecturerId, when));
            System.out.println("scheduled");
        } else if ("2".equals(c)) {
            sessionDao.listAll().forEach(System.out::println);
        }
    }

    private static void attendanceMenu() {
        System.out.println("Attendance: mark by session: 1-mark 0-back");
        String c = in.nextLine().trim();
        if ("1".equals(c)) {
            System.out.print("sessionId: "); int sessionId = Integer.parseInt(in.nextLine().trim());
            System.out.print("studentId: "); int studentId = Integer.parseInt(in.nextLine().trim());
            System.out.print("present (y/n): "); String p = in.nextLine().trim();
            attendanceDao.mark(sessionId, studentId, p.equalsIgnoreCase("y"));
            System.out.println("marked");
        }
    }

    private static void reportsMenu() {
        System.out.println("Reports: 1-by-student 0-back");
        String c = in.nextLine().trim();
        if ("1".equals(c)) {
            System.out.print("studentId: "); int studentId = Integer.parseInt(in.nextLine().trim());
            attendanceDao.reportByStudent(studentId).forEach(System.out::println);
        }
    }
}
