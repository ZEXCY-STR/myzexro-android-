
package com.myzexro.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.graphics.Color;

public class MainActivity extends Activity {
    private WebView webView;
    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.WHITE);

        webView = new WebView(this);
        webView.setLayoutParams(new LinearLayout.LayoutParams(
                -1, -1
        ));

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(
                    WebView view,
                    WebResourceRequest request,
                    WebResourceError error) {
                if (request.isForMainFrame()) {
                    view.loadData(
                        "<html><body style='font-family:sans-serif;padding:24px'>"
                        + "<h3>ZeXro tidak dapat dimuat</h3>"
                        + "<p>Periksa koneksi internet, lalu muat ulang aplikasi.</p>"
                        + "</body></html>",
                        "text/html", "UTF-8"
                    );
                }
            }
        });
        webView.setWebChromeClient(new WebChromeClient());

        root.addView(webView);
        setContentView(root);

        webView.loadUrl("https://project-zexro-v1.netlify.app/");
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
