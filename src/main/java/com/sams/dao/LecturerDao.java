// language: java
package com.sams.dao;

import com.sams.db.Database;
import com.sams.model.Lecturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LecturerDao {
    public void create(Lecturer l) {
        String sql = "INSERT INTO lecturers(name,email) VALUES(?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, l.getName());
            ps.setString(2, l.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public List<Lecturer> listAll() {
        String sql = "SELECT id,name,email FROM lecturers";
        List<Lecturer> res = new ArrayList<>();
        try (Connection conn = Database.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) res.add(new Lecturer(rs.getInt(1), rs.getString(2), rs.getString(3)));
        } catch (SQLException e) { throw new RuntimeException(e); }
        return res;
    }
}
