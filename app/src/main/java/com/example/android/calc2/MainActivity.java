package com.example.android.calc2;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    EditText nameview,emailview,password,cpassword,cityview,qfview;
    Button b;
    ImageButton profiledp;
    String Name=new String();
    String email=new String();
    String s2=new String();
    String pass=new String();
    String cpass=new String();
    String qf=new String();
    String city=new String();
    String val=new String();
    Bitmap bitmap;
    private ProgressDialog pb;
    private FirebaseAuth fa;
    private DatabaseReference dbr;
    String validEmail="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+";
    String validName="[a-zA-Z ]+";
    String validPass="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=*!%(),.':;<>/?{}|+-_])(?=\\S+$).{6,}$";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== RESULT_LOAD_IMAGE && resultCode== RESULT_OK && null!=data){
            bitmap=(Bitmap)data.getExtras().get("data");
            profiledp.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameview=(EditText)findViewById(R.id.name);
        emailview=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        cpassword=(EditText)findViewById(R.id.confirmpassword);
        qfview=(EditText)findViewById(R.id.qualification);
        cityview=(EditText)findViewById(R.id.city);
        b=(Button)findViewById(R.id.registerbutton);
        profiledp=(ImageButton)findViewById(R.id.dp);
        pb=new ProgressDialog(this);
        fa=FirebaseAuth.getInstance();
        dbr= FirebaseDatabase.getInstance().getReference("users");
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Name=nameview.getText().toString().trim();
                email=emailview.getText().toString().trim();
                pass=password.getText().toString();
                cpass=cpassword.getText().toString();
                city=cityview.getText().toString().trim();
                qf=qfview.getText().toString().trim();
                if(pass.matches(validPass)&&Name.matches(validName)&&email.matches(validEmail)&&pass.equals(cpass)&&Name.length()!=0&&email.length()!=0&&pass.length()!=0&&cpass.length()!=0&&qf.length()!=0&&city.length()!=0){

                pb.setMessage("Registering...");
                pb.show();
                fa.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Registered on Firebase",Toast.LENGTH_LONG);
                            Intent intobj=new Intent(MainActivity.this,Profile.class);
                            intobj.putExtra("Username",Name);
                            intobj.putExtra("Email",email);
                            intobj.putExtra("city",city);
                            intobj.putExtra("degree",qf);
                            intobj.putExtra("BitmapImage", bitmap);
                            String id = dbr.push().getKey();
                            user us=new user(id,Name,email,qf,city);
                            dbr.child(id).setValue(us);
                            startActivity(intobj);
                        }
                    }
                });
                }
                else{
                    if(!pass.equals(cpass))
                    Toast.makeText(getApplicationContext(),"Re-entered password does not match",Toast.LENGTH_LONG).show();
                    else if(Name.length()==0||email.length()==0||pass.length()==0||cpass.length()==0||qf.length()==0||city.length()==0)
                        Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_LONG).show();
                    else if(!email.matches(validEmail))
                        Toast.makeText(getApplicationContext(),"Invalid Email Address",Toast.LENGTH_LONG).show();
                    else if(!Name.matches(validName))
                        Toast.makeText(getApplicationContext(),"Name should not contain digits/special characters)",Toast.LENGTH_LONG).show();
                    else if(!pass.matches(validPass))
                        Toast.makeText(getApplicationContext(),"Password should be atleast 6 characters long containing special characters,digits and alphabets without spaces",Toast.LENGTH_LONG).show();
                }
            }
        });
        profiledp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intobj2=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intobj2, RESULT_LOAD_IMAGE);

            }


        });


    }
}
