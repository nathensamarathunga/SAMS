// language: java
package com.sams.model;

public class Student {
    private int id;
    private String regNo;
    private String name;
    private int courseId;
    private String contact;

    public Student() {}

    public Student(int id, String regNo, String name, int courseId, String contact) {
        this.id = id; this.regNo = regNo; this.name = name; this.courseId = courseId; this.contact = contact;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    @Override
    public String toString() {
        return id + " | " + regNo + " - " + name + " (courseId=" + courseId + ")";
    }
}
