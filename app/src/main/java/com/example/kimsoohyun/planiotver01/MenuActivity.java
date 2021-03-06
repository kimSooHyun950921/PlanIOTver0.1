package com.example.kimsoohyun.planiotver01;

import android.animation.ObjectAnimator;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kimsoohyun.planiotver01.Item.MyPlantItem;
import com.github.lzyzsd.circleprogress.CircleProgress;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

import static com.example.kimsoohyun.planiotver01.R.id.sun_movement_switch;

public class MenuActivity extends AppCompatActivity {
   private ConnectedThread mConnectedThread;

    CircleProgress circleProgress;
    CircleProgress circleProgressWater;
    private static final String TAG = "bluetooth";
    final int RECIEVE_MESSAGE = 1;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder sb = new StringBuilder();
    private static int flag = 0;
    private int sunValue;
    private int waterValue;

    Handler handler;


    private ImageView fullStatusImage;
    private TextView suntv;
    private TextView watertv;
    byte[] readBuffer;


    private Switch curtain;
    private Switch curtainForTemp;
    private Switch lighting;
    private Button  watering;
    char mCharDelimiter =  '\n';




    //   private ConnectedThread mConnectedThread;

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address;

    private Intent intentFromMyPlantItem;
    private MyPlantItem plantItem;
    private ImageView view;
    private EditText text;
    private Switch switch1;
    private ObjectAnimator waterAnimator;
    private  ObjectAnimator sunAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lighting = (Switch) findViewById(sun_movement_switch);
        curtain = (Switch) findViewById(R.id.sun_block_curtain);
        curtainForTemp = (Switch) findViewById(R.id.sun_block_curtain_for_temp);
        watering = (Button) findViewById(R.id.watering);
        circleProgress = (CircleProgress)findViewById(R.id.sun_circle_progress);
        circleProgressWater  =(CircleProgress)findViewById(R.id.circle_progress_water);
        fullStatusImage = (ImageView)findViewById(R.id.fullstatusImage);




        intentFromMyPlantItem = getIntent();

        view = (ImageView) findViewById(R.id.imageView);
        text = (EditText) findViewById(R.id.plantName);


        text.setText(intentFromMyPlantItem.getStringExtra("name"));


        Glide.with(this)
                .load(intentFromMyPlantItem.getStringExtra("img"))
                .into(view);

      sunAnimator = ObjectAnimator.ofInt(circleProgress,"progress",0,10);
        sunValue = (Integer)sunAnimator.getAnimatedValue();
        sunAnimator.setInterpolator(new DecelerateInterpolator());
        sunAnimator.start();

        waterAnimator = ObjectAnimator.ofInt(circleProgressWater,"progress",0,30);
        waterValue =(Integer)waterAnimator.getAnimatedValue();
        waterAnimator.setInterpolator(new DecelerateInterpolator());
       waterAnimator.start();
        checkFullStatus();
        AlertDialog.Builder dialog = new AlertDialog.Builder(MenuActivity.this);
        dialog.setTitle("자동모드");
        dialog.setMessage("현재 자동모드입니다. " +
                " switch가 비활성화 되어있습니다.."
               );
        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();




       handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case RECIEVE_MESSAGE:
                        byte[] readBuf = (byte[]) msg.obj;
                        String strIncom = new String(readBuf, 0, msg.arg1);
                        sb.append(strIncom);
                        int endOfLineIndex = sb.indexOf("\r\n");
                        if (endOfLineIndex > 0) {
                            String sbprint = sb.substring(0, endOfLineIndex);
                            Toast.makeText(MenuActivity.this, sbprint, Toast.LENGTH_SHORT).show();
                            sb.delete(0, sb.length());
                            Toast.makeText(MenuActivity.this,"Data From Arduino"+sbprint,Toast.LENGTH_SHORT).show();
                        }
                        break;
                }


                btAdapter = BluetoothAdapter.getDefaultAdapter();
                checkBTState();
            }
        };
        lighting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    mConnectedThread.write("3");
                    sunValue = (Integer)sunAnimator.getAnimatedValue();
                    sunAnimator.setDuration(3000);
                    sunAnimator.setIntValues(sunValue+30);
                    sunAnimator.start();
                    checkFullStatus();



                }
                else{
                  mConnectedThread.write("4");

                }

            }
        });

        curtainForTemp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    mConnectedThread.write("1");
                    TextView temperText = (TextView)findViewById(R.id.temperText);
                    temperText.setText("28.2도");
                    temperText.setText("28.4도");
                    temperText.setText("29.2도");
                    temperText.setText("26.3도");
                    temperText.setText("23.2도");
                    temperText.setText("27.5도");


                }
                else{
                    mConnectedThread.write("2");

                }

            }
        });

        curtain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                   mConnectedThread.write("1");

                    sunValue = (Integer)sunAnimator.getAnimatedValue();
                    sunAnimator.setDuration(3000);
                    sunAnimator.setIntValues(sunValue+20);
                    sunAnimator.start();
                    checkFullStatus();

                }
                else{
           mConnectedThread.write("2");

                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.switch_menu, menu);



        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.mySwitchswitch12){
            if(item.getTitle().toString().equals("수동모드")){

            item.setTitle("자동모드");
          mConnectedThread.write("9");
                AlertDialog.Builder dialog = new AlertDialog.Builder(MenuActivity.this);
                dialog.setTitle("자동모드로 전환");
                dialog.setMessage("자동모드로 전환하였습니다. " +
                        " 자동모드로 전환하였을경우 switch가 비활성화 됩니다.");
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();


            lighting.setEnabled(false);

            curtainForTemp.setEnabled(false);
            curtain.setEnabled(false);}
            else if(item.getTitle().toString().equals("자동모드")){

                item.setTitle("수동모드");





                lighting.setEnabled(true);
                curtainForTemp.setEnabled(true);
                curtain.setEnabled(true);
            }


        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onPause() {
        super.onPause();

       Log.d(TAG, "...In onPause()...");


        try {
            btSocket.close();
        } catch (IOException e) {
            errorExit("Fatal Error", "In onPause() and failed to close socket." + e.getMessage() + ".");
            Toast tooast = Toast.makeText(MenuActivity.this,"In onPause() and failed to close socket." + e.getMessage() ,Toast.LENGTH_SHORT);
            tooast.show();
        }
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if (Build.VERSION.SDK_INT >= 10) {
            try {
                final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[]{UUID.class});
                return (BluetoothSocket) m.invoke(device, MY_UUID);
            } catch (Exception e) {
                Log.e(TAG, "Could not create Insecure RFComm Connection", e);
                Toast toast = Toast.makeText(MenuActivity.this,"Could not create Insecure RFComm Connection",Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        return device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
    }



    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "...onResume - try connect...");



       btAdapter = BluetoothAdapter.getDefaultAdapter();


            Set<BluetoothDevice> pairDevices = btAdapter.getBondedDevices();
            if (pairDevices.size() > 0) {
                for (BluetoothDevice device : pairDevices) {
                    address = device.getAddress().toString();
                }
            }

        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
            Toast toastconerr = Toast.makeText(MenuActivity.this,"In onResume() and socket create failed: " + e.getMessage() ,Toast.LENGTH_LONG);
            toastconerr.show();
        }

        btAdapter.cancelDiscovery();

        Log.d(TAG, "...Connecting...");
        Toast toastcon = Toast.makeText(MenuActivity.this,"Connectiong",Toast.LENGTH_SHORT);
        toastcon.show();
        try {
            btSocket.connect();
            Log.d(TAG, "....Connection ok...");
            Toast toastconok = Toast.makeText(MenuActivity.this,"Connectiong ok",Toast.LENGTH_SHORT);
            toastconok.show();
        } catch (IOException e) {
            Toast toastconerr = Toast.makeText(MenuActivity.this,"In onResume() and unable to close socket during connection failure1" + e.getMessage() ,Toast.LENGTH_LONG);
            toastconerr.show();
            try {
                btSocket.close();
            } catch (IOException e2) {
                errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
                Toast toastconok = Toast.makeText(MenuActivity.this,"In onResume() and unable to close socket during connection failure2" + e2.getMessage() ,Toast.LENGTH_LONG);
                toastconok.show();
            }
        }

        Log.d(TAG, "...Create Socket...");
        //Toast toastconok = Toast.makeText(MenuActivity.this,"Create Socket",Toast.LENGTH_SHORT);
        //toastconok.show();

        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();



    }


    private void checkBTState() {
        if (btAdapter == null) {
            errorExit("Fatal Error", "Bluetooth not support");
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth ON...");

            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }
    public void wateringThePlant(View v){

      //  Toast.makeText(MenuActivity.this, "buttonClicked", Toast.LENGTH_LONG).show();
        waterValue = (Integer)waterAnimator.getAnimatedValue();

        waterAnimator.setDuration(3000);
        waterAnimator.setIntValues(waterValue+20);
        waterAnimator.start();

        checkFullStatus();


        mConnectedThread.write("5");

    }

    private void checkFullStatus() {
        if(waterValue>60&&sunValue>60){
            fullStatusImage.setImageResource(R.drawable.smile);
        }
        else if(waterValue>30&&sunValue>30){
            fullStatusImage.setImageResource(R.drawable.soso);
        }
        else{
            fullStatusImage.setImageResource(R.drawable.angry);
        }
    }

    public void onSwitchClicked(View view){
        switch(view.getId()){
            case R.id.sun_movement_switch:
                break;
            case R.id.sun_block_curtain:
                break;
            case R.id.sun_block_curtain_for_temp:
                break;
        }


    }




    private void errorExit(String title, String message) {
        Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        finish();
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        int readBufferPosition=0;
        private  Toast toast;


        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    handler.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

       public int readSensor(){

                try{
                    int byteAvailable = mmInStream.available();
                    if(byteAvailable>0){
                        byte[] packetBytes = new byte[byteAvailable];
                        mmInStream.read(packetBytes);
                        for(int i = 0; i<byteAvailable;i++) {
                            byte b = packetBytes[i];
                            if(b == mCharDelimiter) {
                            byte[] encodedBytes = new byte[readBufferPosition];
                            System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                            final String data = new String(encodedBytes, "US-ASCII");
                            readBufferPosition = 0;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    watertv.setText(data);

                                }
                            });
                        }
                            else{
                                readBuffer[readBufferPosition++] = b;
                            }

                        }
                    }

                }
                catch(IOException e){

                }


            int sensorValue=0;
       //     toast = Toast.makeText(MenuActivity.this,"data to send",Toast.LENGTH_SHORT);
         //   toast.show();
            try{
                sensorValue = mmInStream.read();


            }
            catch(IOException e){

            }
            return sensorValue;

        }


        public void write(String message) {
            Log.d(TAG, "...Data to send: " + message + "...");
           // Toast toastconok = Toast.makeText(MenuActivity.this,"data to send",Toast.LENGTH_SHORT);
          //  toastconok.show();
            byte[] msgBuffer = message.getBytes();
            try {
                mmOutStream.write(msgBuffer);
                Toast toast = Toast.makeText(MenuActivity.this,"writeOK",Toast.LENGTH_SHORT);
                toast.show();
            } catch (IOException e) {
                Log.d(TAG, "...Error data send: " + e.getMessage() + "...");
                Toast toast = Toast.makeText(MenuActivity.this,"Error data send: " + e.getMessage(),Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }


}
