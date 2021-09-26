package mobile.handygestures.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;


import java.util.ArrayList;

import mobile.handygestures.R;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        getSupportActionBar().hide();

        ViewPager2 viewPager2 = findViewById(R.id.myViewPager);
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new ScreenOneFragment());
        arrayList.add(new ScreenTwoFragment());
        arrayList.add(new ScreenThreeFragment());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), arrayList);
        viewPager2.setAdapter(adapter);
    }
}