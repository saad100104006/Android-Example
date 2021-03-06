package online.ghuri.com.beacondoctorapp.Profile.ViewModel;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import online.ghuri.com.beacondoctorapp.R;

public class ProfileEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (fragment==null){
            fragment=new ProfileEditFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }
    }
}
