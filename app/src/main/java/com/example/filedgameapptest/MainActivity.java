package com.example.filedgameapptest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.filedgameapptest.apiconnections.models.GameUserDTO;
import com.example.filedgameapptest.maps.MapFragment;
import com.example.filedgameapptest.maps.ScannedBarcodeActivity;
import com.example.filedgameapptest.maps.data.GameDataRepository;
import com.example.filedgameapptest.users.login.LoginActivity;
import com.example.filedgameapptest.users.register.RegisterActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import static com.example.filedgameapptest.util.Constants.ERROR_DIALOG_REQUEST;
import static com.example.filedgameapptest.util.Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
import static com.example.filedgameapptest.util.Constants.PERMISSIONS_REQUEST_ENABLE_GPS;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button btnLogIn;
    private Button btnSignUp;
    private Button btnMapFragment;

    private boolean mLocationPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void initViews() {
        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUpActivity);
        btnMapFragment = findViewById(R.id.btnMapFragment);

        btnMapFragment.setEnabled(false);
        btnSignUp.setEnabled(false);
        btnLogIn.setEnabled(false);

        btnLogIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnMapFragment.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnLogIn:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.btnSignUpActivity:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
            case R.id.btnMapFragment:
                startActivity(new Intent(MainActivity.this, MapFragment.class));
                break;
        }

    }

    private boolean checkMapServices(){
        if(isServicesOK()){
            if(isMapsEnabled()){
                return true;
            }
        }
        return false;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean isMapsEnabled(){
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            enableButtons();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: called.");
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ENABLE_GPS: {
                if(mLocationPermissionGranted){
                    enableButtons();
                }
                else{
                    getLocationPermission();
                }
            }
        }

    }

    public void enableButtons(){
        initViews();
        btnMapFragment.setEnabled(true);
        btnSignUp.setEnabled(true);
        btnLogIn.setEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(checkMapServices()){
            if(mLocationPermissionGranted){
                enableButtons();
            }
            else{
                getLocationPermission();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
