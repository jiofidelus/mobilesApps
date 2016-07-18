package com.gkibuh.kisimupdate;

import java.util.ArrayList;

import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.view.Menu;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.ECLAIR)
@SuppressLint("NewApi")
public class PhoneActivity extends Activity {
 
	String nnumber="";
	android.content.ContentProviderOperation.Builder builder;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone);
		ReadPhoneContacts();
	}


	   
	@SuppressLint("NewApi")
	private void ReadPhoneContacts() {
    	
    	ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
        if (cur.getCount() > 0) {
        	while (cur.moveToNext()) {
        		String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
        		String contname = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        		if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                     Cursor pCur = cr.query(
                    		 ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, 
                    		 ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
                    		 new String[]{id}, null);
                     while (pCur.moveToNext()) {
                    	 String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    	 
                    	 String contactid = pCur.getString(pCur.getColumnIndex(Data.CONTACT_ID));
                    	 //all is done here
                         phoneNo = phoneNo.replaceAll("-","");
                         phoneNo = phoneNo.replaceAll(" ","");
                         
                        
                         
                         phoneNo.trim();
                         nnumber=phoneNo;
                                 
                        if(nnumber.length()==12 || nnumber.length()==10 ||nnumber.length()==8 || nnumber.length()==11){
                       	 
                       	 if(nnumber.charAt(0)=='+'){
                       		 
                       		 switch(nnumber.charAt(4)){
                       		 
                       		 case '5' :  nnumber = nnumber.substring(0, 4)+"6"+nnumber.substring(4, 12);
                       		               break;
                       		 case '6' :  nnumber = nnumber.substring(0, 4)+"6"+nnumber.substring(4, 12);
                       		               break;
                       		 case '7' :  nnumber = nnumber.substring(0, 4)+"6"+nnumber.substring(4, 12);
                       		               break;
                       		 case '9' :  nnumber = nnumber.substring(0, 4)+"6"+nnumber.substring(4, 12);
                       		               break;
                       		 case '2' :  nnumber = nnumber.substring(0, 4)+"2"+nnumber.substring(4, 12);
                       		               break;
                       		 case '3' :  nnumber =nnumber.substring(0, 4)+"2"+nnumber.substring(4, 12);
                       		               break;
                                default:    Toast.makeText(getApplicationContext(), "is not in range", Toast.LENGTH_SHORT).show();
                                             break;
                       		 
                       		 }
                       	 }	 

                           	 if(nnumber.length()== 11){
                           		 
                           		 switch(nnumber.charAt(3)){
                           		 
                           		 case '5' :  nnumber = nnumber.substring(0,3)+"6"+nnumber.substring(3, 11);
                           		               break;
                           		 case '6' :  nnumber = nnumber.substring(0,3)+"6"+nnumber.substring(3, 11);
                           		               break;
                           		 case '7' :  nnumber = nnumber.substring(0,3)+"6"+nnumber.substring(3, 11);
                           		               break;
                           		 case '9' :  nnumber = nnumber.substring(0,3)+"6"+nnumber.substring(3, 11);
                           		               break;
                           		 case '2' :  nnumber = nnumber.substring(0,3)+"2"+nnumber.substring(3, 11);
                           		               break;
                           		 case '3' :  nnumber = nnumber.substring(0,3)+"2"+nnumber.substring(3, 11);
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
                           		 
                           		 case '5' :  nnumber = "6"+nnumber.substring(1, 4)+nnumber.substring(5, 10);
                           		               break;
                           		 case '6' :  nnumber =  "6"+nnumber.substring(1, 4)+nnumber.substring(5, 10);
                           		               break;
                           		 case '7' :  nnumber =  "6"+nnumber.substring(1, 4)+nnumber.substring(5, 10);
                           		               break;
                           		 case '9' :  nnumber =  "6"+nnumber.substring(1, 4)+nnumber.substring(5, 10);
                           		               break;
                           		 case '2' :  nnumber =  "2"+nnumber.substring(1, 4)+nnumber.substring(5, 10);
                           		               break;
                           		 case '3' :  nnumber =  "2"+nnumber.substring(1, 4)+nnumber.substring(5, 10);
                           		               break;
                                    default:    Toast.makeText(PhoneActivity.this, "is not in range", Toast.LENGTH_SHORT).show();
                                                break;
                           		 
                           		 }
                       			 
                       		 }
                       	 
                       	 }
                       	 
                        }
      
                        
                        try{
                        	
                        	if(!phoneNo.equals(nnumber)){
                        		Toast.makeText(getApplicationContext(),"old: "+phoneNo + "new: "+nnumber,Toast.LENGTH_LONG).show();
                				
                        		updatePhoneNumber(contactid,nnumber);
                        	}
                        	
                        }catch(Exception ex){
                        	ex.printStackTrace();
                        }
                              
                       } 
                     pCur.close();    
                          	        
      	         }
        		
           }
        	cur.close();
        	
        }
    }

    //update contact from phone
   
	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.ECLAIR)
	private void updateContact(String name, String phone){
    	ContentResolver cr = getContentResolver();
 
        String where = ContactsContract.Data.DISPLAY_NAME + " = ? AND " + 
        			ContactsContract.Data.MIMETYPE + " = ? AND " +
        			String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE) + " = ? ";
        String[] params = new String[] {name,
        		ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
        		String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)};

        Cursor phoneCur = cr.query(ContactsContract.Data.CONTENT_URI, null, where, params, null);
        
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        
        if ( (phoneCur!=null)  ) {
       
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

		
	}
    
    // delete a  contact fro phone
    
    
	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.ECLAIR)
	private void deleteContact(String name) {

    	ContentResolver cr = getContentResolver();
    	String where = ContactsContract.Data.DISPLAY_NAME + " = ? ";
    	String[] params = new String[] {name};
    
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ops.add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI)
    	        .withSelection(where, params)
    	        .build());
        try {
			cr.applyBatch(ContactsContract.AUTHORITY, ops);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			e.printStackTrace();
		}

	}
	

	@SuppressLint("NewApi")
	void updatePhoneNumber(String contactid, String phoneNo) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI);
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phone, menu);
		return true;
	}

}
