package com.example.financeproject.views;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.financeproject.AppDatabase;
import com.example.financeproject.R;
import com.example.financeproject.models.Expense;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class ChartFragment extends Fragment {
    private PieChart chart;

    private Date date;

    public ChartFragment() {
        // Required empty public constructor
    }

    public static ChartFragment newInstance(Date date) {
        ChartFragment fragment = new ChartFragment();
        fragment.setDate(date);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chart, container, false);

        chart = root.findViewById(R.id.chart);

        refreshChart(date);

        return root;
    }

    public List<Expense> getItems(Date date, String category) {
        if (date != null) {
            AppDatabase db = AppDatabase.getAppDatabase(getContext());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            long firstDay = calendar.getTime().getTime();

            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            long lastDay = calendar.getTime().getTime();

            return db.expenseDao().getAllByCategoryFromMonth(
                    category,
                    firstDay,
                    lastDay);
        }
        return new ArrayList<>();
    }

    public void refreshChart(Date date) {
        chart.clear();
        String[] categoriesList = getResources().getStringArray(R.array.categories_array);
        List<PieEntry> entries = new ArrayList<>();

        for (String category : categoriesList) {
            List<Expense> expenses = getItems(date, category);
            if (expenses.size() != 0) {
                Double sum = 0.0;
                for (Expense expense : expenses) {
                    sum += expense.getAmount();
                }
                entries.add(new PieEntry((float) sum.doubleValue(), category));
            }
        }

        PieDataSet dataSet = new PieDataSet(entries, "Thống kê chi tiêu");
        dataSet.setColors(
                Color.parseColor("#00ACC1"),
                Color.parseColor("#FF7043"),
                Color.parseColor("#4CAF50"),
                Color.parseColor("#EF5350"),
                Color.parseColor("#26A69A"),
                Color.parseColor("#FF9800")
        );
        PieData data = new PieData();
        data.setDataSet(dataSet);
        chart.setData(data);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
