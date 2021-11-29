package com.sqlite.roomdb;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    //creating a new variable for student repository.
    private StudentRepository repository;
    //below line is to create a variable for live data where all the students are present.
    private LiveData<List<StudentModel>> allStudents;

    //constructor for our view modal.
    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allStudents = repository.getAllStudents();
    }


    //below method is use to insert the data to our repository.
    public void insert(StudentModel model) {
        repository.insert(model);
    }

    //below line is to update data in our repository.
    public void update(StudentModel model) {
        repository.update(model);
    }

    //below line is to delete the data in our repository.
    public void delete(StudentModel model) {
        repository.delete(model);
    }

    //below method is to delete all the students in our list.
    public void deleteAllStudents() {
        repository.deleteAllStudents();
    }

    //below method is to get all the students in our list.
    public LiveData<List<StudentModel>> getAllStudents() {
        return allStudents;
    }
}
