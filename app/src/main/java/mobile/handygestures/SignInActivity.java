package mobile.handygestures;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import mobile.handygestures.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        //getSupportActionBar().hide();

        //signUp.setOnClickListener(v ->
        //Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment));

        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = binding.signInEmail.getText().toString().trim();
                String passwd = binding.signInPassword.getText().toString().trim();

                if (TextUtils.isEmpty(binding.signInEmail.getText().toString())) {
                    binding.signInEmail.setError("Field can't be empty!!");
                    return;
                } else if (TextUtils.isEmpty(binding.signInPassword.getText().toString())) {
                    binding.signInPassword.setError("Field can't be empty!!");
                    return;
                } else {
                    binding.signInProgressBar.setVisibility(View.VISIBLE);
                }

                firebaseAuth.signInWithEmailAndPassword(mail, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                Intent intent = new Intent(SignInActivity.this, Menu.class);
                                startActivity(intent);
                                Toast.makeText(SignInActivity.this, "welcome", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SignInActivity.this, "Verify your email first", Toast.LENGTH_LONG).show();
                                binding.signInEmail.setText("");
                                binding.signInPassword.setText("");
                                binding.signInProgressBar.setVisibility(View.INVISIBLE);
                            }
                        } else {
                            binding.signInProgressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(SignInActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        binding.dontHaveAccTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}