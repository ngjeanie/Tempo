package sideproject.jeanie;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SongSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songselect);

        Button buttonMapOne = findViewById(R.id.buttonMapOne);
        ImageButton buttonBack = findViewById(R.id.imageButtonBack);

        final MediaPlayer mediaPlayer = MediaPlayer.create(SongSelection.this, R.raw.mapone);
        mediaPlayer.start();

        final Intent intentSelect = new Intent(SongSelection.this, MapOne.class);
        buttonMapOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intentSelect);
                mediaPlayer.stop();
                finish();

            }
        });

        final Intent intentBack = new Intent(SongSelection.this, Home.class);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intentBack);
                mediaPlayer.stop();
                finish();

            }
        });
    }
}
