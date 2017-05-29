package redpig.utilitypresent;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import redpig.utility.network.Ping;

public class MainActivity extends AppCompatActivity implements Ping.OnScanPingCheckListener {
    private final String TAG = "TEST";

    private TextView mResultTextView, mProgressTextView = null;
    private StringBuffer mStringBuffer = new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResultTextView = (TextView)findViewById(R.id.textView);
        mProgressTextView = (TextView)findViewById(R.id.scaninngTextView);

        Ping ping = new Ping("211.109.235.40");
        ping.setOnScanPingCheckListener(this);
        ping.scanAllPing();


//        Fragment fragment = new SettingsScreen();
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        if(savedInstanceState == null){
//            // crated for the first time
//            fragmentTransaction.add(R.id.container, fragment, "settings_fragment");
//            fragmentTransaction.commit();
//        }else{
//            fragment = getFragmentManager().findFragmentByTag("settings_fragment");
//
//        }

    }

    @Override
    public void onSuccessPingCheck(final String ipAddress) {
        Log.d(TAG, "onSuccessPingCheck : " + ipAddress);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mStringBuffer.append(ipAddress + "\n");
                mResultTextView.setText(mStringBuffer.toString());
            }
        });
    }

    @Override
    public void onCompletePingCheck() {
        Log.d(TAG, "onCompletePingCheck..");
        mProgressTextView.setText("스캔이 완료되었습니다..");
    }

    public static class SettingsScreen extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_screen);
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
    public void onBackPressed() {
    }
}


