package com.student.alina.spendingtracker;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.priyankvex.easyocrscannerdemo.Config;
import com.wordpress.priyankvex.easyocrscannerdemo.EasyOcrScanner;
import com.wordpress.priyankvex.easyocrscannerdemo.EasyOcrScannerListener;


public class OcrCaptureActivity extends AppCompatActivity implements EasyOcrScannerListener {
    EasyOcrScanner mEasyOcrScanner;
    TextView textView;
    ProgressDialog mProgressDialog;
    private static final int REQUEST_WRITE_PERMISSION = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        //textView = (TextView) findViewById(R.id.textView);

        // initialize EasyOcrScanner instance.
        mEasyOcrScanner = new EasyOcrScanner(OcrCaptureActivity.this, "EasyOcrScanner",
                Config.REQUEST_CODE_CAPTURE_IMAGE, "eng");

        // Set ocrScannerListener
        mEasyOcrScanner.setOcrScannerListener(this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(OcrCaptureActivity.this, new
                        String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mEasyOcrScanner.takePicture();
                } else {
                    Toast.makeText(OcrCaptureActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Call onImageTaken() in onActivityResult.
        if (resultCode == RESULT_OK && requestCode == Config.REQUEST_CODE_CAPTURE_IMAGE){
            mEasyOcrScanner.onImageTaken();
        }
    }

    /**
     * Callback when after taking picture, scanning process starts.
     * Good place to show a progress dialog.
     * @param filePath file path of the image file being processed.
     */
    @Override
    public void onOcrScanStarted(String filePath) {
        mProgressDialog = new ProgressDialog(OcrCaptureActivity.this);
        mProgressDialog.setMessage("Scanning...");
        mProgressDialog.show();
    }

    /**
     * Callback when scanning is finished.
     * Good place to hide teh progress dialog.
     * @param bitmap Bitmap of image that was scanned.
     * @param recognizedText Scanned text.
     */
    @Override
    public void onOcrScanFinished(Bitmap bitmap, String recognizedText) {
        //textView.setText(recognizedText);
        if (mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }
}
