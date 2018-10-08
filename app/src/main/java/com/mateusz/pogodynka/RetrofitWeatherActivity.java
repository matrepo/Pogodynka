package com.mateusz.pogodynka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RetrofitWeatherActivity extends AppCompatActivity {

    // tag, który jest wykorzystany do logowania
    private static final String CLASS_TAG = "RetrofitWeatherActivity";
    private static final String APPIID = "626c42c79747ed88efafe65b9c83da88";

    // adapter REST z Retrofita
    RestAdapter retrofit;
    // nasz interfejs
    MyWebService myWebService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_weather);

        final TextView cityTextView = (TextView) findViewById(R.id.cityTextView);
        final TextView tempTextView = (TextView) findViewById(R.id.tempTextView);
        final TextView humidityTextView = (TextView) findViewById(R.id.humidityTextView);
        final TextView pressureTextView = (TextView) findViewById(R.id.pressureTextView);

        Intent intent = getIntent();
        String city = intent.getExtras().getString("city");


        // ustawiamy wybrane parametry adaptera
        retrofit = new RestAdapter.Builder()
                // adres API
                //.setEndpoint("https://damianchodorek.com/")
                .setEndpoint("http://api.openweathermap.org/data/2.5/")
                // niech Retrofit loguje wszystko co robi
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        // tworzymy klienta
        myWebService = retrofit.create(MyWebService.class);

        try {
            myWebService.getData(APPIID, city, new Callback<DataBody>() {
                @Override
                public void success(DataBody myWebServiceResponse, Response response) {
                    Log.d(CLASS_TAG, myWebServiceResponse.getName());
                    //DataBody.Main main ;
                    Log.d(CLASS_TAG, myWebServiceResponse.main.getTemp());

                    cityTextView.setText(myWebServiceResponse.getName());
                    tempTextView.setText(myWebServiceResponse.main.getTemp());
                    humidityTextView.setText(myWebServiceResponse.main.getHumidity());
                    pressureTextView.setText(myWebServiceResponse.main.getPressure());

                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d(CLASS_TAG, error.getLocalizedMessage());
                }
            });

        } catch (Exception e) {
            Log.d(CLASS_TAG, e.toString());
        }

    }
}
