package id.ac.uversrivaldo.ocbc_assignment.View.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.uversrivaldo.ocbc_assignment.Api.ApiClient;
import id.ac.uversrivaldo.ocbc_assignment.Model.BalanceResponse;
import id.ac.uversrivaldo.ocbc_assignment.R;
import id.ac.uversrivaldo.ocbc_assignment.Model.Transaction;
import id.ac.uversrivaldo.ocbc_assignment.Adapter.TransactionAdapter;
import id.ac.uversrivaldo.ocbc_assignment.Model.TransactionsResponse;
import id.ac.uversrivaldo.ocbc_assignment.viewmodels.MainActivityViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREF_NAME = "my_shared_pref";
    TextView sgDollar, accNumber, accName;
    RecyclerView recyclerView;
    TransactionAdapter transactionAdapter;
    List<Transaction> transactionList = new ArrayList<>();
    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sgDollar = findViewById(R.id.sgDollar);
        accNumber = findViewById(R.id.accountNumber);
        accName = findViewById(R.id.accountName);
        recyclerView = findViewById(R.id.recycleView);

        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.getTransaction();

        balance();

        transactions();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        accName.setText("" + sharedPreferences.getString("username", null));
        accNumber.setText("" + sharedPreferences.getString("accountNo", null));




    }

    public void balance() {

        Call<BalanceResponse> balanceResponseCall = ApiClient.getApiService(getApplicationContext()).balance();
        balanceResponseCall.enqueue(new Callback<BalanceResponse>() {
            @Override
            public void onResponse(Call<BalanceResponse> call, Response<BalanceResponse> response) {
                if (response.isSuccessful()) {

                    Log.e("Balance", String.valueOf(response.body().getBalance()));

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            BalanceResponse loginResponse = response.body();

                            sgDollar.setText("SGD " + loginResponse.getBalance());

                        }
                    }, 50);
                } else {
                    Toast.makeText(MainActivity.this, "Failed get balance from server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BalanceResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void transactions() {

        Call<TransactionsResponse> transactionsResponseCall = ApiClient.getApiService(getApplicationContext()).transactions();
        transactionsResponseCall.enqueue(new Callback<TransactionsResponse>() {
            @Override
            public void onResponse(Call<TransactionsResponse> call, Response<TransactionsResponse> response) {
                if (response.isSuccessful()) {

                    Log.e("Transaction", String.valueOf(response.body().getData()));

                    transactionAdapter = new TransactionAdapter(transactionList);
                    recyclerView.setAdapter(transactionAdapter);

                    transactionList.addAll(response.body().getData());

                    transactionAdapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<TransactionsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}