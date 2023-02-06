package com.example.topjuantech_ojt;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

//import com.denzcoskun.imageslider.ImageSlider;
//import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class UserDashboard extends NavigationDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_user_dashboard, null, false);

        drawer.addView(v, 0);

//    ImageSlider imageSlider = findViewById(R.id.slider);
//            List<SlideModel> slideModels = new ArrayList<>();
//
//            slideModels.add(new SlideModel(R.drawable.food));
//            slideModels.add(new SlideModel(R.drawable.coffee));
//            slideModels.add(new SlideModel(R.drawable.tourist));
//
//            imageSlider.setImageList(slideModels, true);
    }
}
