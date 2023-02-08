package com.example.topjuantech_ojt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationEstablishment extends AppCompatActivity {
    private EditText estName,estType,estContact;
    private Button btnRegisterEst;
    private String chosenCity, chosenRegion, barangay, longi, lat;
    private TextView txtRegion, txtCity, txtBarangay, txtLong, txtLat;
    private Spinner spinCity, spinRegion;
    private ArrayAdapter<CharSequence> adapterCity, adapterRegion;
    private String establishment, type, contact;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;
    FirebaseFirestore fStore;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_establishment);

        Button btnRegisterEst = (Button) findViewById(R.id.btnRegisterEst);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        fStore= FirebaseFirestore.getInstance();

        spinRegion = findViewById(R.id.region);
        adapterRegion = ArrayAdapter.createFromResource(this, R.array.arr_region, R.layout.spin);
        adapterRegion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRegion.setAdapter(adapterRegion);
        spinRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinCity = findViewById(R.id.city_district);
                chosenRegion = spinRegion.getSelectedItem().toString();
                int parentID = parent.getId();
                if (parentID == R.id.region) {
                    switch (chosenRegion) {
                        case "Select Your Region":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_def_region, R.layout.spin);
                            break;
                        case "Region I - Ilocos Region":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region1_city, R.layout.spin);
                            break;
                        case "Region II - Cagayan Valley":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region2_city, R.layout.spin);
                            break;
                        case "Region III - Central Luzon":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region3_city, R.layout.spin);
                            break;
                        case "Region IVA - CALABARZON":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region4A_city, R.layout.spin);
                            break;
                        case "NCR - National Capital Region":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_NCR_city, R.layout.spin);
                            break;
                        case "CAR - Cordillera Administrative Region":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_CAR_city, R.layout.spin);
                            break;
                        case "MIMAROPA Region":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_MIMAROPA_city, R.layout.spin);
                            break;
                        case "Region V - Bicol Region":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region5_city, R.layout.spin);
                            break;
                        case "Region VI - Western Visayas":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region6_city, R.layout.spin);
                            break;
                        case "Region VII - Central Visayas":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region7_city, R.layout.spin);
                            break;
                        case "Region VIII - Eastern Visayas":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region8_city, R.layout.spin);
                            break;
                        case "Region IX - Zamboanga Peninsula":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region9_city, R.layout.spin);
                            break;
                        case "Region X - Northern Mindanao":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region10_city, R.layout.spin);
                            break;
                        case "Region XI - Davao Region":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region11_city, R.layout.spin);
                            break;
                        case "Region XII - SOCCKSARGEN":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region12_city, R.layout.spin);
                            break;
                        case "Region XIII - Caraga":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_region13_city, R.layout.spin);
                            break;
                        case "BARMM - Bangsamoro Autonomous Region in Muslim Mindanao":
                            adapterCity = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_BARRM_city, R.layout.spin);
                            break;
                        default:
                            break;
                    }
                    adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinCity.setAdapter(adapterCity);
                    spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            chosenCity = spinCity.getSelectedItem().toString();
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
        Button submitButton;
        submitButton = findViewById(R.id.btnRegisterEst);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        estName =findViewById(R.id.editNameEst);
        estType =findViewById(R.id.editTypeEst);
        estContact =findViewById(R.id.editContactNumber);
        txtRegion = findViewById(R.id.txt_region);
        txtCity = findViewById(R.id.txt_district);
        txtBarangay = findViewById(R.id.editBarangay);
        txtLong = findViewById(R.id.editLocationLong);
        txtLat = findViewById(R.id.editLocationLat);
        establishment = estName.getText().toString();
        type = estType.getText().toString();
        contact = estContact.getText().toString();
        barangay = txtBarangay.getText().toString();
        longi = txtLong.getText().toString();
        lat = txtLat.getText().toString();
        if (chosenRegion.equals("Select Your Region")) {
            Toast.makeText(RegistrationEstablishment.this, "Please select your Region from the list", Toast.LENGTH_LONG).show();
            txtRegion.setError("Region is required");      //To set error on TextView
        } else if (chosenCity.equals("Select Your Province/City")) {
            Toast.makeText(RegistrationEstablishment.this, "Please select your Province/City from the list", Toast.LENGTH_LONG).show();
            txtCity.setError("Province/City is required!");
            txtRegion.setError(null);
        } else if (barangay.isEmpty()) {
            Toast.makeText(RegistrationEstablishment.this, "Please input the Establishment's Barangay", Toast.LENGTH_LONG).show();
            txtBarangay.setError("Barangay is required!");
        } else if (longi.isEmpty()) {
            Toast.makeText(RegistrationEstablishment.this, "Please input the Longitude of the Establishment", Toast.LENGTH_LONG).show();
            txtLong.setError("Longitude is required!");
        } else if (lat.isEmpty()) {
            Toast.makeText(RegistrationEstablishment.this, "Please input the Latitude of the Establishment", Toast.LENGTH_LONG).show();
            txtLat.setError("Latitude is required!");
        } else if (establishment.isEmpty()) {
            Toast.makeText(RegistrationEstablishment.this, "Please input your Establishment Name", Toast.LENGTH_LONG).show();
            estName.setError("Establishment Name is required!");
        } else if (type.isEmpty()) {
            Toast.makeText(RegistrationEstablishment.this, "Please input the type of your Establishment", Toast.LENGTH_LONG).show();
            estType.setError("Type is required!");
        } else if (contact.isEmpty()) {
            Toast.makeText(RegistrationEstablishment.this, "Please input your Establishment's Contact Number", Toast.LENGTH_LONG).show();
            txtLong.setError("Contact Number is required!");
        } else {
//            estName.setError(null);
//            estType.setError(null);
//            estContact.setError(null);
            txtRegion.setError(null);
            txtCity.setError(null);
//            txtBarangay.setError(null);
//            txtLong.setError(null);
//            txtLat.setError(null);
            Toast.makeText(RegistrationEstablishment.this, "Selected Region: " + chosenRegion + "\nSelected Province/City: " + chosenCity, Toast.LENGTH_LONG).show();
//            progressDialog.setMessage("Please wait...");
//            progressDialog.setTitle("Registration");
//            progressDialog.setCanceledOnTouchOutside(false);
//            progressDialog.show();

        }

    }
}