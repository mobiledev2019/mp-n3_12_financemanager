package com.example.financeproject;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.financeproject.models.Expense;

import java.util.Date;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText contentEditText;
    private Spinner categorySpinner;
    private EditText amountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        categorySpinner = findViewById(R.id.spinner);
        Button btn = findViewById(R.id.button);
        contentEditText = findViewById(R.id.content_edittext);
        amountEditText = findViewById(R.id.amount_edittext);

        getSupportActionBar().setTitle(R.string.add_item_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        final AppDatabase db = AppDatabase.getAppDatabase(AddExpenseActivity.this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Expense expense = new Expense();
                expense.setAmount(Double.parseDouble(amountEditText.getText().toString()));
                expense.setCategory((String) categorySpinner.getSelectedItem());
                expense.setDate(new Date());
                expense.setName(contentEditText.getText().toString());
                db.expenseDao().insert(expense);

                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
