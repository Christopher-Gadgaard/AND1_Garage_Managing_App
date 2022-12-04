package dk.via.and1.and1_garage_managing_app.data.nav;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NavApi {
    @GET("/maps/api/distancematrix/json?&units=metric&key=AIzaSyA2gE3G5otYa3toBuv5TTyLkpEcZ6cr9gQ")
    Call<NavResponse> getNav(@Query("origins")String origin, @Query("destinations") String destination);
}
