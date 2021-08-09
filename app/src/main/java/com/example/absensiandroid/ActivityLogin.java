package com.example.absensiandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.absensiandroid.entity.Login;
import com.example.absensiandroid.service.APIClient;
import com.example.absensiandroid.service.AbsensiInterface;
import com.example.absensiandroid.service.LoginInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        loginAbsensi();
    }

    AbsensiInterface absensiInterface;
    ProgressDialog progressDialog;
    public void loginAbsensi() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginInterface loginInterface = APIClient.getRetrofit().create(LoginInterface.class);
                Call<ArrayList<Login>> call = loginInterface.loginAbsensi(etUsername.getText().toString(), etPassword.getText().toString());
                call.enqueue(new Callback<ArrayList<Login>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Login>> call, Response<ArrayList<Login>> response) {
                        if (etUsername.getText().toString().equals("") && etPassword.getText().toString().equals("")) {
                            AlertDialog alertDialog = new AlertDialog.Builder(ActivityLogin.this).create();
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("Masukkan username dan password");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        } else if (response.body().isEmpty()) {
                            AlertDialog alertDialog = new AlertDialog.Builder(ActivityLogin.this).create();
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("Username dan password salah");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        } else {
                            Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Login>> call, Throwable t) {
                        System.out.println(t);
                    }
                });
            }
        });
    }
}