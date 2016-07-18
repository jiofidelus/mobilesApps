package com.gkibuh.kisimupdate;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.ECLAIR)
public class Kiupdate extends Activity {

	
	String nnumber = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kiupdate);
		

		Button btn1 ,btn2,btn3,btn4;
		
		btn1 = (Button)findViewById(R.id.esimbtn);
		btn2 = (Button)findViewById(R.id.csimbtn);
		btn3 = (Button)findViewById(R.id.dsimbtn);
		btn4 = (Button)findViewById(R.id.ephonebtn);
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "Please Wait...",Toast.LENGTH_LONG).show();
				
				ReadSIMContact();		
			}
		});
		

		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Toast.makeText(getApplicationContext(),"Please Wait...",Toast.LENGTH_LONG).show();
				
				 //Intent inter = new Intent(Kiupdate.this,PhoneActivity.class);
				 //startActivity(inter);
				displayContacts();
				//updateContact("kibuh","79764864");
				
								
			}
		});
		
		btn3.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View view) {
				
				Intent intent =  new Intent(Kiupdate.this,OptionsActy.class);
				 startActivity(intent);
				
			}
		});
		
		btn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intet = new Intent(Kiupdate.this,About.class);
				startActivity(intet);
				
			}
		});
	}
	
	

	
	// Here are the operations on sim 

    private void ReadSIMContact()
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
                		 
                		 switch(nnumber.charAt(4)){
                		 
                		 case '5' :  nnumber = "6"+nnumber.substring(4, 12);
                		               break;
                		 case '6' :  nnumber = "6"+nnumber.substring(4, 12);
                		               break;
                		 case '7' :  nnumber = "6"+nnumber.substring(4, 12);
                		               break;
                		 case '9' :  nnumber = "6"+nnumber.substring(4, 12);
                		               break;
                		 case '2' :  nnumber = "2"+nnumber.substring(4, 12);
                		               break;
                		 case '3' :  nnumber = "2"+nnumber.substring(4, 12);
                		               break;
                         default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                      break;
                		 
                		 }
                		 
                	 }
                	 

                	 if(nnumber.length() == 8){
                		 
                		 switch(nnumber.charAt(0)){
                		 
                		 case '5' :  nnumber = "6"+nnumber;
                		               break;
                		 case '6' :  nnumber = "6"+nnumber;
                		               break;
                		 case '7' :  nnumber = "6"+nnumber;
                		               break;
                		 case '9' :  nnumber = "6"+nnumber;
                		               break;
                		 case '2' :  nnumber = "2"+nnumber;
                		               break;
                		 case '3' :  nnumber = "2"+nnumber;
                		               break;
                         default:    Toast.makeText(Kiupdate.this, "is not in range", Toast.LENGTH_SHORT).show();
                                      break;
                		 
                		 }
                		 
                	 }
                	 
                 }
                  
                try{
                	
                	if(!ClsSimphoneNo.equals(nnumber)){
                		Toast.makeText(getApplicationContext(), "Name: "+ClsSimPhonename+"  Old number: "+ClsSimphoneNo+"\n"+"\t\t\t\t"+" New number: "+nnumber,Toast.LENGTH_SHORT).show();
                		SimUpdate(Kiupdate.this,ClsSimPhonename, ClsSimphoneNo,ClsSimPhonename+" ",nnumber); 
                	}
                      
                   }catch(Exception ex){
            	   ex.printStackTrace();
                  }
               iterater++; 
               Toast.makeText(getApplicationContext(),iterater+" out of "+numContacts, Toast.LENGTH_SHORT).show();
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
    
    // delete a sim contact

    public void SimDelete(Activity activity,String name, String phoneNumber) {  
        Uri uri = Uri.parse("content://icc/adn");  
        Cursor cursor = activity.getContentResolver().query(uri, null, null,  
                null, null);    
        
        while (cursor.moveToNext()) {  

            String where = "tag='" + name + "'";  
            where += " AND number='" + phoneNumber + "'";  
            activity.getContentResolver().delete(uri, where, null);  
        }  
    }  
    
    // display contacts
    

    public void displayContacts() {
    	
    	ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
        	while (cur.moveToNext()) {
        		String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
        		String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        		if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                     Cursor pCur = cr.query(
                    		 ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
                    		 null, 
                    		 ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
                    		 new String[]{id}, null);
                     while (pCur.moveToNext()) {
                    	 String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    	 String idcont = pCur.getString(pCur.getColumnIndex(Data.CONTACT_ID));
                    	 
                    	 int phone_type = pCur.getInt(pCur.getColumnIndex(Phone.TYPE));  
                         switch (phone_type) 
                         {    
                         case Phone.TYPE_HOME: 
                              String phone_home =pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                 phone_home = phone_home.replaceAll("-", "");
                                 phone_home = phone_home.replaceAll(" ", "");
                                 nnumber = phone_home;
                                 
                              if(nnumber.length()==12 || nnumber.length()==10 ||nnumber.length()==8 || nnumber.length()==11){
                             	 
                             	 if(nnumber.charAt(0)=='+'){
                             		 
                             		 switch(nnumber.charAt(4)){
                             		 
                             		 case '5' :  nnumber = "6"+nnumber.substring(4, 12);
                             		               break;
                             		 case '6' :  nnumber = "6"+nnumber.substring(4, 12);
                             		               break;
                             		 case '7' :  nnumber = "6"+nnumber.substring(4, 12);
                             		               break;
                             		 case '9' :  nnumber = "6"+nnumber.substring(4, 12);
                             		               break;
                             		 case '2' :  nnumber = "2"+nnumber.substring(4, 12);
                             		               break;
                             		 case '3' :  nnumber = "2"+nnumber.substring(4, 12);
                             		               break;
                                      default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                                   break;
                             		 
                             		 }
                             	 }	 

                                 	 if(nnumber.length()== 11){
                                 		 
                                 		 switch(nnumber.charAt(3)){
                                 		 
                                 		 case '5' :  nnumber = "6"+nnumber.substring(3, 11);
                                 		               break;
                                 		 case '6' :  nnumber = "6"+nnumber.substring(3, 11);
                                 		               break;
                                 		 case '7' :  nnumber = "6"+nnumber.substring(3, 11);
                                 		               break;
                                 		 case '9' :  nnumber = "6"+nnumber.substring(3, 11);
                                 		               break;
                                 		 case '2' :  nnumber = "2"+nnumber.substring(3, 11);
                                 		               break;
                                 		 case '3' :  nnumber = "2"+nnumber.substring(3, 11);
                                 		               break;
                                          default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                                       break;
                                 		 
                                 		 }
                             		 
                             	 }
                             	 

                             	 if(nnumber.length()==8){
                             		 
                             		 switch(nnumber.charAt(0)){
                             		 
                             		 case '5' :  nnumber = "6"+nnumber;
                             		               break;
                             		 case '6' :  nnumber = "6"+nnumber;
                             		               break;
                             		 case '7' :  nnumber = "6"+nnumber;
                             		               break;
                             		 case '9' :  nnumber = "6"+nnumber;
                             		               break;
                             		 case '2' :  nnumber = "2"+nnumber;
                             		               break;
                             		 case '3' :  nnumber = "2"+nnumber;
                             		               break;
                                      default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                                   break;
                             		 
                             		 }
                             		 
                             	 }
                             	 

                             	 if(nnumber.length() == 10){
                             		 
                             		 if(nnumber.charAt(0)=='('){
                             			 

                                 		 switch(nnumber.charAt(1)){
                                 		 
                                 		 case '5' :  nnumber = "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                                 		               break;
                                 		 case '6' :  nnumber =  "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                                 		               break;
                                 		 case '7' :  nnumber =  "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                                 		               break;
                                 		 case '9' :  nnumber =  "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                                 		               break;
                                 		 case '2' :  nnumber =  "2"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                                 		               break;
                                 		 case '3' :  nnumber =  "2"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                                 		               break;
                                          default:    Toast.makeText(Kiupdate.this, "is not in range", Toast.LENGTH_SHORT).show();
                                                       break;
                                 		 
                                 		 }
                             			 
                             		 }
                             	 
                             	 }
                             	 
                              }
                                if(!phone_home.equals(nnumber)){
                                	try{
                                		Toast.makeText(Kiupdate.this, "Name: " + name + "Old Phone No: " + phone_home+"\n\t\t\t\t New Number :"+nnumber, Toast.LENGTH_SHORT).show();
                                		updatePhoneNumberHome(idcont,nnumber);
                                	}catch(Exception ex){
                                		
                                		ex.printStackTrace();
                                	}
                                }
                              break;          
                         case Phone.TYPE_MOBILE:    
                              String phone_mob=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                
                              phone_mob = phone_mob.replaceAll("-", "");
                              phone_mob = phone_mob.replaceAll(" ", "");
                              nnumber = phone_mob;
                           if(nnumber.length()==12 || nnumber.length()==10 ||nnumber.length()==8 || nnumber.length()==11){
                          	 
                          	 if(nnumber.charAt(0)=='+'){
                          		 
                          		 switch(nnumber.charAt(4)){
                          		 
                          		 case '5' :  nnumber = "6"+nnumber.substring(4, 12);
                          		               break;
                          		 case '6' :  nnumber = "6"+nnumber.substring(4, 12);
                          		               break;
                          		 case '7' :  nnumber = "6"+nnumber.substring(4, 12);
                          		               break;
                          		 case '9' :  nnumber = "6"+nnumber.substring(4, 12);
                          		               break;
                          		 case '2' :  nnumber = "2"+nnumber.substring(4, 12);
                          		               break;
                          		 case '3' :  nnumber = "2"+nnumber.substring(4, 12);
                          		               break;
                                   default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                                break;
                          		 
                          		 }
                          	 }	 

                              	 if(nnumber.length()== 11){
                              		 
                              		 switch(nnumber.charAt(3)){
                              		 
                              		 case '5' :  nnumber = "6"+nnumber.substring(3, 11);
                              		               break;
                              		 case '6' :  nnumber = "6"+nnumber.substring(3, 11);
                              		               break;
                              		 case '7' :  nnumber = "6"+nnumber.substring(3, 11);
                              		               break;
                              		 case '9' :  nnumber = "6"+nnumber.substring(3, 11);
                              		               break;
                              		 case '2' :  nnumber = "2"+nnumber.substring(3, 11);
                              		               break;
                              		 case '3' :  nnumber = "2"+nnumber.substring(3, 11);
                              		               break;
                                       default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                                    break;
                              		 
                              		 }
                          		 
                          	 }
                          	 

                          	 if(nnumber.length()==8){
                          		 
                          		 switch(nnumber.charAt(0)){
                          		 
                          		 case '5' :  nnumber = "6"+nnumber;
                          		               break;
                          		 case '6' :  nnumber = "6"+nnumber;
                          		               break;
                          		 case '7' :  nnumber = "6"+nnumber;
                          		               break;
                          		 case '9' :  nnumber = "6"+nnumber;
                          		               break;
                          		 case '2' :  nnumber = "2"+nnumber;
                          		               break;
                          		 case '3' :  nnumber = "2"+nnumber;
                          		               break;
                                   default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                                break;
                          		 
                          		 }
                          		 
                          	 }
                          	 

                          	 if(nnumber.length() == 10){
                          		 
                          		 if(nnumber.charAt(0)=='('){
                          			 

                              		 switch(nnumber.charAt(1)){
                              		 
                              		 case '5' :  nnumber = "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                              		               break;
                              		 case '6' :  nnumber =  "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                              		               break;
                              		 case '7' :  nnumber =  "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                              		               break;
                              		 case '9' :  nnumber =  "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                              		               break;
                              		 case '2' :  nnumber =  "2"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                              		               break;
                              		 case '3' :  nnumber =  "2"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                              		               break;
                                       default:    Toast.makeText(Kiupdate.this, "is not in range", Toast.LENGTH_SHORT).show();
                                                    break;
                              		 
                              		 }
                          			 
                          		 }
                          	 
                          	 }
                          	 
                           }
                             if(!phone_mob.equals(nnumber)){
                             	try{
                             		Toast.makeText(Kiupdate.this, "Name: " + name + "Old Phone No: " + phone_mob+"\n\t\t\t\t New Number :"+nnumber, Toast.LENGTH_SHORT).show();
                             		updatePhoneNumberMobile(idcont,nnumber);
                             	}catch(Exception ex){
                             		
                             		ex.printStackTrace();
                             	}
                             }

                              break;            
                         case Phone.TYPE_WORK:                                
                              String phone_work=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                               
                               phone_work = phone_work.replaceAll("-", "");
                               phone_work = phone_work.replaceAll(" ", "");
                               nnumber = phone_work;
                            if(nnumber.length()==12 || nnumber.length()==10 ||nnumber.length()==8 || nnumber.length()==11){
                           	 
                           	 if(nnumber.charAt(0)=='+'){
                           		 
                           		 switch(nnumber.charAt(4)){
                           		 
                           		 case '5' :  nnumber = "6"+nnumber.substring(4, 12);
                           		               break;
                           		 case '6' :  nnumber = "6"+nnumber.substring(4, 12);
                           		               break;
                           		 case '7' :  nnumber = "6"+nnumber.substring(4, 12);
                           		               break;
                           		 case '9' :  nnumber = "6"+nnumber.substring(4, 12);
                           		               break;
                           		 case '2' :  nnumber = "2"+nnumber.substring(4, 12);
                           		               break;
                           		 case '3' :  nnumber = "2"+nnumber.substring(4, 12);
                           		               break;
                                    default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                                 break;
                           		 
                           		 }
                           	 }	 

                               	 if(nnumber.length()== 11){
                               		 
                               		 switch(nnumber.charAt(3)){
                               		 
                               		 case '5' :  nnumber = "6"+nnumber.substring(3, 11);
                               		               break;
                               		 case '6' :  nnumber = "6"+nnumber.substring(3, 11);
                               		               break;
                               		 case '7' :  nnumber = "6"+nnumber.substring(3, 11);
                               		               break;
                               		 case '9' :  nnumber = "6"+nnumber.substring(3, 11);
                               		               break;
                               		 case '2' :  nnumber = "2"+nnumber.substring(3, 11);
                               		               break;
                               		 case '3' :  nnumber = "2"+nnumber.substring(3, 11);
                               		               break;
                                        default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                                     break;
                               		 
                               		 }
                           		 
                           	 }
                           	 

                           	 if(nnumber.length()==8){
                           		 
                           		 switch(nnumber.charAt(0)){
                           		 
                           		 case '5' :  nnumber = "6"+nnumber;
                           		               break;
                           		 case '6' :  nnumber = "6"+nnumber;
                           		               break;
                           		 case '7' :  nnumber = "6"+nnumber;
                           		               break;
                           		 case '9' :  nnumber = "6"+nnumber;
                           		               break;
                           		 case '2' :  nnumber = "2"+nnumber;
                           		               break;
                           		 case '3' :  nnumber = "2"+nnumber;
                           		               break;
                                    default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                                 break;
                           		 
                           		 }
                           		 
                           	 }
                           	 

                           	 if(nnumber.length() == 10){
                           		 
                           		 if(nnumber.charAt(0)=='('){
                           			 

                               		 switch(nnumber.charAt(1)){
                               		 
                               		 case '5' :  nnumber = "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                               		               break;
                               		 case '6' :  nnumber =  "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                               		               break;
                               		 case '7' :  nnumber =  "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                               		               break;
                               		 case '9' :  nnumber =  "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                               		               break;
                               		 case '2' :  nnumber =  "2"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                               		               break;
                               		 case '3' :  nnumber =  "2"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                               		               break;
                                        default:    Toast.makeText(Kiupdate.this, "is not in range", Toast.LENGTH_SHORT).show();
                                                     break;
                               		 
                               		 }
                           			 
                           		 }
                           	 
                           	 }
                           	 
                            }
                              if(!phone_work.equals(nnumber)){
                              	try{
                              		Toast.makeText(Kiupdate.this, "Name: " + name + "Old Phone No: " + phone_work+"\n\t\t\t\t New Number :"+nnumber, Toast.LENGTH_SHORT).show();
                              		updatePhoneNumberWork(idcont,nnumber);
                              	}catch(Exception ex){
                              		
                              		ex.printStackTrace();
                              	}
                              }

                                      break; 
                         case Phone.TYPE_OTHER:                                
                             String phone_other=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                              phone_other = phone_other.replaceAll("-", "");
                              phone_other = phone_other.replaceAll(" ", "");
                              nnumber = phone_other;
                           if(nnumber.length()==12 || nnumber.length()==10 ||nnumber.length()==8 || nnumber.length()==11){
                          	 
                          	 if(nnumber.charAt(0)=='+'){
                          		 
                          		 switch(nnumber.charAt(4)){
                          		 
                          		 case '5' :  nnumber = "6"+nnumber.substring(4, 12);
                          		               break;
                          		 case '6' :  nnumber = "6"+nnumber.substring(4, 12);
                          		               break;
                          		 case '7' :  nnumber = "6"+nnumber.substring(4, 12);
                          		               break;
                          		 case '9' :  nnumber = "6"+nnumber.substring(4, 12);
                          		               break;
                          		 case '2' :  nnumber = "2"+nnumber.substring(4, 12);
                          		               break;
                          		 case '3' :  nnumber = "2"+nnumber.substring(4, 12);
                          		               break;
                                   default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                                break;
                          		 
                          		 }
                          	 }	 

                              	 if(nnumber.length()== 11){
                              		 
                              		 switch(nnumber.charAt(3)){
                              		 
                              		 case '5' :  nnumber = "6"+nnumber.substring(3, 11);
                              		               break;
                              		 case '6' :  nnumber = "6"+nnumber.substring(3, 11);
                              		               break;
                              		 case '7' :  nnumber = "6"+nnumber.substring(3, 11);
                              		               break;
                              		 case '9' :  nnumber = "6"+nnumber.substring(3, 11);
                              		               break;
                              		 case '2' :  nnumber = "2"+nnumber.substring(3, 11);
                              		               break;
                              		 case '3' :  nnumber = "2"+nnumber.substring(3, 11);
                              		               break;
                                       default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                                    break;
                              		 
                              		 }
                          		 
                          	 }
                          	 

                          	 if(nnumber.length()==8){
                          		 
                          		 switch(nnumber.charAt(0)){
                          		 
                          		 case '5' :  nnumber = "6"+nnumber;
                          		               break;
                          		 case '6' :  nnumber = "6"+nnumber;
                          		               break;
                          		 case '7' :  nnumber = "6"+nnumber;
                          		               break;
                          		 case '9' :  nnumber = "6"+nnumber;
                          		               break;
                          		 case '2' :  nnumber = "2"+nnumber;
                          		               break;
                          		 case '3' :  nnumber = "2"+nnumber;
                          		               break;
                                   default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                                break;
                          		 
                          		 }
                          		 
                          	 }
                          	 

                          	 if(nnumber.length() == 10){
                          		 
                          		 if(nnumber.charAt(0)=='('){
                          			 

                              		 switch(nnumber.charAt(1)){
                              		 
                              		 case '5' :  nnumber = "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                              		               break;
                              		 case '6' :  nnumber =  "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                              		               break;
                              		 case '7' :  nnumber =  "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                              		               break;
                              		 case '9' :  nnumber =  "6"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                              		               break;
                              		 case '2' :  nnumber =  "2"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                              		               break;
                              		 case '3' :  nnumber =  "2"+nnumber.substring(1, 3)+nnumber.substring(5, 10);
                              		               break;
                                       default:    Toast.makeText(Kiupdate.this, "is not in range", Toast.LENGTH_SHORT).show();
                                                    break;
                              		 
                              		 }
                          			 
                          		 }
                          	 
                          	 }
                          	 
                           }
                             if(!phone_other.equals(nnumber)){
                             	try{
                             		Toast.makeText(Kiupdate.this, "Name: " + name + "Old Phone No: " + phone_other+"\n\t\t\t\t New Number :"+nnumber, Toast.LENGTH_SHORT).show();
                             		updatePhoneNumberOther(idcont,nnumber);
                             	}catch(Exception ex){
                             		
                             		ex.printStackTrace();
                             	}
                             }

                                     break;                                      
                          }
                         
                    	 
                     } 
      	        pCur.close();
      	    }
        	}
        }
        Toast.makeText(getApplicationContext(), "Congratulation All your Contacts now respect the comeroon code",Toast.LENGTH_LONG).show();
    }
    
    //update phone number

    public void updateContact(String name, String phone) {
    	ContentResolver cr = getContentResolver();
 
        String where = ContactsContract.Data.DISPLAY_NAME + " = ? AND " + 
        			ContactsContract.Data.MIMETYPE + " = ? AND " +
        			String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE) + " = ? ";
        String[] params = new String[] {name,
        		ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
        		String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)};

        Cursor phoneCur = cr.query(ContactsContract.Data.CONTENT_URI, null, where, params, null);
        
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        
        if ( (null == phoneCur)  ) {
        
        	ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
        	        .withSelection(where, params)
        	        .withValue(ContactsContract.CommonDataKinds.Phone.DATA, phone)
        	        .build());
        }
        
        phoneCur.close();
        
        try {
			cr.applyBatch(ContactsContract.AUTHORITY, ops);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			e.printStackTrace();
		}

		Toast.makeText(Kiupdate.this, "Updated the phone number of 'Sample Name' to: " + phone, Toast.LENGTH_SHORT).show();
    }
    
    //contacts with type MOBILE
    
    public void updatePhoneNumberMobile(String contactid, String phoneNo) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        Builder builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI);
        builder.withSelection(ContactsContract.Data.CONTACT_ID + "=?" + " AND " +
              ContactsContract.Data.MIMETYPE + "=?"+ " AND " +
              ContactsContract.CommonDataKinds.Organization.TYPE + "=?",
              new String[]{String.valueOf(contactid), ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
              String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)});
        builder.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNo);
        ops.add(builder.build());

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            // handle error
        }
    }
    
    //contacts with type  HOME
    
    public void updatePhoneNumberHome(String contactid, String phoneNo) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        Builder builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI);
        builder.withSelection(ContactsContract.Data.CONTACT_ID + "=?" + " AND " +
              ContactsContract.Data.MIMETYPE + "=?"+ " AND " +
              ContactsContract.CommonDataKinds.Organization.TYPE + "=?",
              new String[]{String.valueOf(contactid), ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
              String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_HOME)});
        builder.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNo);
        ops.add(builder.build());

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            // handle error
        }
    }
    
    //contacts with type WORK
    
    public void updatePhoneNumberWork(String contactid, String phoneNo) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        Builder builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI);
        builder.withSelection(ContactsContract.Data.CONTACT_ID + "=?" + " AND " +
              ContactsContract.Data.MIMETYPE + "=?"+ " AND " +
              ContactsContract.CommonDataKinds.Organization.TYPE + "=?",
              new String[]{String.valueOf(contactid), ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
              String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_WORK)});
        builder.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNo);
        ops.add(builder.build());

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            // handle error
        }
    }
    
    //contacts with type OTHERS
    
    public void updatePhoneNumberOther(String contactid, String phoneNo) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        Builder builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI);
        builder.withSelection(ContactsContract.Data.CONTACT_ID + "=?" + " AND " +
              ContactsContract.Data.MIMETYPE + "=?"+ " AND " +
              ContactsContract.CommonDataKinds.Organization.TYPE + "=?",
              new String[]{String.valueOf(contactid), ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
              String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_OTHER)});
        builder.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNo);
        ops.add(builder.build());

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            // handle error
        }
    }
     

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kiupdate, menu);
		return true;
	}
	
}
