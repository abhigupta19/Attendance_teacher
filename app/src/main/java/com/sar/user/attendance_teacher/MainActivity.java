package com.sar.user.attendance_teacher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText gmail,password;
    SharedPreferences shared;
    SharedPreferences.Editor sharedPreference;
    public static final String  MY_GLOBAL = "my_global";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        shared=getSharedPreferences(MY_GLOBAL,0);
        final int ab=shared.getInt("floe",0);
        if(ab==2)
        {
            startActivity(new Intent(MainActivity.this,Teacher_front.class));
        }
        firebaseAuth=FirebaseAuth.getInstance();
        gmail=findViewById(R.id.editText2);
        password=findViewById(R.id.editText6);
        sharedPreference = (SharedPreferences.Editor) getSharedPreferences(MY_GLOBAL,MODE_PRIVATE).edit();
        Button button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newuser(gmail.getText().toString(),password.getText().toString());


            }
        });
    }
    private void newuser(String name,String password)
    {
        firebaseAuth.createUserWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(MainActivity.this,"Registration complete",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,Teacher_front.class));
                sharedPreference.putInt("floe",2);
                sharedPreference.commit();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                Log.d("hiiii",e.getMessage());
            }
        });
    }

}
