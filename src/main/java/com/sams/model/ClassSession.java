// language: java
package com.sams.model;

import java.time.LocalDateTime;

public class ClassSession {
    private int id;
    private int courseId;
    private int subjectId;
    private int lecturerId;
    private LocalDateTime scheduledAt;

    public ClassSession() {}

    public ClassSession(int id, int courseId, int subjectId, int lecturerId, LocalDateTime scheduledAt) {
        this.id = id; this.courseId = courseId; this.subjectId = subjectId; this.lecturerId = lecturerId; this.scheduledAt = scheduledAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }

    public int getLecturerId() { return lecturerId; }
    public void setLecturerId(int lecturerId) { this.lecturerId = lecturerId; }

    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }

    @Override
    public String toString() {
        return id + " | courseId=" + courseId + " subjectId=" + subjectId + " lecturerId=" + lecturerId + " at " + scheduledAt;
    }
}
