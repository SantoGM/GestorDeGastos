package com.example.myapplication.view.extras;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.view.pojo.AccountPojo;

import java.util.List;
import java.util.Objects;

import static com.example.myapplication.R.layout.composte_line_account;

public class AccountsListAdapter extends ArrayAdapter {
    private final Context context;
    private final List<AccountPojo> values;

    @SuppressWarnings("unchecked")
    public AccountsListAdapter(Context context, @NonNull List<AccountPojo> values) {
        super(context, -1, Objects.requireNonNull(values.toArray()));
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            // Inflate View
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(composte_line_account, parent, Boolean.FALSE);
        }

        // Obtain Text Views
        TextView name = convertView.findViewById(R.id.accountListItemName);
        TextView descrip = convertView.findViewById(R.id.accountListItemDescrip);
        TextView balance = convertView.findViewById(R.id.accountListItemBalance);


        // Set the values
        AccountPojo data = values.get(position);

        name.setText(data.getName());
        descrip.setText(data.getDescription());
        balance.setText(context.getString(R.string.preffix_cash, data.getBalance().toString()));

        return convertView;
    }
}
