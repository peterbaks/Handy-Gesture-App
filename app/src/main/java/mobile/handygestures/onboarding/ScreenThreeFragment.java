package mobile.handygestures.onboarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import mobile.handygestures.main.Menu;
import mobile.handygestures.R;
import mobile.handygestures.auth.SignInActivity;

public class ScreenThreeFragment extends Fragment {

    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_screen_three, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        Button start6 = view.findViewById(R.id.start3);
        start6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                if (mFirebaseUser != null) {
                    Intent intent = new Intent(getActivity(), Menu.class);
                    startActivity(intent);
                    isOnboardingFinished(requireContext());
                    getActivity().finish();
                } else {
                    Intent intent = new Intent(getActivity(), SignInActivity.class);
                    startActivity(intent);
                    isOnboardingFinished(requireContext());
                    getActivity().finish();
                }
            }
        });

        return view;
    }

    private void isOnboardingFinished(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("finished", true);
        editor.apply();
    }
}