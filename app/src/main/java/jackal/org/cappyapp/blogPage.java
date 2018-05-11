package jackal.org.cappyapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class blogPage extends Fragment {

    WebView mWebView;
    String blogUrl ="https://lovelandcappys.com/blog/";
    public blogPage() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blog_page, container, false);
        mWebView = rootView.findViewById(R.id.webview_blog);
        mWebView.loadUrl(blogUrl);
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
