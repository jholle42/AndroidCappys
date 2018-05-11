package jackal.org.cappyapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class tapListPage extends Fragment {

    WebView mWebView;
    String tapListUrl ="https://lovelandcappys.com/growlers/";
    public tapListPage() {    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tap_list_page, container, false);
        mWebView = rootView.findViewById(R.id.webview_tap_list);
        mWebView.loadUrl(tapListUrl);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

}
