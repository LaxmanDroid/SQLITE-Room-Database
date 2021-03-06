package com.sqlite.roomdb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {
    //below line is the create a variable for dao and list for all students.
    private Dao dao;
    private LiveData<List<StudentModel>> allStudents;

    //creating a constructor for our variables and passing the variables to it.
    public StudentRepository(Application application) {
        StudentDatabase database = StudentDatabase.getInstance(application);
        dao = database.Dao();
        allStudents = dao.getAllStudents();
    }

    //creating a method to insert the data to our database.
    public void insert(StudentModel model) {
        new InsertCourseAsyncTask(dao).execute(model);
    }

    //creating a method to update data in database.
    public void update(StudentModel model) {
        new UpdateCourseAsyncTask(dao).execute(model);
    }

    //creating a method to delete the data in our database.
    public void delete(StudentModel model) {
        new DeleteCourseAsyncTask(dao).execute(model);
    }

    //below is the method to delete all the students.
    public void deleteAllStudents() {
        new DeleteAllStudentsAsyncTask(dao).execute();
    }

    //below method is to read all the students.
    public LiveData<List<StudentModel>> getAllStudents() {
        return allStudents;
    }

    //we are creating a async task method to insert new student.
    private static class InsertCourseAsyncTask extends AsyncTask<StudentModel, Void, Void> {
        private Dao dao;

        private InsertCourseAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StudentModel... model) {
            //below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    //we are creating a async task method to update our student.
    private static class UpdateCourseAsyncTask extends AsyncTask<StudentModel, Void, Void> {
        private Dao dao;

        private UpdateCourseAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StudentModel... models) {
            //below line is use to update our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete student.
    private static class DeleteCourseAsyncTask extends AsyncTask<StudentModel, Void, Void> {
        private Dao dao;

        private DeleteCourseAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StudentModel... models) {
            //below line is use to delete our student modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete all students.
    private static class DeleteAllStudentsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        private DeleteAllStudentsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //on below line calling method to delete all students.
            dao.deleteAllStudents();
            return null;
        }
    }
}
