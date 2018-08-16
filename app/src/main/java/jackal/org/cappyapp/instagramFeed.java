package jackal.org.cappyapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class instagramFeed extends Fragment {

    WebView mWebView;
    String mainURL = "https://www.instagram.com/cappyswineandspirits/?hl=en";

    public instagramFeed() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_instagram, container, false);
        mWebView = rootView.findViewById(R.id.webview_instagram_feed);
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
