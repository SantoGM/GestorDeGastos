package com.example.myapplication.view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import java.util.ArrayList;

import im.dacer.androidcharts.LineView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReportLinealFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReportLinealFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportLinealFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int randomint = 9;
    private OnFragmentInteractionListener mListener;

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
        final LineView lineViewFloat =  inflate.findViewById(R.id.line_view_float);
        initLineView(lineViewFloat);
        randomSet( lineViewFloat);
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

    private void randomSet( LineView lineViewFloat) {
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
}
