package mobile.handygestures.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import mobile.handygestures.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    ActivitySignUpBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = binding.signUpEmail.getText().toString().trim();
                String pass = binding.signUpPassword.getText().toString().trim();

                if (TextUtils.isEmpty(binding.signUpEmail.getText().toString())) {
                    binding.signUpEmail.setError("Field can't be empty!");
                    return;
                } else if (TextUtils.isEmpty(binding.signUpPassword.getText().toString())) {
                    binding.signUpPassword.setError("Field can't be empty!");
                    return;
                } else if (binding.signUpPassword.length() < 6) {
                    binding.signUpPassword.setError("Password should be 6 characters or more!");
                } else if (!isvalidemail(binding.signUpEmail.getText().toString())) {
                    binding.signUpEmail.setError("Invalid Email!");
                } else {
                    binding.signUpProgressBar.setVisibility(View.VISIBLE);
                }

                firebaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseUser = firebaseAuth.getCurrentUser();
                            assert firebaseUser != null;
                            firebaseUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, "Verification Email Sent To Your Email.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(SignUpActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                            binding.signUpProgressBar.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(SignUpActivity.this, "" + task.getException(), Toast.LENGTH_LONG).show();
                            binding.signUpProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

        binding.haveAccTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean isvalidemail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}