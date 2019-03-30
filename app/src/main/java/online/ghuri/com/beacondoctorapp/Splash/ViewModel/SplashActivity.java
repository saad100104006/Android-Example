package online.ghuri.com.beacondoctorapp.Splash.ViewModel;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import online.ghuri.com.beacondoctorapp.HomeActivity;
import online.ghuri.com.beacondoctorapp.Login.ViewModel.MainActivity;
import online.ghuri.com.beacondoctorapp.Profile.Repository.ProfileDBHandler;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.SecondHomeActivity;
import online.ghuri.com.beacondoctorapp.Splash.Presenter.SplashPresenter;

public class SplashActivity extends AppCompatActivity implements SplashViewModel{
    private SplashPresenter mSplashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSplashPresenter=new SplashPresenter(this,new ProfileDBHandler(this));
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void openLoginActivity() {
        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }.start();
    }

    @Override
    public void openHomeActivity() {
        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this,SecondHomeActivity.class));
                finish();
            }
        }.start();
    }
}
