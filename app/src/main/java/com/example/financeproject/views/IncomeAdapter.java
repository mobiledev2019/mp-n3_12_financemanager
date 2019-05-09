package com.example.financeproject.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.financeproject.R;
import com.example.financeproject.models.Income;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.MyViewHolder> {
    private List<Income> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        public TextView incomeNameTextView;
        public TextView incomeAmountTextView;
        public TextView incomeDateTextView;
        public MyViewHolder(View v) {
            super(v);
            layout = v;
            incomeNameTextView = v.findViewById(R.id.name_textview);
            incomeAmountTextView = v.findViewById(R.id.amount_textview);
            incomeDateTextView = v.findViewById(R.id.date_textview);
        }
    }

    public IncomeAdapter(List<Income> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public IncomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.income_item_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.incomeNameTextView.setText(mDataset.get(position).getName());

        NumberFormat formatter = new DecimalFormat("#,###");
        String amount = formatter.format(mDataset.get(position).getAmount());
        holder.incomeAmountTextView.setText(amount);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.incomeDateTextView.setText(dateFormat.format(mDataset.get(position).getDate()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
