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
import android.widget.Toast;

import com.example.kimsoohyun.planiotver01.Adapter.menuAdapter;
import com.example.kimsoohyun.planiotver01.Item.MyPlantItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = mDatabase.getReference("myPlant");
    private DatabaseReference myRef = mDatabase.getReference("myPlant");
    private ListView listview;
    private menuAdapter adapter;
    private Intent IntentFromDicInfo = getIntent();
    private ArrayList<MyPlantItem> list = new ArrayList<>();
    private Long myPlantCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        adapter = new menuAdapter();
        listview = (ListView) findViewById(R.id.myplantList);
        Toast.makeText(MainActivity.this,"어댑터크기:"+String.valueOf(adapter.getCount()),Toast.LENGTH_SHORT);


        Log.i("어댑터크기updataList후에값",String.valueOf(adapter.getCount()));
        Toast.makeText(MainActivity.this,"어댑터크기이후값:"+String.valueOf(adapter.getCount()),Toast.LENGTH_SHORT);

        myRef.child("size").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object i = dataSnapshot.getValue();
                myPlantCount = 0L;
                Object k = (Long)myPlantCount;
                if(i.equals(k)){
                startDialog();}
                else {
                    updateList();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("error","error");

            }
        });

      // int childSize = Integer.parseInt(myRef.child("size").getKey());
        //if( childSize<=0) {
         /*   startDialog();
        }
        else{
            updateList();
            myRef.child("size").setValue(childSize++);
        }*/




        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //ItemMenu item = (ItemMenu)adapterView.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });



    }

    private void startDialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        ad.setTitle("식물이 없어요!");
        ad.setMessage("아직 키우고있는 식물이 없네요 식물을 추가하시겠습니까?");
        ad.setPositiveButton("네",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Intent intent = new Intent(MainActivity.this,DicMenuActivity.class);
                startActivity(intent);

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


    private void updateList() {

        rootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey().equals("size")){
                    myPlantCount++;
                    rootRef.child("size").setValue(1);

                }else {
                    adapter.addItem(dataSnapshot.getValue(MyPlantItem.class).getMyPlantImg(), dataSnapshot.getValue(MyPlantItem.class).getMyPlantName()
                            , dataSnapshot.getValue(MyPlantItem.class).getOriginalPlantName(), dataSnapshot.getValue(MyPlantItem.class).getPlantDate());
                    listview.setAdapter(adapter);
                    Log.i("메뉴스냅샷정보", String.valueOf(dataSnapshot.getValue(MyPlantItem.class).getMyPlantName()));
                }

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
