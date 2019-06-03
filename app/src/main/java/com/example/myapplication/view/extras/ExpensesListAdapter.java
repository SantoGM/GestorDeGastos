package com.example.myapplication.view.extras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.view.pojo.PaymentPojo;

import java.util.ArrayList;
import java.util.List;

public class ExpensesListAdapter extends ArrayAdapter {
    private final Context context;
    private final List<PaymentPojo> values;

    public ExpensesListAdapter(Context context, List<PaymentPojo> values) {
        super(context, -1, values.toArray());
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate View
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.composte_line_adapter, parent, false);

        // Obtain Text Views
        TextView category = rowView.findViewById(R.id.expenseListItemCategory);
        TextView date = rowView.findViewById(R.id.expenseListItemDate);
        TextView detail = rowView.findViewById(R.id.expenseListItemDetail);
        TextView amount = rowView.findViewById(R.id.expenseListItemAmount);

        // Set the values
        PaymentPojo data = values.get(position);

        category.setText(data.getCategory().getName());
        date.setText(DateHelper.toStringDDMM(data.getDate()));
        detail.setText(data.getDetail());
        amount.setText("$ " + data.getAmount());

        return rowView;
    }
}
