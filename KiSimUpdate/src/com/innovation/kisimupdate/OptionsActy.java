package com.gkibuh.kisimupdate;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class OptionsActy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_options_acty);
		
		Button btn1,btn2,btn3,btn4;
		
		btn1 = (Button)findViewById(R.id.esimbtn);
		btn2 = (Button)findViewById(R.id.csimbtn);
		btn3 = (Button)findViewById(R.id.dsimbtn);
		
		btn4 = (Button)findViewById(R.id.ephonebtn);
				
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "please  don't use this now .It is to be used in the future. Tanks for being honest  ",Toast.LENGTH_LONG).show();
				Intent intent22 = new Intent(OptionsActy.this,Edit.class);
				startActivity(intent22);
			}
		});
		
		/*
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Toast.makeText(getApplicationContext(), "get premium version",Toast.LENGTH_LONG).show();
			}
		});

		
		btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				//Intent intent4 = new Intent(OptionsActy.this,DelActivity.class);
				
				 //startActivity(intent4);
				  Toast.makeText(getApplicationContext(), "get premium version", Toast.LENGTH_SHORT).show();
			}
		});
		*/
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options_acty, menu);
		return true;
	}

}
