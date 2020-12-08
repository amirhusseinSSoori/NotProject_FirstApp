package com.amir.ss.noteproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.supercharge.shimmerlayout.ShimmerLayout;

import static maes.tech.intentanim.CustomIntent.customType;

public class InputActivity extends Activity {
    LinearLayout layout;
    EditText titel_input;
    EditText detail_input;
    String currentDateandTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);



        customType(InputActivity.this,"left-to-right");





        layout=(LinearLayout)findViewById(R.id.line_five);

        titel_input=(EditText)findViewById(R.id.input_Titel);
        detail_input=(EditText)findViewById(R.id.input_Detail);
        SimpleDateFormat sdf = (SimpleDateFormat) new SimpleDateFormat("MMM MM, dd, yyyy h:mm a").getDateTimeInstance();
        currentDateandTime = sdf.format(new Date());





//MMM MM, dd, yyyy h:mm a






    }


    @Override
    public void onBackPressed() {
        if(titel_input.getText().length()==0 ){


            new DataBaseHelper(InputActivity.this).add_Category("noTitel",getDetail(),currentDateandTime);
            Intent intent=new Intent(InputActivity.this,MainActivity.class);
            intent.putExtra("TabNumber","0");
            startActivity(intent);
            finish();
        }
        else {
            new DataBaseHelper(InputActivity.this).add_Category(getTitel(),getDetail(),currentDateandTime);

            Intent intent=new Intent(InputActivity.this,MainActivity.class);
            intent.putExtra("TabNumber","0");
            startActivity(intent);
            finish();

        }


    }







    public String getTitel(){
        String titel=((EditText)findViewById(R.id.input_Titel)).getText().toString();
        return titel;
    }
    public String getDetail(){
        String detail=((EditText)findViewById(R.id.input_Detail)).getText().toString();
        return detail;
    }


}
