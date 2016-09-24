package com.cement.app;

import android.support.v7.app.AppCompatActivity;

import com.cement.android.R;

import android.content.ClipData;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartupActivity extends AppCompatActivity implements OnClickListener{

	private Intent chooser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		Button startButton = (Button) findViewById(R.id.start_button);
		startButton.setOnClickListener(this);
		//chooser = Intent.createChooser(intent, "chooser you like app:");
		//startActivity(chooser);
	}
   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startTestAppliction();
	}

	public void startTestAppliction(){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.addCategory("com.cement.TEST");
		chooser = Intent.createChooser(intent, "chooser you like app:");
		startActivity(chooser);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			startTestAppliction();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	

	
}
