package humble.slave.retrofitgeneraltemplet.Network;

import humble.slave.retrofitgeneraltemplet.Model.Model;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
//    example url -> https://samples.openweathermap.org/data/2.5/weather?q=London&appid=b1b15e88fa797225412429c1c50c122a1
    @GET("weather")
    Call<Model> modelList(@Query("q") String cityName, @Query("appid") String APP_ID);

}
