package id.ac.uversrivaldo.ocbc_assignment.View.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import id.ac.uversrivaldo.ocbc_assignment.Model.LoginResponse;
import id.ac.uversrivaldo.ocbc_assignment.R;

import id.ac.uversrivaldo.ocbc_assignment.View.home.MainActivity;
import id.ac.uversrivaldo.ocbc_assignment.viewmodels.MainActivityViewModel;

public class LoginActivity extends AppCompatActivity{

    public static final String SHARED_PREF_NAME = "my_shared_pref";
    EditText editText1, editText2;
    Button btnLogin, btnRegister;
    MainActivityViewModel mMainActivityViewModel;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String username;
    String password;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        editText1 = findViewById(R.id.username);
        editText2 = findViewById(R.id.password);

        btnLogin = findViewById(R.id.LoginBtn);
        btnRegister = findViewById(R.id.RegisterBtn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = editText1.getText().toString();
                password = editText2.getText().toString();
                Log.d(TAG, "onClick- username:" +username);
                Log.d(TAG, "onClick- password:" +password);
                if (username.isEmpty() || password.isEmpty()) {
                    Log.d(TAG, "onClick: username is empty");
                    Toast.makeText(LoginActivity.this, "Username / Password Required", Toast.LENGTH_SHORT).show();
                } else {
                    mMainActivityViewModel.setlogin(username, password, this);
                    login();
                }
            }
        });
    }

    public void saveUser(String username, String accountNo, String token){
        editor.putString("username", username);
        editor.putString("accountNo", accountNo);
        editor.putString("token", token);

        editor.apply();

    }

    /*final Observer<LoginResponse> nameObserver = new Observer<LoginResponse>() {
        @Override
        public void onChanged(LoginResponse loginResponse) {
            saveUser(loginResponse.getUsername(),loginResponse.getAccountNo(), loginResponse.getToken());
            Log.d(TAG, "onChanged-token: "+sharedPreferences.getString("token",""));
            Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intentMain);
        }
    };*/

    public void login(){
        mMainActivityViewModel.getDataLogin().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse.getError() != null){
                    Toast.makeText(LoginActivity.this, loginResponse.getError(),
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    saveUser(loginResponse.getUsername(),loginResponse.getAccountNo(), loginResponse.getToken());
                    Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentMain);
                }
            }
        });
    }
}