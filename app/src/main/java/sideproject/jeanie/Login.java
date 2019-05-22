package sideproject.jeanie;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    String strEmail= "";
    String strPassword= "";

    Button buttonLogin, buttonSignUp;
    EditText editEmail, editPassword;
    TextView errorMessage;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        errorMessage = findViewById(R.id.txterrorMessage);

        mAuth = FirebaseAuth.getInstance();

        buttonSignUp.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Intent intent = new Intent(Login.this, Home.class);

        if (currentUser != null) {

            startActivity(intent);
            finish();

        }
    }

    public void onClick(View v) {
        strEmail = editEmail.getText().toString();
        strPassword = editPassword.getText().toString();

        if (v.getId() == R.id.buttonSignUp) {
            signUp(strEmail, strPassword);
        }

        if (v.getId() == R.id.buttonLogin) {
            loginUser(strEmail, strPassword);
        }
    }

    private void signUp (final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            errorMessage.setText("Registration was successful!");
                        } else {
                            errorMessage.setText("Registration failed. Please try again.");
                        }
                    }
                });
    }

    private void loginUser (String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                            errorMessage.setText("");
                            finish();

                        } else {

                            errorMessage.setText("The username or password does not match/User does not exist.");

                        }
                    }
                });
    }
}