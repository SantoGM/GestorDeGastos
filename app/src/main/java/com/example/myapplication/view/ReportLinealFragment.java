package com.example.myapplication.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.ExpenseFacade;
import com.example.myapplication.view.extras.DatePickerFragment;
import com.example.myapplication.view.pojo.PaymenyPojo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import im.dacer.androidcharts.LineView;



public class ReportLinealFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Date since = addMonth(Calendar.getInstance().getTime(),-1);
    private Date until = Calendar.getInstance().getTime();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int randomint = 9;
    private LineView lineViewFloat;
    private OnFragmentInteractionListener mListener;
    Switch aSwitch;

    public ReportLinealFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportLinealFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportLinealFragment newInstance(String param1, String param2) {
        ReportLinealFragment fragment = new ReportLinealFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private Date addMonth(Date date, int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, amount);

        return calendar.getTime();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_report_lineal, container, false);
        // Inflate the layout for this fragment
        lineViewFloat =  inflate.findViewById(R.id.line_view_float);
        LinearLayout contenedor_filtros = inflate.findViewById(R.id.filtro_cat);
        final EditText txtDateFrom = inflate.findViewById(R.id.txtDateFrom);
        final EditText txtDateTo = inflate.findViewById(R.id.txtDateTo);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtDateFrom.setText(simpleDateFormat.format(since));
        txtDateTo.setText(simpleDateFormat.format(until));
        initLineView(lineViewFloat);
        loadData( lineViewFloat,contenedor_filtros);




        txtDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.txtDateFrom:
                        showDatePickerDialog(txtDateFrom);
                        break;
                }
            }
        });

        txtDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.txtDateTo:
                        showDatePickerDialog(txtDateTo);
                        break;
                }
            }
        });




        return inflate;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
  //                  + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
    private void showDatePickerDialog(final EditText txtDate) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = twoDigits(day) + "/" + twoDigits(month+1) + "/" + year;
                txtDate.setText(selectedDate);
            }

            private String twoDigits(int n) {
                return (n<=9) ? ("0"+n) : String.valueOf(n);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");
    }

    private void loadData( LineView lineViewFloat,LinearLayout contenedor_filtros ) {

        aSwitch = new  Switch(getContext());
        HashMap<String,ArrayList<Float>> datos = new HashMap<>();

        List<PaymenyPojo> payments = ExpenseFacade.getInstance().getExpensesBetween(since, until, getContext());


        LinealReportAdapter Lineal = new LinealReportAdapter(payments);

        ArrayList<ArrayList<Float>> dataListFs = new ArrayList<>();
        ArrayList<String> fechas = new ArrayList<>();

        for(String key : Lineal.getDataArray().keySet()) {
            Log.e("key",key);
            dataListFs.add(Lineal.getDataArray().get(key).values);
            aSwitch.setText(key);
            aSwitch.setChecked(true);
            aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.e("pasa color","color");
                    //lineViewFloat.setColorArray(new int[] {
                    //        Color.parseColor("#FFFFFF"), Color.parseColor("#9C27B0"),
                    //       Color.parseColor("#2196F3")
                    // });
                    //loadData( lineViewFloat,aSwitch);
                }
            });

            contenedor_filtros.addView(aSwitch);








        }
        //int dias=(int) ((since.getTime()-until.getTime())/86400000)*-1;
        int mayor=0;
        for (ArrayList<Float> expense:dataListFs){
            if(mayor<expense.size()){
                mayor=expense.size();
            }
        }
        for(int i=1;i<=mayor;i++){
            fechas.add(getString(R.string.report_expense_label)+" "+i);

        }
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
    class LinealReportAdapter{
        public HashMap<String,DataLine> data = new HashMap<>();
        private ArrayList<ArrayList<Float>> expenses = new ArrayList<>();
        public HashMap<String,DataLine> getDataArray(){
            return data;
        }
        LinealReportAdapter(List<PaymenyPojo> paymenyPojos){


            Iterator it =paymenyPojos.iterator();
            while (it.hasNext()){
                PaymenyPojo paymenyPojo = (PaymenyPojo) it.next();





                try{
                    Log.e("LinealReportAdapterACA",paymenyPojo.getDate().toString());
                    data.get(paymenyPojo.getCategory().getName()).values.add(paymenyPojo.getAmount());
                    data.get(paymenyPojo.getCategory().getName()).fechas.add(paymenyPojo.getDate().toString());
                }catch (Exception e){
                    Log.e("LinealReportAdapterACA",paymenyPojo.getDate().toString());
                    DataLine dataLine= new DataLine();
                    dataLine.descripcion = paymenyPojo.getCategory().getName();
                    dataLine.values.add(paymenyPojo.getAmount());
                    dataLine.fechas.add(paymenyPojo.getDate().toString());

                    data.put(paymenyPojo.getCategory().getName(),dataLine);
                }



            }
        }
        class DataLine{
            public String descripcion;
            public ArrayList<Float> values = new ArrayList<>();
            public ArrayList<String> fechas = new ArrayList<>();
        }
    }
}
