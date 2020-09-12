package cg.stevendende.gadsleaderboard.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import cg.stevendende.gadsleaderboard.LearningLeaderFragment;
import cg.stevendende.gadsleaderboard.ScoreLeaderFragment;

public class MainAdapter extends FragmentPagerAdapter {

    public String[] TITLES;

    public MainAdapter(@NonNull FragmentManager fm, int behavior, String[] titles) {
        super(fm, behavior);
        TITLES = titles;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LearningLeaderFragment();

            default:
                return new ScoreLeaderFragment();

        }
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
