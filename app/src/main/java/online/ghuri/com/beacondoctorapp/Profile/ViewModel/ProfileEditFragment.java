package online.ghuri.com.beacondoctorapp.Profile.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import online.ghuri.com.beacondoctorapp.Profile.Presenter.ProfilePresenter;
import online.ghuri.com.beacondoctorapp.Profile.Repository.ProfileDBHandler;
import online.ghuri.com.beacondoctorapp.Profile.Repository.User;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.databinding.FragmentProfileBinding;

import static android.app.Activity.RESULT_OK;


public class ProfileEditFragment extends Fragment implements ProfileViewModel {
    private FragmentProfileBinding mBinding;
    private ProfilePresenter mPresenter;
    CircleImageView mProfileImg;
    public ProfileEditFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false);

        mPresenter=new ProfilePresenter(this,new ProfileDBHandler(getActivity()));
        mPresenter.getProfileInfo();
      //  Realm realm=Realm.getDefaultInstance();



        mBinding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()

                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setCropShape(CropImageView.CropShape.OVAL)
                        .setBorderLineColor(Color.RED)
                        .setGuidelinesColor(Color.GREEN)
                        .start(getActivity(),getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container));

            }
        });

                mBinding.degreeResultTv.addTextChangedListener(watcher);
        mBinding.designationResultTv.addTextChangedListener(watcher);
        mBinding.departmentResultTv.addTextChangedListener(watcher);
        mBinding.instituteResultTv.addTextChangedListener(watcher);
        mBinding.chamber1ResultTv.addTextChangedListener(watcher);
        mBinding.chamber2ResultTv.addTextChangedListener(watcher);
        mBinding.phoneNumberResultTv.addTextChangedListener(watcher);
        mBinding.emailResultTv.addTextChangedListener(watcher);
        mBinding.birthdayResultTv.addTextChangedListener(watcher);
        mBinding.marriageDayResultTv.addTextChangedListener(watcher);
        mBinding.facebookLinkResultTv.addTextChangedListener(watcher);

        return mBinding.getRoot();
    }


    TextWatcher watcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mPresenter.userInfoChanged();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("result_test","access outside");
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            Log.i("result_test","access inside");

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {



                Log.i("result_test","access inside 111");


                Uri resultUri = result.getUri();

                Drawable yourDrawable;

                try {
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(resultUri);
                  /*  BufferedInputStream stream=new BufferedInputStream(inputStream);
                    Bitmap bitmap=BitmapFactory.decodeStream(stream);
                    mPresenter.setProfilePic(bitmap);*/
                    yourDrawable = Drawable.createFromStream(inputStream, resultUri.toString() );
                    Bitmap bitmap=   ((BitmapDrawable) yourDrawable).getBitmap();
                    ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                    byte[] byteData=outputStream.toByteArray();
                    mPresenter.saveUserProfilePic(byteData);


                } catch (FileNotFoundException e) {
                    yourDrawable = getResources().getDrawable(R.mipmap.ic_launcher_round);
                }



              mBinding.profileImage.setImageDrawable(yourDrawable);






            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void setUserData(User user) {
        mBinding.setUserInfo(user);




    }

    @Override
    public String getDegree() {
        return mBinding.degreeResultTv.getText().toString();
    }

    @Override
    public String getDesig() {
        return mBinding.designationResultTv.getText().toString();
    }

    @Override
    public String getDepartment() {
        return mBinding.departmentResultTv.getText().toString();
    }

    @Override
    public String getInstitute() {
        return mBinding.instituteResultTv.getText().toString();
    }

    @Override
    public String getChamber1() {
        return mBinding.chamber1ResultTv.getText().toString();
    }

    @Override
    public String getChamber2() {
        return mBinding.chamber2ResultTv.getText().toString();
    }

    @Override
    public String getPhoneNumber() {
        return mBinding.phoneNumberResultTv.getText().toString();
    }

    @Override
    public String getEmail() {
        return mBinding.emailResultTv.getText().toString();
    }

    @Override
    public String getBirthDay() {
        return mBinding.birthdayResultTv.getText().toString();
    }

    @Override
    public String getMarriageDay() {
        return mBinding.marriageDayResultTv.getText().toString();
    }

    @Override
    public String getFaccebookLink() {
        return mBinding.facebookLinkResultTv.getText().toString();
    }

    @Override
    public void openEditProfileFragment() {

    }

    @Override
    public void finishActivity() {

    }

    @Override
    public void setProfilePic(Bitmap bitmap) {
        mBinding.profileImage.setImageBitmap(bitmap);
    }

}
