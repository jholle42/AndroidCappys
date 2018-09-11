package jackal.org.cappyapp;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
        startActivity(getOpenFacebookIntent(getActivity()));
        // Inflate the layout for this fragment
        return rootView;// Inflate the layout for this fragment
    }

    public static Intent getOpenFacebookIntent(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/431259720256588"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/LovelandCappys"));
        }
    }

}
