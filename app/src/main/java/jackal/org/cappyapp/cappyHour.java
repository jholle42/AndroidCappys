package jackal.org.cappyapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by jholle42 on 6/15/18.
 */

public class cappyHour extends Fragment {

    WebView mTapList;
    public cappyHour() {    }

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
        mTapList.getSettings().setJavaScriptEnabled(false);
        mTapList.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        mTapList.loadData(unTappdMenu,"text/html" , "utf-8" );
        return rootView;
    }

}
