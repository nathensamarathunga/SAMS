// language: java
package com.sams.dao;

import com.sams.db.Database;
import com.sams.model.ClassSession;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ClassSessionDao {
    public void create(ClassSession cs) {
        String sql = "INSERT INTO sessions(course_id,subject_id,lecturer_id,scheduled_at) VALUES(?,?,?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cs.getCourseId());
            ps.setInt(2, cs.getSubjectId());
            ps.setInt(3, cs.getLecturerId());
            ps.setTimestamp(4, Timestamp.valueOf(cs.getScheduledAt()));
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public List<ClassSession> listAll() {
        String sql = "SELECT id,course_id,subject_id,lecturer_id,scheduled_at FROM sessions";
        List<ClassSession> res = new ArrayList<>();
        try (Connection conn = Database.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Timestamp t = rs.getTimestamp(5);
                LocalDateTime dt = t != null ? t.toLocalDateTime() : null;
                res.add(new ClassSession(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), dt));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return res;
    }
}
