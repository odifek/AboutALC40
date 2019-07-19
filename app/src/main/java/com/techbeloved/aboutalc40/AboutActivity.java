package com.techbeloved.aboutalc40;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class AboutActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "extraUrl";
    private WebView aboutAlcWebView;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);

        aboutAlcWebView = findViewById(R.id.webview_about_alc);

        loadingProgressBar = findViewById(R.id.progressbar_loading);

        Intent intent = getIntent();

        setTitle(R.string.about_alc_4_0);

        if (intent.hasExtra(EXTRA_URL)) {
            String aboutUrl = intent.getStringExtra(EXTRA_URL);
            setupWebView(aboutUrl);
        } else {
            // No url received. Just finish and go back
            finish();
        }
    }

    private void setupWebView(String aboutUrl) {
        aboutAlcWebView.getSettings().setJavaScriptEnabled(true);

        aboutAlcWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                // We may want to show the user some dialog here

                AlertDialog.Builder builder = new AlertDialog.Builder(AboutActivity.this);

                builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                        dialog.dismiss();
                        // Since we cannot proceed, we should finish the activity
                        finish();
                    }
                });

                builder.setMessage(getString(R.string.ssl_error_message));

                builder.create().show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loadingProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingProgressBar.setVisibility(View.GONE);
            }
        });

        aboutAlcWebView.loadUrl(aboutUrl);

    }
}
