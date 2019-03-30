package online.ghuri.com.beacondoctorapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import online.ghuri.com.beacondoctorapp.Login.ViewModel.MainActivity;
import online.ghuri.com.beacondoctorapp.Profile.ViewModel.ProfileDisplayActivity;
import online.ghuri.com.beacondoctorapp.databinding.ActivitySecondHomeBinding;

public class SecondHomeActivity extends AppCompatActivity {

    ActivitySecondHomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_second_home);


        mBinding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // openActivity("Flexibac",R.drawable.flexibac);
                startActivity(new Intent(SecondHomeActivity.this, ProfileDisplayActivity.class));

            }
        });

        mBinding.flexibacImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("Flexibac",R.drawable.flexibac);
            }
        });
        mBinding.journalImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("Journal",R.drawable.journal);
            }
        });
        mBinding.medicalDictionaryImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("Medical Dictionary",R.drawable.medical_dictionary);
            }
        });
        mBinding.gbImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("Generic And Brand",R.drawable.generci_brand);
            }
        });
        mBinding.helpImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("Help Centre",R.drawable.help_centre);
            }
        });
        mBinding.aboutImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("About",R.drawable.about);
            }
        });
        mBinding.logoutImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openActivity("flexibac",R.drawable.flexibac);
                Preferences.setToken(SecondHomeActivity.this,null);
                startActivity(new Intent(SecondHomeActivity.this, MainActivity.class));
                finish();
            }
        });




    }

    private void openActivity(String title, int drawableId) {
        startActivity(new Intent(this,HomeActivity.class)
        .putExtra(HomeActivity.TOOLBAR_TITLE_EXTRA,title)
        .putExtra(HomeActivity.TOOLBAR_DRAWABLE_ID_EXTRA,drawableId));

    }
}
