package com.sar.user.attendance_teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Last extends AppCompatActivity {
    ListView listView1;
    String a,b,w;
    ArrayList<String> arrayList;
    int attandance,ey;
    static String r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        listView1=findViewById(R.id.name);
        arrayList=new ArrayList<>();
        Intent intent=getIntent();
        r=intent.getStringExtra("fgh");



        final Adapter_teacher adapter_teacher=new Adapter_teacher(this,arrayList);
        FirebaseDatabase.getInstance().getReference().child(r).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    a= (String) dataSnapshot.getValue();
                    Log.d("jj",a);

                            FirebaseDatabase.getInstance().getReference().child(a).child("users");
                            FirebaseDatabase.getInstance().getReference().child(a).child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                                    {
                                        arrayList.add(dataSnapshot1.getKey());
                                        adapter_teacher.notifyDataSetChanged();

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }





            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //FirebaseDatabase.getInstance().getReference().child(b).child("users");

          listView1.setAdapter(adapter_teacher);
          listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //startActivity(new Intent(Last.this,MainActivity.class));
                ey=position;
                FirebaseDatabase.getInstance().getReference().child(arrayList.get(position)).child(r).child("attend").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            w= dataSnapshot.getValue().toString();
                            attandance= Integer.parseInt(w)+1;
                            FirebaseDatabase.getInstance().getReference().child(arrayList.get(ey)).child(r).child("attend").setValue(attandance);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Log.d("kkuo","jj");

            }
        });


    }
}
