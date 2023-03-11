package humble.slave.retrofitgeneraltemplet.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import humble.slave.retrofitgeneraltemplet.Network.API;
import humble.slave.retrofitgeneraltemplet.Model.Model;
import humble.slave.retrofitgeneraltemplet.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

//    Declaring the viewBind :
    private ActivityMainBinding binding;



//    setting up the baseUrl and appId :
    String BASE_URL = "https://samples.openweathermap.org/data/2.5/";
    String APP_ID = "b6907d289e10d714a6e88b30761fae22";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009933")));

//        Instantiating the viewBind :
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.output.setText("");


//        Building the url using retrofit with the additional converter from json data type :
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        Creating an instance of the API type interface :
        API api = retrofit.create(API.class);

//        for callback : with the query parameters' value
        Call <Model> call = api.modelList("London", APP_ID);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {

                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }

//                Instantiating a predefined model type data
                Model data = response.body();
                assert data != null;
                binding.output.append("Humidity : "+ data.getMain().getHumidity() +"\n");
                binding.output.append("Pressure : "+ data.getMain().getPressure() +"\n");
                binding.output.append("Temperature : "+ data.getMain().getTemp() +"\n");
                binding.output.append("Max Temperature : "+ data.getMain().getTempMax() +"\n");
                binding.output.append("Min Temperature : "+ data.getMain().getTempMin() +"\n");
                binding.output.append("Latitude : "+ data.getCoord().getLat() +"\n");
                binding.output.append("Longitude : "+ data.getCoord().getLon() +"\n");
                binding.output.append("Weather : "+ data.getWeather().get(0).getId());
//                for (int i = 0; i < Objects.requireNonNull(data).size(); i++){
////                    binding.output.append("SL No: "+data.get(i).getId()+"\nTitle: "+data.get(i).getTitle()+"\n\n\n");
//                    binding.output.append("COORDINATES: "+ data.get(i).getCoord().getLat()+"\n\n");
//                }
            }

            @Override
            public void onFailure(@NonNull Call<Model> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }


        });
    }
}