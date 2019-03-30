package online.ghuri.com.beacondoctorapp;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import online.ghuri.com.beacondoctorapp.databinding.FragmentWebViewBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {


    public WebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final FragmentWebViewBinding binding= DataBindingUtil.inflate(inflater,R.layout.fragment_web_view, container, false);
        WebSettings settings=binding.webView.getSettings();
        settings.setJavaScriptEnabled(true);

        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                // hide element by class name
//                binding.webView.loadUrl("javascript:(function() { " +
//                        "document.getElementsByClassName('navbar navbar-default navbar-fixed-top appsLand-navbar navBar__style-2 active-navbar')[0].style.display='none'; })()");
//                // hide element by id
                binding.webView.loadUrl("javascript:(function() { " +
                        "document.getElementById('nav_hidden').style.display='none';})()");

            }
        });

        binding.webView.loadUrl("http://flexibac.com.bd");

        return binding.getRoot();
    }


}
