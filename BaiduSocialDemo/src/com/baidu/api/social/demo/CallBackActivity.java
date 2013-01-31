/***************************************************************************
 *
 * Copyright (c) 2012 Baidu.com, Inc. All Rights Reserved
 *
 **************************************************************************/

/**   
* @Title: CallBackActivity.java 
* @Package com.baidu.api.social.demo 
* @Description: 回调Activity，登录组件会将userinfo想关信息传入到该Activity，userInfo中包括accesstoken信息。
* @author shupan@baidu.com
* @date 2012-11-30 下午04:02:29
* @version V1.0   
*/ 


package com.baidu.api.social.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 回调的Activity，展示用户信息
 * @author shupan
 */
public class CallBackActivity extends Activity {

	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.callback);
		
		textView = (TextView)findViewById(R.id.contentTextView);
		
		Intent intent = getIntent();
		String userInfo = intent.getStringExtra("userInfo");
		textView.setText(userInfo);
	}
}



