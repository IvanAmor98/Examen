package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {
    String[] options = {"Apocalyptic", "Double"};
    int[] images = {R.drawable.apocaliptico, R.drawable.x2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.selector);

        CustomAdapter adapter = new CustomAdapter(this, R.layout.spinner_line, options);
        spinner.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.date:
                Properties prop = System.getProperties();
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                return true;
        }
        return true;
    }

    public void calculateClick(View v) {
        String output = "";

        int selectedNumber = Integer.parseInt(((Spinner) findViewById(R.id.num1)).getSelectedItem().toString()
            + ((Spinner) findViewById(R.id.num2)).getSelectedItem().toString()
            + ((Spinner) findViewById(R.id.num3)).getSelectedItem().toString()
            + ((Spinner) findViewById(R.id.num4)).getSelectedItem().toString());

        ImageView outputImage = findViewById(R.id.outputImage);

        if (((TextView) findViewById(R.id.selector).findViewById(R.id.lineText)).getText().equals("Double")) {
            outputImage.setBackgroundResource(R.drawable.x2);
            output += calculateDouble(selectedNumber);
        } else {
            outputImage.setBackgroundResource(R.drawable.apocaliptico);
            output += calculateApocalyptic(selectedNumber);
        }

        ((TextView) findViewById(R.id.output)).setText(output);
    }

    private String calculateDouble(int selectedNumber) {
        return "El doble de " + selectedNumber + " es igual a " + (int) selectedNumber * 2;
    }

    private String calculateApocalyptic(int selectedNumber) {
        String response = "";

        // Forma facil
        //double result = Math.pow(2, selectedNumber);
        double result = 2;
        if (selectedNumber == 0) {
            result = 1;
        } else {
            for (int i = 2; i <= selectedNumber; i++) {
                result *= 2;
            }
        }

        if (parseNumber(result)) {
            response += "El numero " + selectedNumber + " es apocaliptico";
        } else {
            response += "El numero " + selectedNumber + " NO es apocaliptico";
        }

        return String.valueOf(response);
    }

    private boolean parseNumber(double number) {
        String num = String.valueOf(number);
        if (num.contains("666")) {
            return true;
        } else {
            return false;
        }
    }

    public class CustomAdapter extends ArrayAdapter<String> {

        public CustomAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertedView, ViewGroup parent) {
            return createCustomLine(position, convertedView, parent);
        }

        @Override
        public View getView(int position, View convertedView, ViewGroup parent) {
            return createCustomLine(position, convertedView, parent);
        }

        public View createCustomLine(int position, View convertedView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();

            View line = inflater.inflate(R.layout.spinner_line, parent, false);

            ((TextView) line.findViewById(R.id.lineText)).setText(options[position]);
            ((ImageView) line.findViewById(R.id.lineImage)).setImageResource(images[position]);

            return line;
        }

    }
}