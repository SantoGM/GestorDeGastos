package com.example.myapplication.view.extras;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;

import java.util.Calendar;
import java.util.Objects;

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;
    private Boolean nfd;

    /***
     * Crea una nueva instancia de DatePickerFragment con su correspondiente DatePickerDialog para seleccionar una fecha
     * que será asignada a un TextView
     * @param listener
     *      El Listener para mi DatePickerDialog que se relacionará con el TextView, ejemplo de como declararlo:
     *      new DatePickerDialog.OnDateSetListener() {
     *             @Override
     *             public void onDateSet(DatePicker datePicker, int year, int month, int day) {
     *                 final String selectedDate = DateHelper.toStringDDMMYYYY(day, month, year);
     *                 txtDate.setText(selectedDate);
     *             }
     *         }
     * @param noFutureDates
     *      Indica si el diálogo debe permitir agregar fechas futuras o no
     *      TRUE -> No se pueden elegir fechas postareiores a la actual
     *      FALSE -> Se puede seleccionar cualquier fecha
     * @return
     */
    public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener, Boolean noFutureDates) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(listener);
        fragment.setNoFutureDates(noFutureDates);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    public void setNoFutureDates(Boolean noFutureDates){
        nfd = noFutureDates;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        Dialog result = new DatePickerDialog(Objects.requireNonNull(getActivity()), listener, year, month, day);
        if (nfd){
            ((DatePickerDialog) result).getDatePicker().setMaxDate(System.currentTimeMillis());
        }
        return result;
    }
}
