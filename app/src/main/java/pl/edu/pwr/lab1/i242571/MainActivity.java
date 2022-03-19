package pl.edu.pwr.lab1.i242571;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    private BMI bmi;
    private TextView bmi_tv;
    private EditText mass_et;
    private EditText height_et;
    private TextView mass_tv;
    private TextView height_tv;
    private Button count_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmi = new BMI();
        count_button = findViewById(R.id.button);
        bmi_tv = findViewById(R.id.bmi_tv);
        mass_et = findViewById(R.id.massInput);
        height_et = findViewById(R.id.heightInput);
        mass_tv = findViewById(R.id.mass_tv);
        height_tv = findViewById(R.id.height_tv);

        count_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String height = height_et.getText().toString();
                String mass = mass_et.getText().toString();
                if (bmi.validateInput(height, mass)){
                    String bmi_value = bmi.calculateBMI(height, mass);
                    bmi_tv.setText(bmi_value);
                    bmi_tv.setTextColor(bmi.getBMIColor(MainActivity.this, bmi_value));
                }else{
                    AlertDialog alert_dialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.invalid_input_title)
                            .setMessage(R.string.invalid_input_msg)
                            .setPositiveButton(R.string.invalid_input_btn, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .create();
                    alert_dialog.show();
                }
                return;
            }
        });
        Log.d(TAG, "The onCreate() event");
    }

    protected void switchMeasurement(boolean isMetric){
        if (isMetric){
            mass_tv.setText(R.string.mass_tv_m);
            height_tv.setText(R.string.height_tv_m);
        }else{
            mass_tv.setText(R.string.mass_tv_i);
            height_tv.setText(R.string.height_tv_i);
        }
        bmi.switchSystem(isMetric);
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.measurement_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.metric_item:
                switchMeasurement(true);
                return true;
            case R.id.imperial_item:
                switchMeasurement(false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "The onStart() event");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "The onResume() event");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "The onPause() event");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "The onStop() event");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "The onDestroy() event");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "The onRestart() event");
    }
}