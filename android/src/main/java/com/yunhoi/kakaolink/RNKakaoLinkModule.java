
package com.yunhoi.kakaolink;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.message.template.ButtonObject;
import com.kakao.message.template.ContentObject;
import com.kakao.message.template.FeedTemplate;
import com.kakao.message.template.LinkObject;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.util.helper.log.Logger;

public class RNKakaoLinkModule extends ReactContextBaseJavaModule implements ActivityEventListener{

    private final ReactApplicationContext reactContext;

    public RNKakaoLinkModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(this);
        this.reactContext = reactContext;
    }

    private String getString(ReadableMap map, String key) {
        if(map.hasKey(key)) {
            return map.getString(key);
        }
        return null;
    }

    @ReactMethod
    public void link(ReadableMap params) {
        FeedTemplate feedParams = FeedTemplate.newBuilder(
                        ContentObject.newBuilder(
                                getString(params, "title"),
                                getString(params, "imageUrl"),
                                LinkObject.newBuilder()
                                        .setWebUrl(getString(params, "webUrl"))
                                        .setMobileWebUrl(getString(params, "mobileWebUrl"))
                                        .build())
                                .setDescrption(getString(params, "desc"))
                                .build())
                .addButton(new ButtonObject("웹으로 보기", LinkObject.newBuilder()
                    .setWebUrl(getString(params, "webUrl"))
                    .setMobileWebUrl(getString(params, "mobileWebUrl")).build()))
                .addButton(new ButtonObject("앱으로 보기", LinkObject.newBuilder()
                        .setIosExecutionParams(getString(params, "iosExecutionParams"))
                        .setAndroidExecutionParams(getString(params, "androidExecutionParams"))
                        .build()))
                .build();

        KakaoLinkService.getInstance().sendDefault(getCurrentActivity(), feedParams, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Logger.e(errorResult.toString());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {

            }
        });
    }

    @Override
    public String getName() {
        return "RNKakaoLink";
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onNewIntent(Intent intent) {
        if(intent.getData() != null) {

            Log.d("jijuyeo", intent.getData().toString());
        }
    }
}
