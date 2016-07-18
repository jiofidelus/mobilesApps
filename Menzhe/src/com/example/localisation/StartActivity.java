package com.example.localisation;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StartActivity extends Activity {
private ListView lv=null;
ArrayAdapter<Message> adapter=null;
public static String LONG="longitude";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		 Uri allMessage = Uri.parse("content://sms/");
	        ContentResolver cr = getContentResolver();
	        Cursor c = cr.query(allMessage, null, null, null, null);
	       
	        ArrayList<Message> list=new ArrayList<Message>();
	       lv=(ListView) findViewById(R.id.listView1); 
	      while  (c.moveToNext()) 
	        {
	        	Log.i("num ",c.getString(2));
	        	Log.i("message ",c.getString(11));
	        	Log.i("date ",c.getString(4));
	        	if(c.getString(2).equals("+23794766201"))
	        list.add(new Message(c.getString(2),c.getString(11),c.getString(4)));
	        }
	       c.close();
	      //list.add(new Message("98675453","salut&3.876772&43.613087","09-05-2013"));
	      // list.add(new Message("98675452","salut&1.44455&2.3334","09-05-2013"));
	      // list.add(new Message("98675451","salut&6.4455&45.667777","09-05-2013"));
	      // list.add(new Message("98675450","salut","09-05-2013"));
	        adapter = new ArrayAdapter<Message>(this,
		     		android.R.layout.simple_list_item_1,list);
	        lv.setAdapter(adapter);
	        lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub
					Message message=adapter.getItem(position);
					Log.i("num ",message.getNumero());
		        	Log.i("message ",message.getMessage());
		        	Log.i("date ",message.getDate());
		        	String coordlong="",coordlarg="";
		        	 Intent secondeActivite = new Intent(StartActivity.this,MainActivity.class);
		        	 boolean test=false;
		        	try{
		        	StringTokenizer st=new StringTokenizer(message.getMessage(),"&");
		        	st.nextToken();
		        	coordlong=st.nextToken();
		        	coordlarg=st.nextToken();
		        	Log.i("coordlong",coordlong);
		        	Log.i("coordlarg",coordlarg);
		 	        // On rajoute un extra
		        	secondeActivite.putExtra(LONG,new float[]{Float.parseFloat(coordlong),Float.parseFloat(coordlarg)});
		 	  
		 	        // Puis on lance l'intent !
		 	        
		        	}catch(Exception e){test=true;}
		        	
		        	if(test==false)
		        		startActivity(secondeActivite);
		        	else
		        		showDialog(1);	
				}
	        	
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}
	
	public Dialog onCreateDialog (int id) {
		  Dialog box = null;
		  switch(id) {	 
		  case 1:
		    box = new Dialog(this);
		    box.setTitle("Localisation Absente!");
		    break;

		  }
		  return box;
		}

}
