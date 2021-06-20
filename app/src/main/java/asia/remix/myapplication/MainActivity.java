package asia.remix.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
	static final int STORAGE_REQUEST_CODE = 102;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		if( ContextCompat.checkSelfPermission( this, Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED ){
			ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_REQUEST_CODE );
		}
	}

	@Override
	public void onRequestPermissionsResult( int requestCode, String[] permissions, int[] grantResults ){
		Log.d( "■", "onRequestPermissionsResult()" );
		super.onRequestPermissionsResult( requestCode, permissions, grantResults );
		if( requestCode == STORAGE_REQUEST_CODE ){
			if( grantResults[0] != PackageManager.PERMISSION_GRANTED ){
				Toast.makeText( this, "must WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT ).show();
				finish();
			}
		}
	}
}