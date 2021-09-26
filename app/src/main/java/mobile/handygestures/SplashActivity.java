package mobile.handygestures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import mobile.handygestures.auth.SignInActivity;
import mobile.handygestures.main.Menu;
import mobile.handygestures.onboarding.ViewPagerActivity;

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

                /*if (firebaseUser != null) {
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
                }*/

                FirebaseUser mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                if (mFirebaseUser != null && onboardingDone()){
                    Intent intent = new Intent(SplashActivity.this, Menu.class);
                    startActivity(intent);
                    finish();
                }
                else if (onboardingDone()){
                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(SplashActivity.this, ViewPagerActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }


    private boolean onboardingDone(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("onBoarding", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("finished",false);
    }
}