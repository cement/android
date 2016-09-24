/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cement.gesture.recognition;

import java.util.ArrayList;

import com.cement.android.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.GestureOverlayView.OnGesturingListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class GestureRecognitionActivity extends Activity {
    private static final float LENGTH_THRESHOLD = 120.0f;


	private GestureOverlayView mGestureView;

	private TextView inputTextVew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture_recognition);


        mGestureView = (GestureOverlayView) findViewById(R.id.gestures_overlay);
//       // mGesreView.addOnGestureListener(new GesturesProcessor());
//        //设置手势可多笔画绘制，默认情况为单笔画绘制  
//        mGestureView.setGestureStrokeType(GestureOverlayView.GESTURE_STROKE_TYPE_MULTIPLE);  
//        //设置手势的颜色(蓝色)  
//        mGestureView.setGestureColor(android.R.color.holo_blue_bright);  
//        //设置还没未能形成手势绘制是的颜色(红色)  
//        mGestureView.setUncertainGestureColor(android.R.color.holo_red_light));  
//        //设置手势的粗细  
//        mGestureView.setGestureStrokeWidth(4);
        
        /*手势绘制完成后淡出屏幕的时间间隔，即绘制完手指离开屏幕后相隔多长时间手势从屏幕上消失； 
         * 可以理解为手势绘制完成手指离开屏幕后到调用onGesturePerformed的时间间隔 
         * 默认值为420毫秒，这里设置为2秒 
         */  
        mGestureView.setFadeOffset(2000);  
          
        //绑定监听器  
        mGestureView.addOnGesturePerformedListener(new GesTuresPerformedListener());
        mGestureView.addOnGesturingListener(new GesTuringListener());
    }

    
    public class GesTuringListener implements OnGesturingListener {

		@Override
		public void onGesturingStarted(GestureOverlayView overlay) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGesturingEnded(GestureOverlayView overlay) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    public class GesTuresPerformedListener implements OnGesturePerformedListener {
    	@Override
    	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
    		// TODO Auto-generated method stub
             Log.v(getClass().getSimpleName(), "...call...onGesturePerformed()............");
             ArrayList<Prediction> predictions  =  Constant.sStore.recognize(gesture);
             
             if(predictions!=null&&!predictions.isEmpty()){
            	 double score = predictions.get(0).score;
            	 if(score>2.0f){
            		 String name = predictions.get(0).name;
            		 Log.v(getClass().getSimpleName(), ">>>>>>name:  "+name);
            		 SharedPreferences preference = getSharedPreferences("gesture",Context.MODE_PRIVATE);
            		 String className = preference.getString(name,"");
            		 String packageName = preference.getString(className, "");
            		
            		 if(className.equals("")||packageName.equals("")){
            			 return;
            		 }
            		 Intent intent = new Intent(Intent.ACTION_MAIN);  
        	         intent.addCategory(Intent.CATEGORY_LAUNCHER);  
        	         ComponentName cn = new ComponentName(packageName, className);  
            	     intent.setComponent(cn);
            	     
            	     Toast.makeText(GestureRecognitionActivity.this, "即将打开应用，请稍等！", Toast.LENGTH_SHORT).show();
					 startActivity(intent);
            	 }else{
            		 Toast.makeText(GestureRecognitionActivity.this, "手势相似度太低，请重新输入或设定！", Toast.LENGTH_SHORT).show();
            	 }
             }else{
            	 Toast.makeText(GestureRecognitionActivity.this, "手势没有设定，请重试或设定！", Toast.LENGTH_SHORT).show();
             }
             
    	}
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.gesture_recon, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.gesture_builder) {
			Intent intent = new Intent(this,GestureStoreActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
