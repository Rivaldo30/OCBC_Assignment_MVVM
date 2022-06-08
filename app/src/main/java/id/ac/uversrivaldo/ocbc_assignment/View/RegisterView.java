package id.ac.uversrivaldo.ocbc_assignment.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.ac.uversrivaldo.ocbc_assignment.R;

public class RegisterView extends AppCompatActivity {

    public static String URL_REGIST = "https://green-thumb-64168.uc.r.appspot.com/register";
    public static final String SHARED_PREF_NAME = "my_shared_pref";
    EditText userNameRegister, passwordRegister, confirmPasswordRegister;
    Button  buttonRegister;
    ImageView buttonBack;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//
//        userNameRegister = findViewById(R.id.usernameRegister);
//        passwordRegister = findViewById(R.id.passwordRegisterOne);
//        confirmPasswordRegister = findViewById(R.id.passwordRegisterTwo);
//
//        buttonBack = findViewById(R.id.btnBack);
//        buttonRegister = findViewById(R.id.btnRegister);
//        buttonBack.setOnClickListener(this);
//        buttonRegister.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v == buttonBack){
//            Intent back = new Intent(RegisterView.this, LoginView.class);
//            startActivity(back);
//        } else if (v == buttonRegister) {
//            String username = userNameRegister.getText().toString().trim();
//            String password = passwordRegister.getText().toString().trim();
//            String cfpassword = confirmPasswordRegister.getText().toString().trim();
//
//            if (!username.isEmpty() && !password.isEmpty() && !cfpassword.isEmpty()){
//                if(password.equals(cfpassword)) Register();
//                else confirmPasswordRegister.setError("Confirm password is wrong");
//            } else {
//                userNameRegister.setError("Fill out this field");
//                passwordRegister.setError("Fill out this field");
//                confirmPasswordRegister.setError("Fill out this field");
//            }
//        }
//    }
//
//    private void Register() {
//        final String username = userNameRegister.getText().toString().trim();
//        final String password = passwordRegister.getText().toString().trim();
//        final String cfpassword = confirmPasswordRegister.getText().toString().trim();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            String status = jsonObject.getString("status");
//                            String pesan = jsonObject.getString("pesan");
//                            if (status.equals("success")) {
//                                Toast.makeText(RegisterView.this, pesan, Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(RegisterView.this, pesan, Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(RegisterView.this, "Register error" + e.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError e) {
//                        Toast.makeText(RegisterView.this, "Register error" + e.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
//
//        protected Map<String, String> getParams() throw AuthFailureError {
//            Map<String , String> params = new HashMap<>();
//            params.put("username", username);
//            params.put("password", password);
//            params.put("confirm passowrd", cfpassword);
//            return params;
//        }
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
    }
}