package com.newport.app.ui.eventsgallery;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.perf.metrics.AddTrace;
import com.newport.app.BuildConfig;
import com.newport.app.R;
import com.newport.app.data.models.response.PhotoGalleryEventResponse;
import com.newport.app.util.Constant;
import com.newport.app.util.PreferencesHeper;
import com.newport.app.util.UBitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.newport.app.util.Constant.REQUEST_GROUP_PERMISSIONS;
import static com.newport.app.util.Constant.REQUEST_OBJECTS_CODE;

public class EventsGalleryFragment extends Fragment implements EventsGalleryContract.View, EventsGalleryAdapter.OnClickPhotoListener, View.OnClickListener {

    private static final String ARG_PARAM1 = "idNew";
    private static final String ARG_PARAM2 = "uploadPhotos";
    private static final int REQUEST_OBJECTS_CODE_GALLERY = 258;

    private static final int REQUEST_PERMISSIONS_FOT_GALLERY = 201;

    private int contador = 1;
    private Context mContext;

    private EventsGalleryPresenter eventsGalleryPresenter;
    private EventsGalleryAdapter eventsGalleryAdapter;

    private FirebaseAnalytics mFirebaseAnalytics;

    private static int idDetailEvent;
    private static int uploadPhotos;

    private View rootView;
    private RelativeLayout rltProgress;
    private RecyclerView rcvGalleryPhotos;
    private boolean isFABOpen = false;
    private FloatingActionButton fab0;
    private FloatingActionButton fab1;
    private FloatingActionButton fab;
    private CoordinatorLayout crdGallery;

    private String absolutPath;

    public EventsGalleryFragment() {
        // Required empty public constructor
    }

    public static EventsGalleryFragment newInstance(int idDetailEvent, int uploadPhotos) {
        EventsGalleryFragment fragment = new EventsGalleryFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, idDetailEvent);
        args.putInt(ARG_PARAM2, uploadPhotos);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idDetailEvent = getArguments().getInt(ARG_PARAM1);
            uploadPhotos = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_gallery_events, container, false);
        mContext = inflater.getContext();
        init();
        return rootView;
    }

    private void init() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());

        crdGallery = rootView.findViewById(R.id.crdGallery);
        rltProgress = rootView.findViewById(R.id.rltProgress);
        rcvGalleryPhotos = rootView.findViewById(R.id.rcvGalleryPhotos);
        rcvGalleryPhotos.setHasFixedSize(true);

        fab = rootView.findViewById(R.id.fab);
        fab0 = rootView.findViewById(R.id.fab0);
        fab1 = rootView.findViewById(R.id.fab1);

        fab.setOnClickListener(this);

        //Get width system
        int width = PreferencesHeper.getWidthSystem(getActivity());

        eventsGalleryAdapter = new EventsGalleryAdapter(width);
        eventsGalleryAdapter.setOnPhotoClickListener(this);
        rcvGalleryPhotos.setAdapter(eventsGalleryAdapter);

        eventsGalleryPresenter = new EventsGalleryPresenter();
        eventsGalleryPresenter.attachedView(this);

        fab0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                requestPermissionForGallery();
            }
        });
    }

    private void openGallery(){
        Intent i=new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,REQUEST_OBJECTS_CODE_GALLERY);
    }

    private void showFABMenu(){
        isFABOpen=true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_75));
        fab.animate().translationY(-getResources().getDimension(R.dimen.standard_120));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fab1.animate().translationY(0);
        fab.animate().translationY(0);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventsGalleryPresenter.getPhotosGalleryEvent(idDetailEvent);
    }

    @Override
    public void onGalleryItemClick(int position, PhotoGalleryEventResponse photoGalleryEventResponse, ImageView imgItemGalleryPhoto) {

        Intent intent = new Intent(getActivity(), PhotoGalleryActivity.class);
        intent.putExtra(Constant.EXTRA_PHOTO_ITEM, photoGalleryEventResponse);
        intent.putExtra(Constant.EXTRA_PHOTO_TRANSITION_NAME, ViewCompat.getTransitionName(imgItemGalleryPhoto));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                imgItemGalleryPhoto,
                ViewCompat.getTransitionName(imgItemGalleryPhoto));

        startActivity(intent, options.toBundle());
    }

    @Override
    public void showLoading() {
        rcvGalleryPhotos.setVisibility(View.GONE);
        rltProgress.setVisibility(View.VISIBLE);
        fab.hide();
        fab1.hide();
        fab0.hide();
    }

    @Override
    public void hideLoading() {
        rcvGalleryPhotos.setVisibility(View.VISIBLE);
        rltProgress.setVisibility(View.GONE);
    }

    @Override
    public void showPhotosEvent(List<PhotoGalleryEventResponse> photoGalleryEventResponseList) {
        eventsGalleryAdapter.addData(photoGalleryEventResponseList);
        if (uploadPhotos != 0) {
            fab.show();
            fab1.show();
            fab0.show();
        }
    }

    @Override
    public void reloadGalleryPhotos() {
        eventsGalleryPresenter.getPhotosGalleryEvent(idDetailEvent);
    }

    @Override
    public void showPhotosEmpty(String message) {
        rltProgress.setVisibility(View.GONE);
        Snackbar.make(crdGallery, message, Snackbar.LENGTH_SHORT).show();
        fab.show();
        fab1.show();
        fab0.show();
    }

    @Override
    public void showPhotosError(String error) {
        rltProgress.setVisibility(View.INVISIBLE);
        Snackbar snackbar = Snackbar
                .make(crdGallery, error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry_request, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        eventsGalleryPresenter.getPhotosGalleryEvent(idDetailEvent);
                    }
                });

        snackbar.show();
    }

    @Override
    public void onClick(View view) {
        requestPermission();
    }

    public void requestPermissionForGallery() {
        List<String> permissionNeeded = new ArrayList<>();
        List<String> permissionAvailable = new ArrayList<>();

        permissionAvailable.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissionAvailable.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        for (String permission : permissionAvailable) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                permissionNeeded.add(permission);
            }
        }

        if (permissionNeeded.isEmpty()) {
            openGallery();
        } else {
            requestGroupPermission(permissionNeeded, REQUEST_PERMISSIONS_FOT_GALLERY);
        }
    }

    public void requestPermission() {
        List<String> permissionNeeded = new ArrayList<>();
        List<String> permissionAvailable = new ArrayList<>();

        permissionAvailable.add(Manifest.permission.CAMERA);
        permissionAvailable.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissionAvailable.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        for (String permission : permissionAvailable) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                permissionNeeded.add(permission);
            }
        }

        if (permissionNeeded.isEmpty()) {
            callCamera();
        } else {
            requestGroupPermission(permissionNeeded, REQUEST_GROUP_PERMISSIONS);
        }
    }

    public void requestGroupPermission(List<String> permissions, int code) {
        String[] permissionList = new String[permissions.size()];
        permissions.toArray(permissionList);
        ActivityCompat.requestPermissions(getActivity(), permissionList, code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("ACS", "Galleryfragment.onRequestPermissionsResult(...: "+requestCode);
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
                callCamera();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
        } else if(requestCode == REQUEST_PERMISSIONS_FOT_GALLERY){
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
                openGallery();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

    private void callCamera() {
        //Track Event
        Bundle bundle = new Bundle();
        bundle.putInt("idNew", idDetailEvent);
        mFirebaseAnalytics.logEvent("take_photo", bundle);

        File imageFile = null;

        try {
            String imageFileName = "PNG_NEWPORT";
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
            absolutPath = imageFile.getAbsolutePath();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (imageFile != null) {
            Uri photoURI = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID, imageFile);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, REQUEST_OBJECTS_CODE);
            showLoading();
        }




    }

    private String getRealPathFromURI(String contentString) {
        Uri contentUri = Uri.parse(contentString);
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(mContext, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @SuppressLint("StaticFieldLeak")
    @SuppressWarnings("ConstantConditions")
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {

        if (requestCode == REQUEST_OBJECTS_CODE && resultCode == RESULT_OK) {

            System.out.println("Path Camera: " + absolutPath);

            new AsyncTask<Void, Void, String>() {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    Toast.makeText(getActivity(), "Subiendo foto", Toast.LENGTH_SHORT).show();
                }

                @Override
                @AddTrace(name = "imageProcessTo64")
                protected String doInBackground(Void... voids) {
                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();

                    bmOptions.inJustDecodeBounds = false;
                    bmOptions.inSampleSize = 2;

                    Bitmap bitmap = BitmapFactory.decodeFile(absolutPath, bmOptions);

                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

                    try {
                        bitmap = UBitmap.handleSamplingAndRotationBitmap(getActivity(), Uri.fromFile(new File(absolutPath)));
                    } catch (Exception e) {
                        Log.e("ACS", "Error al tratar image de camara: ", e);
                    }

                    if (bitmap != null) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteStream);
                    }

                    return Base64.encodeToString(byteStream.toByteArray(), Base64.NO_WRAP);
                }

                @Override
                protected void onPostExecute(String base64Data) {
                    eventsGalleryPresenter.savePhotoGalleryEvent(idDetailEvent, base64Data);
                }
            }.execute();

        } else if(requestCode == REQUEST_OBJECTS_CODE_GALLERY && resultCode == RESULT_OK){

            absolutPath = getRealPathFromURI( data.getData().toString() );
            Log.i("ACS","Path Gallery: " + absolutPath);

            new AsyncTask<Void, Void, String>() {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    Toast.makeText(getActivity(), "Subiendo foto", Toast.LENGTH_SHORT).show();
                }

                @Override
                @AddTrace(name = "imageProcessTo64")
                protected String doInBackground(Void... voids) {
                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();

                    bmOptions.inJustDecodeBounds = false;
                    bmOptions.inSampleSize = 2;

                    Bitmap bitmap = BitmapFactory.decodeFile(absolutPath, bmOptions);

                    Log.i("ACS", "img_w: "+bitmap.getWidth());
                    Log.i("ACS", "img_h: "+bitmap.getHeight());

                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

                    try {
                        bitmap = UBitmap.handleSamplingAndRotationBitmap(getActivity(), data.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (bitmap != null) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteStream);
                    }

                    return Base64.encodeToString(byteStream.toByteArray(), Base64.NO_WRAP);
                }

                @Override
                protected void onPostExecute(String base64Data) {
                    eventsGalleryPresenter.savePhotoGalleryEvent(idDetailEvent, base64Data);
                }
            }.execute();

        } else {
            hideLoading();
            Snackbar snackbar = Snackbar.make(crdGallery, "Subida cancelada", Snackbar.LENGTH_SHORT);
            snackbar.show();
            fab.show();
            fab1.show();
            fab0.show();
        }
    }

    public static Bitmap rotate(Bitmap bitmap, int degree) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix mtx = new Matrix();
        //       mtx.postRotate(degree);
        mtx.setRotate(degree);

        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }

}