package claudia.rent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by ei047234 on 4/11/17.
 */

public class RulesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        String url = "file:///android_asset/RentzRules";
        WebView webView = (WebView) findViewById(R.id.rulesWebView);
        webView.loadUrl(url);
    }
}
