package cg.stevendende.gadsleaderboard.interfaces;

import java.util.List;

import cg.stevendende.gadsleaderboard.models.HoursTopLearner;
import cg.stevendende.gadsleaderboard.models.ScoreLeader;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GadsApiService {

    @GET("api/hours")
    Call<List<HoursTopLearner>> getTopLearners( );

    @GET("api/skilliq")
    Call<List<ScoreLeader>> getScoreLeaders( );
}
