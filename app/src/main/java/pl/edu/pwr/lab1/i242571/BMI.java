package pl.edu.pwr.lab1.i242571;


import android.content.Context;

import java.util.Collections;

public class BMI {

    public enum bmi_level{
        UNDERWEIGHT,
        NORMAL,
        OVERWEIGHT,
        OBESE
    }

    private static String serializeChar = ";";

    private static final int precision = 1;
    private static final double metricToImperial = 0.0703;
    private static final double underWeight = 18.5;
    private static final double normalWeight = 24.9;
    private static final double overWeight = 29.9;


    public static boolean validateInput(String height, String mass){
        if (height.isEmpty() || mass.isEmpty()){
            return false;
        }else{
            try {
                Double h = Double.valueOf(height);
                if (h == 0){
                    return false;
                }
                Double.valueOf(mass);
            } catch(Exception e){
                return false;
            }
            return true;
        }
    }

    public static String calculateBMI(String height, String mass, boolean metric){
        double bmi = Double.parseDouble(mass) / Math.pow(Double.parseDouble(height) / 100, 2);
        if(!metric){
            bmi = bmi * metricToImperial;
        }
        return String.format("%."+Integer.toString(precision)+"f", bmi);
    }

    public static bmi_level getBMILevel(String bmi){
        double value = Double.parseDouble(bmi);
        if (value < underWeight){
            return bmi_level.UNDERWEIGHT;
        }else if (value < normalWeight){
            return bmi_level.NORMAL;
        }else if (value < overWeight){
            return bmi_level.OVERWEIGHT;
        }else{
            return bmi_level.OBESE;
        }
    }

    public static int getBMIColor(Context context, String bmi){
        bmi_level level = getBMILevel(bmi);
        switch (level){
            case UNDERWEIGHT:
                return context.getResources().getColor(R.color.blue);
            case NORMAL:
                return context.getResources().getColor(R.color.green);
            case OVERWEIGHT:
                return context.getResources().getColor(R.color.orange);
            case OBESE:
                return context.getResources().getColor(R.color.red);
        }
        return context.getResources().getColor(R.color.white);
    }

    public static String serializeBMIQueue(circularFifoQueue<String> queue){
        StringBuilder queueString = new StringBuilder();
        if (queue.isEmpty()){
            return queueString.toString();
        }
        for (String o : queue){
            queueString.append(o).append(serializeChar);
        }
        queueString.deleteCharAt(queueString.length()-1);
        return queueString.toString();
    }

    private static String[] stringToArray(String queueString){
        return queueString.split(serializeChar);
    }

    public static circularFifoQueue<String> deserializeBMIQueue(String queueString){
        String[] arrOfStr = stringToArray(queueString);
        int limit = arrOfStr.length;
        return arrayToQueue(arrOfStr, limit);
    }

    private static circularFifoQueue<String> arrayToQueue(String[] arrOfStr, int limit){
        circularFifoQueue<String> queue =  new circularFifoQueue<>(limit);
        Collections.addAll(queue, arrOfStr);
        return queue;
    }

    public static circularFifoQueue<String> deserializeBMIQueue(String queueString, int limit){
        String[] arrOfStr = stringToArray(queueString);
        return arrayToQueue(arrOfStr, limit);
    }
}
