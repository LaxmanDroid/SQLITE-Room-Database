package com.sqlite.roomdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//below line is for setting table name.
@Entity(tableName = "student_table")
public class StudentModel {

    //below line is to auto increment id for each student.
    @PrimaryKey(autoGenerate = true)
    //variable for our id.
    private int id;
    //below line is a variable for student name.
    private String studentName;
    //below line is use for student email.
    private String studentEmail;
    //below line is use for student phone no.
    private String studentPhoneNo;

    //below line we are creating constructor class.
    //inside constructor class we are not passing our id because it is incrementing automatically
    public StudentModel(String studentName, String studentEmail, String studentPhoneNo) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhoneNo = studentPhoneNo;
    }

    //on below line we are creating getter and setter methods.
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhoneNo() {
        return studentPhoneNo;
    }

    public void setStudentPhoneNo(String studentPhoneNo) {
        this.studentPhoneNo = studentPhoneNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
