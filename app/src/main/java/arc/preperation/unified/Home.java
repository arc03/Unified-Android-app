package arc.preperation.unified;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    Switch s1,s2,s3,s4;
    Button b1,b2,b3;
    BluetoothAdapter ba;
    WifiManager wm;
    CameraManager cm;
    MediaPlayer mp;

    private boolean blue = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        s1 = (Switch) findViewById(R.id.switch1);
        s2 = (Switch) findViewById(R.id.switch2);
        s3 = (Switch) findViewById(R.id.switch3);
        s4 = (Switch) findViewById(R.id.switch4);
        b1 = (Button) findViewById(R.id.button6);
        b2 = (Button) findViewById(R.id.button8);
        b3 = (Button) findViewById(R.id.button11);
        ba= BluetoothAdapter.getDefaultAdapter();
        wm=(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        cm=(CameraManager) getSystemService(CAMERA_SERVICE);
        mp=MediaPlayer.create(this,R.raw.k);
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ba.enable();
                    blue=true;
                }
                else{
                    ba.disable();
                    blue=false;
                }
            }
        });
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    wm.setWifiEnabled(true);
                }
                else{
                    wm.setWifiEnabled(false);
                }
            }
        });
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    try{
                        String id = cm.getCameraIdList()[0];
                        cm.setTorchMode(id,true);
                    }
                    catch(CameraAccessException e){

                    }
                }
                else{
                    try{
                        String id = cm.getCameraIdList()[0];
                        cm.setTorchMode(id,false);
                    }
                    catch(CameraAccessException e){

                    }
                }
            }
        });
        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    mp.start();
                }
                else{
                    mp.pause();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this, Login.class);
                startActivity(i);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this, Camera.class);
                startActivity(i);
                finish();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(Home.this,Calc.class);
                startActivity(i);
                finish();
            }
        });
    }
}