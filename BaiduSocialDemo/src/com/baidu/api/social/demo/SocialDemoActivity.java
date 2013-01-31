/***************************************************************************
 *
 * Copyright (c) 2012 Baidu.com, Inc. All Rights Reserved
 *
 **************************************************************************/

/**   
 * @Title: CommonUtils.java 
 * @Package com.baidu.api.social.utils 
 * @Description: 实现分享功能的demo，提供四种功能演示。不支持附加图的分享，支持系统自动抓取的图片分享，
 * 支持开发者自传图片，外加一个系统分享的功能演示，供参考。
 * @author shupan@baidu.com
 * @date 2012-12-09 下午04:05:41
 * @version V1.0   
 */

package com.baidu.api.social.demo;

import com.baidu.api.social.exception.CommonException;
import com.baidu.api.social.sdk.BaiduSocialSDK;

import com.baidu.api.social.demo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SocialDemoActivity extends Activity {

	/**
	 * 在百度开发者中心申请的社会化登录APIKEY
	 */
	private final String API_KEY = "wOLsoULzCkE4FWFGaODoy350";

	private TextView articleView;
	private EditText urlEdit;
	private Button shareWithoutPicsBtn;
	private Button shareWithPicsBtn;
	private Button shareWithMyOwnPicsBtn;
	private Button sysShareBtn;
	private ImageView shareWeiboImageView;
	
	private Button loginBtn;
	private ImageView loginWeiboImageView;

	private String mUrl;
	private String mContent;

	private BaiduSocialSDK socialSDK;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baidu_social_demo);

		// 实例化BaiduSocialSDK对象，参数为API_KEY
		socialSDK = new BaiduSocialSDK(SocialDemoActivity.this, API_KEY);

		articleView = (TextView) findViewById(R.id.article_view);
		urlEdit = (EditText) findViewById(R.id.url_edit);
		// 分享的URL
		mUrl = urlEdit.getText().toString();
		// 分享的内容，用户可编辑
		mContent = articleView.getText().toString();

		// 分享按钮，不带附加图片
		shareWithoutPicsBtn = (Button) findViewById(R.id.share_without_pics_btn);
		shareWithoutPicsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					// 不带附加图片
					socialSDK.openShareListDialog(mContent, mUrl, false);
				} catch (CommonException e) {
					// 如果失败会抛出异常，Toast异常消息
					Toast.makeText(getApplicationContext(),
							e.getErrCode() + ":" + e.getErrMsg(),
							Toast.LENGTH_LONG).show();
				}
			}
		});

		// 分享按钮，分享网页中解析出来的图片
		shareWithPicsBtn = (Button) findViewById(R.id.share_with_pics_btn);
		shareWithPicsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					// 附加系统抓取的图片
					socialSDK.openShareListDialog(mContent, mUrl, true);
				} catch (CommonException e) {
					// 如果失败会抛出异常，Toast异常消息
					Toast.makeText(getApplicationContext(),
							e.getErrCode() + ":" + e.getErrMsg(),
							Toast.LENGTH_LONG).show();
				}
			}
		});

		// 分享按钮，分享开发者自己传入的图片，最多可传入5张图片
		shareWithMyOwnPicsBtn = (Button) findViewById(R.id.share_with_my_own_pics_btn);
		shareWithMyOwnPicsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					String p0 = "http://s6.mogujie.cn/b7/pic/130116/fyhxf_kqyxumk2m5bgu6dwgfjeg5sckzsew_359x541.jpg_225x999.jpg";
					String p1 = "http://s6.mogujie.cn/b7/bao/121110/j8cy7_kqyviqkkm5bg2vtwgfjeg5sckzsew_397x598.jpg_225x999.jpg";
					String p2 = "http://s6.mogujie.cn/b7/bao/121109/7ps_kqyucs27m5bdiwsugfjeg5sckzsew_584x754.jpg_225x999.jpg";
					String p3 = "http://s5.mogujie.cn/b7/bao/121226/dp2ey_kqytk2c7m5bfcstwgfjeg5sckzsew_500x750.jpg_225x999.jpg";
					String p4 = "http://s6.mogujie.cn/b7/pic/130109/15ysd_kqyxuukmm5bgo6dwgfjeg5sckzsew_466x700.jpg_225x999.jpg";
					// 附加自己传入的图片，限制5张
					socialSDK.openShareListDialog(mContent, mUrl, p0, p1, p2,
							p3, p4);
				} catch (CommonException e) {
					// 如果失败会抛出异常，Toast异常消息
					Toast.makeText(getApplicationContext(),
							e.getErrCode() + ":" + e.getErrMsg(),
							Toast.LENGTH_LONG).show();
				}
			}
		});

		// 系统自带的分享功能
		sysShareBtn = (Button) findViewById(R.id.sys_share_btn);
		sysShareBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				// 设置分享主题
				intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
				// 设置分享内容
				intent.putExtra(Intent.EXTRA_TEXT, mContent + "(分享自百度分享组件)");
				startActivity(Intent.createChooser(intent, getTitle()));
			}
		});

		// 分享到微博的图片
		shareWeiboImageView = (ImageView) findViewById(R.id.shareIconWeibo);
		shareWeiboImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					String p0 = "http://s6.mogujie.cn/b7/pic/130116/fyhxf_kqyxumk2m5bgu6dwgfjeg5sckzsew_359x541.jpg_225x999.jpg";
					String p1 = "http://s6.mogujie.cn/b7/bao/121110/j8cy7_kqyviqkkm5bg2vtwgfjeg5sckzsew_397x598.jpg_225x999.jpg";
					String p2 = "http://s6.mogujie.cn/b7/bao/121109/7ps_kqyucs27m5bdiwsugfjeg5sckzsew_584x754.jpg_225x999.jpg";
					String p3 = "http://s5.mogujie.cn/b7/bao/121226/dp2ey_kqytk2c7m5bfcstwgfjeg5sckzsew_500x750.jpg_225x999.jpg";
					String p4 = "http://s6.mogujie.cn/b7/pic/130109/15ysd_kqyxuukmm5bgo6dwgfjeg5sckzsew_466x700.jpg_225x999.jpg";
					// 附加自己传入的图片，限制5张

					socialSDK.openShareActivity("2", mContent, mUrl, p0, p1, p2, p3, p4);
				} catch (CommonException e) {
					// 如果失败会抛出异常，Toast异常消息
					Toast.makeText(getApplicationContext(),
							e.getErrCode() + ":" + e.getErrMsg(),
							Toast.LENGTH_LONG).show();
				}
			}
		});
		
		// 登录按钮
		loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try {
					// 调用openLoginActivity接口，打开登录选择列表。第一个参数为当前context，第二个参数为回调的Activity，均必须。
					socialSDK.openLoginListDialog(CallBackActivity.class);
				} catch (CommonException e) {
					// 如果失败会抛出异常，Toast异常消息
					Toast.makeText(getApplicationContext(), e.getErrMsg(), Toast.LENGTH_LONG).show();
				}
			}
		});
        
     // 登录到微博的图片
        loginWeiboImageView = (ImageView) findViewById(R.id.loginIconWeibo);
        loginWeiboImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				try {
					// 调用OpenLoginWebView接口，打开登录webview。第一个参数为当前context，
					// 第二个参数为回调的Activity，第三个参数为mediaTyp，均必须。
					socialSDK.openLoginWebView(CallBackActivity.class, "2");
				} catch (CommonException e) {
					// 如果失败会抛出异常，Toast异常消息
					Toast.makeText(getApplicationContext(), e.getErrMsg(), Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}