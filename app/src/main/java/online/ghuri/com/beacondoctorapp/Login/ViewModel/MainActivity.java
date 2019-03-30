package online.ghuri.com.beacondoctorapp.Login.ViewModel;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import online.ghuri.com.beacondoctorapp.HomeActivity;
import online.ghuri.com.beacondoctorapp.Login.Presenter.LoginPresenter;
import online.ghuri.com.beacondoctorapp.Login.Repository.LoginRegistrationDbHandler;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.SecondHomeActivity;

public class MainActivity extends AppCompatActivity implements  LoginViewModel {
        private LoginPresenter mLoginPresenter;
        private EditText mEmailET,mPassET;
        private ImageButton mPassShowImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoginPresenter=new LoginPresenter(new LoginRegistrationDbHandler(this),this);

        Button signButton=findViewById(R.id.sign_in_btn);
        TextView signUpView=findViewById(R.id.sign_up_tv);
        mEmailET=findViewById(R.id.email_edit_text);
        mPassET=findViewById(R.id.password_edit_text);
        mPassShowImageButton=findViewById(R.id.password_image_btn);

        mPassShowImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.onClickPassShowImageButton();
            }
        });
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.onClickSignInBtn();
            }
        });


        signUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignUpDialog dialog=new SignUpDialog();
                dialog.show(getSupportFragmentManager(),null);
            }
        });

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void endProgressBar() {

    }



    @Override
    public String getEmailText() {
        return mEmailET.getText().toString();
    }

    @Override
    public String getPasswordText() {
        return mPassET.getText().toString();
    }

    @Override
    public void openHomeActivity() {
        startActivity(new Intent(MainActivity.this,SecondHomeActivity.class));
        finish();

    }

    @Override
    public void showPassword() {
        mPassET.setTransformationMethod(null);
        mPassShowImageButton.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_remove_red_eye_black_24dp));
    }

    @Override
    public void hidePassword() {
        mPassShowImageButton.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_remove_red_eye_white_24dp));
        mPassET.setTransformationMethod(new PasswordTransformationMethod());
    }

    @Override
    public void setPassCursor(int length) {
        mPassET.setSelection(length);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
