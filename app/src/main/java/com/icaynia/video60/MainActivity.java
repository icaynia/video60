package com.icaynia.video60;

import android.content.Intent;
import android.media.MediaRecorder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    int frameRate = 60;


    MediaRecorder mediaRecorder = null;

    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        Button startButton = (Button) findViewById(R.id.startButton);
        Button endButton = (Button) findViewById(R.id.endButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Started Recording", Toast.LENGTH_SHORT).show();

                mediaRecorder = new MediaRecorder();

                try {
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
                    mediaRecorder.setVideoFrameRate(frameRate);
                    mediaRecorder.setVideoSize(1920, 1080);

                    mediaRecorder.setOutputFile("/sdcard/tt.mp4");

                    mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
                    mediaRecorder.prepare();
                    mediaRecorder.start();

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "IOException", Toast.LENGTH_SHORT).show();
                }


            }
        });

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast .makeText(MainActivity.this, "End Recording", Toast.LENGTH_SHORT).show();
                mediaRecorder .stop();
                mediaRecorder .release();
                mediaRecorder = null;
            }
        });
    }
}
