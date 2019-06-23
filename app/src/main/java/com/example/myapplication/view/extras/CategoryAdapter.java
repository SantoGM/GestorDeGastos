package com.example.myapplication.view.extras;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.view.pojo.CategoryPojo;

import java.util.List;
import java.util.Objects;

import static com.example.myapplication.R.layout.composte_line_account;
import static com.example.myapplication.R.layout.elv_parent_account;

public class CategoryAdapter extends ArrayAdapter {
    private final Context context;
    private final List<CategoryPojo> values;

    @SuppressWarnings("unchecked")
    public CategoryAdapter(Context context, @NonNull List<CategoryPojo> values) {
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
            convertView = inflater.inflate(elv_parent_account, parent, Boolean.FALSE);
        }

        // Obtain Text Views
        TextView name = convertView.findViewById(R.id.tvParentAccount);


        // Set the values
        CategoryPojo data = values.get(position);

        name.setText(data.nameToShow());

        return convertView;
    }
}
