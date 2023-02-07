package com.example.topjuantech_ojt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LocationInput extends AppCompatActivity {

    private String chosenHighlands, chosenCity, chosenRegion;
    private TextView txtHighlands, txtRegion, txtCity;
    private Spinner spinHighlands, spinCity, spinRegion;
    private ArrayAdapter<CharSequence> adapterHighlands, adapterCity, adapterRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_input);

        spinHighlands = findViewById(R.id.highlands);
        spinRegion = findViewById(R.id.region);
        adapterHighlands = ArrayAdapter.createFromResource(this,
                R.array.arr_highlands, R.layout.spin);
        adapterHighlands.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinHighlands.setAdapter(adapterHighlands);
        spinHighlands.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Define City Spinner but we will populate the options through the selected state
                spinCity = findViewById(R.id.region);

                chosenHighlands = spinHighlands.getSelectedItem().toString();

                int parentID = parent.getId();
                if (parentID == R.id.highlands) {
                    switch (chosenHighlands) {
                        case "Select Your Highlands":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_def_region, R.layout.spin);
                            switch (chosenRegion){
                                case "Select Your Region":
                                    adapterCity=ArrayAdapter.createFromResource(parent.getContext(),
                                            R.array.arr_def_city, R.layout.spin);
                            }
                            break;
                        case "Luzon":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_luzon_region, R.layout.spin);
                            switch (chosenRegion){
                                case "Select Your Region":
                                    adapterCity=ArrayAdapter.createFromResource(parent.getContext(),
                                            R.array.arr_def_city, R.layout.spin);
                            }
                            break;
                        default:  break;
                    }
                    adapterRegion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinRegion.setAdapter(adapterRegion);
                    spinRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            chosenRegion = spinRegion.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button submitButton;                                //To display the selected State and District
        submitButton = findViewById(R.id.button_submit);
        txtHighlands = findViewById(R.id.txt_highlands);
        txtRegion = findViewById(R.id.txt_region);
        txtCity = findViewById(R.id.txt_region);
        submitButton.setOnClickListener(v -> {
            if (chosenHighlands.equals("Select Your Highlands")) {
                Toast.makeText(LocationInput.this, "Please select your Highlands from the list", Toast.LENGTH_LONG).show();
                txtHighlands.setError("Highlands is required!");      //To set error on TextView
                txtHighlands.requestFocus();
            } else if (chosenRegion.equals("Select Your Region")) {
                Toast.makeText(LocationInput.this, "Please select your Region from the list", Toast.LENGTH_LONG).show();
                txtRegion.setError("Region is required!");
                txtRegion.requestFocus();
                txtRegion.setError(null);                      //To remove error from stateSpinner
            }
            else if (chosenCity.equals("Select Your City/District")) {
                Toast.makeText(LocationInput.this, "Please select your City from the list", Toast.LENGTH_LONG).show();
                txtCity.setError("City/District is required!");
                txtCity.requestFocus();
                txtCity.setError(null);
            }else {
                txtHighlands.setError(null);
                txtRegion.setError(null);
                txtCity.setError(null);
                Toast.makeText(LocationInput.this, "Selected State: "+chosenHighlands+"\nSelected District: "+chosenRegion, Toast.LENGTH_LONG).show();
            }
        });
    }
}