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

import id.ac.uversrivaldo.ocbc_assignment.Model.Transaction;
import id.ac.uversrivaldo.ocbc_assignment.Model.TransactionsResponse;
import id.ac.uversrivaldo.ocbc_assignment.R;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionAdapterVH> {

    public List<Transaction> transactionList;
    public Context context;
    public int size = 0;


    public TransactionAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    public TransactionAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
        notifyDataSetChanged();
    }

//    public void setTransactionList(List<Transaction> transactionList){
//        this.transactionList = transactionList;
//        notifyDataSetChanged();
//    }


    @NonNull
    @Override
    public TransactionAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_transaction, parent, false);
        return new TransactionAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapterVH holder, int position) {

//        holder.accountHolder.setText(this.transactionList.get(position).getReceipient().getAccountHolder().toString());
//        holder.accountNo.setText(this.transactionList.get(position).getReceipient().getAccountNo());
//        holder.amount.setText((int) this.transactionList.get(position).getAmount());


        Object transaction = transactionList.get(position);
        LinkedTreeMap<Object,Object> t = (LinkedTreeMap) transaction;

        Object receipient = t.get("receipient");
        LinkedTreeMap<Object,Object> r = (LinkedTreeMap) receipient;
        Double amount = (Double) t.get("amount");
        String accountHolder = r.get("accountHolder").toString();
        String accountNo = r.get("accountNo").toString();

        Log.e("accountNo", accountNo);
        Log.d("TAG", "onBindViewHolder: amount" +amount);


        holder.amount.setText(amount.toString());
        holder.accountHolder.setText(accountHolder);
        holder.accountNo.setText(accountNo);
    }

    @Override
    public int getItemCount() {
        if(this.transactionList != null){
            return this.transactionList.size();
        }
        return 0;
    }


    public class TransactionAdapterVH extends RecyclerView.ViewHolder {

        TextView accountHolder;
        TextView accountNo;
        TextView amount;

        public TransactionAdapterVH(@NonNull View itemView) {
            super(itemView);

            accountHolder = itemView.findViewById(R.id.accountHolder);
            accountNo = itemView.findViewById(R.id.accountNo);
            amount = itemView.findViewById(R.id.Amount);

        }
    }


}

