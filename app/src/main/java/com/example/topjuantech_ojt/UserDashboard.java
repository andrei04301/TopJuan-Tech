package com.example.topjuantech_ojt;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;



public class UserDashboard extends NavigationDrawer {
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
}

