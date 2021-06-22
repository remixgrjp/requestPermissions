package asia.remix.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		if( ContextCompat.checkSelfPermission( this, Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED ){
			launcher.launch( Manifest.permission.WRITE_EXTERNAL_STORAGE );
		}
	}

	ActivityResultLauncher<String> launcher =registerForActivityResult(
		new ActivityResultContracts.RequestPermission(), isGranted -> {
			if( isGranted ){
				Log.d( "■", "onActivityResult()" );
			}else{
				Toast.makeText( this, "must WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT ).show();
				finish();
			}
		}
	);
}