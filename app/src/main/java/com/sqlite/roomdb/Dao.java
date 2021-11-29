package com.sqlite.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Adding annotation to our Dao class
@androidx.room.Dao
public interface Dao {

    //below method is use to add data to database.
    @Insert
    void insert(StudentModel model);

    //below method is use to update the data in our database.
    @Update
    void update(StudentModel model);

    //below line is use to delete a specific student in our database.
    @Delete
    void delete(StudentModel model);

    //on below line we are making query to delete all students from our database.
    @Query("DELETE FROM student_table")
    void deleteAllStudents();

    //beloe line is to read all the students from our database.
    //in this we are ordering our students in ascending order with our student name.
    @Query("SELECT * FROM student_table ORDER BY studentName ASC")
    LiveData<List<StudentModel>> getAllStudents();

}
