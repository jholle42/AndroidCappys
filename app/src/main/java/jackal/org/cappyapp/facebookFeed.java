package jackal.org.cappyapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class facebookFeed extends Fragment {

    WebView mWebView;
    String mainURL = "https://www.facebook.com/LovelandCappys/?ref=br_rs";

    public facebookFeed() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_facebook, container, false);
        mWebView = rootView.findViewById(R.id.webview_facebook_feed);
        mWebView.loadUrl(mainURL);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        // Inflate the layout for this fragment
        return rootView;// Inflate the layout for this fragment
    }

}
