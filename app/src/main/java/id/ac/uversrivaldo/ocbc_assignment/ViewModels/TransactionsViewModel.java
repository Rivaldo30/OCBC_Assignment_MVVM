package id.ac.uversrivaldo.ocbc_assignment.ViewModels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.uversrivaldo.ocbc_assignment.Api.ApiClient;
import id.ac.uversrivaldo.ocbc_assignment.Api.ApiService;
import id.ac.uversrivaldo.ocbc_assignment.Model.Transaction;
import id.ac.uversrivaldo.ocbc_assignment.Model.TransactionsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionsViewModel extends ViewModel {

    private MutableLiveData<TransactionsResponse> mTransaction = new MutableLiveData<>();

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
                Log.e("onFailureLogin", t.getMessage());
            }
        });
    }

    public MutableLiveData<TransactionsResponse> getmTransaction(){
        return mTransaction;
    }

    public void setDataDummy(TransactionsResponse transactionsResponse){
        mTransaction.postValue(transactionsResponse);
    }

}
