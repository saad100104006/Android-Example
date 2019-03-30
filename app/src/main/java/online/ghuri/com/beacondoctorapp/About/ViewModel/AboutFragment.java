package online.ghuri.com.beacondoctorapp.About.ViewModel;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import online.ghuri.com.beacondoctorapp.About.Presenter.AboutPresenter;
import online.ghuri.com.beacondoctorapp.About.Repository.AboutDbHandler;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.databinding.FragmentAboutBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements AboutViewModel {



private FragmentAboutBinding mBinding;
    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_about, container, false);

        AboutPresenter presenter=new AboutPresenter(this,new AboutDbHandler(getActivity()));



        return mBinding.getRoot();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void exitProgressBar() {

    }

    @Override
    public void showAboutText(String aboutTxt) {
        mBinding.aboutTv.setText(aboutTxt);
    }

    @Override
    public void showAboutpic(String url) {
        Glide.with(getActivity()).load(url).thumbnail(0.1f).into(mBinding.aboutImageView);
    }
}
