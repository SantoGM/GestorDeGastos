package com.example.myapplication.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.MovementsFacade;
import com.example.myapplication.view.extras.DateHelper;
import com.example.myapplication.view.extras.DatePickerFragment;
import com.example.myapplication.view.extras.BarViewExtended;
import com.example.myapplication.view.pojo.PaymentPojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;




public class ReportBarFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Date since = addMonth(Calendar.getInstance().getTime(),-1);
    private Date until = Calendar.getInstance().getTime();

    private BarViewExtended barView;
    private OnFragmentInteractionListener mListener;
    Switch aSwitch;

    public ReportBarFragment() {
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
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_report_lineal, container, false);
        // Inflate the layout for this fragment
        //lineViewFloat =  inflate.findViewById(R.id.line_view_float);
        barView = new BarViewExtended(getContext());
        final LinearLayout filterContainer = inflate.findViewById(R.id.filtro_cat);
        final LinearLayout colorContainer = inflate.findViewById(R.id.colors_cont);
        final EditText txtDateFrom = inflate.findViewById(R.id.txtDateFrom);
        final EditText txtDateTo = inflate.findViewById(R.id.txtDateTo);
        final Button button_filter = inflate.findViewById(R.id.button_filter);
        final HorizontalScrollView cont = inflate.findViewById(R.id.horizontalScrollViewFloat);
        cont.removeAllViews();
        cont.addView(barView);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtDateFrom.setText(simpleDateFormat.format(since));
        txtDateTo.setText(simpleDateFormat.format(until));

        List<PaymentPojo> payments = MovementsFacade.getInstance().getExpensesBetween(since, until, getContext());
        final BarReportAdapter Lineal = new BarReportAdapter(payments,barView,colorContainer);

        Lineal.loadData();
        filterContainer.removeAllViewsInLayout();
        for  (Switch s: Lineal.getOptions())
        {
            filterContainer.addView(s);
        }


        button_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> categories = new ArrayList<>();
                for (Switch s:Lineal.getOptions()) {
                    if(!s.isChecked())
                        categories.add(s.getText().toString());

                }
                try {
                    since = simpleDateFormat.parse(txtDateFrom.getText().toString());
                    until = simpleDateFormat.parse(txtDateTo.getText().toString());

                    Log.e("since",txtDateFrom.getText().toString());
                    Log.e("until",txtDateTo.getText().toString());
                    barView = new BarViewExtended(getContext());
                    List<PaymentPojo> paymentsLocal = MovementsFacade.getInstance().getExpensesBetweenAndCats(categories,since, until,getContext());
                    BarReportAdapter Lineal = new BarReportAdapter(paymentsLocal, barView, colorContainer);

                    cont.removeAllViews();
                    cont.addView(barView);
                    Lineal.loadData();
                }catch (ParseException e){
                    Log.e("ReportLinealFragment",e.getMessage());


                }


            }
        });

        txtDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.txtDateFrom) {
                    showDatePickerDialog(txtDateFrom);
                }
            }
        });

        txtDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.txtDateTo) {
                    showDatePickerDialog(txtDateTo);
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

    private void showDatePickerDialog(final EditText txtDate) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = DateHelper.toStringDDMMYYYY(day, month, year);
                txtDate.setText(selectedDate);

            }
        }, Boolean.FALSE);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    class BarReportAdapter implements CompoundButton.OnCheckedChangeListener {
        private final HashMap<String,DataLine> data = new HashMap<>();
        private final HashMap<String,DataLine> dataHidden = new HashMap<>();
        private final BarViewExtended barView;
        private List<PaymentPojo> paymentPojos;
        private final List<Switch> options = new ArrayList<>();
        private final LinearLayout containerColors;

        private LinearLayout containerButtons;

        private ArrayList<ArrayList<Float>> dataListFs = new ArrayList<>();

        final int[] colors = new int[] {Color.parseColor("#F44336"), Color.parseColor("#9C27B0"), Color.parseColor("#2196F3"), Color.parseColor("#009688"), Color.parseColor("#FFCC33"), Color.parseColor("#FF6633") };
        public HashMap<String,DataLine> getDataArray(){
            return data;
        }
        public HashMap<String,DataLine> getDataHiddenArray(){
            return dataHidden;
        }

        private void fillDataHolder(){
            data.clear();
            dataHidden.clear();

            for (PaymentPojo paymentPojo : paymentPojos) {
                try {
                    Log.e("LinealReportAdapterACA", paymentPojo.getDate().toString());
                    this.data.get(paymentPojo.getCategory().getName()).values.add(paymentPojo.getAmount());
                    this.data.get(paymentPojo.getCategory().getName()).fechas.add(paymentPojo.getDate().toString());
                } catch (Exception e) {
                    Log.e("LinealReportAdapterACA", paymentPojo.getDate().toString());
                    DataLine dataLine = new DataLine();
                    dataLine.descripcion = paymentPojo.getCategory().getName();
                    dataLine.values.add(paymentPojo.getAmount());
                    dataLine.fechas.add(paymentPojo.getDate().toString());

                    this.data.put(paymentPojo.getCategory().getName(), dataLine);
                }


            }

        }

        public void setPaymentPojo(List<PaymentPojo> payments){
            this.paymentPojos =payments;
        }

        BarReportAdapter(List<PaymentPojo> paymentPojos, BarViewExtended barView, LinearLayout containerColors ){
            setPaymentPojo(paymentPojos);
            this.barView = barView;

            this.containerColors = containerColors;
            fillDataHolder();

            for(String key : getDataArray().keySet()) {
                Log.e("key",key);
                aSwitch = new Switch(getContext());
                aSwitch.setText(key);

                aSwitch.setChecked(true);
                aSwitch.setOnCheckedChangeListener(this);
                //containerButtons.addView(aSwitch);
                options.add(aSwitch);
            }
        }

        public List<Switch> getOptions(){
            return options;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.e("pasa color", buttonView.getText().toString());

        }
        private void initLineView() {

        }

        private void addColor(int color, String tipo){

            TextView color_desc = new TextView(getContext());
            color_desc.setTextColor(colors[color]);
            color_desc.setText(tipo);
            color_desc.setPadding(20,0,0,0);
            containerColors.addView(color_desc);

        }

        private void loadData() {

            initLineView();
            dataListFs = new ArrayList<>();
            containerColors.removeAllViewsInLayout();

            ArrayList<String> buttonLabels = new ArrayList<>();
            ArrayList<String> dates = new ArrayList<>();
            int j = 0;
            for(String key : getDataArray().keySet()) {
                Log.e("key",key);
                buttonLabels.add(key);

                dataListFs.add(getDataArray().get(key).values);
                addColor(j,key);
                j++;
            }

            int mayor=0;
            for (ArrayList<Float> expense:dataListFs){
                if(mayor<expense.size()){
                    mayor=expense.size();
                }
            }

            ArrayList<Float> barDataList = new ArrayList<>();
            float total = 0;
            for (List<Float> cat:dataListFs) {
                float acum =0;
                for (Float expense: cat) {
                    acum=acum+expense;
                }
                barDataList.add(acum);
                total=acum+total;
            }

            ArrayList<Integer> porcent = new ArrayList<>();
            for (Float acum_expense :barDataList) {
                porcent.add(Math.round(acum_expense*100/total));
                dates.add(String.valueOf(Math.round(acum_expense*100/total)));

            }

            barView.setDataList(porcent,100);
            barView.setBottomTextList(dates);
            barView.setColors(new int[] {Color.parseColor("#F44336"), Color.parseColor("#9C27B0"), Color.parseColor("#2196F3"), Color.parseColor("#009688"), Color.parseColor("#FFCC33"), Color.parseColor("#FF6633") });
        }


        class DataLine{
            public String descripcion;
            public final ArrayList<Float> values = new ArrayList<>();
            public final ArrayList<String> fechas = new ArrayList<>();
        }
    }
}
