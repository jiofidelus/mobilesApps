package com.example.localisation;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
 
 
public class MainActivity extends Activity {
 private WebView web=null;
private Button bt=null;

	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
		
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       /* Uri allMessage = Uri.parse("content://sms/");
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(allMessage, null, null, null, null);
        while  (c.moveToNext()) 
        {
        	Log.i("num ",c.getString(2));
        	Log.i("message ",c.getString(11));
        	Log.i("date ",c.getString(4));
 
        }	
        */
        Intent i = getIntent();
        float[] tab=i.getFloatArrayExtra(StartActivity.LONG);
        
	   float longitude =tab[0];
	    float latitude =tab[1];
        
	    Log.i("long",String.valueOf(longitude));
	    Log.i("lat",String.valueOf(latitude));
        web = (WebView) findViewById(R.id.webView1);
        bt=(Button) findViewById(R.id.button1);
        bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(arg0.getId()==R.id.button1){
					 Intent secondeActivite = new Intent(MainActivity.this,StartActivity.class);
			 	        // On rajoute un extra
			 	        // Puis on lance l'intent !
			 	        startActivity(secondeActivite);
				}
			}
        	
        });
        GMaps map = new GMaps(longitude,latitude);

        map.addCursor("RED", ".",longitude,latitude);
        try {
       	map.showCoordinate(500,500);
       	//map.showLocation("limbe","Cameroon",200,200);
       } catch (Exception e) {
       	// TODO Auto-generated catch block
       	e.printStackTrace();
       }
       
        
    }
	
	 class GMaps
	{
	     
	    private float longitude;
	    private float latitude;
	    private int zoomFactor = 7;
	    private String ApiKey = "ABQIAAAA5U876NY9uQtuJYcYdj0hPRSB3fYKqo9sRR7o2MKyAKh3L0V4NBREpf50rgLOScH_jEQR7BtpLbAZ0g";
	    private String roadmap = "roadmap";
	    public final String viewTerrain = "terrain";
	    public final String viewSatellite = "satellite";
	    public final String viewHybrid = "hybrid";
	    public final String viewRoadmap = "roadmap";
	    //private String bouton = "";
	    private String cursor ="";
	    private String line ="";
	 
	    public GMaps(float longitude,float latitude)
	    {
	      
	        this.setLongitude(longitude);
	        this.setLatitude(latitude);
	 
	    }/* C'EST LA FONCTION POUR RAJOUTER DES CURSEUR*/
	 
	    public void addCursor(String color,String lettre,float clongitude,float clatitude)
	    {
	        cursor +="&markers=color:"+color+"%7Clabel:"+lettre+"%7C"+clatitude+","+clongitude;
	    }
	    
	/* C'EST LA FONCTION POUR RAJOUTER DES ligne sur ta carte*/
	    public void addLine(String color,float llongitude1, float llatitude1,float llongitude2,float llatitude2) {
	        line +="&path=color:0x0000ff|weight:5|"+llatitude1+","+llongitude1+"|"+llongitude2+","+llatitude2;
	    }
	    /**
	     * Fixer le zoom
	     * @param zoom valeur de 0 � 21
	     */
	    public void setZoom(int zoom) {
	        this.zoomFactor = zoom;
	    }
	    public int getZoom() {
	        return zoomFactor;
	    }
	 
	    /**
	     * Fixer la cl� de developpeur
	     * @param key APIKey fourni par Google
	     */
	    public void setApiKey(String key) {
	        this.ApiKey = key;
	    }
	 
	    /**
	     * Fixer le type de vue
	     * @param roadMap
	     */
	    public void setRoadmap(String roadMap) {
	        this.roadmap = roadMap;
	    }
	 
	    /**
	     * Afficher la carte d'apr�s des coordonn�es GPS
	     * @param latitude
	     * @param longitude
	     * @param width
	     * @param height
	     * @throws Exception erreur si la APIKey est non renseign�e
	     */
	    public void showCoordinate(int width, int height) throws Exception {
	        this.setMap(Float.toString(latitude),Float.toString(longitude) , width, height);
	    }
	 
	    /**
	     * Afficher la carte d'apr�s une ville et son pays
	     * @param city
	     * @param country
	     * @param width
	     * @param height
	     * @throws Exception erreur si la APIKey est non renseign�e
	     */
	    public void showLocation(String city, String country, int width, int height) throws Exception {
	        this.setMap(city, country, width, height);
	    }
	 
	   /**
	    *
	    * @param x
	    * @param y
	    * @param width
	    * @param height
	    * @throws Exception
	    */
	    public void setMap(String x, String y, Integer width, Integer height) throws Exception {
	        if (this.ApiKey.equals("")) {
	            throw new Exception("Developper API Key not set !!!!");
	        }
	        String url = "http://maps.google.com/maps/api/staticmap?";
	        url += "center=" + x + "," + y;
	        url += "&amp;zoom=" + this.zoomFactor;
	        url += "&amp;size=" + width.toString() + "x" + height.toString();
	        url += "&amp;maptype=" + this.roadmap;
	        url += cursor;
	        url += line;
	        url += "&amp;sensor=false";
	       String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
	        html += "<html><head></head>";
	        html +=    "<body><img src='" + url + "'>";
	        html += "</body></html>";
	        web.loadData(html,"text/html","UTF-8");
	       
	       
	    
	    }
	    public void setLongitude(float longitude) {this.longitude = longitude;}
	    public float getLongitude() {return longitude;}
	    public void setLatitude(float latitude) {this.latitude = latitude;}
	    public float getLatitude() {return latitude;}
	   
}
	 


}
