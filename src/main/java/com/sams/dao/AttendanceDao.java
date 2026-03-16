// language: java
package com.sams.dao;

import com.sams.db.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDao {
    public void mark(int sessionId, int studentId, boolean present) {
        String sql = "INSERT INTO attendance(session_id,student_id,present) VALUES(?,?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sessionId);
            ps.setInt(2, studentId);
            ps.setBoolean(3, present);
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public List<String> reportByStudent(int studentId) {
        String sql = "SELECT a.id, s.name, subj.title, sess.scheduled_at, a.present " +
                "FROM attendance a JOIN students s ON a.student_id=s.id " +
                "JOIN sessions sess ON a.session_id=sess.id " +
                "JOIN subjects subj ON sess.subject_id=subj.id WHERE s.id=?";
        List<String> out = new ArrayList<>();
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add("record=" + rs.getInt(1) + " student=" + rs.getString(2) + " subject=" + rs.getString(3) + " at=" + rs.getTimestamp(4) + " present=" + rs.getBoolean(5));
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return out;
    }
}
