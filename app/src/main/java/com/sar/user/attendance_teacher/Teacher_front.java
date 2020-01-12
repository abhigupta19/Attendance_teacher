package com.sar.user.attendance_teacher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Teacher_front extends AppCompatActivity {
     String ab="radha";
    SharedPreferences.Editor sharedprefence;
     Set<String> set;
    EditText editText,editText2;
    AlertDialog.Builder alert;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<String> saa,sab;
    ws ws;
    String atyy;
    static int sum=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_front);
        saa=new ArrayList<>();
        //saa.add("gg");
        listView=findViewById(R.id.ram);
        ws=new ws(this,saa);
        sab=new ArrayList<>();
        //Adapter_teacher adapter_teacher=new Adapter_teacher(this,saa);
        FloatingActionButton floatingActionButton=findViewById(R.id.floatingActionbutton);
        sharedprefence = (SharedPreferences.Editor) getSharedPreferences(ab,0).edit();
        set=new HashSet<>();
        set.add("00");
        sharedprefence.putStringSet("kaka",set);
        firebaseDatabase= FirebaseDatabase.getInstance();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert=new AlertDialog.Builder(Teacher_front.this);
                LayoutInflater layoutInflater=getLayoutInflater();
                View view=layoutInflater.inflate(R.layout.box,null);
                rab( view);
                alert.setView(view);
                alert.show();
            }



            private void rab(View view) {
                 editText=view.findViewById(R.id.edit_text);
                 editText2=view.findViewById(R.id.edit_text2);
                Button button=view.findViewById(R.id.buttu);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SharedPreferences sharedPreferences=getSharedPreferences(ab,0);
                        Set<String> k=sharedPreferences.getStringSet("kaka",null);
                        if(!set.contains(editText2.getText().toString()))
                        {
                            set.add(editText2.getText().toString());
                            sharedprefence.putStringSet("kaka",set);
                            databaseReference=firebaseDatabase.getReference(editText2.getText().toString());
                            databaseReference.child("class").setValue(editText.getText().toString());
                            FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(editText.getText().toString()).setValue("subject");
                            FirebaseDatabase.getInstance().getReference().child(editText.getText().toString()).setValue(editText2.getText().toString());
                            ws.notifyDataSetChanged();
                            Toast.makeText(Teacher_front.this,"Done",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(Teacher_front.this,"Not Done",Toast.LENGTH_SHORT).show();
                        }


                    }
                });




            }
        });
        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotk:dataSnapshot.getChildren())
                {
                    Log.d("gg","ll");
                    saa.add(dataSnapshotk.getKey());
                    ws.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        listView.setAdapter(ws);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(Teacher_front.this,Last.class);
                          intent.putExtra("fgh",saa.get(position));
                        // sum=sum+1;
                         //FirebaseDatabase.getInstance().getReference().child(arrayList.get(ey)).child(r).child("attend").setValue(attandance);
                        startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        onDestroy();

    }
}
