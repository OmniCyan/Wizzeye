package app.wizzeye.app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeadworkService {
    @GET("GlassesController.php?action=GET")
    Call<JSONResponse> serviceResponse(@Query("latitude") String latitude, @Query("longitude") String longitude);
}

