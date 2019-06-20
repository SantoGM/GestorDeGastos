package com.example.myapplication.view.extras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.view.pojo.PaymentPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableCategoryAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> listAccounts;
    private Map<String, List<PaymentPojo>> listExpenses;
    private Context context;
    private Map<Integer, Integer> colorMap;

    public ExpandableCategoryAdapter(ArrayList<String> listAccounts, Map<String, List<PaymentPojo>> listExpenses, Context context) {
        this.listAccounts = listAccounts;
        this.listExpenses = listExpenses;
        this.context = context;
        buildColorMap();
    }

    private void buildColorMap() {
        colorMap = new HashMap<>();

        int c1 = context.getResources().getColor(R.color.colorBckg1);
        int c2 = context.getResources().getColor(R.color.colorBckg2);
        int c3 = context.getResources().getColor(R.color.colorBckg3);
        int c4 = context.getResources().getColor(R.color.colorBckg4);
        int c5 = context.getResources().getColor(R.color.colorBckg5);

        colorMap.put(0, c1);
        colorMap.put(1, c2);
        colorMap.put(2, c3);
        colorMap.put(3, c4);
        colorMap.put(4, c5);
    }

    @Override
    public int getGroupCount() {
        return listAccounts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int result = listExpenses.get(listAccounts.get(groupPosition)).size();
        if (result == 0) {
            result = 1;
        }
        return result;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listAccounts.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listExpenses.get( listAccounts.get(groupPosition) ).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String account = (String) getGroup(groupPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.elv_parent_account, null);
        TextView tv = convertView.findViewById(R.id.tvParentAccount);
        tv.setText(account);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // Inflate View
        convertView = LayoutInflater.from(context).inflate(R.layout.composte_line_adapter, null);

        // Obtain Text Views
        TextView category = convertView.findViewById(R.id.expenseListItemCategory);
        TextView date = convertView.findViewById(R.id.expenseListItemDate);
        TextView detail = convertView.findViewById(R.id.expenseListItemDetail);
        TextView amount = convertView.findViewById(R.id.expenseListItemAmount);

        if (listExpenses.get( listAccounts.get(groupPosition) ).size() == 0) {
            category.setText("No registra gastos");
            category.setTextSize(20);
            date.setText("");
            detail.setText("");
            amount.setText("");

            date.setBackgroundResource(android.R.color.transparent);
        } else {
            // Set data
            PaymentPojo data = (PaymentPojo) getChild(groupPosition, childPosition);

            category.setText(data.getCategory().getName());
            date.setText(DateHelper.toStringDDMM(data.getDate()));
            detail.setText(data.getDetail());
            amount.setText("$ " + data.getAmount());

            date.setBackgroundColor(colorMap.get(childPosition));
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
