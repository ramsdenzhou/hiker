package com.iawall.hiker;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.iawall.hiker.galleryshow.GalleryAdapter;
import com.iawall.hiker.galleryshow.GuideGallery;
import com.iawall.hiker.util.SharePreferenceUtil;
import com.iawall.wanfeng.R;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	public GuideGallery images_ga;
	private int positon = 0;
	public ImageTimerTask timeTask,timeTask2 = null;
	int galleryposition = 0;
	private Thread timeThread = null;
	private boolean isExit = false;
	public boolean timeFlag = true;
	
	private MenuInflater mi;// 菜单
	private SharePreferenceUtil util;	
	
	Date d1;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
		
		timeTask = new ImageTimerTask();
		
		 		
		
        autoGallery.scheduleAtFixedRate(timeTask, 5000, 5000);
        
        timeThread = new Thread(){
        	public void run()
        	{
        		while(!isExit){
        			try{ 
        				Thread.sleep(1500);
        			}catch(InterruptedException e){
        				e.printStackTrace();
        			}
        			
        			synchronized(timeTask){
        				if(!timeFlag)
        				{   
        					
//        					timeTask2 = new ImageTimerTask();
//        					Timer secondGallery = new Timer();
        					autoGallery.scheduleAtFixedRate(timeTask, 5000, 5000);
        					
        				 
        				 
        					
        					timeTask.timeCondition = true;
        					
	
        					//timeTask.notifyAll();//主要是针对下面的wait()
        				}
        			}
        			 timeFlag = true;
        			
        		}
        	}
        };
        timeThread.start();
	}
	
//	protected void onResume(){
//		super.onResume();
//		System.out.println("======onResume=======");
//		timeFlag = false;
//	} 
	
	
	
	
    private void init() { 
		// TODO 
    	images_ga = (GuideGallery) findViewById(R.id.image_wall_gallery); 
        images_ga.setImageActivity(this); 
        
        GalleryAdapter imageAdapter = new GalleryAdapter(this);
        images_ga.setAdapter(imageAdapter);
        
        LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
        pointLinear.setBackgroundColor(Color.argb(200, 135, 135, 152));
        for (int i = 0; i < 4; i++) {
        	ImageView pointView = new ImageView(this);
        	if(i==0){
        		pointView.setBackgroundResource(R.drawable.feature_point_cur);
        	}else
        		pointView.setBackgroundResource(R.drawable.feature_point);
        	    pointLinear.addView(pointView);
		}
	}
    
    
    
    
    final Handler autoGalleryHandler = new Handler(){
    	public void handleMessage (Message message)
    	{
    		super.handleMessage(message);
    		switch(message.what){
    		case 1:
    			images_ga.setSelection(message.getData().getInt("pos"));
    			break;
    		}
    	}
    };


	
	public class ImageTimerTask extends TimerTask{
		public boolean timeCondition = true;
		

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized(this){
				while(!timeCondition){
					try{
						
						Thread.sleep(100);
//						autoGallery.cancel();
//						autoGallery.purge();
						timeTask.cancel(); 
						autoGallery.purge();
//						timeTask = null;
						
						//wait();
					
					}catch(InterruptedException e){
						Thread.interrupted();
					}
						
				}
			}
				
			try{
				galleryposition = images_ga.getSelectedItemPosition() + 1;
				Message msg = new Message();
				Bundle date = new Bundle();
				date.putInt("pos", galleryposition);
				msg.setData(date);
				msg.what = 1;
				autoGalleryHandler.sendMessage(msg);
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
	}
	
	public void changePointView(int cur){
    	LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
   	 	View view = pointLinear.getChildAt(positon);
   	 	View curView = pointLinear.getChildAt(cur);
   	 
   	 	if(view!=null&& curView!=null){
   	 		ImageView pointView = (ImageView)view;
   	 		ImageView curPointView = (ImageView)curView;
   	 		pointView.setBackgroundResource(R.drawable.feature_point);
   	 		curPointView.setBackgroundResource(R.drawable.feature_point_cur);
   	 		positon = cur;
   	 	}
    }
	
	 Timer autoGallery = new Timer();
	
	 public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.splash, menu);
	        return true;
	  }
}
