package com.example.echo.myapplication;

/**
 * Created by kikii on 4/23/2017.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class UserArea extends AppCompatActivity {
    private static final int SELECT_PHOTO = 100;
    ImageView dpImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);

        //To hide AppBar for fullscreen.
        ActionBar ab = getSupportActionBar();
        ab.hide();

        TextView txtname = (TextView) findViewById(R.id.txt_success_name);
        TextView txtemail = (TextView) findViewById(R.id.txt_success_email);
        Button _btn = (Button) findViewById(R.id.btn);
        dpImage = (ImageView) findViewById(R.id.imgclick);

        Intent intent = getIntent();

        String loginName = intent.getStringExtra("fullname");
        String loginEmail = intent.getStringExtra("email");
        txtname.setText("Welcome, " +loginName);
        txtemail.setText(loginEmail);

        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UserArea.this,MapsActivity.class);
                startActivity(intent);
//                final AlertDialog.Builder builder = new AlertDialog.Builder(UserArea.this);
//                builder.setTitle("Info");
//                builder.setMessage("Do you want to logout ??");
//                builder.setPositiveButton("Take me away!", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        Intent intent = new Intent(UserArea.this,Login.class);
//                        startActivity(intent);
//
//                        finish();
//
//                    }
//                });
//
//                builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        dialogInterface.dismiss();
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();
            }
        });

        //=========Section For Changing Display Image When Click=========

        dpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

    }

    //this Method call when user pick an image from ImagePicker. e.g gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();

                    Bitmap yourSelectedImage = null;
                    try {
                        //decodeUri() Method Defined Below
                        yourSelectedImage = decodeUri(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    dpImage.setImageBitmap(yourSelectedImage);
                }
        }
    }

    //decodeUri() Method for decoding image for Out of Memory Exception
    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 140;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Uri uri;
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.profile:
                Intent i1 = new Intent(this, profile.class);
                startActivity(i1);
                return true;

            case R.id.feedback:
                Uri uri2 = Uri.parse("mailto:customerservice@gmail.com");
                Intent i2 = new Intent(Intent.ACTION_SENDTO, uri2);
                startActivity(i2);

                return true;

            case R.id.close:

                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}