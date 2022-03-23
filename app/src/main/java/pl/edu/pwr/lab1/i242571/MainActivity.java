package pl.edu.pwr.lab1.i242571;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static final String BMI_MESSAGE = "pl.edu.pwr.lab1.i242571.BMI_MESSAGE";
    public static final String MEASUREMENTS_MESSAGE = "pl.edu.pwr.lab1.i242571.MEASUREMENTS_MESSAGE";

    private static final String saved_measurements_key = "MEASUREMENTS_KEY";

    private static final String default_measurements = "";


    private MainActivityViewModel viewModel;
    private SharedPreferences sharedPref;

    private TextView bmi_tv;
    private EditText mass_et;
    private EditText height_et;
    private TextView mass_tv;
    private TextView height_tv;
    private Button count_button;

    class MainActivityModelFactory extends ViewModelProvider.NewInstanceFactory {

        private final SharedPreferences sharedPref;

        public MainActivityModelFactory(SharedPreferences sharedPref) {
            this.sharedPref = sharedPref;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass == MainActivityViewModel.class) {
                return (T) new MainActivityViewModel(sharedPref);
            }
            return null;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        viewModel = new MainActivityModelFactory(sharedPref).create(MainActivityViewModel.class);

        count_button = findViewById(R.id.button);
        bmi_tv = findViewById(R.id.bmi_tv);
        mass_et = findViewById(R.id.massInput);
        height_et = findViewById(R.id.heightInput);
        mass_tv = findViewById(R.id.mass_tv);
        height_tv = findViewById(R.id.height_tv);

        count_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.height = height_et.getText().toString();
                viewModel.mass = mass_et.getText().toString();
                if (BMI.validateInput(viewModel.height, viewModel.mass)){
                    viewModel.calculateBMI();
                    bmi_tv.setText(viewModel.bmi_value);
                    bmi_tv.setTextColor(BMI.getBMIColor(MainActivity.this, viewModel.bmi_value));
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
            }
        });
        bmi_tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!bmi_tv.getText().toString().equals(getResources().getString(R.string.middle_tv))){
                    Intent intent = new Intent(v.getContext(), BMIInfoActivity.class);
                    intent.putExtra(BMI_MESSAGE, bmi_tv.getText().toString());
                    startActivity(intent);
                }
                return;
            }

        });

        readLastMeasurements();

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
        viewModel.switchSystem(isMetric);
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
            case R.id.information_item:
            {
                Intent intent = new Intent(this, InformationActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.last_measurements_item:
                Intent intent = new Intent(this, LastMeasurementsActivity.class);
                intent.putExtra(MEASUREMENTS_MESSAGE, BMI.serializeBMIQueue(viewModel.validMeasurements));
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveCurrentMeasurements() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(saved_measurements_key, BMI.serializeBMIQueue(viewModel.validMeasurements));
        editor.apply();
    }

    private void readLastMeasurements() {
        String queueString = sharedPref.getString(saved_measurements_key, default_measurements);
        viewModel.loadQueue(queueString);
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
        saveCurrentMeasurements();
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