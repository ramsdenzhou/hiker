package com.iawall.hiker.galleryshow;

import java.util.TimerTask;

import com.iawall.hiker.MainActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;
import android.widget.Toast;

public class GuideGallery extends Gallery {

	private MainActivity m_iact;
	public int minVelocity = 0;
	
	public GuideGallery(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public GuideGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public GuideGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void setImageActivity(MainActivity iact){
		m_iact = iact;
	}
	
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		//接触时触发
		System.out.println("======onScroll======");
		// TODO Auto-generated method stub
		m_iact.timeTask.timeCondition = false;
		
		return super.onScroll(e1, e2, distanceX, distanceY);
	}
	
	
	public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,
			float velocityY){
		System.out.println("======onFling======");
		//离开后才触发
		int kEvent; 
		
		
        if(isScrollingLeft(e1, e2) && (velocityX >0)){ //Check if scrolling left
          kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
          //System.out.println("======toRight======");
          //System.out.println("AAAA"+this.getSelectedItemPosition());
        }else{ //Otherwise scrolling right
        	//System.out.println("======toLeft======");
            kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
          //System.out.println("BBB"+this.getSelectedItemPosition());
        }
        
        onKeyDown(kEvent, null);//如果不加的话会导致，必须滑动满才能翻页
        
//        new java.util.Timer().schedule(new TimerTask(){
//		       public void run() {
//		    	  
//		    	   m_iact.timeFlag = false;
//		    	   this.cancel();
//		       }}, 7000);
        m_iact.timeFlag = false;
	    return true; 
	}
	
	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2){
	   //System.out.println(this.getSelectedItemPosition());
       return e2.getX() > e1.getX(); 
    }
	
	

}
