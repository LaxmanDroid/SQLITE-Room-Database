package com.sqlite.roomdb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //creating a variables for our recycler view.
    private RecyclerView studentsRV;
    private static final int ADD_STUDENT_REQUEST = 1;
    private static final int EDIT_STUDENT_REQUEST = 2;
    private ViewModel viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing our variable for our recycler view and fab.
        studentsRV = findViewById(R.id.idRVStudents);
        AppCompatButton btnAdd = findViewById(R.id.btn_addStudent);

        //adding on click listner for floating action button.
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting a new activity for adding a new student and passing a constant value in it.
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivityForResult(intent, ADD_STUDENT_REQUEST);
            }
        });

        //setting layout manager to our adapter class.
        studentsRV.setLayoutManager(new LinearLayoutManager(this));
        studentsRV.setHasFixedSize(true);
        //initializing adapter for recycler view.
        final StudentRVAdapter adapter = new StudentRVAdapter();
        //setting adapter class for recycler view.
        studentsRV.setAdapter(adapter);
        //passing a data from view modal.
        viewmodal = ViewModelProviders.of(this).get(ViewModel.class);
        //below line is use to get all the students from view modal.
        viewmodal.getAllStudents().observe(this, new Observer<List<StudentModel>>() {
            @Override
            public void onChanged(List<StudentModel> models) {
                //when the data is changed in our models we are adding that list to our adapter class.
                adapter.submitList(models);
            }
        });
        //below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //on recycler view item swiped then we are deleting the item of our recycler view.
                viewmodal.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Course deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                //below line is use to attact this to recycler view.
                        attachToRecyclerView(studentsRV);
        //below line is use to set item click listner for our item of recycler view.
        adapter.setOnItemClickListener(new StudentRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StudentModel model) {
                //after clicking on item of recycler view
                //we are opening a new activity and passing a data to our activity.
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                intent.putExtra(AddStudentActivity.EXTRA_ID, model.getId());
                intent.putExtra(AddStudentActivity.EXTRA_STUDENT_NAME, model.getStudentName());
                intent.putExtra(AddStudentActivity.EXTRA_STUDENT_EMAIL, model.getStudentEmail());
                intent.putExtra(AddStudentActivity.EXTRA_STUDENT_PHONE, model.getStudentPhoneNo());
                //below line is to start a new activity and adding a edit student constant.
                startActivityForResult(intent, EDIT_STUDENT_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_STUDENT_REQUEST && resultCode == RESULT_OK) {
            String studentName = data.getStringExtra(AddStudentActivity.EXTRA_STUDENT_NAME);
            String studentEmail = data.getStringExtra(AddStudentActivity.EXTRA_STUDENT_EMAIL);
            String studentPhoneNo = data.getStringExtra(AddStudentActivity.EXTRA_STUDENT_PHONE);
            StudentModel model = new StudentModel(studentName, studentEmail, studentPhoneNo);
            viewmodal.insert(model);
            Toast.makeText(this, "Student Details saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_STUDENT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddStudentActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Student Details can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String studentName = data.getStringExtra(AddStudentActivity.EXTRA_STUDENT_NAME);
            String studentEmail = data.getStringExtra(AddStudentActivity.EXTRA_STUDENT_EMAIL);
            String studentPhoneNo = data.getStringExtra(AddStudentActivity.EXTRA_STUDENT_PHONE);
            StudentModel model = new StudentModel(studentName, studentEmail, studentPhoneNo);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Student Details updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Student Details not saved", Toast.LENGTH_SHORT).show();
        }

    }
}