package com.example.android.calc2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView nameview,emailview,qfview,cityview;
    FloatingActionButton fab;
    String s1,s2;
    Bitmap bitmap;
    de.hdodenhof.circleimageview.CircleImageView pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nameview=(TextView)findViewById(R.id.namedisplay);
        emailview=(TextView)findViewById(R.id.emaildisplay);
        qfview=(TextView)findViewById(R.id.degreedisplay);
        cityview=(TextView)findViewById(R.id.locationdisplay);
        fab=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        pro=(de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.profilephoto);
        Intent int1=getIntent();
        s1=(String)int1.getSerializableExtra("Username");
        s2=(String)int1.getSerializableExtra("Email");
        nameview.setText(s1);
        emailview.setText(s2);
        qfview.setText((String)int1.getSerializableExtra("degree"));
        cityview.setText((String)int1.getSerializableExtra("city"));
        bitmap=int1.getParcelableExtra("BitmapImage");
        pro.setImageBitmap(bitmap);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2=new Intent(Profile.this,EditProfile.class);
                int2.putExtra("Username",nameview.getText().toString());
                int2.putExtra("qf",qfview.getText().toString());
                int2.putExtra("city",cityview.getText().toString());
                int2.putExtra("bitmap",bitmap);
                int2.putExtra("em",emailview.getText().toString());
                startActivity(int2);
            }
        });
    }
}
