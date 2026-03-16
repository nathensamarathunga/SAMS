// language: java
package com.sams.model;

public class Attendance {
    private int id;
    private int sessionId;
    private int studentId;
    private boolean present;

    public Attendance() {}

    public Attendance(int id, int sessionId, int studentId, boolean present) {
        this.id = id; this.sessionId = sessionId; this.studentId = studentId; this.present = present;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSessionId() { return sessionId; }
    public void setSessionId(int sessionId) { this.sessionId = sessionId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public boolean isPresent() { return present; }
    public void setPresent(boolean present) { this.present = present; }
}
