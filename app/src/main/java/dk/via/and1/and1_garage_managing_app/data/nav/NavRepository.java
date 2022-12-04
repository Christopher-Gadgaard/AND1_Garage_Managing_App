package dk.via.and1.and1_garage_managing_app.data.nav;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NavRepository {

    private static NavRepository instance;
    private MutableLiveData<NavResponse> navResponse;

    public static NavRepository getInstance()
    {
        if (instance == null) {
            instance = new NavRepository();

        }
        return instance;
    }

    public NavRepository()
    {
        navResponse = new MutableLiveData<>();
    }

    public void getNav(String origin, String destination)
    {
        NavApi navApi = ServiceGenerator.getNavApi();
        Call<NavResponse> call = navApi.getNav(origin, destination);
        call.enqueue(new Callback<NavResponse>() {
            @Override
            public void onResponse(Call<NavResponse> call, Response<NavResponse> response)
            {
                if (response.isSuccessful())
                {
                    navResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NavResponse> call, Throwable t)
            {
                System.out.println("Something went wrong");
            }
        });
    }

    public LiveData<NavResponse> getNavResponse()
    {
        return navResponse;
    }

    public static class ServiceGenerator {
        private static NavApi navApi;

        public static NavApi getNavApi()
        {
            if (navApi == null) {
                navApi = new Retrofit.Builder()
                        .baseUrl("https://maps.googleapis.com").addConverterFactory(GsonConverterFactory.create()).build().create(NavApi.class);
            }
            return navApi;
        }
    }
}

