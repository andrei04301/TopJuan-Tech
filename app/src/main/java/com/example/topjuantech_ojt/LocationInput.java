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
    private TextView tvStateSpinner, tvDistrictSpinner;
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
                    adapterRegion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     // Specify the layout to use when the list of choices appears
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
        tvStateSpinner = findViewById(R.id.txt_highlands);
        tvDistrictSpinner = findViewById(R.id.txt_region);

        submitButton.setOnClickListener(v -> {
            if (chosenHighlands.equals("Select Your State")) {
                Toast.makeText(LocationInput.this, "Please select your Highlands from the list", Toast.LENGTH_LONG).show();
                tvStateSpinner.setError("Highlands is required!");      //To set error on TextView
                tvStateSpinner.requestFocus();
            } else if (chosenRegion.equals("Select Your District")) {
                Toast.makeText(LocationInput.this, "Please select your Region from the list", Toast.LENGTH_LONG).show();
                tvDistrictSpinner.setError("Region is required!");
                tvDistrictSpinner.requestFocus();
                tvStateSpinner.setError(null);                      //To remove error from stateSpinner
            }
            else if (chosenCity.equals("Select Your District")) {
                Toast.makeText(LocationInput.this, "Please select your City from the list", Toast.LENGTH_LONG).show();
                tvDistrictSpinner.setError("City/District is required!");
                tvDistrictSpinner.requestFocus();
                tvStateSpinner.setError(null);
            }else {
                tvStateSpinner.setError(null);
                tvDistrictSpinner.setError(null);
                Toast.makeText(LocationInput.this, "Selected State: "+chosenHighlands+"\nSelected District: "+chosenRegion, Toast.LENGTH_LONG).show();
            }
        });
    }
}