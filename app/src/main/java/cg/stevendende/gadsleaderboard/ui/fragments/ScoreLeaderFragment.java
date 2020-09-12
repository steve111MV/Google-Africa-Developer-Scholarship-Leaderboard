package cg.stevendende.gadsleaderboard.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import cg.stevendende.gadsleaderboard.R;
import cg.stevendende.gadsleaderboard.databinding.FragmentLearningLeaderBinding;
import cg.stevendende.gadsleaderboard.databinding.FragmentScoreLeaderBinding;
import cg.stevendende.gadsleaderboard.databinding.ItemScoreLeaderBinding;
import cg.stevendende.gadsleaderboard.databinding.ItemTopLearnerBinding;
import cg.stevendende.gadsleaderboard.interfaces.GadsApiService;
import cg.stevendende.gadsleaderboard.models.HoursTopLearner;
import cg.stevendende.gadsleaderboard.models.ScoreLeader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScoreLeaderFragment extends Fragment {

    FragmentScoreLeaderBinding binding;

    List<ScoreLeader> developersList;
    ScoreRecyclerAdapter mRecyclerAdapter;
    GadsApiService service;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentScoreLeaderBinding.inflate(getLayoutInflater());

        mRecyclerAdapter = new ScoreRecyclerAdapter(getActivity());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mRecyclerAdapter);
        binding.recyclerView.setHasFixedSize(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadScoreLeaders();
    }

    private void loadScoreLeaders(){

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://gadsapi.herokuapp.com/")
                .build();

        service = retrofit.create(GadsApiService.class);
        service.getScoreLeaders().enqueue(new Callback<List<ScoreLeader>>() {
            @Override
            public void onResponse(Call<List<ScoreLeader>> call, retrofit2.Response<List<ScoreLeader>> response) {
                Log.i("scores", response.body().get(0).getCountry());

                //bind data
                developersList = response.body();
                mRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ScoreLeader>> call, Throwable t) {

            }
        });

    }



    public class ScoreRecyclerAdapter extends RecyclerView.Adapter<ScoreRecyclerAdapter.ScoreLeaderViewHolder> {

        Context context;

        public ScoreRecyclerAdapter(Context context ){
            this.context = context;
        }

        @NonNull
        @Override
        public  ScoreLeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemScoreLeaderBinding binding = ItemScoreLeaderBinding.inflate(LayoutInflater.from(context), parent, false);
            return new ScoreLeaderViewHolder(binding);
        }

        @Override
        public int getItemCount() {
            return developersList != null ? developersList.size() : 0;
        }

        @Override
        public void onBindViewHolder(@NonNull  ScoreLeaderViewHolder holder, int position) {
            holder.itemBinding.name.setText(developersList.get(position).getName());
            holder.itemBinding.country.setText(developersList.get(position).getCountry());
            holder.itemBinding.skilliq.setText(developersList.get(position).getScore()+"");

            Glide.with(getActivity())
                    .load(developersList.get(position).getBadgeUrl())
                    .apply(RequestOptions.centerInsideTransform().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.alt_image))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.itemBinding.photo);
        }

        class ScoreLeaderViewHolder extends RecyclerView.ViewHolder{
            ItemScoreLeaderBinding itemBinding;

            public ScoreLeaderViewHolder(ItemScoreLeaderBinding binding) {
                super(binding.getRoot());
                this.itemBinding = binding;
            }
        }
    }

}
