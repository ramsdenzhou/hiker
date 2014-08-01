package com.iawall.hiker;

import java.util.List;

import com.iawall.hiker.util.SharePreferenceUtil;
import com.iawall.wanfeng.R;


import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SplashActivity extends Activity {
  
	private SharePreferenceUtil util;
	private final int SPLASH_DISPLAY_LENGHT = 2000;
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		 
		util = new SharePreferenceUtil(this, "JIUFENG"); 
		
		if (util.getisFirst()) { 
			//createShut();// 创建快捷方式	
		}
		
		
		initView();
			
	}
	
	public void initView() {
	
			new Handler().postDelayed(new Runnable(){ 
				public void run(){ 	 
					goMainActivity();
				}
			},SPLASH_DISPLAY_LENGHT);
			
		
	  }
	 

	protected void goMainActivity() {
		// TODO Auto-generated method stub	
		Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
	
		SplashActivity.this.startActivity(mainIntent);
		
		SplashActivity.this.finish();
		
	}
	
	
	
	
	
	
	/**
	 * 创建桌面快捷方式
	 */
//	public void createShut() {
//		// 创建添加快捷方式的Intent
//		Intent addIntent = new Intent(
//				"com.android.launcher.action.INSTALL_SHORTCUT");
//		String title = getResources().getString(R.string.app_name);
//		// 加载快捷方式的图标
//		Parcelable icon = Intent.ShortcutIconResource.fromContext(
//				SplashActivity.this, R.drawable.ic_launcher);
//		// 创建点击快捷方式后操作Intent,该处当点击创建的快捷方式后，再次启动该程序
//		Intent myIntent = new Intent(SplashActivity.this,
//				SplashActivity.class);
//		// 设置快捷方式的标题
//		addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
//		// 设置快捷方式的图标
//		addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
//		// 设置快捷方式对应的Intent
//		addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);
//		// 发送广播添加快捷方式
//		sendBroadcast(addIntent);
//		util.setIsFirst(false);
//	}
	
	

	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}
	


}
