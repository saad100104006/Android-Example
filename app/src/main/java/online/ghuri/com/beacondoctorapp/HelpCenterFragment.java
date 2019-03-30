package online.ghuri.com.beacondoctorapp;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import online.ghuri.com.beacondoctorapp.databinding.FragmentHelpCenterBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class HelpCenterFragment extends Fragment {
    private FragmentHelpCenterBinding mBinding;
    public HelpCenterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_help_center, container, false);
        return mBinding.getRoot();
    }

}
