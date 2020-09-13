package cg.stevendende.gadsleaderboard.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import cg.stevendende.gadsleaderboard.databinding.ActivitySplashBinding;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int UI_DELAY = 3000;

    Handler mHandler;
    Runnable mRunnable;

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Enable full screen
        binding.getRoot().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //annimate logo size
        ViewCompat.animate(binding.logo).scaleX(1f).setDuration(1000).start();
        ViewCompat.animate(binding.logo).scaleY(1f).setDuration(1000).start();

        mRunnable = new Runnable() {
                @Override
                public void run() {

                    mHandler.removeCallbacks(mRunnable);

                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);

                    SplashScreenActivity.this.finish();
                }
            };

            mHandler = new Handler();
            mHandler.postDelayed(mRunnable, UI_DELAY);
    }

}