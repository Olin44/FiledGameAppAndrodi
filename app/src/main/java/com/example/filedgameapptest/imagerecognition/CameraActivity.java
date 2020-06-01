package com.example.filedgameapptest.imagerecognition;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import dmax.dialog.SpotsDialog;
/**
 * Klasa odpowiedzialna za uruchomienie kamery, zrobienie zdjęcia obiektu i sprawdzenie, czy sfotografowany obiekt to obiekt, którego szuka gracz.
 */
public class CameraActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     * Widok odpowiedzialny za wyświetlenie podlądu kamery.
     */
    CameraView cameraView;
    /**
     * Przycisk, po naciśnięciu, którego zostanie wykonane zdjęcie.
     */
    Button btnDetect;
    /**
     * Alert informujący o tym, że zdjęcie jest przetwarzane.
     */
    AlertDialog alertDialog;
    private FloatingActionButton btnGoBackToMap;
    /**
     * Pole przechowujące nazwę klasy, konieczne do logowania błędów.
     */
    private static final String TAG = "CameraActivity";
    /**
     * Pole przechowujące czas, który pozostał do zakończenia gry.
     */
    private Long mTimeLeftInMillis;
    /**
     * Pole informację o tym jaki obiekt ma zostać znaleziony.
     */
    private String correctObject;
    /**
     * Pole informację o tym czy obiekt został znaleziony poprawnie.
     */
    private boolean isCorrectObject;
    /**
     * Pole textowe odpowiedzialne za wyświetlanie, czasu, który pozostał do zakończenia gry.
     */
    private TextView timerTextView;
    /**
     * Klasa przechwoująca licznik odliczający czas do końca gry.
     */
    private CountDownTimer countDownTimer;
    /**
     * Metoda odpowiedzialna za akcję po wznowieniu działania widoku.
     */
    @Override
    protected void onResume(){
        super.onResume();
        cameraView.start();
    }
    /**
     * Metoda odpowiedzialna za akcję po zatrzymaniu działania widoku.
     */
    @Override
    protected void onPause() {
        super.onPause();
        cameraView.stop();
    }
    /**
     * Metoda odpowiedzialna za stworzenie widoku.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initViews();
        Intent intent = getIntent();
        correctObject = intent.getStringExtra("objectType");
        isCorrectObject = false;
        mTimeLeftInMillis = intent.getLongExtra("timeLeft", 0);
        startTimer();
    }

    /**
     * Metoda odpowiedzialna za zainicjowanie kontrolek na widoku i obsługę kamery.
     */
    private void initViews() {

        btnGoBackToMap = findViewById(R.id.btnGoBackToMap);
        btnGoBackToMap.setOnClickListener(this);
        timerTextView = findViewById(R.id.timerCameraTextView);

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
    /**
     * Metoda odpowiedzialna za zainicjowanie wykrywacza obiektów na obrazie.
     */
    private void runDetector(Bitmap bitmap) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);

        FirebaseVisionOnDeviceImageLabelerOptions options = new FirebaseVisionOnDeviceImageLabelerOptions.Builder().build();
        FirebaseVisionImageLabeler detector =  FirebaseVision.getInstance().getOnDeviceImageLabeler(options);
        Task<List<FirebaseVisionImageLabel>> processImage = detector.processImage(image);
        processImage.addOnSuccessListener(this::processDataResults);
        processImage.addOnFailureListener(e -> Log.d("Recognition error", Objects.requireNonNull(e.getMessage())));
    }
    /**
     * Metoda odpowiedzialna za przetworzenie informacji jakie przesłał detektor.
     */
    private void processDataResults(List<FirebaseVisionImageLabel> labelList){
        List<String> objectFoundOnImageNames = new ArrayList<>();
        for(FirebaseVisionImageLabel label : labelList){
            objectFoundOnImageNames.add(label.getText());
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String name: objectFoundOnImageNames) {
            stringBuilder
                    .append(name.toUpperCase())
                    .append(" ");
        }
        Toast.makeText(this, "Result" + stringBuilder.toString(), Toast.LENGTH_SHORT).show();
        isCorrectObject = isCorrectObject(objectFoundOnImageNames);
        checkType();
        alertDialog.dismiss();
    }
    /**
     * Metoda definiująca aktywności jakie zostaną wykonane po naciśnięciu poszczególnych przycisków.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGoBackToMap:
                goBackToMap();
                break;
        }
    }
    /**
     * Metoda odpowiedzialna za powrót do widoku mapy.
     */
    private void goBackToMap() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("isCorrectObject", isCorrectObject);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    /**
     * Metoda odpowiedzialna za aktualizację pola tekstowego z czasem pozostałym do zakończenia rozgrywki.
     */
    private void updateTimerText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);
    }
    /**
     * Metoda odpowiedzialna za rozpoczęcia odliczania czasu pozostałego do zakończenia rozgrywki.
     */
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
    /**
     * Metoda odpowiedzialna za zakończenie gry.
     */
    private void endGame(){
        finish();
    }
    /**
     * Metoda odpowiedzialna za aktywność jaka zostanie wykonana po odnalezieniu poprawnego obiektu.
     */
    private void checkType() {
        if (isCorrectObject) {
            Toast.makeText(getApplicationContext(), "You found correct object!", Toast.LENGTH_SHORT).show();
            goBackToMap();
        }
    }
    /**
     * Metoda sprawdzająca czy szukany obiekt znajduje się na liście obiektów znalezionych przez detektor.
     */
    private boolean isCorrectObject(List<String> objectOnImageNamesList){
        return objectOnImageNamesList.contains(correctObject);
    }
}
