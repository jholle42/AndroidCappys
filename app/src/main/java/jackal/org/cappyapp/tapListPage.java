package jackal.org.cappyapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class tapListPage extends Fragment {

    WebView mTapList;
    //String tapListUrl ="https://lovelandcappys.com/growlers/";
    public tapListPage() {    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tap_list_page, container, false);
        mTapList = rootView.findViewById(R.id.webview_tap_list);
        String unTappdMenu = "<div id=\"menu-container\"></div>\n" +
                "<script type=\"text/javascript\">\n" +
                "  !function(e,n){var t=document.createElement(\"script\"),a=document.getElementsByTagName(\"script\")[0];t.async=1,a.parentNode.insertBefore(t,a),t.onload=t.onreadystatechange=function(e,a){(a||!t.readyState||/loaded|complete/.test(t.readyState))&&(t.onload=t.onreadystatechange=null,t=void 0,a||n&&n())},t.src=e}(\"https://embed-menu-preloader.untappdapi.com/embed-menu-preloader.min.js\",function(){PreloadEmbedMenu(\"menu-container\",1967,4344)});\n" +
                "</script>\n" +
                " ";
       // mWebView.loadUrl(tapListUrl);
        mTapList.getSettings().setJavaScriptEnabled(true);
        mTapList.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);
                return false;
            }
        });
        mTapList.loadData(unTappdMenu,"text/html" , "utf-8" );

        // Inflate the layout for this fragment
        return rootView;
    }

}
