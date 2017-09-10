package com.example.kimsoohyun.planiotver01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kimsoohyun.planiotver01.Adapter.menuAdapter;
import com.example.kimsoohyun.planiotver01.Item.MyPlantItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = mDatabase.getReference("myPlant");
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;

    private ListView listview;
    private menuAdapter adapter;

    private Intent IntentFromDicInfo = getIntent();

    private ArrayList<MyPlantItem> list = new ArrayList<>();
    //private RecyclerView myPlantRecyclerView;
    //private MyPlantRecyclerAdapter plantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

       // plantAdapter = new MyPlantRecyclerAdapter(list,this);
      //  myPlantRecyclerView = (RecyclerView)findViewById(R.id.recycler_myplant_view);
      //  myPlantRecyclerView.setHasFixedSize(true);
      //  myPlantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       // myPlantRecyclerView.setAdapter(plantAdapter);
       updateList();
        adapter = new menuAdapter();
        listview = (ListView) findViewById(R.id.myplantList);

       // addList();






        if(adapter.isEmpty()){
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            ad.setTitle("식물이 없어요!");
            ad.setMessage("아직 키우고있는 식물이 없네요 식물을 추가하시겠습니까?");
            ad.setPositiveButton("네",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    Intent intent = new Intent(MainActivity.this,DicMenuActivity.class);
                    startActivity(intent);
                    /*db에저장한것을 불러와 adapter에 넣어주어야한다*/
                    //adapter.addItem(ContextCompat.getDrawable(MainActivity.this,R.drawable.tulip),"튤립","2017년 8월 3일");
                    //listview.setAdapter(adapter);
                    dialog.dismiss();
                }
            });
            ad.setNegativeButton("아니요",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            ad.show();
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //ItemMenu item = (ItemMenu)adapterView.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });
      // adapter.addItem(ContextCompat.getDrawable(this,R.drawable.images),"달심이","2014년 4월 3일");



    }
    private void addList(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds:dataSnapshot.getChildren()){
            MyPlantItem item = new MyPlantItem();
            item.setMyPlantImg(ds.child(userID).getValue(MyPlantItem.class).getMyPlantImg());
            item.setMyPlantName(ds.child(userID).getValue(MyPlantItem.class).getMyPlantName());
            item.setOriginalPlantName(ds.child(userID).getValue(MyPlantItem.class).getOriginalPlantName());
            item.setPlantDate(ds.child(userID).getValue(MyPlantItem.class).getPlantDate());

            adapter.addItem(item.getMyPlantImg(),item.getMyPlantName(),item.getOriginalPlantName(),item.getPlantDate());
            listview.setAdapter(adapter);
            Log.i("메뉴스냅샷정보",String.valueOf(dataSnapshot.getValue(MyPlantItem.class).getMyPlantName()));
        }
    }

    private void updateList() {
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.addItem(dataSnapshot.getValue(MyPlantItem.class).getMyPlantImg(),dataSnapshot.getValue(MyPlantItem.class).getMyPlantName()
                        ,dataSnapshot.getValue(MyPlantItem.class).getOriginalPlantName(),dataSnapshot.getValue(MyPlantItem.class).getPlantDate());
                listview.setAdapter(adapter);
                Log.i("메뉴스냅샷정보",String.valueOf(dataSnapshot.getValue(MyPlantItem.class).getMyPlantName()));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.actionbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.newPost) {
            Intent intent = new Intent(MainActivity.this,DicMenuActivity.class);
            startActivity(intent);


           
        }
            return super.onOptionsItemSelected(item);
    }







}
