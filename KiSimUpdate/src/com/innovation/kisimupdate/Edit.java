package com.gkibuh.kisimupdate;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends Activity {
 String nnumber="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		final EditText text1 = (EditText)findViewById(R.id.editText1);
		final EditText text3 = (EditText)findViewById(R.id.editText2);
		Button btns = (Button)findViewById(R.id.button1);
		btns.setOnClickListener(new OnClickListener(){
			
			public void onClick(View view)
			{
				String text = text1.getText().toString();
				String textt = text3.getText().toString();
				int k = Integer.parseInt(text);
				if(isStringInt(text)){
					ReadSIMContact(k,textt);
					
				}
			}
			
		});
	}
	

	public boolean isStringInt(String s)
	{
	    try
	    {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex)
	    {
	        return false;
	    }
	}
	private void ReadSIMContact(int position,String digit)
    {
        try
        {
            String ClsSimPhonename = null; 
            String ClsSimphoneNo = null;

            Uri simUri = Uri.parse("content://icc/adn"); 
            Cursor cursorSim = this.getContentResolver().query(simUri,null,null,null,null);

           int numContacts=cursorSim.getCount();
           int iterater=0;
            while (cursorSim.moveToNext()) 
            {      
                ClsSimPhonename =cursorSim.getString(cursorSim.getColumnIndex("name"));
                ClsSimphoneNo = cursorSim.getString(cursorSim.getColumnIndex("number"));
                ClsSimphoneNo.replaceAll("\\D","");
                ClsSimphoneNo.replaceAll("&", "");
                ClsSimPhonename=ClsSimPhonename.replace("|","");
                
            //every work will be done here 
                
                  nnumber=ClsSimphoneNo;
                  
                 if(nnumber.length()==12 || nnumber.length()==8){
                	 
                	 if(nnumber.charAt(0)=='+'){
                		 
                		 nnumber = nnumber.substring(0, 3+position-1)+digit+nnumber.substring(3+position, 12);
                		 
                	 }
                	 

                	 if(nnumber.length() == 8){
                		 
                		 if(position - 1 < 1){
                			 nnumber = digit+nnumber;               			 
                		 }
                		 else{
                			 nnumber = nnumber.substring(0,position-1)+digit+ nnumber.substring(position,8); 
                		 }
                		 
                	 }
                	 
                 }
                  
                try{
                	
                	if(!ClsSimphoneNo.equals(nnumber)){
                		
                		SimUpdate(Edit.this,ClsSimPhonename, ClsSimphoneNo,ClsSimPhonename+" ",nnumber); 
                	}
                      
                   }catch(Exception ex){
            	   ex.printStackTrace();
                  }
               iterater++; 
               Toast.makeText(getApplicationContext(),iterater+" out of "+numContacts, Toast.LENGTH_LONG).show();
               cursorSim.close(); 
            }  
            Toast.makeText(getApplicationContext(),"congratulations you can now restart your phone all is right", Toast.LENGTH_LONG).show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    
    // update a sim contact
    
    public void SimUpdate(Activity activity,String oldName, String oldNumber, String newName, String newNumber) {  
        Uri uri = Uri.parse("content://icc/adn");  
      try{
        ContentValues values = new ContentValues();  
        values.put("tag", oldName);  
        values.put("number",oldNumber);  
        values.put("newTag",newName);  
        values.put("newNumber",newNumber);  
        activity.getContentResolver().update(uri, values, null, null); 
        values.clear();
      }catch(Exception ex){
    	  ex.printStackTrace(); 
    	  }
    } 


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

}
