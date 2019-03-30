package online.ghuri.com.beacondoctorapp.Profile.ViewModel;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import online.ghuri.com.beacondoctorapp.Profile.Presenter.ProfilePresenter;
import online.ghuri.com.beacondoctorapp.Profile.Repository.ProfileDBHandler;
import online.ghuri.com.beacondoctorapp.Profile.Repository.User;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.databinding.FragmentNewProfileBinding;

import static online.ghuri.com.beacondoctorapp.Profile.ViewModel.ProfileTabFragment.BASIC_INDEX;
import static online.ghuri.com.beacondoctorapp.Profile.ViewModel.ProfileTabFragment.EDUCATIONAL_INDEX;
import static online.ghuri.com.beacondoctorapp.Profile.ViewModel.ProfileTabFragment.PROFESSIONAL_INDEX;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewProfileFragment extends Fragment implements ProfileViewModel {
        private FragmentNewProfileBinding mBinding;
        private ProfilePresenter mPresenter;
        private User mUser;

    public NewProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_new_profile, container, false);
        mPresenter=new ProfilePresenter(this,new ProfileDBHandler(getActivity()));


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


        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getProfileInfo();
    }

    @Override
    public void setUserData(User user) {
        mBinding.setProfile(user);
        mUser=user;
        ProfilePagerAdapter adapter=new ProfilePagerAdapter(getChildFragmentManager());
        mBinding.viewpager.setAdapter(adapter);
        mBinding.tablayout.setupWithViewPager(mBinding.viewpager);
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

    }

    @Override
    public void setProfilePic(Bitmap bitmap) {
        mBinding.profileImage.setImageBitmap(bitmap);
    }


    public class ProfilePagerAdapter extends FragmentStatePagerAdapter {


        public ProfilePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ProfileTabFragment fragment=new ProfileTabFragment();
            fragment.initFragment(mUser,position);


            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case BASIC_INDEX:
                    return "Basic";
                case PROFESSIONAL_INDEX:
                    return "Professional";
                case EDUCATIONAL_INDEX:
                    return "Educational";
            }

            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
