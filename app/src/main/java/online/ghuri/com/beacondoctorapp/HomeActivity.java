package online.ghuri.com.beacondoctorapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import online.ghuri.com.beacondoctorapp.About.ViewModel.AboutFragment;
import online.ghuri.com.beacondoctorapp.ConferenceNews.ViewModel.ConferanceFragment;
import online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.ViewModel.SearchMedicineFragment;
import online.ghuri.com.beacondoctorapp.Dictionary.View.DictionaryFragment;
import online.ghuri.com.beacondoctorapp.JournalCategory.ViewModel.JournalFragment;
import online.ghuri.com.beacondoctorapp.Login.ViewModel.MainActivity;
import online.ghuri.com.beacondoctorapp.Profile.Repository.ProfileDBHandler;
import online.ghuri.com.beacondoctorapp.Profile.ViewModel.ProfileDisplayActivity;
import online.ghuri.com.beacondoctorapp.databinding.ActivityHomeBinding;
import online.ghuri.com.beacondoctorapp.databinding.DrawerHeaderViewBinding;

public class HomeActivity extends AppCompatActivity implements JournalFragment.FragmentCallback {
    public static final String TOOLBAR_TITLE_EXTRA = "toolbar_title";
    public static final String TOOLBAR_DRAWABLE_ID_EXTRA = "toolbar_drawable_id_extra";
    private ActivityHomeBinding mBinding;
    private DrawerHeaderViewBinding headerViewBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mBinding= DataBindingUtil. setContentView(this,R.layout.activity_home);

       String toolbarTitle=getIntent().getStringExtra(TOOLBAR_TITLE_EXTRA);
       int toolbarDrawableId=getIntent().getIntExtra(TOOLBAR_DRAWABLE_ID_EXTRA,R.drawable.about);

        mBinding.toolbarTitle.setText(toolbarTitle);
        mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,toolbarDrawableId));



            if (toolbarTitle.equals("Journal")){
                openFragment(new JournalFragment());

            }else if (toolbarTitle.equals("Conference News")){
                openFragment(new ConferanceFragment());
            }else if(toolbarTitle.equals("News Paper")){
                openFragment(new NewsPaperFragment());
            }else if(toolbarTitle.equals("Current Affairs")){
                openFragment(new CurrentAffairFragment());
            }
            else if(toolbarTitle.equals("Medical Dictionary")){
                openFragment(new DictionaryFragment());
            }
            else if(toolbarTitle.equals("Generic And Brand")){
                openFragment(new SearchMedicineFragment());
            }
            else if(toolbarTitle.equals("Help Centre")){
                openFragment(new HelpCenterFragment());
            } else if(toolbarTitle.equals("About")){
                openFragment(new AboutFragment());
            } else if(toolbarTitle.equals("Flexibac")){
                openFragment(new WebViewFragment());
            }




            mBinding.backImgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // finish();
//                    if (getSupportFragmentManager().getFragments().size()>1){
//                        getSupportFragmentManager().popBackStack();
//                    }else
//                        finish();

                    if (!getSupportFragmentManager().popBackStackImmediate())
                        finish();
                }
            });





    headerViewBinding=DataBindingUtil.inflate(getLayoutInflater(),R.layout.drawer_header_view,mBinding.navigationView,false);
        mBinding.navigationView.addHeaderView(headerViewBinding.getRoot());


        headerViewBinding.profileImageCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileFragment();

            }
        });

        headerViewBinding.profileText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileFragment();
            }
        });

        headerViewBinding.profileText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileFragment();
            }
        });


        headerViewBinding.profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileFragment();
            }
        });


        headerViewBinding.journalCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("Journal");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.journal));
                openFragment(new JournalFragment());
            }
        });

        headerViewBinding.journalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("Journal");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.journal));
                openFragment(new JournalFragment());
            }
        });

        headerViewBinding.journalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("Journal");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.journal));
                openFragment(new JournalFragment());
            }
        });

        headerViewBinding.conferenceCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("Conference News");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.conference_news));
                openFragment(new ConferanceFragment());
            }
        });

        headerViewBinding.conferenceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("Conference News");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.conference_news));
                openFragment(new ConferanceFragment());
            }
        });


        headerViewBinding.conferenceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("Conference News");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.conference_news));
                openFragment(new ConferanceFragment());
            }
        });

        headerViewBinding.newsPaperCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("News Paper");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.news_paper));
                openFragment(new NewsPaperFragment());
            }
        });

        headerViewBinding.newsPaperText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("News Paper");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.news_paper));
                openFragment(new NewsPaperFragment());
            }
        });


        headerViewBinding.newsPaperView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("News Paper");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.news_paper));
                openFragment(new NewsPaperFragment());
            }
        });


        headerViewBinding.currentAffairCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("Current Affairs");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.current_affairs));
                openFragment(new CurrentAffairFragment());
            }
        });

        headerViewBinding.currentAffairText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("Current Affairs");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.current_affairs));
                openFragment(new CurrentAffairFragment());
            }
        });

        headerViewBinding.currentAffairView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("Current Affairs");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.current_affairs));
                openFragment(new CurrentAffairFragment());
            }
        });


        headerViewBinding.dictionaryCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.toolbarTitle.setText("Medical Dictionary");
                mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,R.drawable.medical_dictionary));
                openFragment(new DictionaryFragment());
            }
        });

        headerViewBinding.dictionaryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateToolbar("Medical Dictionary",R.drawable.medical_dictionary);
                openFragment(new DictionaryFragment());
            }
        });

        headerViewBinding.dictionaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateToolbar("Medical Dictionary",R.drawable.medical_dictionary);
                openFragment(new DictionaryFragment());
            }
        });


        headerViewBinding.genericCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateToolbar("Generic And Brand",R.drawable.generci_brand);
                openFragment(new SearchMedicineFragment());
            }
        });

        headerViewBinding.genericView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateToolbar("Generic And Brand",R.drawable.generci_brand);
                openFragment(new SearchMedicineFragment());
            }
        });

        headerViewBinding.genericText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateToolbar("Generic And Brand",R.drawable.generci_brand);
                openFragment(new SearchMedicineFragment());
            }
        });


        headerViewBinding.helpCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateToolbar("Help Centre",R.drawable.help_centre);
                openFragment(new HelpCenterFragment());
            }
        });

        headerViewBinding.helpText.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                updateToolbar("Help Centre",R.drawable.help_centre);
                openFragment(new HelpCenterFragment());
            }
        });

        headerViewBinding.helpView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                updateToolbar("Help Centre",R.drawable.help_centre);
                openFragment(new HelpCenterFragment());
            }
        });



        headerViewBinding.logOutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.setToken(HomeActivity.this,null);
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        });

        headerViewBinding.aboutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateToolbar("About",R.drawable.about);
            openFragment(new AboutFragment());
        }
        });


        headerViewBinding.flexibacView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String url = "http://flexibac.com.bd";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
                openFragment(new WebViewFragment());
                updateToolbar("Flexibac",R.drawable.flexibac);
                mBinding.drawerLayout.closeDrawers();

            }
        });





        mBinding.menuImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.drawerLayout.openDrawer(Gravity.START);
            }
        });










//        Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.fragment_container);
//
//
//
//        if (fragment==null){
//            fragment=new AboutFragment();
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_container,fragment)
//                    .commit();
//            updateToolbar("About",R.drawable.about);
//        }

    }

    public void updateToolbar(String title, int drawableID) {
        mBinding.toolbarTitle.setText(title);
        mBinding.toolbarPicImgView.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this,drawableID));
    }

    private void openProfileFragment() {
       /* openFragment(new ProfileEditFragment());
        mBinding.toolbarTitle.setText("Profile");*/
        mBinding.drawerLayout.closeDrawers();
        startActivity(new Intent(HomeActivity.this, ProfileDisplayActivity.class));

    }

    private void openFragment(Fragment fragment) {
        mBinding.drawerLayout.closeDrawers();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,fragment)

                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        final ProfileDBHandler dbHandler=new ProfileDBHandler(HomeActivity.this);


//        dbHandler.getProfileInfoFromLocal(new ProfileCallback() {
//            @Override
//            public void getUser(User user) {
//                headerViewBinding.profileText1.setText(user.getProfile().getDesig()+" "+user.getName());
//                headerViewBinding.profileText2.setText(user.getProfile().getDegree()+", "+user.getProfile().getDept());
//
//                byte[]profilePicByteArray=user.getmProfilePicByteData();
//                if (profilePicByteArray!=null){
//                    Bitmap bitmap= BitmapFactory.decodeByteArray(profilePicByteArray,0,profilePicByteArray.length);
//                    if (bitmap!=null){
//                        headerViewBinding.profileImageCircleImageView.setImageBitmap(bitmap);
//                    }
//                }
//            }
//
//            @Override
//            public void errorOccured(String msg) {
//
//                if (msg.equals("NUI")) {
//
//
//                    dbHandler.getProfileInfoFromRemote(new ProfileCallback() {
//                        @Override
//                        public void getUser(User user) {
//                            headerViewBinding.profileText1.setText(user.getProfile().getDesig() + " " + user.getName());
//                            headerViewBinding.profileText2.setText(user.getProfile().getDegree() + ", " + user.getProfile().getDept());
//
//                            byte[] profilePicByteArray = user.getmProfilePicByteData();
//                            if (profilePicByteArray != null) {
//                                Bitmap bitmap = BitmapFactory.decodeByteArray(profilePicByteArray, 0, profilePicByteArray.length);
//                                if (bitmap != null) {
//                                    headerViewBinding.profileImageCircleImageView.setImageBitmap(bitmap);
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void errorOccured(String msg) {
//
//                        }
//                    });
//                }
//            }
//        });


    }
}
