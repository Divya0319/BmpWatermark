package com.fastturtlePractice.BmpWaterMarkTesting.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.fastturtlePractice.BmpWaterMarkTesting.R;

import io.fastturtle.BmpWatermark.WatermarkProvider;


/**
 * @Author: Divya Gupta
 * @Date: 20-Dec-22
 */
public class MainActivity extends AppCompatActivity {

    AppCompatButton btPickImage;
    AppCompatImageView ivPickedImage;
    ActivityResultLauncher<Intent> pickImageFromGalleryForResult;
    WatermarkProvider.Builder watermarkBuilder;
    Uri selectedImageUri;
    CustomTarget<Bitmap> target;
    Bitmap selectedBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPickImage = findViewById(R.id.bt_pick_image);
        ivPickedImage = findViewById(R.id.pickedImage);
        target = new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition transition) {
                selectedBitmap = resource;
                watermarkBuilder = new WatermarkProvider.Builder(MainActivity.this, selectedBitmap, getString(R.string.english_text));

                WatermarkProvider wmp = watermarkBuilder
                        .setColor(R.color.blue_500)
//                .setAlpha(50)
//                .setxCoordinate(0)
//                .setyCoordinate(120)
//                .setTextSize(78)
//                .setRotationAngle(45)
                        .build();
                selectedBitmap = wmp.getWaterMarkedBitmap();
                ivPickedImage.setImageBitmap(selectedBitmap);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        };

        pickImageFromGalleryForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        if (result.getData() != null) {
                            selectedImageUri = result.getData().getData();
                            if (selectedImageUri != null) {
                                Glide.with(MainActivity.this)
                                        .asBitmap()
                                        .load(selectedImageUri)
                                        .into(target);

                            }

                        }
                    }
                });

        btPickImage.setOnClickListener(v -> openImageChooser());

    }

    void openImageChooser() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
        String[] mimeTypes = {"image/jpeg", "image/png"};
        galleryIntent.setType("image/jpeg|image/png");
        galleryIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        pickImageFromGalleryForResult.launch(galleryIntent);
    }
}
