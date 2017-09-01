package com.example.kimsoohyun.planiotver01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dic extends AppCompatActivity {

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference();
    DicDatabase dicdb;
    ListView list;
    CustomList adapter;
    String[] names = {"튤립","장미","해바리기","로즈마리"};
    Integer[] image = {R.drawable.tulip,R.drawable.rose,R.drawable.sunflower,R.drawable.rosemary};
    String[] summary ={"백합과의 여러해살이풀로 튤립속 식물의 총칭","장미과 장미속에 속하는 관목의 총칭","" +
            "국화과에 속하는 일년생 식물로, 꽃은 두상화","바늘같은 잎을 가진 여러해살이 식물로, 민트와 같은 과"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dic);
        adapter = new CustomList(Dic.this);
        final Intent intent = new Intent(Dic.this,Dic_info.class);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        writeNewUser();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(getBaseContext(), names[+position], Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }
    private void writeNewUser(){
        dicdb = new DicDatabase();
        DatabaseReference dic  = myRef.child("dictionary");
        DatabaseReference myPlant = dic.child("라벤더").child("이름");
        dic.child("산세베리아").child("이름");
        dic.child("애플민트").child("이름");
        dic.child("포인세티아").child("이름");

        myPlant.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Lavendar = dataSnapshot.getValue(String.class);

                names[names.length-1]= Lavendar;
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList(Activity contexts) {
            super(contexts, R.layout.listitem,names);
            this.context = contexts;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView name = (TextView) rowView.findViewById(R.id.name);
            TextView content = (TextView) rowView.findViewById(R.id.content);
            name.setText(names[position]);
            imageView.setImageResource(image[position]);
            content.setText(summary[position]);
            return rowView;
        }

    }
}