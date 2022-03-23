package pl.edu.pwr.lab1.i242571;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Collections;

public class LastMeasurementsActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_measurements);
        listView = (ListView) findViewById(R.id.list_view);

        Intent intent = getIntent();
        String queueString = intent.getStringExtra(MainActivity.MEASUREMENTS_MESSAGE);

        circularFifoQueue<String> queue = BMI.deserializeBMIQueue(queueString);
        Collections.reverse(queue);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(LastMeasurementsActivity.this, android.R.layout.simple_list_item_1, queue);

        listView.setAdapter(adapter);
    }
}