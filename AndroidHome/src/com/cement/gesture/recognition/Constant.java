package com.cement.gesture.recognition;


import com.cement.app.App;

import android.content.Context;
import android.content.Intent;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;

public class Constant {
    public static final String INTENT_ACTION_TEST = "com.cement.action.Test";
	public static final String INTENT_CATEGORY_TEST = "com.cement.category.Test";
	public static final String INTENT_CATEGORY_DEFAULT = Intent.ACTION_DEFAULT;
	
	public static Context sContext = App.getAppContext();
	public static GestureLibrary sStore = GestureLibraries.fromPrivateFile(sContext, "gestures");;
}
