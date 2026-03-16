// language: java
package com.sams.dao;

import com.sams.db.Database;
import com.sams.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {
    public void create(Course c) {
        String sql = "INSERT INTO courses(code,title) VALUES(?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCode());
            ps.setString(2, c.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public List<Course> listAll() {
        String sql = "SELECT id,code,title FROM courses";
        List<Course> res = new ArrayList<>();
        try (Connection conn = Database.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) res.add(new Course(rs.getInt(1), rs.getString(2), rs.getString(3)));
        } catch (SQLException e) { throw new RuntimeException(e); }
        return res;
    }

    public void delete(int id) {
        String sql = "DELETE FROM courses WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}
