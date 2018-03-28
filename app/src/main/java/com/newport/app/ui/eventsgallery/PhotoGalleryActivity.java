package com.newport.app.ui.eventsgallery;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.newport.app.R;
import com.newport.app.data.models.response.PhotoGalleryEventResponse;
import com.newport.app.util.Constant;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.newport.app.util.Constant.REQUEST_GROUP_PERMISSIONS;

public class PhotoGalleryActivity extends AppCompatActivity {

    private PhotoGalleryEventResponse photoGalleryEventResponse;
    private FirebaseAnalytics mFirebaseAnalytics;

    private Matrix matrix = new Matrix();
    private Float scale = 1f;
    private ScaleGestureDetector gestureDetector;

    private boolean firstTouch = false;
    private boolean imageLoaded = false;

    private ImageView imgGalleryPhoto;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        supportPostponeEnterTransition();

        init();
    }

    private void init() {
        context = this;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        photoGalleryEventResponse = (PhotoGalleryEventResponse) getIntent().getSerializableExtra(Constant.EXTRA_PHOTO_ITEM);

        imgGalleryPhoto = findViewById(R.id.imgGalleryPhoto);
        TextView lblNamePhoto = findViewById(R.id.lblNamePhoto);
        TextView lblHourPhoto = findViewById(R.id.lblHourPhoto);

        lblNamePhoto.setText(photoGalleryEventResponse.getNews_title());
        lblHourPhoto.setText(photoGalleryEventResponse.getCreated_at());

        gestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        String imageTransitionName = getIntent().getStringExtra(Constant.EXTRA_PHOTO_TRANSITION_NAME);
        imgGalleryPhoto.setTransitionName(imageTransitionName);

        Picasso.with(this)
                .load(photoGalleryEventResponse.getImage_url())
                .noFade()
                .into(imgGalleryPhoto, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                        imageLoaded = true;
                    }

                    @Override
                    public void onError() {
                        supportStartPostponedEnterTransition();
                    }
                });
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale = scale * detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5f));
            matrix.setScale(scale, scale);
            imgGalleryPhoto.setImageMatrix(matrix);
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!firstTouch && imageLoaded){
            firstTouch = true;
            imgGalleryPhoto.setScaleType(ImageView.ScaleType.MATRIX);
        }
        gestureDetector.onTouchEvent(event);
        return true;
    }

    public void checkPermissions(View view) {
        requestPermission();
    }

    public void requestPermission() {
        List<String> permissionNeeded = new ArrayList<>();
        List<String> permissionAvailable = new ArrayList<>();

        permissionAvailable.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissionAvailable.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        for (String permission : permissionAvailable) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionNeeded.add(permission);
            }
        }

        if (permissionNeeded.isEmpty()) {
            downloadImage();
        } else {
            requestGroupPermission(permissionNeeded);
        }
    }

    private void downloadImage() {
        //Track Event
        Bundle bundle = new Bundle();
        bundle.putInt(FirebaseAnalytics.Param.ITEM_ID, photoGalleryEventResponse.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, photoGalleryEventResponse.getImage());
        bundle.putString("new_title", photoGalleryEventResponse.getNews_title());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent("download_photo", bundle);

        Toast.makeText(this, "Se ha iniciado la descarga", Toast.LENGTH_SHORT).show();
        Picasso.with(this)
                .load(photoGalleryEventResponse.getImage_url())
                .into(getTarget(photoGalleryEventResponse.getImage()));
    }

    public void requestGroupPermission(List<String> permissions) {
        String[] permissionList = new String[permissions.size()];
        permissions.toArray(permissionList);
        ActivityCompat.requestPermissions(this, permissionList, REQUEST_GROUP_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("ACS", "PhotoGalleryfragment.onRequestPermissionsResult(...: "+requestCode);
        if (requestCode == REQUEST_GROUP_PERMISSIONS) {
            String result = "";
            int i = 0;
            int numberPermissions = permissions.length;
            for (String perm : permissions) {
                String status;
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    status = "GRANTED";
                    numberPermissions--;
                } else {
                    status = "DENIED";
                }
                result = result + "\n" + perm + " : " + status;
                i++;
            }

            if (numberPermissions == 0) {
                downloadImage();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Permisos");
                builder.setMessage(result);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }

    //target to save
    private static Target getTarget(final String imageName) {
        return new Target() {

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {

                new AsyncTask<Void, Void, Boolean>() {

                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        boolean result = true;

                        File folderDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/NewportImages");
                        if (!folderDirectory.exists()) {
                            folderDirectory.mkdir();
                        }

                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath() + "/NewportImages/" + imageName);
                        FileOutputStream ostream = null;
                        try {
                            ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                        } catch (IOException e) {
                            Crashlytics.logException(e);
                            result = false;
                            Log.e("IOException", e.getLocalizedMessage());
                        } finally {
                            try {
                                ostream.close();
                            } catch (IOException e) {
                                Crashlytics.logException(e);
                                e.printStackTrace();
                            }
                        }
                        return result;
                    }

                    @Override
                    protected void onPostExecute(Boolean result) {
                        if (result) {
                            Toast.makeText(context, "Descarga Exitosa", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, R.string.error_to_download_image, Toast.LENGTH_SHORT).show();
                        }

                    }
                }.execute();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Toast.makeText(context, R.string.error_to_download_image, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
    }
}
