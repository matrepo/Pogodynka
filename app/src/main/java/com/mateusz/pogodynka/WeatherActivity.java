package com.mateusz.pogodynka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class WeatherActivity extends AppCompatActivity {

    TextView cityField, currentTemperatureField;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityField = (TextView)findViewById(R.id.cityField);
        currentTemperatureField = (TextView)findViewById(R.id.temperatureField);

        final String[] cities = getResources().getStringArray(R.array.sections);

        button = (Button) findViewById(R.id.button);

        Intent intent = getIntent();
        final int position = intent.getExtras().getInt("position");

        JsonWeather.placeIdTask asyncTask =new JsonWeather.placeIdTask(new JsonWeather.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn) {

                cityField.setText(weather_city);
                currentTemperatureField.setText(weather_temperature);
            }
        });
        asyncTask.execute(cities[position]); //  asyncTask.execute("Latitude", "Longitude")

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent start = new Intent(WeatherActivity.this, RetrofitWeatherActivity.class);
                start.putExtra("city", cities[position]);
                startActivity(start);
            }
        });

    }


}
