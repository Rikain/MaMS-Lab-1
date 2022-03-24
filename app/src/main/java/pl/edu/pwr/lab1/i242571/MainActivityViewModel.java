package pl.edu.pwr.lab1.i242571;

import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;


public class MainActivityViewModel extends ViewModel {

    private static final String saved_bmi_key = "BMI_KEY";
    private static final String default_bmi_meas = "";
    private static final int savedMeasurements = 10;

    public String bmi_value;
    public String mass;
    public String height;

    public boolean metric = true;

    public CircularFifoQueue<String> validMeasurements;

    public MainActivityViewModel(SharedPreferences sharedPreferences){
        super();
        String bmi_values = sharedPreferences.getString(saved_bmi_key, default_bmi_meas);
        validMeasurements = BMI.deserializeBMIQueue(bmi_values, savedMeasurements);
    }

    public void loadQueue(String queueString){
        validMeasurements = BMI.deserializeBMIQueue(queueString, savedMeasurements);
    }

    public void switchSystem(boolean to_metric){
        metric = to_metric;
    }

    public void calculateBMI(){
        bmi_value = BMI.calculateBMI(height, mass, metric);
        validMeasurements.add(bmi_value);
    }

}
