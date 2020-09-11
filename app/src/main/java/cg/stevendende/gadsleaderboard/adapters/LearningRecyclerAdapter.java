package cg.stevendende.gadsleaderboard.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cg.stevendende.gadsleaderboard.models.Developer;

public class LearningRecyclerAdapter extends RecyclerView.Adapter<LearningRecyclerAdapter.LearningLeaderViewHolder> {

    ArrayList<Developer> list;

    @NonNull
    @Override
    public LearningLeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLeaderViewHolder holder, int position) {

    }

    class LearningLeaderViewHolder extends RecyclerView.ViewHolder{

        public LearningLeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
