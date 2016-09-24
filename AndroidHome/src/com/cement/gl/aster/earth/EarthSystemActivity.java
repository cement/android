package com.cement.gl.aster.earth;


import com.cement.android.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class EarthSystemActivity extends Activity{

	private GLSurfaceView mGLView;
	private Renderer mRenderer;
	private Bitmap[] mTextures;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mGLView = new GLSurfaceView(this);
		
		mRenderer = new EarthSystemRenderer();
		
		mGLView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		mGLView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		mGLView.setZOrderOnTop(true);
	    mGLView.setRenderer(mRenderer);
	    mGLView.setBackgroundResource(R.drawable.space_background);
	
	    setContentView(mGLView);
	}


	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(null != mGLView){
			mGLView.onResume();
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(null != mGLView){
			mGLView.onPause();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings){
			if(mGLView.getRenderMode()==GLSurfaceView.RENDERMODE_CONTINUOUSLY){
				mGLView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
			}else{
				mGLView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
