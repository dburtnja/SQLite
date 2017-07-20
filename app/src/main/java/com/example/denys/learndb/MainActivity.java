package com.example.denys.learndb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button          add;
    private EditText        fName;
    private EditText        lName;
    private EditText        age;
    private DBHelper        dbHelper;
    private SQLiteDatabase  database;
    private ContentValues   values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor  cursor;

        add = (Button) findViewById(R.id.add);
        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        age = (EditText) findViewById(R.id.age);

        dbHelper = new DBHelper(this, 2);
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        cursor = database.query(dbHelper.T_CONTACTS, null, null, null, null, null, null);

        if (cursor.moveToFirst()){
            int idI = cursor.getColumnIndex(dbHelper.KEY_ID);
            int fnI = cursor.getColumnIndex(dbHelper.KEY_F_NAME);
            int lnI = cursor.getColumnIndex(dbHelper.KEY_L_NAME);
            int ageI = cursor.getColumnIndex(dbHelper.KEY_AGE);

            fName.setText(cursor.getString(fnI));
            lName.setText(cursor.getString(lnI));
            age.setText(cursor.getString(ageI));
        }else
            Log.d("Cursor", "no row");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.put(dbHelper.KEY_F_NAME, fName.getText().toString());
                values.put(dbHelper.KEY_L_NAME, lName.getText().toString());
                values.put(dbHelper.KEY_AGE, Integer.parseInt(age.getText().toString()));

                database.insert(dbHelper.T_CONTACTS, null, values);

            }
        });
    }
}
