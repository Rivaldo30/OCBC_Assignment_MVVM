package id.ac.uversrivaldo.ocbc_assignment.View;

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

import id.ac.uversrivaldo.ocbc_assignment.ViewModels.LoginViewModel;

public class LoginView extends AppCompatActivity{

    public static final String SHARED_PREF_NAME = "my_shared_pref";
    EditText userName, Password;
    Button btnLogin, btnRegister;
    LoginViewModel mActivityViewModel;
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

        mActivityViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        userName = findViewById(R.id.username);
        Password = findViewById(R.id.password);

        btnLogin = findViewById(R.id.LoginBtn);
        btnRegister = findViewById(R.id.RegisterBtn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = userName.getText().toString();
                password = Password.getText().toString();
                Log.d(TAG, "onClick- username:" +username);
                Log.d(TAG, "onClick- password:" +password);
                if (username.isEmpty() || password.isEmpty()) {
                    Log.d(TAG, "onClick: username is empty");
                    Toast.makeText(LoginView.this, "Username / Password Required", Toast.LENGTH_SHORT).show();
                } else {
                    mActivityViewModel.setlogin(username, password, this);
                    login();
                }
            }
        });
    }

    private boolean validateUsername(){
        String usernameInput = userName.getText().toString().trim();

        if(usernameInput.isEmpty()){
            userName.setError("Field can't empty");
            return false;
        } else if(usernameInput.length() > 3) {
            userName.setError("username too short");
            return false;
        } else {
            userName.setError(null);
            return true;
        }
    }


    public void saveUser(String username, String accountNo, String token){
        editor.putString("username", username);
        editor.putString("accountNo", accountNo);
        editor.putString("token", token);

        editor.apply();

    }

    public void login(){
        mActivityViewModel.getDataLogin().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse.getError() != null){
                    Toast.makeText(LoginView.this, loginResponse.getError(),
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    saveUser(loginResponse.getUsername(),loginResponse.getAccountNo(), loginResponse.getToken());
                    Intent intentMain = new Intent(LoginView.this, MainView.class);
                    startActivity(intentMain);
                }
            }
        });
    }
}