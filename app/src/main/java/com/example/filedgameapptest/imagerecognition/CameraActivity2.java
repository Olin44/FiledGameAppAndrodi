//package com.example.filedgameapptest.imagerecognition;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.ImageFormat;
//import android.graphics.Rect;
//import android.graphics.YuvImage;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.util.Log;
//import android.util.SparseArray;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.filedgameapptest.R;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.vision.CameraSource;
//import com.google.android.gms.vision.Detector;
//import com.google.android.gms.vision.Frame;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.ml.vision.FirebaseVision;
//import com.google.firebase.ml.vision.common.FirebaseVisionImage;
//import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
//import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
//import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetectorOptions;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.Locale;
//
//import static com.example.filedgameapptest.util.Constants.PERMISSIONS_REQUEST_CAMERA_PERMISSION;
//
//public class CameraActivity2 extends AppCompatActivity implements View.OnClickListener {
//
//    private FloatingActionButton btnGoBackToMap;
//    private SurfaceView surfaceViewCamera;
//    private static final String TAG = "CameraActivity";
//    private Long mTimeLeftInMillis;
//
//    private CameraSource cameraSource;
//    private String  objectType;
//    private boolean isCorrectObject;
//    private TextView timerTextView;
//    private CountDownTimer countDownTimer;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_camera);
//        initViews();
//        Intent intent = getIntent();
//        objectType = intent.getStringExtra("objectType");
//        isCorrectObject = false;
//        mTimeLeftInMillis = intent.getLongExtra("timeLeft", 0);
//        startTimer();
//    }
//
//
//    private void initViews() {
//
//        btnGoBackToMap = findViewById(R.id.btnGoBackToMap);
//        btnGoBackToMap.setOnClickListener(this);
//        timerTextView = findViewById(R.id.timerCameraTextView);
//        surfaceViewCamera = findViewById(R.id.surfaceViewCamera);
//        setSurfaceViewCamera();
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnGoBackToMap:
//                goBackToMap();
//                break;
//        }
//    }
//
//    private void setSurfaceViewCamera(){
//        Log.d(TAG, "setSurfaceViewCamera: called.");
//
//        //TODO Jacek: image detection
//        Detector detector = new Detector() {
//            @Override
//            public SparseArray detect(Frame frame) {
//                SparseArray sparseArray = new SparseArray();
//                FirebaseVisionObjectDetectorOptions options =
//                        new FirebaseVisionObjectDetectorOptions
//                                .Builder()
//                                .setDetectorMode(FirebaseVisionObjectDetectorOptions.SINGLE_IMAGE_MODE)
//                                .enableClassification()
//                                .build();
//                YuvImage yuvImage = new YuvImage(frame.getGrayscaleImageData().array(), ImageFormat.NV21, frame.getMetadata().getWidth(), frame.getMetadata().getHeight(), null);
//                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                yuvImage.compressToJpeg(new Rect(0, 0, frame.getMetadata().getWidth(), frame.getMetadata().getHeight()), 100, byteArrayOutputStream);
//                byte[] jpegArray = byteArrayOutputStream.toByteArray();
//                Bitmap tempBitmap = BitmapFactory.decodeByteArray(jpegArray, 0, jpegArray.length);
//                FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(tempBitmap);
//
//                FirebaseVisionImageLabeler objectDetector = FirebaseVision.getInstance().getOnDeviceImageLabeler();
// //                       FirebaseVision.getInstance().getOnDeviceObjectDetector();
//                objectDetector.processImage(image)
//                        .addOnSuccessListener(labels -> {
//                            int i = 0;
//                            for (FirebaseVisionImageLabel l : labels) {
//                                sparseArray.append(i, l);
//                                i++;
//                            }
//                        })
//                        .addOnFailureListener(
//                                new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        System.out.println("Failure");
//                                        //failure aka ja
//                                    }
//                                });
//                return sparseArray;
//            }
//        };
//
//        cameraSource = new CameraSource.Builder(this, detector)
//                .setRequestedPreviewSize(1920, 1080)
//                .setAutoFocusEnabled(true)
//                .build();
//
//        surfaceViewCamera.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                try {
//                    if (ActivityCompat.checkSelfPermission(CameraActivity2.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                        cameraSource.start(surfaceViewCamera.getHolder());
//                    } else {
//                        ActivityCompat.requestPermissions(CameraActivity2.this, new
//                                String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA_PERMISSION);
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                cameraSource.stop();
//            }
//        });
//    }
//
//    private void checkType(){
//        if(isCorrectObject){
//            Toast.makeText(getApplicationContext(), "You found correct object!", Toast.LENGTH_SHORT).show();
//            goBackToMap();
//        }
//    }
//
//    private void goBackToMap() {
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra("isCorrectObject", isCorrectObject);
//        setResult(RESULT_OK, resultIntent);
//        finish();
//    }
//
//    private void updateTimerText(){
//        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
//        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
//        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
//        timerTextView.setText(timeLeftFormatted);
//    }
//
//    private void startTimer() {
//        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMillis = millisUntilFinished;
//                updateTimerText();
//            }
//            @Override
//            public void onFinish() {
//                endGame();
//            }
//        }.start();
//    }
//
//    private void endGame(){
//        finish();
//    }
//}
