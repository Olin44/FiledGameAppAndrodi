package com.example.filedgameapptest.imagerecognition;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.example.filedgameapptest.R;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import static com.example.filedgameapptest.util.Constants.PERMISSIONS_REQUEST_CAMERA_PERMISSION;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton btnGoBackToMap;
    private SurfaceView surfaceViewCamera;
    private static final String TAG = "CameraActivity";

    private CameraSource cameraSource;
    private String  objectType;
    private boolean isCorrectObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initViews();
        Intent intent = getIntent();
        objectType = intent.getStringExtra("objectType");
        isCorrectObject = false;
    }


    private void initViews() {

        btnGoBackToMap = findViewById(R.id.btnGoBackToMap);
        btnGoBackToMap.setOnClickListener(this);

        surfaceViewCamera = findViewById(R.id.surfaceViewCamera);
        setSurfaceViewCamera();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGoBackToMap:
                goBackToMap();
                break;
        }
    }

    private void setSurfaceViewCamera(){
        Log.d(TAG, "setSurfaceViewCamera: called.");

        //TODO Jacek: image detection
        Detector detector = new Detector() {
            @Override
            public SparseArray detect(Frame frame) {
                return null;
            }
        };

        cameraSource = new CameraSource.Builder(this, detector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true)
                .build();

        surfaceViewCamera.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceViewCamera.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(CameraActivity.this, new
                                String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
    }

    private void checkType(){
        if(isCorrectObject){
            Toast.makeText(getApplicationContext(), "You found correct object!", Toast.LENGTH_SHORT).show();
            goBackToMap();
        }
    }

    private void goBackToMap() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("isCorrectObject", isCorrectObject);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
