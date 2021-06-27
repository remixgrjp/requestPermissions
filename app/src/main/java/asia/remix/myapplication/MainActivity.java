package asia.remix.myapplication;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity{
	static String[] strPermission = new String[]{
		Manifest.permission.WRITE_EXTERNAL_STORAGE
	,	Manifest.permission.RECORD_AUDIO
	};

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		if( ContextCompat.checkSelfPermission( this, Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED
		||  ContextCompat.checkSelfPermission( this, Manifest.permission.RECORD_AUDIO ) != PackageManager.PERMISSION_GRANTED
		){
			launcher.launch( strPermission );
		}
	}

	ActivityResultLauncher<String[]> launcher = registerForActivityResult(
		new ActivityResultContracts.RequestMultiplePermissions()
	,	new ActivityResultCallback<Map<String, Boolean>>(){
			public void onActivityResult( Map<String, Boolean> isGranted ){
				boolean allGranted = true;
				for( String key : isGranted.keySet() ){
					Log.d( "■", "onActivityResult()" + key + ":" + isGranted.get( key ) );
					if( ! isGranted.get( key ) ){
						allGranted = false;
					}
				}
				if( ! allGranted ){
					Toast.makeText( MainActivity.this, "must WRITE_EXTERNAL_STORAGE, RECORD_AUDIO", Toast.LENGTH_SHORT ).show();
					finish();
				}
			}
		}
	);
}