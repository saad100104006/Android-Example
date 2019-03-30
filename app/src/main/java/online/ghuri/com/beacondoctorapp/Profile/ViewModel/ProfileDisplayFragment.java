package online.ghuri.com.beacondoctorapp.Profile.ViewModel;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import online.ghuri.com.beacondoctorapp.Profile.Presenter.ProfileDisplayPresenter;
import online.ghuri.com.beacondoctorapp.Profile.Presenter.ProfilePresenter;
import online.ghuri.com.beacondoctorapp.Profile.Repository.ProfileDBHandler;
import online.ghuri.com.beacondoctorapp.Profile.Repository.User;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.databinding.FragmentProfileDisplayBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileDisplayFragment extends Fragment implements ProfileViewModel {

    private FragmentProfileDisplayBinding mBinding;
    private ProfileDisplayPresenter mPresenter;


    public ProfileDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_profile_display, container, false);
        mPresenter=new ProfileDisplayPresenter(this,new ProfileDBHandler(getActivity()));

        mBinding.settingImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onSettingBtnClick();
            }
        });

        mBinding.backImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.backImageBtnClick();
            }
        });
        setListenerToRootView();

        return mBinding.getRoot();
    }

    boolean isOpened = false;

    public void setListenerToRootView() {
        final View activityRootView = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if (heightDiff > 100) { // 99% of the time the height diff will be due to a keyboard.
                    Toast.makeText(getActivity(), "Gotcha!!! softKeyboardup", Toast.LENGTH_LONG).show();

                    if (isOpened == false) {
                        //Do two things, make the view top visible and the editText smaller
                    }
                    isOpened = true;
                } else if (isOpened == true) {
                    Toast.makeText(getActivity(), "softkeyborad Down!!!", Toast.LENGTH_SHORT).show();


                    isOpened = false;
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getProfileInfo();
    }

    @Override
    public void setUserData(User user) {
        mBinding.setProfile(user);
    }

    @Override
    public String getDegree() {
        return null;
    }

    @Override
    public String getDesig() {
        return null;
    }

    @Override
    public String getDepartment() {
        return null;
    }

    @Override
    public String getInstitute() {
        return null;
    }

    @Override
    public String getChamber1() {
        return null;
    }

    @Override
    public String getChamber2() {
        return null;
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getBirthDay() {
        return null;
    }

    @Override
    public String getMarriageDay() {
        return null;
    }

    @Override
    public String getFaccebookLink() {
        return null;
    }

    @Override
    public void openEditProfileFragment() {
        startActivity(new Intent(getActivity(),ProfileEditActivity.class));
    }

    @Override
    public void finishActivity() {
        getActivity().finish();
    }

    @Override
    public void setProfilePic(Bitmap bitmap) {
        mBinding.profileImage.setImageBitmap(bitmap);
    }
}
