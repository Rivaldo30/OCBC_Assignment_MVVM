package id.ac.uversrivaldo.ocbc_assignment.viewmodels;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

import id.ac.uversrivaldo.ocbc_assignment.Api.ApiClient;
import id.ac.uversrivaldo.ocbc_assignment.Api.ApiService;
import id.ac.uversrivaldo.ocbc_assignment.Model.BalanceResponse;
import id.ac.uversrivaldo.ocbc_assignment.Model.LoginResponse;
import id.ac.uversrivaldo.ocbc_assignment.Model.TransactionsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<TransactionsResponse> mTransaction = new MutableLiveData<>();
    private MutableLiveData<BalanceResponse> mBalance = new MutableLiveData<>();
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

    public MutableLiveData<BalanceResponse> getmBalance(){
        return mBalance;
    }
    //set Api for Balance
    public void setBalance(Context context){
        ApiService apiService = ApiClient.getRetrofit(context).create(ApiService.class);
        Call<BalanceResponse> balanceResponseCall = apiService.balance();
        balanceResponseCall.enqueue(new Callback<BalanceResponse>() {
            @Override
            public void onResponse(Call<BalanceResponse> call, Response<BalanceResponse> response) {
                mBalance.postValue((BalanceResponse) response.body());
            }

            @Override
            public void onFailure(Call<BalanceResponse> call, Throwable t) {
                mBalance.postValue(null);
            }
        });
    }

    public MutableLiveData<TransactionsResponse> getmTransactionObserver(){
        return mTransaction;
    }

    //set Api for Transaction
    public void setTransaction(Context context){
        ApiService apiService = ApiClient.getRetrofit(context).create(ApiService.class);
        Call<TransactionsResponse> transactionsResponseCall = apiService.transactions();
        transactionsResponseCall.enqueue(new Callback<TransactionsResponse>() {
            @Override
            public void onResponse(Call<TransactionsResponse> call, Response<TransactionsResponse> response) {
                mTransaction.postValue((TransactionsResponse) response.body());
            }

            @Override
            public void onFailure(Call<TransactionsResponse> call, Throwable t) {
                mTransaction.postValue(null);
            }
        });
    }

    public LiveData<LoginResponse> getDataLogin(){
        return mLoginLiveData;
    }
}