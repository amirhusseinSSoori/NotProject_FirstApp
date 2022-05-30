package com.amir.ss.noteproject;

import static maes.tech.intentanim.CustomIntent.customType;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amir.ss.noteproject.data.db.DataBaseHelper;

public class DetailActivity extends AppCompatActivity {


    EditText edit_title;
    EditText edit_detail;
    TextView txt_title;
    TextView text_detail;
    Category category;
    LinearLayout layout;

    private androidx.appcompat.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        layout = (LinearLayout) findViewById(R.id.line_fure);
        customType(DetailActivity.this, "left-to-right");

        edit_title = (EditText) findViewById(R.id.Detail_Titel);
        edit_detail = (EditText) findViewById(R.id.Detail_Detail);
        txt_title = (TextView) findViewById(R.id.Detail_txt_Titel);
        text_detail = (TextView) findViewById(R.id.Detail_txt_Detail);
        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar_one);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        category = (Category) getIntent().getExtras().getSerializable("category");

        txt_title.setText(category.getTitle().toString());
        text_detail.setText(category.getDetail().toString());
        edit_title.setText(category.getTitle().toString());
        edit_detail.setText(category.getDetail().toString());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_call:
                SetVisibility();
                break;

            case android.R.id.home:
                new DataBaseHelper(this).UpdateCategory(category.getCategoryId(), edit_title.getText().toString(), edit_detail.getText().toString());
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("TabNumber", "0");
                startActivity(intent);
                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void SetVisibility() {
        if (txt_title.getVisibility() == View.VISIBLE && text_detail.getVisibility() == View.VISIBLE) {
            edit_title.setVisibility(View.VISIBLE);
            edit_detail.setVisibility(View.VISIBLE);
            txt_title.setVisibility((View.GONE));
            text_detail.setVisibility((View.GONE));
            new DataBaseHelper(this).UpdateCategory(category.getCategoryId(), edit_title.getText().toString(), edit_detail.getText().toString());
            txt_title.setText(edit_title.getText().toString());
            text_detail.setText(edit_detail.getText().toString());

        } else {
            edit_title.setVisibility(View.GONE);
            edit_detail.setVisibility(View.GONE);
            txt_title.setVisibility((View.VISIBLE));
            text_detail.setVisibility((View.VISIBLE));
            txt_title.setText(edit_title.getText().toString());
            text_detail.setText(edit_detail.getText().toString());
            new DataBaseHelper(this).UpdateCategory(category.getCategoryId(), txt_title.getText().toString(), text_detail.getText().toString());
        }

    }


    @Override
    public void onBackPressed() {
        new DataBaseHelper(this).UpdateCategory(category.getCategoryId(), edit_title.getText().toString(), edit_detail.getText().toString());
        startActivity(new Intent(DetailActivity.this, MainActivity.class));
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TabNumber", "0");
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
