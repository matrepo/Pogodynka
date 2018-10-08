package com.mateusz.pogodynka;

public class DataBody {

    public String name;
    public Main main;

    public String getName() {
        return name;
    }

    public class Main {
        public String temp;
        public String pressure;
        public String humidity;

        public String getTemp() {
            return temp;
        }

        public String getPressure() {
            return pressure;
        }

        public String getHumidity() {
            return humidity;
        }
    }

}
