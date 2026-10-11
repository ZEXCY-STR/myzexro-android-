
package com.myzexro.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.ValueCallback;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    private WebView webView;
    private LinearLayout root;

    private ValueCallback<Uri[]> filePathCallback;
    private static final int FILE_CHOOSER_REQUEST = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.WHITE);

        webView = new WebView(this);
        webView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));

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

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onShowFileChooser(
                    WebView view,
                    ValueCallback<Uri[]> callback,
                    FileChooserParams params) {

                if (filePathCallback != null) {
                    filePathCallback.onReceiveValue(null);
                }

                filePathCallback = callback;

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");

                try {
                    startActivityForResult(
                        Intent.createChooser(intent, "Pilih Foto"),
                        FILE_CHOOSER_REQUEST
                    );
                } catch (Exception e) {
                    filePathCallback = null;
                    callback.onReceiveValue(null);
                    return false;
                }

                return true;
            }
        });

        root.addView(webView);
        setContentView(root);

        webView.loadUrl("https://project-zexro-v1.netlify.app/");
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_CHOOSER_REQUEST) {
            if (filePathCallback != null) {
                Uri[] results = null;

                if (resultCode == RESULT_OK
                        && data != null
                        && data.getData() != null) {
                    results = new Uri[] { data.getData() };
                }

                filePathCallback.onReceiveValue(results);
                filePathCallback = null;
            }
        }
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
