package com.example.android.calc2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class welcome extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txt=(TextView)findViewById(R.id.textView);
        Intent int2=getIntent();
        String ss=new String();
        ss=(String)int2.getSerializableExtra("Username");
        //Log.d("myTag", ss);
        txt.setText("Welcome "+ss);

    }
}
