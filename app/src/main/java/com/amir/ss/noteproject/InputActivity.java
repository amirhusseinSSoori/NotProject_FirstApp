package com.amir.ss.noteproject;

import static maes.tech.intentanim.CustomIntent.customType;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;


import com.amir.ss.noteproject.data.db.DataBaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputActivity extends Activity {

    EditText edit_title;
    EditText edit_description;
    String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        customType(InputActivity.this, "left-to-right");
        edit_title = (EditText) findViewById(R.id.input_Titel);
        edit_description = (EditText) findViewById(R.id.input_Detail);
        SimpleDateFormat sdf = (SimpleDateFormat) new SimpleDateFormat("MMM MM, dd, yyyy h:mm a").getDateTimeInstance();
        date = sdf.format(new Date());
    }


    @Override
    public void onBackPressed() {
        if (edit_title.getText().length() == 0) {


            new DataBaseHelper(InputActivity.this).add_Category("noTitel", detail(), date);
            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            intent.putExtra("TabNumber", "0");
            startActivity(intent);
            finish();
        } else {
            new DataBaseHelper(InputActivity.this).add_Category(title(), detail(), date);

            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            intent.putExtra("TabNumber", "0");
            startActivity(intent);
            finish();

        }


    }


    public String title() {
        String title = ((EditText) findViewById(R.id.input_Titel)).getText().toString();
        return title;
    }

    public String detail() {
        String data = ((EditText) findViewById(R.id.input_Detail)).getText().toString();
        return data;
    }


}
