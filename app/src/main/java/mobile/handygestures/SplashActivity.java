package mobile.handygestures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (firebaseUser != null) {
                    if (firebaseUser.isEmailVerified()) {
                        Intent mainIntent = new Intent(SplashActivity.this, Menu.class);
                        startActivity(mainIntent);
                        finish();
                    } else {
                        Toast.makeText(SplashActivity.this, "Please Verify your account", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent mainIntent = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        }, 3000);
    }
}