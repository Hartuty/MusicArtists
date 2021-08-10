package com.abachapp.musicartists.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abachapp.musicartists.R;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

public class PlaySample extends AppCompatActivity {

    TextView textView,textView1;
    ImageView imageView,imageView1;
    String url,url1;
    SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_sample);
        textView=findViewById(R.id.playing_sample);
        textView1=findViewById(R.id.artistname_sample);
        imageView=findViewById(R.id.image_sample);
        imageView1=findViewById(R.id.image_artist);
        player = new SimpleExoPlayer.Builder(PlaySample.this).build();
        Intent intent=getIntent();
        String Album=intent.getStringExtra("Album");
        String Artist=intent.getStringExtra("Artist");
        String Image=intent.getStringExtra("Image");
        url = intent.getStringExtra("Sample");
        url1 = intent.getStringExtra("Sample1");
        startbasics(Album,Image,url1,Artist);
    }

    public void startbasics(String Album,String image,String image2,String Artist) {
        textView.setText("Playing "+Album);
        textView1.setText(Artist);
        Picasso.get().load(image).into(imageView);
        Picasso.get().load(image2).into(imageView1);

    }

    public void playsample(View view) {
        if(player.isPlaying())
        {
         player.release();
         player = new SimpleExoPlayer.Builder(PlaySample.this).build();
        }
        Uri myUri = Uri.parse(url);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(PlaySample.this,
                Util.getUserAgent(PlaySample.this, "yourApplicationName"));
        MediaSource videoSource =
                new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(myUri);
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
    }

    public void Return_Home(View view) {
        finish();
        player.release();

    }

    @Override
    protected void onStop() {
        super.onStop();
        player.release();
    }
}
