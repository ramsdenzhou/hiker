package com.iawall.hiker.galleryshow;

import com.iawall.hiker.MainActivity;
import com.iawall.wanfeng.R;
  
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {
	private Context context;
	private GalleryAdapter self;
	

	public static Integer[] imgs = {
		R.drawable.one,
		R.drawable.two,
		R.drawable.three,
		R.drawable.four
	};
	 ImageView imageView;
	
	public GalleryAdapter(/*List<String> imageUrls, */Context context) {  
	       // this.imageUrls = imageUrls;  
	        this.context = context;  
	        this.self = this;
	    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		
		
		if(convertView==null)
		{  
			convertView = LayoutInflater.from(context).inflate(R.layout.item,null); //实例化convertView
			Gallery.LayoutParams params = new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT,Gallery.LayoutParams.WRAP_CONTENT);
			convertView.setLayoutParams(params);
			//convertView.setTag(imgs);
			
		}else{
			
		}
		
		imageView = (ImageView) convertView.findViewById(R.id.gallery_image);
		imageView.setImageResource(imgs[position % imgs.length]);
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		    
		((MainActivity) context).changePointView(position % imgs.length);
		 
		return convertView;
		
		
		
	} 
}
