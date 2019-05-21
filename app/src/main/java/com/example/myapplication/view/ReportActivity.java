package com.example.myapplication.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.myapplication.R;

import java.util.ArrayList;

import im.dacer.androidcharts.LineView;


public class ReportActivity extends AppCompatActivity {
    int randomint = 9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        final LineView lineView = (LineView) findViewById(R.id.line_view);
        final LineView lineViewFloat = (LineView) findViewById(R.id.line_view_float);

        initLineView(lineView);
        initLineView(lineViewFloat);
        Button lineButton = (Button) findViewById(R.id.line_button);
        lineButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                randomSet(lineView, lineViewFloat);
            }
        });

        randomSet(lineView, lineViewFloat);
    }

    private void initLineView(LineView lineView) {
        ArrayList<String> test = new ArrayList<String>();
        for (int i = 0; i < randomint; i++) {
            test.add(String.valueOf(i + 1));
        }
        lineView.setBottomTextList(test);
        lineView.setColorArray(new int[] {
                Color.parseColor("#F44336"), Color.parseColor("#9C27B0"),
                Color.parseColor("#2196F3"), Color.parseColor("#009688")
        });
        lineView.setDrawDotLine(true);
        lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);
    }

    private void randomSet(LineView lineView, LineView lineViewFloat) {
        ArrayList<Integer> dataList = new ArrayList<>();
        float random = (float) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataList.add((int) (Math.random() * random));

        }

        ArrayList<Integer> dataList2 = new ArrayList<>();
        random = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataList2.add((int) (Math.random() * random));
        }

        ArrayList<Integer> dataList3 = new ArrayList<>();
        random = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataList3.add((int) (Math.random() * random));
        }

        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<>();
        dataLists.add(dataList);
        dataLists.add(dataList2);
        dataLists.add(dataList3);

        lineView.setDataList(dataLists);
        ArrayList<String> fechas = new ArrayList<>();
        fechas.add("dia 1");
        fechas.add("dia 2");
        fechas.add("dia 3");
        fechas.add("dia 4");
        fechas.add("dia 5");
        fechas.add("dia 6");
        fechas.add("dia 7");
        fechas.add("dia 8");
        fechas.add("dia 9");
        lineView.setBottomTextList(fechas);

        ArrayList<Float> dataListF = new ArrayList<>();
        float randomF = (float) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataListF.add((float) (Math.random() * randomF));
        }

        ArrayList<Float> dataListF2 = new ArrayList<>();
        randomF = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataListF2.add((float) (Math.random() * randomF));
        }

        ArrayList<Float> dataListF3 = new ArrayList<>();
        randomF = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataListF3.add((float) (Math.random() * randomF));
        }

        ArrayList<ArrayList<Float>> dataListFs = new ArrayList<>();
        dataListFs.add(dataListF);
        dataListFs.add(dataListF2);
        dataListFs.add(dataListF3);
        lineViewFloat.setBottomTextList(fechas);
        lineViewFloat.setFloatDataList(dataListFs);
    }
}
