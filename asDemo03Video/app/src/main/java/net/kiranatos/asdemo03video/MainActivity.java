package net.kiranatos.asdemo03video;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private static boolean isVideoFile = true;
    // https://www.sample-videos.com/
    // https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4
    // http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4
    private static String path="https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
    private static Uri uri= Uri.parse(path);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButtonSwitch(View view) {
        VideoView videoView = findViewById(R.id.videoView01);

        // android.resource:// - Простір імен (namespaces)
        if (isVideoFile) {
            videoView.setVideoPath("android.resource://" +
                    getPackageName() + "/" + R.raw.demo);
            isVideoFile = false;
        } else {
            videoView.setVideoURI(uri);
            isVideoFile = true;
        }

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}