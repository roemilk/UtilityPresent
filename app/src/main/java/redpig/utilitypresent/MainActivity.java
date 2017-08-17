package redpig.utilitypresent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import redpig.utility.gps.GpsInfo;
import redpig.utility.gps.GpsInterface;
import redpig.utility.gps.GpsManager;
import redpig.utility.gps.GpsUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GpsInterface.onUpdateListener {
    private final String TAG = "TEST";

    private Button mGpsButton, mGpsStopButton;
    private TextView mGpsTextView;
    private GpsManager mGPGpsManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGpsButton = (Button)findViewById(R.id.button3);
        mGpsStopButton = (Button)findViewById(R.id.button);
        mGpsTextView = (TextView)findViewById(R.id.textView);
        mGpsButton.setOnClickListener(this);
        mGpsStopButton.setOnClickListener(this);

        boolean gpsCheck = GpsUtils.getGpsState(this);
        if(gpsCheck){
            Toast.makeText(this, "GPS 사용이 가능합니다.", Toast.LENGTH_SHORT).show();
            mGPGpsManager = GpsManager.getGpsManager(this, this);
        }else{
            Toast.makeText(this, "GPS 사용이 불가능합니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button3 :
                mGPGpsManager.startGpsService(100, 0, true);
                break;
            case R.id.button :
                mGPGpsManager.stopGpsService();
                break;
        }
    }

    @Override
    public void updateGpsInfo(GpsInfo gpsinfo) {
        double latitude = gpsinfo.getLatitude();
        double longitue = gpsinfo.getLongitude();
        float speed = gpsinfo.getSpeed();
        float bearing = gpsinfo.getBearing();
        mGpsTextView.setText("위도 : " + latitude + " 경도 : " + longitue + " 속도 : " + speed + " 방향 : " + bearing);
    }
}


