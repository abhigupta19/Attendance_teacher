package com.sar.user.attendance_teacher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Adapter_teacher extends BaseAdapter
{
    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    Context context;
    ArrayList<String> as,ab;
    TextView textView,textView2;
    static int attandance=0;
    String ff;

    public Adapter_teacher(Context context, ArrayList<String> as) {
        this.context = context;
        this.as = as;
        ab=new ArrayList<>();
        ab.add("kk");
        ab.add("lk");
        ab.add("rk");

    }

    @Override
    public int getCount() {
        return as.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.card2,null);
        textView=view.findViewById(R.id.textView70);
        textView2=view.findViewById(R.id.textView72);
        //textView2.setText(as.get(position));
        String s=as.get(position);
        Log.d("ll",s);
        int e=dc(s);

        while (true)
        {
            if(e==1)
            {
                break;
            }
        }
        Log.d("ppp",ab.get(4));
        textView2.setText(ab.get(position));




        return view;
    }

    private void kd(String s) {
        FirebaseDatabase.getInstance().getReference().child(s).child("reds").child("attend").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ff= (String) dataSnapshot.getValue().toString();
                ab.add(ff);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    private int dc(String s) {

        FirebaseDatabase.getInstance().getReference().child(s).child("rollnumber").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String c= (String) dataSnapshot.getValue();
                    //Toast.makeText(Last.this,c,Toast.LENGTH_SHORT).show();
                    FirebaseDatabase.getInstance().getReference().child("users_name").child(c).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ab.add ((String) dataSnapshot.getValue());
                            //Log.d("uuu",d);


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
        return 1;



    }
    public void  khh(ListView listView)
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                attandance=attandance+1;
                Log.d("kku",as.get(position));
                FirebaseDatabase.getInstance().getReference().child(as.get(position)).child("attend").setValue(attandance);
                  textView.setText("jj");
            }
        });
    }
}
