package com.example.topjuantech_ojt;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;



public class UserDashboard extends NavigationDrawer {
    private ScrollView icon1, icon2, icon3, icon4,
            icon5, icon6, icon7, icon8, icon9;
    // creating object of ViewPager
    ViewPager mViewPager;

    // images array
    int[] images = {R.drawable.food, R.drawable.coffee, R.drawable.tourist, R.drawable.hospital,
            R.drawable.hotel, R.drawable.gas, R.drawable.logo, R.drawable.food};

    // Creating Object of ViewPagerAdapter
    SliderAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_user_dashboard, null, false);
        drawer.addView(v, 0);
        // creating object of ViewPager
        ViewPager mViewPager;

        // Initializing the ViewPager Object
        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new SliderAdapter(UserDashboard.this, images);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);

    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View v = inflater.inflate(R.layout.activity_user_dashboard, false);
//        icon1 = (ImageView) findViewById(R.id.icon1);
//        icon2 = (ImageView) findViewById(R.id.icon2);
//        icon3 = (ImageView) findViewById(R.id.icon3);
//        icon4 = (ImageView) findViewById(R.id.icon4);
//        icon5 = (ImageView) findViewById(R.id.icon5);
//        icon6 = (ImageView) findViewById(R.id.icon6);
//        icon7 = (ImageView) findViewById(R.id.icon7);
//        icon8 = (ImageView) findViewById(R.id.icon8);
//        icon9 = (ImageView) findViewById(R.id.icon9);
//
//        icon1.setOnClickListener(View.OnClickListener) this);
//        icon2.setOnClickListener(View.OnClickListener) this);
//        icon3.setOnClickListener(View.OnClickListener) this);
//        icon4.setOnClickListener(View.OnClickListener) this);
//        icon5.setOnClickListener(View.OnClickListener) this);
//        icon6.setOnClickListener(View.OnClickListener) this);
//        icon7.setOnClickListener(View.OnClickListener) this);
//        icon8.setOnClickListener(View.OnClickListener) this);
//        icon9.setOnClickListener(View.OnClickListener) this);
//
//        @Override
//        public void onClick(View v) {
//            Intent i;
//            switch (v.getId()) {
//                case R.id.icon1:
//                    i = new Intent(this, CategoryAmusement.class);
//                    startActivity(i);
//                    break;
//                case R.id.icon2:
//                    i = new Intent(this, CategoryBanks.class);
//                    startActivity(i);
//                    break;
//                case R.id.icon3:
//                    i = new Intent(this, CategoryBeverages.class);
//                    startActivity(i);
//                    break;
//                case R.id.icon4:
//                    i = new Intent(this, CategoryFoodSpots.class);
//                    startActivity(i);
//                    break;
//                case R.id.icon5:
//                    i = new Intent(this, CategoryGasStations.class);
//                    startActivity(i);
//                    break;
//                case R.id.icon6:
//                    i = new Intent(this, CategoryGovernment.class);
//                    startActivity(i);
//                    break;
//                case R.id.icon7:
//                    i = new Intent(this, CategoryHospitals.class);
//                    startActivity(i);
//                    break;
//                case R.id.icon8:
//                    i = new Intent(this, CategoryHotels.class);
//                    startActivity(i);
//                    break;
//                case R.id.icon9:
//                    i = new Intent(this, CategoryAttractions.class);
//                    startActivity(i);
//                    break;
//
//            }
//            {}


    }


