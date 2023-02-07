package com.example.topjuantech_ojt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LocationInput extends AppCompatActivity {

    private String  chosenCity, chosenRegion;
    private TextView txtRegion, txtCity;
    private Spinner  spinCity, spinRegion;
    private ArrayAdapter<CharSequence>  adapterCity, adapterRegion;
    private EditText etBarangay, etLong, etLat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_input);

        spinRegion = findViewById(R.id.region);
        adapterRegion = ArrayAdapter.createFromResource(this,
                R.array.arr_region, R.layout.spin);
        adapterRegion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRegion.setAdapter(adapterRegion);
        spinRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Define City Spinner but we will populate the options through the selected state
                spinCity = findViewById(R.id.region);

                chosenRegion = spinRegion.getSelectedItem().toString();

                int parentID = parent.getId();
                if (parentID == R.id.region) {
                    switch (chosenRegion) {
                        case "Select Your Region":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_def_region, R.layout.spin);
                            break;
                        case "Region I - Ilocos Region":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region1_city, R.layout.spin);
                            break;
                        case "Region II - Cagayan Valley":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region2_city, R.layout.spin);
                            break;
                        case "Region III - Central Luzon":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region3_city, R.layout.spin);
                            break;
                        case "Region IVA - CALABARZON":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region4A_city, R.layout.spin);
                            break;
                        case "NCR - National Capital Region":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_NCR_city, R.layout.spin);
                            break;
                        case "CAR - Cordillera Administrative Region":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_CAR_city, R.layout.spin);
                            break;
                        case "MIMAROPA Region":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_MIMAROPA_city, R.layout.spin);
                            break;
                        case "Region V - Bicol Region":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region5_city, R.layout.spin);
                            break;
                        case "Region VI - Western Visayas":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region6_city, R.layout.spin);
                            break;
                        case "Region VII - Central Visayas":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region7_city, R.layout.spin);
                            break;
                        case "Region VIII - Eastern Visayas":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region8_city, R.layout.spin);
                            break;
                        case "Region IX - Zamboanga Peninsula":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region9_city, R.layout.spin);
                            break;
                        case "Region X - Northern Mindanao":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region10_city, R.layout.spin);
                            break;
                        case "Region XI - Davao Region":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region11_city, R.layout.spin);
                            break;
                        case "Region XII - SOCCKSARGEN":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region12_city, R.layout.spin);
                            break;
                        case "Region XIII - Caraga":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region13_city, R.layout.spin);
                            break;
                        case "BARMM - Bangsamoro Autonomous Region in Muslim Mindanao":
                            adapterRegion = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_BARRM_city, R.layout.spin);
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
        txtRegion = findViewById(R.id.txt_region);
        txtCity = findViewById(R.id.txt_district);

        submitButton.setOnClickListener(v -> {
            if (chosenRegion.equals("Select Your Region")) {
                Toast.makeText(LocationInput.this, "Please select your Region from the list", Toast.LENGTH_LONG).show();
                txtRegion.setError("Region is required");      //To set error on TextView
                txtRegion.requestFocus();
            } else if (chosenCity.equals("Select Your Province/City")) {
                Toast.makeText(LocationInput.this, "Please select your Province/City from the list", Toast.LENGTH_LONG).show();
                txtCity.setError("Province/City is required!");
                txtCity.requestFocus();
                txtRegion.setError(null);                      //To remove error from stateSpinner
            }else {
                txtRegion.setError(null);
                txtCity.setError(null);
                Toast.makeText(LocationInput.this, "Selected Region: "+chosenRegion+"\nSelected Province/City: "+chosenCity, Toast.LENGTH_LONG).show();
            }
        });
    }
}