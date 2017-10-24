package audricchabert.com.doubleselfie;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.hardware.camera2.CameraManager;
import android.widget.TextView;

@TargetApi(23)
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//my code

        TextView t = (TextView)findViewById(R.id.myTextView);



        Context c = getApplicationContext();
        CameraManager cameraManager = c.getSystemService(CameraManager.class);
        String[] cameraIdList = null;
        try{
            cameraIdList = cameraManager.getCameraIdList();
        }catch(CameraAccessException cae){
            //todo : find log function
            Log.e(TAG,"CameraAccessException getCameraIdList");
        }

        if(cameraIdList == null){
            Log.e(TAG,"cameraIdList is null");
        }else{
            for (String cameraId :
                    cameraIdList) {
                t.setText(t.getText()+cameraId);
                Log.e(TAG,"debug : cameraId"+cameraId);
                try {
                    CameraCharacteristics cc = cameraManager.getCameraCharacteristics(cameraId);
                    Log.e(TAG,"debug : cameraCharacteristics"+cc);

                }catch(CameraAccessException cae){
                    Log.e(TAG,"CameraAccessException getCameraCharacteristics");
                }
            }
        }

        if(checkCameraHardware(c)){
            Log.e(TAG,"debug : hasCameraHardware");
        }


//end my code


    }

    //my code

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    //end my code

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
