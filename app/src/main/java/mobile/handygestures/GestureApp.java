package mobile.handygestures;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GestureApp extends Application {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public void onCreate() {
        super.onCreate();

        /*firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            if (firebaseUser.isEmailVerified()) {
                startActivity(new Intent(this, Menu.class));
            } else {
                Toast.makeText(this, "Email is not verified, open your mails and verify", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SignInActivity.class));
            }

        } else {
            startActivity(new Intent(this, SignInActivity.class));
        }*/
    }
}
