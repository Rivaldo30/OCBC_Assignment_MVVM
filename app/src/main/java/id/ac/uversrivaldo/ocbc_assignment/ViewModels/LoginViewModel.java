package id.ac.uversrivaldo.ocbc_assignment.ViewModels;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

import id.ac.uversrivaldo.ocbc_assignment.Api.ApiClient;
import id.ac.uversrivaldo.ocbc_assignment.Model.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginResponse> mLoginLiveData = new MutableLiveData<>();

    public void setlogin(String username, String password, View.OnClickListener context){
        // set function for login request
        LoginResponse loginData = new LoginResponse();
        loginData.setUsername(username);
        loginData.setPassword(password);

        Call<LoginResponse> loginResponseCall = ApiClient.getApiServiceNoAuth().userLogin(loginData);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = new LoginResponse();
                if (response.isSuccessful()) {
                    loginResponse.setToken(response.body().getToken());
                    loginResponse.setUsername(response.body().getUsername());
                    loginResponse.setAccountNo(response.body().getAccountNo());
                    mLoginLiveData.postValue(loginResponse);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        loginResponse.setError(jObjError.getString("error"));
                        Log.d("TAG", "onResponse - error: "+loginResponse.getError().toString());
                        mLoginLiveData.postValue(loginResponse);
                    } catch (Exception e) {
                        Log.e("TAG", "onResponse:"+e.getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("onFailureLogin", t.getMessage());
            }
        });
    }

    public LiveData<LoginResponse> getDataLogin(){
        return mLoginLiveData;
    }
    
    public void setDataDummy(LoginResponse loginResponse){
        mLoginLiveData.postValue(loginResponse);
    }
}