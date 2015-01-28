
package com.android.share.wxapi;

import android.os.Bundle;
import android.os.PersistableBundle;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

/**
 *
 * this is a bak file
 *
 * if want to get WX callback ,can use it .
 * package must be $app packageName$wxapi/WXEntryActivity
 *
 */
public class WXEntryActivity extends WXCallbackActivity {
    public static final String TAG = "WXEntryActivity";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    /**
     * invock it when onRequest
     * @param req
     */
    @Override
    public void onReq(BaseReq req) {
        super.onReq(req);


    }

    /**
     * do something in this callback mehod
     * @param resp
     */
    @Override
    public void onResp(BaseResp resp) {
        super.onResp(resp);


    }
}
