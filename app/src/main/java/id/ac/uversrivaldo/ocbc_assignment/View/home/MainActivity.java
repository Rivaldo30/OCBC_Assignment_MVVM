package id.ac.uversrivaldo.ocbc_assignment.View.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
    TextView sgDollar, accNumber, accName, noResutlt;
    RecyclerView recyclerView;
    TransactionAdapter transactionAdapter;
    List<Transaction> transactionList = new ArrayList<>();
    private MainActivityViewModel mMainActivityViewModel;
    private List<TransactionsResponse> transactions;
    private TransactionAdapter adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        sgDollar = findViewById(R.id.sgDollar);
        accNumber = findViewById(R.id.accountNumber);
        accName = findViewById(R.id.accountName);
        noResutlt = findViewById(R.id.noResultTv);
        recyclerView = findViewById(R.id.recycleView);


        adapter = new TransactionAdapter(this, transactionList);
        recyclerView.setAdapter(adapter);

        //intisialisasi ViewModel
        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        balance();
        transactions();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        accName.setText("" + sharedPreferences.getString("username", null));
        accNumber.setText("" + sharedPreferences.getString("accountNo", null));
    }

    public void balance(){
        mMainActivityViewModel.setBalance(this);
        mMainActivityViewModel.getmBalance().observe(this, new Observer<BalanceResponse>() {
            @Override
            public void onChanged(BalanceResponse balanceResponse) {
                if (balanceResponse.getStatus().isEmpty()){
                    noResutlt.setVisibility(View.VISIBLE);
                } else {
                    Log.d("TAG", "onChanged: Balance"+balanceResponse);
                    BalanceResponse loginResponse = balanceResponse;
                    sgDollar.setText("SGD " + loginResponse.getBalance());
                }
            }
        });
    }

    //model-ViewModel
    public void transactions(){
        mMainActivityViewModel.setTransaction(this);
        mMainActivityViewModel.getmTransactionObserver().observe(this, new Observer<TransactionsResponse>() {
            @Override
            public void onChanged(TransactionsResponse transactionsResponse) {
                if (transactionsResponse.getData().isEmpty()) {

                    noResutlt.setVisibility(View.VISIBLE);
                } else {
                    transactionAdapter = new TransactionAdapter(transactionList);
                    recyclerView.setAdapter(transactionAdapter);
                    transactionList.addAll(transactionsResponse.getData());
                    transactionAdapter.notifyDataSetChanged();
                    noResutlt.setVisibility(View.GONE);
                }
            }
        });
    }
}