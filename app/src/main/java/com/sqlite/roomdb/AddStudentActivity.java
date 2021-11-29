package com.sqlite.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

    //creating a variables for our button and edittext.
    private EditText studentNameEdt, studentEmailEdt, studentPhoneEdt;
    private Button saveBtn;

    //creating a constant string variable for our student name, email and phone no.
    public static final String EXTRA_ID =
            "com.sqlite.roomdb.EXTRA_ID";
    public static final String EXTRA_STUDENT_NAME =
            "com.sqlite.roomdb.EXTRA_STUDENT_NAME";
    public static final String EXTRA_STUDENT_EMAIL =
            "com.sqlite.roomdb.EXTRA_STUDENT_EMAIL";
    public static final String EXTRA_STUDENT_PHONE =
            "com.sqlite.roomdb.EXTRA_STUDENT_PHONE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        //initializing our variables for each view.
        studentNameEdt = findViewById(R.id.idEdtStudentName);
        studentEmailEdt = findViewById(R.id.idEdtStudentEmail);
        studentPhoneEdt = findViewById(R.id.idEdtStudentPhone);
        saveBtn = findViewById(R.id.idBtnSaveStudent);
        //below line is to get intent as we are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            //if we get id for our data then we are setting values to our edit text fields.
            studentNameEdt.setText(intent.getStringExtra(EXTRA_STUDENT_NAME));
            studentEmailEdt.setText(intent.getStringExtra(EXTRA_STUDENT_EMAIL));
            studentPhoneEdt.setText(intent.getStringExtra(EXTRA_STUDENT_PHONE));
        }
        //adding on click listner for our save button.
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting text value from edittext and validating if the text fields are empty or not.
                String studentName = studentNameEdt.getText().toString();
                String studentEmail = studentEmailEdt.getText().toString();
                String studentPhone = studentPhoneEdt.getText().toString();
                if (studentName.isEmpty() || studentPhone.isEmpty() || studentPhone.isEmpty()) {
                    Toast.makeText(AddStudentActivity.this, "Please enter the valid student details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //calling a method to save our students.
                saveCourse(studentName, studentEmail, studentPhone);
            }
        });

    }

    private void saveCourse(String studentName, String studentEmail, String studentPhone) {
        //inside this method we are passing all the data via an intent.
        Intent data = new Intent();
        //in below line we are passing all our student detail.
        data.putExtra(EXTRA_STUDENT_NAME, studentName);
        data.putExtra(EXTRA_STUDENT_EMAIL, studentEmail);
        data.putExtra(EXTRA_STUDENT_PHONE, studentPhone);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            //in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }
        //at last we are setting result as data.
        setResult(RESULT_OK, data);
        //displaying a toast message after adding the data
        Toast.makeText(this, "Student has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }

}