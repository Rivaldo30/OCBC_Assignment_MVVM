package id.ac.uversrivaldo.ocbc_assignment.ViewModels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.uversrivaldo.ocbc_assignment.Api.ApiClient;
import id.ac.uversrivaldo.ocbc_assignment.Api.ApiService;
import id.ac.uversrivaldo.ocbc_assignment.Model.BalanceResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceViewModel extends ViewModel {

    private MutableLiveData<BalanceResponse> mBalance = new MutableLiveData<>();

    //set Api for Balance
    public void setBalance(Context context) {
        ApiService apiService = ApiClient.getRetrofit(context).create(ApiService.class);
        Call<BalanceResponse> balanceResponseCall = apiService.balance();
        balanceResponseCall.enqueue(new Callback<BalanceResponse>() {
            @Override
            public void onResponse(Call<BalanceResponse> call, Response<BalanceResponse> response) {
                mBalance.postValue((BalanceResponse) response.body());
            }

            @Override
            public void onFailure(Call<BalanceResponse> call, Throwable t) {
                Log.e("onFailureLogin", t.getMessage());
            }
        });
    }

    public MutableLiveData<BalanceResponse> getmBalance(){
        return mBalance;
    }

    public void setDataDummy(BalanceResponse balanceResponse){
        mBalance.postValue(balanceResponse);
    }

}
