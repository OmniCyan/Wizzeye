package app.wizzeye.app;

import app.wizzeye.app.headwork.Data;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeadworkService {
    @GET("crowd-glasses/tap.php")
    Call<Data> serviceResponse(@Query("data") String data);
}

