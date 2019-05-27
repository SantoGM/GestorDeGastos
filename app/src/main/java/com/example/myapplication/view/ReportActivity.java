package com.example.myapplication.view;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.myapplication.R;
import java.util.ArrayList;
import im.dacer.androidcharts.LineView;


public class ReportActivity extends BaseActivity  {
    int randomint = 9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        createMenu();

        ViewPager vp_pages= (ViewPager) findViewById(R.id.vp_pages);
        PagerAdapter pagerAdapter=new FragmentAdapter(getSupportFragmentManager());
        vp_pages.setAdapter(pagerAdapter);

        TabLayout tbl_pages= (TabLayout) findViewById(R.id.tab_menu);
        tbl_pages.setupWithViewPager(vp_pages);


        //final LineView lineView = findViewById(R.id.line_view);
        //final LineView lineViewFloat = findViewById(R.id.line_view_float);

        //initLineView(lineView);
        //initLineView(lineViewFloat);
        /*
        Button lineButton = (Button) findViewById(R.id.line_button);
        lineButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
               // randomSet(lineView, lineViewFloat);
            }
        });

       // randomSet(lineView, lineViewFloat);

        android.app.Fragment fragment = new ReportCakeFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        fragmentTransaction.add(R.id.reportFragment,fragment);
        fragmentTransaction.commit();
        */

    }

    private void initLineView(LineView lineView) {
        ArrayList<String> test = new ArrayList<>();
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
        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<>();

        ArrayList<Integer> dataList = generarRandom();
        ArrayList<Integer> dataList2 = generarRandom();
        ArrayList<Integer> dataList3 = generarRandom();
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





        dataLists.add(dataList);
        dataLists.add(dataList2);
        dataLists.add(dataList3);

        lineView.setBottomTextList(fechas);
        lineView.setDataList(dataLists);


        ArrayList<Float> dataListF = generarRandomFloat();

        ArrayList<Float> dataListF2 = generarRandomFloat();

        ArrayList<Float> dataListF3 = generarRandomFloat();

        ArrayList<ArrayList<Float>> dataListFs = new ArrayList<>();
        dataListFs.add(dataListF);
        dataListFs.add(dataListF2);
        dataListFs.add(dataListF3);
        lineViewFloat.setBottomTextList(fechas);
        lineViewFloat.setFloatDataList(dataListFs);
    }




    private ArrayList<Integer> generarRandom(){
        ArrayList<Integer> dataList = new ArrayList<>();
        float random = (float) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataList.add((int) (Math.random() * random));

        }
        return dataList;

    }
    private ArrayList<Float> generarRandomFloat(){
    ArrayList<Float> dataListF = new ArrayList<>();
    float randomF = (float) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
        dataListF.add((float) (Math.random() * randomF));
    }
        return dataListF;

    }


    class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new ReportLinealFragment();
                case 1:
                    return new ReportCakeFragment();
                case 2:
                    return new ReportBarFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                //
                //Your tab titles
                //
                case 0:return getString(R.string.report_evolution_expenses);
                case 1:return getString(R.string.report_expense_by_cat);
                case 2: return getString(R.string.report_expense_by_date);
                default:return null;
            }
        }
    }

}
