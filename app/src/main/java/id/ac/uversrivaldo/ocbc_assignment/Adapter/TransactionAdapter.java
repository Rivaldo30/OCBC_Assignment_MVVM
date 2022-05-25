package id.ac.uversrivaldo.ocbc_assignment.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import id.ac.uversrivaldo.ocbc_assignment.R;
import id.ac.uversrivaldo.ocbc_assignment.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionAdapterVH> {

    public List<Transaction> transactionList;
    public Context context;
    public int size = 0;


    public TransactionAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }


    @NonNull
    @Override
    public TransactionAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_transaction, parent, false);
        return new TransactionAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapterVH holder, int position) {


        Object transaction = transactionList.get(position);
        LinkedTreeMap<Object,Object> t = (LinkedTreeMap) transaction;

        Object receipient = t.get("receipient");
        LinkedTreeMap<Object,Object> r = (LinkedTreeMap) receipient;
        String accountHolder = r.get("accountHolder").toString();
        String accountNo = r.get("accountNo").toString();

        Log.e("accountNo", accountNo);



        holder.accountHolder.setText(accountHolder);
        holder.accountNo.setText(accountNo);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }


    public class TransactionAdapterVH extends RecyclerView.ViewHolder {

        TextView accountHolder;
        TextView accountNo;

        public TransactionAdapterVH(@NonNull View itemView) {
            super(itemView);

            accountHolder = itemView.findViewById(R.id.accountHolder);
            accountNo = itemView.findViewById(R.id.accountNo);

        }
    }


}

