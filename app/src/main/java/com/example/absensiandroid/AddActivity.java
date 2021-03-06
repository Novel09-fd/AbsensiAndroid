package com.example.absensiandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.absensiandroid.entity.Absensi;
import com.example.absensiandroid.service.APIClient;
import com.example.absensiandroid.service.AbsensiInterface;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {
    Button btnFoto, btnSubmitFoto;
    ImageView imgLogin;
    String mediaPath, textUsername;
    String locationText = "0";
    FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        varInit();

        getLocation();

        textUsername = getIntent().getStringExtra("username");

        btnFoto.setOnClickListener(v -> {
            Intent gambar = new Intent(AddActivity.this, ImageSelectActivity.class);
            gambar.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
            gambar.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
            gambar.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
            startActivityForResult(gambar, 1);
        });

        btnSubmitFoto.setOnClickListener(v -> {
            uploadAbsen();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            mediaPath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            imgLogin.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
        }
    }

    private void uploadAbsen() {
        Absensi absen = new Absensi();

        absen.setUser(textUsername);
        absen.setTanggalMasuk(getTanggal());
        absen.setJamMasuk(getJam());
        absen.setGps(locationText);

        Gson gson = new Gson();
        String json = gson.toJson(absen);

        AbsensiInterface absenAPI = APIClient
                .getRetrofit().create(AbsensiInterface.class);

        File file = new File(mediaPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file",
                file.getName(), requestBody);
        RequestBody data = RequestBody.create(MediaType.parse("text/plain"), json);

        Call<ResponseBody> call = absenAPI.checkInAbsen(fileToUpload, data);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Toast.makeText(AddActivity.this, (getJam() / 100) + ":" + String.valueOf(getJam() % 100) + " " + response.body().string(), Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private int getJam() {
        Calendar calendar = Calendar.getInstance();
        int hour24hrs = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        return (hour24hrs * 100) + minutes;
    }

    @SuppressLint("MissingPermission")
    private String getLocation() {
        mFusedLocationProviderClient = LocationServices
                .getFusedLocationProviderClient(AddActivity.this);

        mFusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(AddActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            locationText = location.getLatitude() + ", " +
                                    location.getLongitude();
                        } else locationText = "null";
                    }
                });

        return locationText;
    }

    private String getTanggal() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        return df.format(c);
    }

    private void varInit() {
        btnFoto = findViewById(R.id.btn_foto);
        btnSubmitFoto = findViewById(R.id.btn_submit_foto);
        imgLogin = findViewById(R.id.iv_foto);
    }
}