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
import cg.stevendende.gadsleaderboard.databinding.ItemTopLearnerBinding;
import cg.stevendende.gadsleaderboard.interfaces.GadsApiService;
import cg.stevendende.gadsleaderboard.models.HoursTopLearner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LearningLeaderFragment extends Fragment {

    FragmentLearningLeaderBinding binding;
    List<HoursTopLearner> developersList;
    LearningRecyclerAdapter mRecyclerAdapter;
    GadsApiService service;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentLearningLeaderBinding.inflate(getLayoutInflater());
        mRecyclerAdapter = new LearningRecyclerAdapter(getActivity());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(mRecyclerAdapter);
        binding.recyclerView.setHasFixedSize(true);

        loadTopLearningLeaders();
        return binding.getRoot();
    }


    private void loadTopLearningLeaders(){

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://gadsapi.herokuapp.com/")
                .build();

        service = retrofit.create(GadsApiService.class);
        service.getTopLearners().enqueue(new Callback<List<HoursTopLearner>>() {
            @Override
            public void onResponse(Call<List<HoursTopLearner>> call, retrofit2.Response<List<HoursTopLearner>> response) {
                Log.i("cases", response.body().get(0).getCountry());

                //bind data
                developersList = response.body();
                mRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HoursTopLearner>> call, Throwable t) {

            }
        });

    }



    public class LearningRecyclerAdapter extends RecyclerView.Adapter<LearningRecyclerAdapter.LearningLeaderViewHolder> {

        Context context;

        public LearningRecyclerAdapter(Context context ){
            this.context = context;
        }

        @NonNull
        @Override
        public  LearningLeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemTopLearnerBinding binding = ItemTopLearnerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new LearningLeaderViewHolder(binding);
        }

        @Override
        public int getItemCount() {
            return developersList != null ? developersList.size() : 0;
        }

        @Override
        public void onBindViewHolder(@NonNull  LearningLeaderViewHolder holder, int position) {

            holder.itemBinding.name.setText(developersList.get(position).getName());
            holder.itemBinding.country.setText(developersList.get(position).getCountry());
            holder.itemBinding.hours.setText(developersList.get(position).getHours()+"");

            Glide.with(getActivity())
                    .load(developersList.get(position).getBadgeUrl())
                    .apply(RequestOptions.centerInsideTransform().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.alt_image))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.itemBinding.photo);
        }

        class LearningLeaderViewHolder extends RecyclerView.ViewHolder{
            ItemTopLearnerBinding itemBinding;

            public LearningLeaderViewHolder(ItemTopLearnerBinding binding) {
                super(binding.getRoot());
                this.itemBinding = binding;
            }
        }
    }

}
