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
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
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
    private ImageView bigImage;
    private Integer currentImage;
    private Integer numImages;

    private Bitmap[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Choose or take 3 images");
        gameManager = GameManager.getInstance(this);

        numImages = 0;
        Button saveBtn = findViewById(R.id.save_imgs_btn);
        saveBtn.setEnabled(false);
        images = new Bitmap[3];

        bigImage = findViewById(R.id.ivSelectedImage);

        setUpSaveImages();
        setUpChooseImages();
        setUpStartGameBtn();
    }

    //Convert each imageView into byte[]'s and save as attributes in GameManager.
    private void setUpSaveImages() {
        Button saveBtn = findViewById(R.id.save_imgs_btn);
        Button startBtn = findViewById(R.id.start_game_btn);

        saveBtn.setOnClickListener((v) -> {
            byte[] imageOneByte = convertImageViewToByteArray(imageOne);
            byte[] imageTwoByte = convertImageViewToByteArray(imageTwo);
            byte[] imageThreeByte = convertImageViewToByteArray(imageThree);

            images[0] = convertByteArrayToBitmap(imageOneByte);
            images[1] = convertByteArrayToBitmap(imageTwoByte);
            images[2] = convertByteArrayToBitmap(imageThreeByte);

            gameManager.setImages(images);

            Toast.makeText(ImageActivity.this, "Images saved!",Toast.LENGTH_SHORT).show();
            startBtn.setEnabled(true);
        });
    }

    private void setUpChooseImages() {
        imageOne = findViewById(R.id.userImage1);
        imageTwo = findViewById(R.id.userImage2);
        imageThree = findViewById(R.id.userImage3);
        Button saveBtn = findViewById(R.id.save_imgs_btn);

        imageOne.setOnClickListener((v) -> {
            currentImage = 1;
            chooseImage();
            numImages++;

            if (numImages == 3) {
                saveBtn.setEnabled(true);
            }
        });

        imageTwo.setOnClickListener((v) -> {
            currentImage = 2;
            chooseImage();
            numImages++;

            if (numImages == 3) {
                saveBtn.setEnabled(true);
            }
        });

        imageThree.setOnClickListener((v) -> {
            currentImage = 3;
            chooseImage();
            numImages++;

            if (numImages == 3) {
                saveBtn.setEnabled(true);
            }
        });
    }

    private void chooseImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ImageActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_image, null);

        builder.setCancelable(false);
        builder.setView(dialogView);

        ImageView cameraImg = dialogView.findViewById(R.id.camera_img);
        ImageView galleryImg = dialogView.findViewById(R.id.gallery_img);
        TextView tvCancel = dialogView.findViewById(R.id.tvCancel);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        tvCancel.setOnClickListener((v) -> alertDialog.cancel());

        cameraImg.setOnClickListener((v) -> {
            if (checkAndRequestPermissions()) {
                takePictureFromCamera();
                alertDialog.cancel();
            }
        });

        galleryImg.setOnClickListener((v) -> {
            takePictureFromGallery();
            alertDialog.cancel();
        });
    }

    @SuppressWarnings("deprecation")
    private void takePictureFromGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("QueryPermissionsNeeded")
    private void takePictureFromCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(takePicture, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    assert data != null;
                    Uri selectedImageUri = data.getData();
                    setImageURI(selectedImageUri);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    assert data != null;
                    Bundle bundle = data.getExtras();
                    Bitmap bitmapImage = (Bitmap) bundle.get("data");
                    setImageBitmap(bitmapImage);
                }
                break;
        }
    }

    private void setImageURI(Uri currentUri) {
        if (currentImage == 1) {
            imageOne.setImageURI(currentUri);
        }
        else if (currentImage == 2) {
            imageTwo.setImageURI(currentUri);
        }
        else {
            imageThree.setImageURI(currentUri);
        }
        bigImage.setImageURI(currentUri);
    }

    private void setImageBitmap(Bitmap currentBitmap) {
        if (currentImage == 1) {
            imageOne.setImageBitmap(currentBitmap);
        }
        else if (currentImage == 2) {
            imageTwo.setImageBitmap(currentBitmap);
        }
        else {
            imageThree.setImageBitmap(currentBitmap);
        }
        bigImage.setImageBitmap(currentBitmap);
    }

    @SuppressLint("ObsoleteSdkInt")
    private boolean checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= 27) {
            int cameraPermission = ActivityCompat.checkSelfPermission(
                    ImageActivity.this, Manifest.permission.CAMERA);
            if (cameraPermission == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(ImageActivity.this,
                        new String[]{Manifest.permission.CAMERA}, 20);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 20 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takePictureFromCamera();
        } else
            Toast.makeText(ImageActivity.this, "Permission not Granted",
                    Toast.LENGTH_SHORT).show();
    }

    private byte[] convertImageViewToByteArray(ImageView imageView){
        Bitmap bitmap=((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private Bitmap convertByteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

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
        startBtn.setEnabled(false);
        startBtn.setOnClickListener((v) -> {
            Intent intent = GameScreen.makeIntent(this);
            startActivity(intent);
        });
    }
}