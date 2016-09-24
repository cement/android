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


import com.cement.android.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class GestureBuilderActivity extends Activity {
    private static final float LENGTH_THRESHOLD = 120.0f;

    private Gesture mGesture;

	private GestureOverlayView mGestureView;

	private int CHOOSER_RESULT;

	private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gesture_recognition);
        mGestureView = (GestureOverlayView) findViewById(R.id.gestures_overlay);
        //mGesreView = (GestureOverlayView) getLayoutInflater().inflate(R.id.gestures_overlay, (ViewGroup) getWindow().getContainer().getDecorView());
        mGestureView.addOnGestureListener(new GesturesProcessor());
       // mGesreView.addOnGesturePerformedListener(new GesTuresPerformedListener());
        mPreferences = getSharedPreferences("gesture",Context.MODE_PRIVATE);
    }


    public void openApplicationChooser(){
    	Intent intent = new Intent();
    	intent.setAction(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_DEFAULT);
    	intent.addCategory(Intent.CATEGORY_LAUNCHER);
    	Intent pick = new Intent(Intent.ACTION_PICK_ACTIVITY);
    	pick.putExtra(Intent.EXTRA_TITLE, "请选择你想链接的应用：");
    	pick.putExtra(Intent.EXTRA_INTENT, intent);
    	//Intent chooser = Intent.createChooser(intent, "请选择你想链接的应用：");
    	startActivityForResult(pick, CHOOSER_RESULT);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	// TODO Auto-generated method stub
    	if(resultCode == RESULT_OK && requestCode == CHOOSER_RESULT){
    		ComponentName componet = intent.resolveActivity(getPackageManager());
    		String packageName = componet.getPackageName();
    		String className = componet.getClassName();
	        String shortClassName = componet.getShortClassName();
  	    
	          Constant.sStore.addGesture(shortClassName, mGesture);
	          Constant.sStore.save();
	          
	          
	          mPreferences.edit().putString(shortClassName,className).commit();
	          mPreferences.edit().putString(className,packageName).commit();
	    	  
	          Log.v(getClass().getSimpleName(),"........reault intent.........."+intent);
	          this.setResult(RESULT_OK);
    	}else{
    		this.setResult(RESULT_CANCELED);
    	}
    	this.finish();
    }
    
    public class GesTuresPerformedListener implements OnGesturePerformedListener {
    	@Override
    	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
    		// TODO Auto-generated method stub
             Log.v(getClass().getSimpleName(), "...call...onGesturePerformed()............");
             
    	}

    }
    private class GesturesProcessor implements OnGestureListener {
        public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
            mGesture = null;
        }

        public void onGesture(GestureOverlayView gestureView, MotionEvent event) {
        }

        public void onGestureEnded(GestureOverlayView gestureView, MotionEvent event) {
            mGesture = gestureView.getGesture();
            if (mGesture.getLength() < LENGTH_THRESHOLD) {
            	gestureView.clear(false);
            }else{
            	openApplicationChooser();
            	gestureView.clear(true);
            }
        }
        public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
        
        }
    }
}
