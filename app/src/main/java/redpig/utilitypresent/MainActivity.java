package redpig.utilitypresent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "TEST";

    private EditText mDbNameEditText, mTbEditText;
    private Button mCreateDbButton, mCreateTbButton;
    private TextView mResultTextView;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbNameEditText = (EditText)findViewById(R.id.editText);
        mTbEditText = (EditText)findViewById(R.id.editText2);
        mCreateDbButton = (Button)findViewById(R.id.button);
        mCreateTbButton = (Button)findViewById(R.id.button2);
        mResultTextView = (TextView)findViewById(R.id.result_textView);

        mCreateDbButton.setOnClickListener(this);
        mCreateTbButton.setOnClickListener(this);

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

    private void createDatabase(String name){

    }

    private void createTable(){

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button :
                String dbName = mDbNameEditText.getText().toString().trim();

                break;
            case R.id.button2 :

                break;
        }

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


