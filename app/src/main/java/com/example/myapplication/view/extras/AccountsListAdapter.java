package com.example.myapplication.view.extras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.view.pojo.AccountPojo;
import com.example.myapplication.view.pojo.PaymentPojo;

import java.util.ArrayList;
import java.util.List;

public class AccountsListAdapter extends ArrayAdapter {
    private final Context context;
    private final List<AccountPojo> values;

    public AccountsListAdapter(Context context, List<AccountPojo> values) {
        super(context, -1, values.toArray());
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate View
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.composte_line_account, parent, false);

        // Obtain Text Views
        TextView name = rowView.findViewById(R.id.accountListItemName);
        TextView descrip = rowView.findViewById(R.id.accountListItemDescrip);
        TextView balance = rowView.findViewById(R.id.accountListItemBalance);


        // Set the values
        AccountPojo data = values.get(position);

        name.setText(data.getName());
        descrip.setText(data.getDescription());
        balance.setText("$ " + data.getBalance());

        return rowView;
    }
}
