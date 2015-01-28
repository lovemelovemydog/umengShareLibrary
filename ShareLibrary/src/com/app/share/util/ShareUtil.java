package com.app.share.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.Toast;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.media.*;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

import java.io.File;

/**
 * Created by anzhuo on 2015/1/26.
 */
public class ShareUtil {
    public static final String DESCRIPTOR = "com.android.share";
    private final UMSocialService mController = UMServiceFactory
            .getUMSocialService(DESCRIPTOR);

    private SHARE_MEDIA mPlatform = SHARE_MEDIA.SINA;


    public ShareUtil (Activity activity){}
    public void openSare (final Activity context) {
        configPlatforms (context);

        mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,SHARE_MEDIA.SINA);
        mController.openShare(context, false);


    }

    /**
     * 配置分享平台参数</br>
     */
    public void configPlatforms(Activity context) {


        // 添加新浪
        addSinaPlatform(context);

        // 添加QQ、QZone平台
        addQQQZonePlatform(context);

        // 添加微信、微信朋友圈平台
        addWXPlatform(context);
    }

    /**
     * 添加新浪平台SSO授权分享
     */
    public void addSinaPlatform (Context context){
        mController.getConfig().setSsoHandler(new SinaSsoHandler());

    }

    /**
     * @功能描述 : 添加微信平台分享
     * @return
     */
    public void addWXPlatform(Context context) {
        // 注意：在微信授权的时候，必须传递appSecret
        // wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
        String appId = "wx967daebe835fbeac";
        String appSecret = "5bb696d9ccd75a38c8a0bfe0675559b3";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(context, appId, appSecret);
        wxHandler.addToSocialSDK();

        // 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(context, appId, appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
    }

    /**
     * @功能描述 : 添加QQ平台支持 QQ分享的内容， 包含四种类型， 即单纯的文字、图片、音乐、视频. 参数说明 : title, summary,
     *       image url中必须至少设置一个, targetUrl必须设置,网页地址必须以"http://"开头 . title :
     *       要分享标题 summary : 要分享的文字概述 image url : 图片地址 [以上三个参数至少填写一个] targetUrl
     *       : 用户点击该分享时跳转到的目标地址 [必填] ( 若不填写则默认设置为友盟主页 )
     * @return
     */
    public void addQQQZonePlatform(Activity context) {
        String appId = "100424468";
        String appKey = "c7394704798a158208a74ab60104f0ba";
        // 添加QQ支持, 并且设置QQ分享内容的target url
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(context,
                appId, appKey);
        qqSsoHandler.setTargetUrl("http://www.umeng.com/social");
        qqSsoHandler.addToSocialSDK();

        // 添加QZone平台
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(context, appId, appKey);
        qZoneSsoHandler.addToSocialSDK();
    }

    /**
     * 根据不同的平台设置不同的分享内容</br>
     */
    public void setShareContent(Activity context) {

        // 配置SSO
        mController.getConfig().setSsoHandler(new SinaSsoHandler());

        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(context,
                "100424468", "c7394704798a158208a74ab60104f0ba");
        qZoneSsoHandler.addToSocialSDK();
        mController.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能。http://www.umeng.com/social");


//        UMImage localImage = new UMImage(getActivity(), R.drawable.device);
        UMImage urlImage = new UMImage(context,
                "http://www.umeng.com/images/pic/social/integrated_3.png");
        // UMImage resImage = new UMImage(getActivity(), R.drawable.icon);

//        // 视频分享
//        UMVideo video = new UMVideo(
//                "http://v.youku.com/v_show/id_XNTc0ODM4OTM2.html");
//        // vedio.setThumb("http://www.umeng.com/images/pic/home/social/img-1.png");
//        video.setTitle("友盟社会化组件视频");
//        video.setThumb(urlImage);
//
//        UMusic uMusic = new UMusic(
//                "http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
//        uMusic.setAuthor("umeng");
//        uMusic.setTitle("天籁之音");
////        uMusic.setThumb(urlImage);
//        uMusic.setThumb("http://www.umeng.com/images/pic/social/chart_1.png");

//        UMEmoji emoji = new UMEmoji(getActivity(), "http://www.pc6.com/uploadimages/2010214917283624.gif");
//        UMEmoji emoji = new UMEmoji(getActivity(), "/storage/sdcard0/emoji.gif");

        WeiXinShareContent weixinContent = new WeiXinShareContent();
        weixinContent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能-微信。http://www.umeng.com/social");
        weixinContent.setTitle("友盟社会化分享组件-微信");
        weixinContent.setTargetUrl("http://www.umeng.com/social");
        weixinContent.setShareMedia(urlImage);
        mController.setShareMedia(weixinContent);

        // 设置朋友圈分享的内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能-朋友圈。http://www.umeng.com/social");
        circleMedia.setTitle("友盟社会化分享组件-朋友圈");
        circleMedia.setShareMedia(urlImage);
        // circleMedia.setShareMedia(uMusic);
        // circleMedia.setShareMedia(video);
        circleMedia.setTargetUrl("http://www.umeng.com/social");
        mController.setShareMedia(circleMedia);


        UMImage qzoneImage = new UMImage(context,
                "http://www.umeng.com/images/pic/social/integrated_3.png");
        qzoneImage
                .setTargetUrl("http://www.umeng.com/images/pic/social/integrated_3.png");

        // 设置QQ空间分享内容
        QZoneShareContent qzone = new QZoneShareContent();
        qzone.setShareContent("share test");
        qzone.setTargetUrl("http://www.umeng.com");
        qzone.setTitle("QZone title");
        qzone.setShareMedia(urlImage);
//        qzone.setShareMedia(uMusic);
        mController.setShareMedia(qzone);

//        video.setThumb(new UMImage(getActivity(), BitmapFactory.decodeResource(
//                getResources(), R.drawable.device)));

//        QQShareContent qqShareContent = new QQShareContent();
////        qqShareContent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能 -- QQ");
//        qqShareContent.setTitle("hello, title");
//        qqShareContent.setShareMedia(uMusic);
//        qqShareContent.setTargetUrl("http://www.umeng.com/social");
//        mController.setShareMedia(qqShareContent);
//
//        // 视频分享
//        UMVideo umVideo = new UMVideo(
//                "http://v.youku.com/v_show/id_XNTc0ODM4OTM2.html");
//        umVideo.setThumb("http://www.umeng.com/images/pic/home/social/img-1.png");
//        umVideo.setTitle("友盟社会化组件视频");
//
//        TencentWbShareContent tencent = new TencentWbShareContent();
//        tencent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能-腾讯微博。http://www.umeng.com/social");
//        // 设置tencent分享内容
//        mController.setShareMedia(tencent);



    }
}
