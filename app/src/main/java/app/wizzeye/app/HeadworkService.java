package app.wizzeye.app;

import app.wizzeye.app.headwork.Data;
import app.wizzeye.app.headwork.JSONResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeadworkService {
    @GET("GlassesController.php?action=GET")
    Call<JSONResponse> serviceResponse(@Query("data") String data);
}

