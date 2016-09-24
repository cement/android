package com.cement.app;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class App extends Application implements ActivityLifecycleCallbacks{

	
	private static Context sAppContext;
	public static Context getAppContext(){
		return sAppContext;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		registerActivityLifecycleCallbacks(this);
		sAppContext = getApplicationContext();
		
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}


	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		Log.i(activity.getClass().getSimpleName(), "......."+methodName+"...............");
	}

	@Override
	public void onActivityStarted(Activity activity) {
		// TODO Auto-generated method stub
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		Log.i(activity.getClass().getSimpleName(), "......."+methodName+"...............");
	}

	@Override
	public void onActivityResumed(Activity activity) {
		// TODO Auto-generated method stub
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		Log.i(activity.getClass().getSimpleName(), "......."+methodName+"...............");
	}

	@Override
	public void onActivityPaused(Activity activity) {
		// TODO Auto-generated method stub
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		Log.i(activity.getClass().getSimpleName(), "......."+methodName+"...............");	
	}

	@Override
	public void onActivityStopped(Activity activity) {
		// TODO Auto-generated method stub
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		Log.i(activity.getClass().getSimpleName(), "......."+methodName+"...............");	
	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
		// TODO Auto-generated method stub
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		Log.i(activity.getClass().getSimpleName(), "......."+methodName+"...............");	
	}

	@Override
	public void onActivityDestroyed(Activity activity) {
		// TODO Auto-generated method stub
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		Log.i(activity.getClass().getSimpleName(), "......."+methodName+"...............");	
	}
	

}
