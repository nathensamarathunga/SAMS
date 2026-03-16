// language: java
package com.sams.dao;

import com.sams.db.Database;
import com.sams.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao {
    public void create(Subject s) {
        String sql = "INSERT INTO subjects(code,title) VALUES(?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getCode());
            ps.setString(2, s.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public List<Subject> listAll() {
        String sql = "SELECT id,code,title FROM subjects";
        List<Subject> res = new ArrayList<>();
        try (Connection conn = Database.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) res.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3)));
        } catch (SQLException e) { throw new RuntimeException(e); }
        return res;
    }
}
