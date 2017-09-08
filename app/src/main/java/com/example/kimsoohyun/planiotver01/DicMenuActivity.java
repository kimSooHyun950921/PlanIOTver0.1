package com.example.kimsoohyun.planiotver01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.kimsoohyun.planiotver01.Adapter.DicAdpaterRecycler;
import com.example.kimsoohyun.planiotver01.Item.DicItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class DicMenuActivity extends AppCompatActivity {

    private  FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = mDatabase.getReference("dictionary");
    private  FirebaseStorage storage = FirebaseStorage.getInstance();
    private  StorageReference storageRef = storage.getReferenceFromUrl("gs://firebase-planiot.appspot.com");
    private  StorageReference spaceRef;
    private ArrayList<DicItem>list = new ArrayList<>();
    private RecyclerView dicRecyclerView;


    private DicAdpaterRecycler dicAdpaterRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_dic);
        dicAdpaterRecycler = new DicAdpaterRecycler(list,this);

       dicRecyclerView = (RecyclerView) findViewById(R.id.recycler_dic_view);
        dicRecyclerView.setHasFixedSize(true);
        dicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dicAdpaterRecycler = new DicAdpaterRecycler(list,DicMenuActivity.this);
        dicRecyclerView.setAdapter(dicAdpaterRecycler);

        updateList();
        final Intent intent = new Intent(DicMenuActivity.this,DicInfoActivity.class);
        dicRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Toast.makeText(getBaseContext(),"Touchevent", Toast.LENGTH_SHORT).show();
                startActivity(intent);


            }
        });

    }

    private void updateList(){
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(dataSnapshot.getValue(DicItem.class));
                dicAdpaterRecycler.notifyDataSetChanged();

                Log.i("딕셔너리스냅샷정보",String.valueOf(dataSnapshot.getValue(DicItem.class).getHumidity()));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                DicItem item ;
                item = dataSnapshot.getValue(DicItem.class);
                int index = getItemIndex(item);
                list.set(index,item);
                dicAdpaterRecycler.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private int getItemIndex(DicItem item){
        int index = -1;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getName().equals("plant"+i+1)){
                index = i;
                break;
            }
        }
        return index;
    }

}
