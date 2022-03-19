package pl.edu.pwr.lab1.i242571;


import android.content.Context;

public class BMI {

    private boolean metric = true;
    private static final double metricToImperial = 0.0703;
    private static final double underWeight = 18.5;
    private static final double normalWeight = 24.9;
    private static final double overWeight = 29.9;

    void switchSystem(boolean to_metric){
        metric = to_metric;
    }

    boolean validateInput(String height, String mass){
        if (height == null || height.length() == 0 || mass == null || mass.length() == 0){
            return false;
        }
        try {
            Double.valueOf(height);
            Double.valueOf(mass);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    String calculateBMI(String height, String mass){
        double bmi = Double.parseDouble(mass) / Math.pow(Double.parseDouble(height) / 100, 2);
        if(!metric){
            bmi = bmi * metricToImperial;
        }
        return String.format("%.2f", bmi);
    }

    int getBMIColor(Context context, String bmi){
        double value = Double.parseDouble(bmi);
        if (value < underWeight){
            return context.getResources().getColor(R.color.blue);
        }else if (value < normalWeight){
            return context.getResources().getColor(R.color.green);
        }else if (value < overWeight){
            return context.getResources().getColor(R.color.orange);
        }else{
            return context.getResources().getColor(R.color.red);
        }
    }
}
