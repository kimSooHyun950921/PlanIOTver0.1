package com.example.kimsoohyun.planiotver01;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kimsoohyun.planiotver01.Adapter.DicAdapter;
import com.example.kimsoohyun.planiotver01.Adapter.DicAdpaterRecycler;
import com.example.kimsoohyun.planiotver01.Item.DicItem;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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


    private DicAdapter dicAdapter;
    private ListView listView;






    DicDatabase dicdb;


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





        //  adapter = new CustomList(Dic.this);
        final Intent intent = new Intent(DicMenuActivity.this,DicInfoActivity.class);
        // list = (ListView)findViewById(R.id.dic_list);
        //  list.setAdapter(adapter);


       // writeNewUser();
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





    private void writeNewUser(){
        dicdb = new DicDatabase();
        DatabaseReference dic  = myRef.child("dictionary");


        dic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void showData(DataSnapshot dataSnapshot){
        setupRecyclerView(dataSnapshot);


    }

    private void setupRecyclerView(DataSnapshot snap) {
        DatabaseReference picRef = FirebaseDatabase.getInstance()
                .getReference("fir-planiot").child("dictionary");
        picRef.limitToLast(10).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snap: dataSnapshot.getChildren()){
                   DicItem item = snap.getValue(DicItem.class);
                    Log.i("title:",item.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                    Log.e("dicITem","failed:"+databaseError.getMessage());
            }
        });
    }

    private Drawable drawImage(int i) {
        ImageView imageView = (ImageView)findViewById(R.id.dic_image);
        spaceRef = storageRef.child("images/dictionary/"+(i+1)+"jpg");

        return  (Drawable)Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(spaceRef)
                .into(imageView);
    }



  /*  public class CustomList extends ArrayAdapter<String> {
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
        }*/

}
