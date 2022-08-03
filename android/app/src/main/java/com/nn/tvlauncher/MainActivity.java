package com.nn.tvlauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView)findViewById(R.id.webView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE))
            { WebView.setWebContentsDebuggingEnabled(true); }
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new LauncherWebChromeClient());
        webView.setWebViewClient(new LauncherWebViewClient());
        webView.addJavascriptInterface(new JavaScriptInterface(this), "_android_");
        webView.loadUrl(BuildConfig.WEB_URL);
    }
    private static class LauncherWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        // Для старых устройств
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override public void onPageFinished(WebView view, String url) {
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    }
    private static class LauncherWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
            return true;
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue,
                                  final JsPromptResult result) {
            return true;
        }
    }
    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public String installedApps(){
            ArrayList<JavaScriptInterface.PInfo> apps = getInstalledApps(false); /* false = no system packages */
            return new Gson().toJson(apps);
        }

        @JavascriptInterface
        public void runApplication(String name) {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage(name);
            if (launchIntent != null) {
                startActivity(launchIntent);
            } else {
                Toast.makeText(MainActivity.this, "Не удалось запустить приложение", Toast.LENGTH_LONG).show();
            }
        }
        @JavascriptInterface
        public void openSettings() {
            startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
        }

        class PInfo {
            private String appname = "";
            private String pname = "";
            private String versionName = "";
            private int versionCode = 0;
            private String icon;
            private Boolean hasBanner = false;
            private String banner = "";
        }

        private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
            ArrayList<PInfo> res = new ArrayList<PInfo>();
            List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
            for(int i=0;i<packs.size();i++) {
                PackageInfo p = packs.get(i);
                Boolean add = false;
                if ((!getSysPackages) && (p.versionName == null)) {
                    continue ;
                }
                if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                    if (getSysPackages) {
                        add = true;
                    }
                } else {
                    add = true;
                }
                if (add) {
                    PInfo newInfo = new PInfo();
                    newInfo.appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
                    newInfo.pname = p.packageName;
                    newInfo.versionName = p.versionName;
                    newInfo.versionCode = p.versionCode;
                    if (p.applicationInfo.banner != 0) {
                        newInfo.hasBanner = true;
                        newInfo.banner = ImageUtils.convert(p.applicationInfo.loadBanner(getPackageManager()));
                    } else {
                        newInfo.icon = ImageUtils.convert(p.applicationInfo.loadIcon(getPackageManager()));
                    };
                    res.add(newInfo);
                }
            }
            return res;
        }
    }
}

