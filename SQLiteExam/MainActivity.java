package com.example.sqliteexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public class MyDB extends SQLiteOpenHelper {
        public MyDB(Context context) {
            super(context, "StudentDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE studentInfo ( number INTEGER PRIMARY KEY, name CHAR(20), grade CHAR(20));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS studentInfo");
            onCreate(db);
        }
    }

    MyDB myHelper;
    EditText student_number_edt, name_edt, grade_edt;
    EditText student_number_result_edt, name_result_edt, grade_result_edt;
    Button init_btn, insert_btn, select_btn;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("학생 관리 DB");

        student_number_edt = (EditText) findViewById(R.id.student_number_edt);
        name_edt = (EditText) findViewById(R.id.name_edt);
        grade_edt = (EditText) findViewById(R.id.grade_edt);
        student_number_result_edt = (EditText) findViewById(R.id.student_number_result_edt2);
        name_result_edt = (EditText) findViewById(R.id.name_result_edt);
        grade_result_edt = (EditText) findViewById(R.id.grade_result_edt2);

        init_btn = (Button) findViewById(R.id.init_btn);
        insert_btn = (Button) findViewById(R.id.insert_btn);
        select_btn = (Button) findViewById(R.id.select_btn);

        myHelper = new MyDB(this);

        init_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });

        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = student_number_edt.getText().toString();
                String name = name_edt.getText().toString();
                String grade = grade_edt.getText().toString();

                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL( "INSERT INTO studentInfo VALUES ( " + number + ",'" + name + "', '" + grade + "');" );
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력됨", Toast.LENGTH_SHORT).show();

                InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        select_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM studentInfo;", null);

                String number_str = "학번" + "\r\n" + "------------------------" + "\r\n";
                String name_str = "이름" + "\r\n" + "------------------------" + "\r\n";
                String grade_str = "성적" + "\r\n" + "------------------------" + "\r\n";

                while (cursor.moveToNext()) {
                    number_str += cursor.getString(0) + "\r\n";
                    name_str += cursor.getString(1) + "\r\n";
                    grade_str += cursor.getString(2) + "\r\n";
                }

                student_number_result_edt.setText(number_str);
                name_result_edt.setText(name_str);
                grade_result_edt.setText(grade_str);

                cursor.close();
                sqlDB.close();

                InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }
}