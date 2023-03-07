package humble.slave.retrofitgeneraltemplet.Network;

import humble.slave.retrofitgeneraltemplet.Model.Model;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
//    @GET("posts")
    @GET("weather")
    Call<Model> modelList(@Query("q") String cityName, @Query("appid") String APP_ID);

}
