// language: java
package com.sams.dao;

import com.sams.db.Database;
import com.sams.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public void create(Student s) {
        String sql = "INSERT INTO students(reg_no,name,course_id,contact) VALUES(?,?,?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getRegNo());
            ps.setString(2, s.getName());
            ps.setInt(3, s.getCourseId());
            ps.setString(4, s.getContact());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public List<Student> listAll() {
        String sql = "SELECT id,reg_no,name,course_id,contact FROM students";
        List<Student> res = new ArrayList<>();
        try (Connection conn = Database.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) res.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
        } catch (SQLException e) { throw new RuntimeException(e); }
        return res;
    }

    public List<Student> findByCourse(int courseId) {
        String sql = "SELECT id,reg_no,name,course_id,contact FROM students WHERE course_id=?";
        List<Student> res = new ArrayList<>();
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) res.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return res;
    }

    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}
