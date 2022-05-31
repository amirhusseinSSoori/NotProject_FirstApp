package com.amir.ss.noteproject;

import static maes.tech.intentanim.CustomIntent.customType;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.amir.ss.noteproject.data.di.AppContainer;
import com.amir.ss.noteproject.ui.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputActivity extends AppCompatActivity {

    EditText edit_title;
    EditText edit_description;
    String date;

    MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        mainViewModel= new AppContainer().ProvideMainVieModel();
        customType(InputActivity.this, "left-to-right");
        edit_title = (EditText) findViewById(R.id.input_Titel);
        edit_description = (EditText) findViewById(R.id.input_Detail);
        SimpleDateFormat sdf = (SimpleDateFormat) new SimpleDateFormat("MMM MM, dd, yyyy h:mm a").getDateTimeInstance();
        date = sdf.format(new Date());
    }


    @Override
    public void onBackPressed() {
        if (edit_title.getText().length() == 0) {

            mainViewModel.insertToNotes("noTitle", detail(), date);
            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            intent.putExtra("TabNumber", "0");
            startActivity(intent);
            finish();
        } else {
            mainViewModel.insertToNotes(title(), detail(), date);
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
