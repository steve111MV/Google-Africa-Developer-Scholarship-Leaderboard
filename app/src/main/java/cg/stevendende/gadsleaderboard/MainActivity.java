package cg.stevendende.gadsleaderboard;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import cg.stevendende.gadsleaderboard.adapters.MainAdapter;
import cg.stevendende.gadsleaderboard.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;

    private ActivityMainBinding binding;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        mAdapter = new MainAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mAdapter.TITLES = getResources().getStringArray(R.array.main_tabs_titles);
        binding.contentMain.viewPager.setAdapter(mAdapter);

        binding.tabLayout.setupWithViewPager(binding.contentMain.viewPager);

        binding.submitProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}