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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.MovementsFacade;
import com.example.myapplication.view.extras.DateHelper;
import com.example.myapplication.view.extras.DatePickerFragment;
import com.example.myapplication.view.pojo.PaymentPojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import im.dacer.androidcharts.PieView;


public class ReportCakeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Date since = addMonth(Calendar.getInstance().getTime(),-1);
    private Date until = Calendar.getInstance().getTime();

    private ListView listview;
    private OnFragmentInteractionListener mListener;
    Switch aSwitch;

    public ReportCakeFragment() {
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
    public static ReportCakeFragment newInstance(String param1, String param2) {
        ReportCakeFragment fragment = new ReportCakeFragment();
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
        final View inflate = inflater.inflate(R.layout.fragment_report_cake, container, false);
        // Inflate the layout for this fragment

        //PieView  piechart = new PieView  (getContext());
        final LinearLayout filterContainer = inflate.findViewById(R.id.filtro_cat);
        final LinearLayout colorContainer = inflate.findViewById(R.id.colors_cont);
        final EditText txtDateFrom = inflate.findViewById(R.id.txtDateFrom);
        final EditText txtDateTo = inflate.findViewById(R.id.txtDateTo);
        final Button button_filter = inflate.findViewById(R.id.button_filter);
        final HorizontalScrollView cont = inflate.findViewById(R.id.horizontalScrollViewFloat);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final List<PaymentPojo> payments = MovementsFacade.getInstance().getExpensesBetween(since, until, getContext());
        //final ResumeReportAdapter Lineal = new ResumeReportAdapter(payments,pieChartView,colorContainer);
        //cont.removeAllViews();
        //cont.addView(pieChartView);

        txtDateFrom.setText(simpleDateFormat.format(since));
        txtDateTo.setText(simpleDateFormat.format(until));



        final ResumeReportAdapter Lineal = new ResumeReportAdapter(payments,colorContainer,getContext(), R.layout.list_item);
        listview = inflate.findViewById(R.id.list_view);
        Lineal.loadData();

        listview.setAdapter(Lineal);


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
                    PieView pieView = new PieView(getContext());
                    List<PaymentPojo> paymentsLocal = MovementsFacade.getInstance().getExpensesBetweenAndCats(categories,since, until,getContext());
                    ResumeReportAdapter Lineal = new ResumeReportAdapter(paymentsLocal,colorContainer,getContext(), R.layout.list_item);

//                    cont.removeAllViews();
 ///                   cont.addView(pieView);

                    listview = inflate.findViewById(R.id.list_view);
                    Lineal.loadData();

                    listview.setAdapter(Lineal);
                  //  Lineal.loadData();
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






    class ResumeReportAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
        private final HashMap<String,DataLine> data = new HashMap<>();
        private final HashMap<String,DataLine> dataHidden = new HashMap<>();

        private final ArrayList<PaymentPojo> paymentPojos;
        private final List<Switch> options = new ArrayList<>();
        private final LinearLayout containerColors;

        private LinearLayout containerButtons;
        private PieView pieViewAdap;
        private ArrayList<ArrayList<Float>> dataListFs = new ArrayList<>();
        private Context context;
        private int layout;


        int[] colors = new int[] {Color.parseColor("#F44336"), Color.parseColor("#F44336"), Color.parseColor("#F44336"), Color.parseColor("#F44336"), Color.parseColor("#F44336"), Color.parseColor("#F44336") };
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


        ResumeReportAdapter(List<PaymentPojo> paymentPojos, LinearLayout containerColors, Context context, int layout){


            this.paymentPojos =  new ArrayList<>(paymentPojos);

            this.containerColors=containerColors;
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
            Log.e("pasa color",buttonView.getText().toString());

        }

        private void loadData() {



        }
        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            //Inflamos la vista con nuestro propio layout
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());

            View v = layoutInflater.inflate(R.layout.list_item, null);
            // Valor actual según la posición

            PaymentPojo currentPayment  = paymentPojos.get(position);

            // Referenciamos el elemento a modificar y lo rellenamos
            TextView text_date = v.findViewById(R.id.txt_fecha);
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            TextView text_cat = v.findViewById(R.id.text_cat);
            TextView text_desc = v.findViewById(R.id.text_desc);
            TextView text_mount = v.findViewById(R.id.text_mount);

            text_date.setText(simpleDateFormat.format(currentPayment.getDate()));
            text_cat.setText(currentPayment.getCategory().getName().trim());
            text_desc.setText(currentPayment.getDetail());
            text_mount.setText("$" + currentPayment.getAmount().toString());
            //Devolvemos la vista inflada
            return v;
        }


        @Override
        public int getCount() {
            return this.paymentPojos.size();
        }

        @Override
        public Object getItem(int position) {
            return this.paymentPojos.get(position);
        }

        @Override
        public long getItemId(int id) {
            return id;
        }



        class DataLine{
            public String descripcion;
            public final ArrayList<Float> values = new ArrayList<>();
            public final ArrayList<String> fechas = new ArrayList<>();
        }
    }
}
