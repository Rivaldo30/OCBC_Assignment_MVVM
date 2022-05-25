package id.ac.uversrivaldo.ocbc_assignment.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.uversrivaldo.ocbc_assignment.Api.ApiClient;
import id.ac.uversrivaldo.ocbc_assignment.LoginRequest;
import id.ac.uversrivaldo.ocbc_assignment.Response.LoginResponse;
import id.ac.uversrivaldo.ocbc_assignment.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{

    public static final String SHARED_PREF_NAME = "my_shared_pref";
    EditText editText1, editText2;
    Button btnLogin, btnRegister;
    private static String URL_LOGIN = "https://green-thumb-64168.uc.r.appspot.com/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText1 = findViewById(R.id.username);
        editText2 = findViewById(R.id.password);

        btnLogin = findViewById(R.id.LoginBtn);
        btnRegister = findViewById(R.id.RegisterBtn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editText1.getText().toString()) || TextUtils.isEmpty(editText2.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Username / Password Required", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
            }

        });

    }

    public void saveUser(String username, String accountNo, String token){
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("username", username);
        editor.putString("accountNo", accountNo);
        editor.putString("token", token);

        editor.apply();

    }

    public void login(){

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(editText1.getText().toString());
        loginRequest.setPassword(editText2.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.getApiServiceNoAuth().userLogin(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                    Log.e("Login", response.body().toString());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LoginResponse loginResponse = response.body();

                            saveUser(loginResponse.getUsername(), loginResponse.getAccountNo(), loginResponse.getToken());

                            startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("data", loginResponse.getUsername()));
                        }
                    }, 50);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Throwable"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    }
