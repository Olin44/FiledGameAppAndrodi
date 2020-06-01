package com.example.filedgameapptest.imagerecognition;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.filedgameapptest.R;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import dmax.dialog.SpotsDialog;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener{
    CameraView cameraView;
    Button btnDetect;
    AlertDialog alertDialog;
    private FloatingActionButton btnGoBackToMap;
    private static final String TAG = "CameraActivity";
    private Long mTimeLeftInMillis;

    private String  objectType;
    private boolean isCorrectObject;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;

    @Override
    protected void onResume(){
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.stop();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        Intent intent = getIntent();
        objectType = intent.getStringExtra("objectType");
        isCorrectObject = false;
        mTimeLeftInMillis = intent.getLongExtra("timeLeft", 0);
        startTimer();
    }


    private void initViews() {

        btnGoBackToMap = (FloatingActionButton) findViewById(R.id.btnGoBackToMap);
        btnGoBackToMap.setOnClickListener(this);
        timerTextView = findViewById(R.id.timerCameraTextView);

        setContentView(R.layout.activity_camera);

        cameraView = findViewById(R.id.camera_view);
        btnDetect = findViewById(R.id.btn_detect);

        alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Please wait...")
                .setCancelable(false)
                .build();

        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                alertDialog.show();
                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap
                        .createScaledBitmap(bitmap, cameraView.getWidth(), cameraView.getHeight(), false);
                cameraView.stop();
                runDetector(bitmap);
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });

        btnDetect.setOnClickListener(v -> {
            cameraView.start();
            cameraView.captureImage();
        });
    }

    private void runDetector(Bitmap bitmap) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);

        FirebaseVisionOnDeviceImageLabelerOptions options = new FirebaseVisionOnDeviceImageLabelerOptions.Builder().build();
        FirebaseVisionImageLabeler detector =  FirebaseVision.getInstance().getOnDeviceImageLabeler(options);
        Task<List<FirebaseVisionImageLabel>> processImage = detector.processImage(image);
        processImage.addOnSuccessListener(this::processDataResults);
        processImage.addOnFailureListener(e -> Log.d("Recognition error", Objects.requireNonNull(e.getMessage())));
    }

    private void processDataResults(List<FirebaseVisionImageLabel> labelList){
        StringBuilder stringBuilder = new StringBuilder();
        for(FirebaseVisionImageLabel label : labelList){
            stringBuilder.append(label.getText());
        }
        Toast.makeText(this, "Result" + stringBuilder.toString(), Toast.LENGTH_SHORT).show();
        alertDialog.dismiss();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGoBackToMap:
                goBackToMap();
                break;
        }
    }

    private void goBackToMap() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("isCorrectObject", isCorrectObject);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void updateTimerText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }
            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
    }

    private void endGame(){
        finish();
    }

    private void checkType(){
        if(isCorrectObject){
            Toast.makeText(getApplicationContext(), "You found correct object!", Toast.LENGTH_SHORT).show();
            goBackToMap();
        }
    }
}
