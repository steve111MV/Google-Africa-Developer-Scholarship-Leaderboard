package cg.stevendende.gadsleaderboard.interfaces;

import java.util.List;

import cg.stevendende.gadsleaderboard.models.HoursTopLearner;
import cg.stevendende.gadsleaderboard.models.ScoreLeader;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GadsApiService {

    @GET("api/hours")
    Call<List<HoursTopLearner>> getTopLearners( );

    @GET("api/skilliq")
    Call<List<ScoreLeader>> getScoreLeaders( );

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Response> postProject(
            @Field("entry.1824927963") String firstName,
            @Field("entry.1877115667") String lastName,
            @Field("entry.2006916086") String emailName,
            @Field("entry.284483984") String projectRepository
    );
}
