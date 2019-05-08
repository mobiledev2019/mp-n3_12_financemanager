package com.example.financeproject.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.financeproject.R;
import com.example.financeproject.models.Expense;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {
    private List<Expense> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        public TextView expenseNameTextView;
        public TextView expenseAmountTextView;
        public TextView expenseCategoryTextView;;
        public TextView expenseDateTextView;
        public MyViewHolder(View v) {
            super(v);
            layout = v;
            expenseNameTextView = v.findViewById(R.id.name_textview);
            expenseAmountTextView = v.findViewById(R.id.amount_textview);
            expenseCategoryTextView = v.findViewById(R.id.category_textview);
            expenseDateTextView = v.findViewById(R.id.date_textview);
        }
    }

    public ExpenseAdapter(List<Expense> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ExpenseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_item_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.expenseNameTextView.setText(mDataset.get(position).getName());

        NumberFormat formatter = new DecimalFormat("#,###");
        String amount = formatter.format(mDataset.get(position).getAmount());
        holder.expenseAmountTextView.setText(amount);

        holder.expenseCategoryTextView.setText(mDataset.get(position).getCategory());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.expenseDateTextView.setText(dateFormat.format(mDataset.get(position).getDate()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
