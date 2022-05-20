package app.wizzeye.app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("crowd-glasses/tap.php")
    Call<Data> serviceResponse(@Query("data") String data);
}

