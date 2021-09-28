package com.example.sample_video;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sample_video.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding mBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(mBinding.getRoot());

    mBinding.lottie.setImageAssetsFolder("images");
    mBinding.lottie.setAnimation("data.json");
    mBinding.lottie.playAnimation();
  }


}