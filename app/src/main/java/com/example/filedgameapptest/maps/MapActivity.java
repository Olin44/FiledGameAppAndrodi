package com.example.filedgameapptest.maps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.filedgameapptest.MainActivity;
import com.example.filedgameapptest.R;
import com.example.filedgameapptest.apiconnections.ObjectOnMapDetails;
import com.example.filedgameapptest.apiconnections.RetrofitClientInstance;
import com.example.filedgameapptest.apiconnections.models.GameService;
import com.example.filedgameapptest.apiconnections.models.GameUserDTO;
import com.example.filedgameapptest.imagerecognition.CameraActivity;
import com.example.filedgameapptest.imagerecognition.ImageRecognition;
import com.example.filedgameapptest.maps.data.GameDataRepository;
import com.example.filedgameapptest.maps.data.MapDataRepository;
import com.example.filedgameapptest.users.account.UserAccountActivity;
import com.example.filedgameapptest.users.data.UserDataRepository;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.filedgameapptest.util.Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MapActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private UserDataRepository userDataRepository = UserDataRepository.getInstance();
    private GameUserDTO gameUserDTO;
    private MapDataRepository mapDataRepository = MapDataRepository.getInstance();
    private GameDataRepository gameDataRepository = GameDataRepository.getInstance();

    private FloatingActionButton btnCamera;
    private FloatingActionButton btnEndGame;

    private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */

    private static final String TAG = "MapActivity";

    private GoogleMap mMap;
    private LatLng userLocation;
    private LatLng objectLocation;
    private LocationRequest mLocationRequest;
    private MarkerOptions objectMarker;
    private ObjectOnMapDetails currentObject;

    private ImageRecognition imageRecognition = new ImageRecognition();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initViews();
        addNewUserGameToUser();
        currentObject = mapDataRepository.getObjectOnMapDetails().get(0);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void initViews() {

        btnCamera = findViewById(R.id.btnCamera);
        btnEndGame = findViewById(R.id.btnEndGame);

        btnEndGame.setOnClickListener(this);
        btnCamera.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: called.");
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                boolean isObjectFoud = data.getBooleanExtra("isCorrectObject",false);
                if(isObjectFoud){
                    //TODO:Monika Go to next object
                }
                else{
                    Toast.makeText(getApplicationContext(), "Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void addNewUserGameToUser() {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        GameService gameService = retrofit.create(GameService.class);
        Call<GameUserDTO> call = gameService.registerUser(userDataRepository.getId(), mapDataRepository.getId());
        call.enqueue(new Callback<GameUserDTO>() {
            @Override
            public void onResponse(Call<GameUserDTO> call, Response<GameUserDTO> response) {
                if(response.isSuccessful()) {
                    gameUserDTO = response.body();
                    setNewGame();
                }
            }
            @Override
            public void onFailure(Call<GameUserDTO> call, Throwable t) {
                System.out.println(t.toString());
            }
        });

    }

    private void setNewGame() {
        gameDataRepository.setAllData(gameUserDTO.getId(), gameUserDTO.getMapId(),gameUserDTO.getUserId(),gameUserDTO.getPoints(), gameUserDTO.isActive());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCamera:
                runCamera();
                break;
            case R.id.btnEndGame:
                startActivity(new Intent(this, EndGameActivity.class));
                break;
        }
    }

    private void runCamera() {
        startActivityForResult(new Intent(this, CameraActivity.class).putExtra("currectObjectType", currentObject.getObjectType()), 1);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(checkPermissions()) {
            googleMap.setMyLocationEnabled(true);
        }
        mMap = googleMap;
        startLocationUpdates();
        setObjectOnMap();
    }


    private void setObjectOnMap() {
        Log.d(TAG, "setObjectOnMap: called.");
        //Get object location
        objectLocation = currentObject.getLatLng();
        objectMarker = new MarkerOptions().position(objectLocation).title("Object location");
        mMap.addMarker(objectMarker);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(objectLocation, 15));
    }

    // Trigger new location updates at interval
    protected void startLocationUpdates() {

        // Create the location request to start receiving updates
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        // do work here
                        onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());
    }

    public void getLastLocation() {
        // Get last known recent location using new Google Play Services SDK (v11+)
        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(this);

        locationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // GPS location can be null if GPS is switched off
                        if (location != null) {
                            onLocationChanged(location);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MapDemoActivity", "Error trying to get last GPS location");
                        e.printStackTrace();
                    }
                });
    }

    public void onLocationChanged(Location location) {
        // New location has now been determined
//        String msg = "Updated Location: " +
//                Double.toString(location.getLatitude()) + "," +
//                Double.toString(location.getLongitude());
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        // You can now create a LatLng Object for use with maps
        userLocation = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
    }


    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions();
            return false;
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    }

    @Override
    protected void onDestroy() {
        //TODO Jacek closing the game
        super.onDestroy();
    }
}
