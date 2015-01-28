package com.app.share.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.app.share.R;
import com.app.share.util.ShareUtil;

public class MyActivity extends Activity {

    Activity context = null;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_test_main);


        final ShareUtil shareUtil = new ShareUtil(context);

        Button btn = (Button)findViewById(R.id.btn);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    shareUtil.openSare(context);
                }
            });
        }

    }

}
