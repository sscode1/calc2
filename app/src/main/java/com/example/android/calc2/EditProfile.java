package com.example.android.calc2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditProfile extends AppCompatActivity {
    EditText nameed,cityed,qfed,emailed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        nameed=(EditText)findViewById(R.id.name);
        cityed=(EditText)findViewById(R.id.city);
        emailed=(EditText)findViewById(R.id.email);
        qfed=(EditText)findViewById(R.id.qualification);
        Intent int3=getIntent();
        nameed.setText((String)int3.getSerializableExtra("Username"));
        cityed.setText((String)int3.getSerializableExtra("city"));
        qfed.setText((String)int3.getSerializableExtra("qf"));
        emailed.setText((String)int3.getSerializableExtra("em"));
    }
}
