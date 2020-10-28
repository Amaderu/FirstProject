package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;

import android.widget.MediaController;
import android.view.View;



public class VideoActivity extends AppCompatActivity {
    VideoView videoPlayer;
    //AudioManager audioManager;
    String url = "http://video.ch9.ms/ch9/507d/71f4ef0f-3b81-4d2c-956f-c56c81f9507d/AndroidEmulatorWithMacEmulator.mp4";
    SurfaceView surfaceViewFrame;
    MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoPlayer =  (VideoView) findViewById(R.id.videoPlayer);
        if(url.matches(".+\\.mp4$")) videoPlayer.setVideoURI(Uri.parse(url));
        //Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.gu);
        //videoPlayer.setVideoURI(myVideoUri);
        //am = (AudioManager) getSystemService(AUDIO_SERVICE);
        //videoPlayer.setAudioAttributes();//setAudioStreamType(AudioManager.STREAM_MUSIC);
        //--MediaController mediaController = new MediaController(this);
        /*surfaceViewFrame = (SurfaceView) findViewById(R.id.surfaceViewFrame);
        player = new MediaPlayer();
        SurfaceHolder surfaceHolder = getHolder();
        player.setDisplay(holder);
        player.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared (MediaPlayer mp){
                int videoWidth = player.getVideoWidth();
                int videoHeight = player.getVideoHeight();
                float videoProportion = (float) videoWidth / (float) videoHeight;
                int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
                int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
                float screenProportion = (float) screenWidth / (float) screenHeight;
                android.view.ViewGroup.LayoutParams lp = surfaceViewFrame.getLayoutParams();

                if (videoProportion > screenProportion) {
                    lp.width = screenWidth;
                    lp.height = (int) ((float) screenWidth / videoProportion);
                } else {
                    lp.width = (int) (videoProportion * (float) screenHeight);
                    lp.height = screenHeight;
                }
                surfaceViewFrame.setLayoutParams(lp);

                if (!player.isPlaying()) {
                    player.start();
                }
            }
        });*/
        videoPlayer.setMediaController(new MediaController(this));
        //mediaController.setMediaPlayer(videoPlayer);
        //mediaController.setAnchorView(videoPlayer);
    }

    public void play(View v){
        videoPlayer.start();
    }
    public void pause(View v){
        videoPlayer.pause();
    }
    public void stop(View v){
        videoPlayer.stopPlayback();
        videoPlayer.resume();
    }
}
