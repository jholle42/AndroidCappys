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
public class eventPage extends Fragment {

    WebView mWebView;
    //String eventEmbed ="<iframe showTitle=0&amp;showNav=0&amp;showDate=0&amp;showPrint=0&amp;showTabs=0&amp;showCalendars=0&amp;showTz=0&amp;mode=AGENDA&amp;height=600&amp;wkst=1&amp;bgcolor=%23FFFFFF&amp;src=cappycaploveland%40gmail.com&amp;color=%231B887A&amp;ctz=America%2FNew_York\" style=\"border-width:0\" width=\"800\" height=\"600\" frameborder=\"0\" scrolling=\"no\"></iframe>";
    String eventsUrl ="https://lovelandcappys.com/events/";
    public eventPage(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_page, container, false);
        mWebView = rootView.findViewById(R.id.webview_events);
        //mWebView.loadUrl(eventsUrl);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        mWebView.loadUrl(eventsUrl);
        return rootView;
    }

}
