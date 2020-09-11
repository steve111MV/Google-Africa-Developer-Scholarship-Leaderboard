package cg.stevendende.gadsleaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cg.stevendende.gadsleaderboard.databinding.FragmentLearningLeaderBinding;

public class LearningLeaderFragment extends Fragment {

    FragmentLearningLeaderBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentLearningLeaderBinding.inflate(getLayoutInflater());



        return binding.getRoot();
    }
}
