package sideproject.jeanie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button buttonNewGame;
    Button buttonLeaderboard;
    Button buttonQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonNewGame = findViewById(R.id.buttonNewGame);
        buttonLeaderboard = findViewById(R.id.buttonLeaderboard);
        buttonQuit = findViewById(R.id.buttonQuit);
        final Intent intent = new Intent(Home.this, SongSelection.class);

        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
                finish();

            }
        });

    /*    buttonLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String message = tvmessage.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                // DatabaseReference testRef = myRef.child(main.strEmail);

                DatabaseReference pushedMyRef = myRef.push();
                // String pushedId = pushedMyRef.getKey();

                pushedMyRef.setValue(message);
            }

        }); */

        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}