package online.ghuri.com.beacondoctorapp.Login.ViewModel;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import online.ghuri.com.beacondoctorapp.Login.Presenter.SignUpPresenter;
import online.ghuri.com.beacondoctorapp.Login.Repository.LoginRegistrationDbHandler;
import online.ghuri.com.beacondoctorapp.R;

/**
 * Created by Tanvir on 2/14/2018.
 */

public class SignUpDialog extends DialogFragment implements  SignUpViewModel{
    Spinner mrSpinner;
    Spinner daySpinner,monthSpinner, yearSpinner;
    EditText mFullNameET,mEmailET,mMobileET,mPassET,mBmdcET;
    SignUpPresenter mPresenter;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_sign_up,null,false);
        Button createButton=view.findViewById(R.id.create_account_btn);

        mPresenter=new SignUpPresenter(new LoginRegistrationDbHandler(getActivity()),this);

        mrSpinner=view.findViewById(R.id.mr_spinner);
        daySpinner=view.findViewById(R.id.day_spiiner);
        monthSpinner=view.findViewById(R.id.month_spiiner);
        yearSpinner=view.findViewById(R.id.year_spinner);

        mFullNameET=view.findViewById(R.id.full_name_edit_text);
        mEmailET=view.findViewById(R.id.email_edit_text);
        mMobileET=view.findViewById(R.id.mobile_no_edit_text);
        mPassET=view.findViewById(R.id.password_edit_text);
        mBmdcET=view.findViewById(R.id.bmdc_edit_text);




        List<String> mrList,dayList,monthList,yearList;

        mrList= new LinkedList<>(Arrays.asList("Mr","Mrs"));
        monthList=new LinkedList<>(Arrays.asList("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"));

        dayList=new ArrayList<>();
        yearList=new ArrayList<>();

        for (int i=1;i<=31;i++){
            dayList.add(i+"");
        }

        for (int i=1965;i<=2000;i++){
            yearList.add(i+"");
        }

        ArrayAdapter<String> msAdapter = new ArrayAdapter<String>(getActivity(),R.layout.view_spinner,mrList);
//        msAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mrSpinner.setAdapter(msAdapter);

        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.view_spinner,dayList);
//        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(getActivity(),R.layout.view_spinner,monthList);
//        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(getActivity(),R.layout.view_spinner,yearList);
//        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);





        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mPresenter.onClickCreateBtn();
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    @Override
    public String getMrSpinnnerTxt() {

        return mrSpinner.getSelectedItem().toString();
    }

    @Override
    public String getNameText() {
       return mFullNameET.getText().toString();
    }

    @Override
    public String getMobileText() {
        return mMobileET.getText().toString();

    }

    @Override
    public String getPassText() {
        return mPassET.getText().toString();

    }

    @Override
    public String getEmailText() {
        return mEmailET.getText().toString();

    }

    @Override
    public String getMonthSpinnerText() {
        return monthSpinner.getSelectedItem().toString();

    }

    @Override
    public String getdaySpinnnerText() {
        return daySpinner.getSelectedItem().toString();
    }

    @Override
    public String getyearSpinnerText() {
        return yearSpinner.getSelectedItem().toString();
    }

    @Override
    public String getBmdcRegNoText() {
        return mBmdcET.getText().toString();

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeDialog() {
        getDialog().dismiss();
    }
}
