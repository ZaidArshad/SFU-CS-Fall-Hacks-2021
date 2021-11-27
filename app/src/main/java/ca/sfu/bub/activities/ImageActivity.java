package ca.sfu.bub.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

import ca.sfu.bub.R;
import ca.sfu.bub.model.GameManager;

/**
 * This class handles the image/camera functionality
 */
public class ImageActivity extends AppCompatActivity {
    private GameManager gameManager;
    private ImageView imageOne;
    private ImageView imageTwo;
    private ImageView imageThree;
    private Integer numImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Choose or take 3 images");

        Button saveBtn = findViewById(R.id.save_imgs_btn);
        saveBtn.setEnabled(false);

        //Convert image views --> byte arrays --> bitmaps, bitmap attributes in game manager.
        //setUpSaveImages();
        //setUpChooseImages();
        setUpStartGameBtn();
    }

//    private void setUpChooseImages() {
//        numImages = 0;
//        imageOne = findViewById(R.id.userImage1);
//        imageTwo = findViewById(R.id.userImage2);
//        imageThree = findViewById(R.id.userImage3);
//        //Button saveBtn = findViewById(R.id.save_imgs_btn);
//
//        imageOne.setOnClickListener((v) -> {
//            chooseImage(imageOne);
//            numImages++;
//        });
//
//        imageTwo.setOnClickListener((v) -> {
//            chooseImage(imageTwo);
//            numImages++;
//        });
//
//        imageThree.setOnClickListener((v) -> {
//            chooseImage(imageThree);
//            numImages++;
//        });
//    }
//
//    private void chooseImage(ImageView currentImage) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(ImageActivity.this);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.alert_dialog_image, null);
//
//        builder.setCancelable(false);
//        builder.setView(dialogView);
//
//        ImageView cameraImg = dialogView.findViewById(R.id.camera_img);
//        ImageView galleryImg = dialogView.findViewById(R.id.gallery_img);
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//
//        cameraImg.setOnClickListener((v) -> {
//            if (checkAndRequestPermissions()) {
//                takePictureFromCamera(currentImage);
//                alertDialog.cancel();
//            }
//        });
//
//        galleryImg.setOnClickListener((v) -> {
//            takePictureFromGallery(currentImage);
//            alertDialog.cancel();
//        });
//    }
//
//    @SuppressWarnings("deprecation")
//    private void takePictureFromGallery(ImageView currentImage) {
//        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickPhoto, 1);
//    }
//
//    //Probably need to add a parameter to takePictureFromGallery and FromCamera, so we can update
//    //proper imageview and data in onActivityResult()
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 1:
//                if (resultCode == RESULT_OK) {
//                    assert data != null;
//                    Uri selectedImageUri = data.getData();
//                    childImageInput.setImageURI(selectedImageUri);
//                }
//                break;
//            case 2:
//                if (resultCode == RESULT_OK) {
//                    assert data != null;
//                    Bundle bundle = data.getExtras();
//                    Bitmap bitmapImage = (Bitmap) bundle.get("data");
//                    childImageInput.setImageBitmap(bitmapImage);
//                }
//                break;
//        }
//    }
//
//    @SuppressLint("ObsoleteSdkInt")
//    private boolean checkAndRequestPermissions() {
//        if (Build.VERSION.SDK_INT >= 27) {
//            int cameraPermission = ActivityCompat.checkSelfPermission(
//                    ImageActivity.this, Manifest.permission.CAMERA);
//            if (cameraPermission == PackageManager.PERMISSION_DENIED) {
//                ActivityCompat.requestPermissions(ImageActivity.this,
//                        new String[]{Manifest.permission.CAMERA}, 20);
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 20 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            takePictureFromCamera();
//        } else
//            Toast.makeText(ImageActivity.this, "Permission not Granted",
//                    Toast.LENGTH_SHORT).show();
//    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, ImageActivity.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();

        if (itemID == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpStartGameBtn() {
        Button startBtn = findViewById(R.id.start_game_btn);
        startBtn.setOnClickListener((v) -> {
            Intent intent = GameScreen.makeIntent(this);
            startActivity(intent);
        });
    }
}