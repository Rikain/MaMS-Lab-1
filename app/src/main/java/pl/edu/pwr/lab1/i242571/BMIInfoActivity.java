package pl.edu.pwr.lab1.i242571;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BMIInfoActivity extends AppCompatActivity {
    TextView bmi_value;
    TextView bmi_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiinfo);

        bmi_value = findViewById(R.id.bmi_tv);
        bmi_info = findViewById(R.id.bmi_info_tv);
    }

    protected void onResume(){
        super.onResume();

        Intent intent = getIntent();
        String bmi = intent.getStringExtra(MainActivity.BMI_MESSAGE);

        bmi_value.setText(bmi);
        bmi_value.setTextColor(BMI.getBMIColor(this, bmi));

        switch (BMI.getBMILevel(bmi)){
            case UNDERWEIGHT:
                bmi_info.setText(R.string.info_underweight);
                break;
            case NORMAL:
                bmi_info.setText(R.string.info_normal_weight);
                break;
            case OVERWEIGHT:
                bmi_info.setText(R.string.info_overweight);
                break;
            case OBESE:
                bmi_info.setText(R.string.info_obese);
                break;
        }
    }
}