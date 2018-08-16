package jackal.org.cappyapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class twitterFeed extends Fragment {

    WebView mWebView;
    String mainURL = "https://twitter.com/LovelandCappys?lang=en";

    public twitterFeed() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_twitter, container, false);
        mWebView = rootView.findViewById(R.id.webview_twitter_feed);
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
