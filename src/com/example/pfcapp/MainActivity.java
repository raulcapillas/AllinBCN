package com.example.pfcapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity implements SensorEventListener, LocationListener{

	// sensores y localizacion
	private LocationManager locationManager = null;
	private TextView gpsLoc = null;
	
	InputStream is = null;
	StringBuilder sb=null;
	String result = null;
	JSONArray jArray;
	
	String user;
	TextView txt_usr;
	String IP_Server="192.168.1.35";
    //String IP_Server="193.145.34.109";
    String URL_connect="http://"+IP_Server+"/pfc/acces.php";//ruta en donde estan nuestros archivos
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        HTTPconection();
        
        txt_usr= (TextView) findViewById(R.id.usr_name);
        txt_usr.setText(result);
        gpsLoc = (TextView) findViewById(R.id.gpsbox);
        
        // localizacion
     		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
     		if (locationManager == null) {
     			Toast.makeText(this, "Error al recuperar el LocationManager",Toast.LENGTH_LONG).show();
     		}
     		try {
     	// recepcion de datos del GPS
     			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 20,(LocationListener) this);
     		} catch (Exception e) {
     			Toast.makeText(this, "Error al inicializar el GPS",Toast.LENGTH_LONG).show();
     		}
    }

    public void HTTPconection (){
		//http post
       try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(URL_connect);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
       }catch(Exception e){
                Log.e("log_tag", "Error in http connection"+e.toString());
       }
       //convert response to string
       try{
             BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
              sb = new StringBuilder();
              sb.append(reader.readLine() + "\n");

              String line="0";
              while ((line = reader.readLine()) != null) {
                             sb.append(line + "\n");
               }
               is.close();
               result=sb.toString();
        }catch(Exception e){
               Log.e("log_tag", "Error converting result "+e.toString());
        }
    }
    
	public void onLocationChanged(Location location) {
		String lat = String.valueOf(location.getLatitude());
		String lon = String.valueOf(location.getLongitude());
		gpsLoc.setText(location.getProvider() + ": lat=" + lat + " lon=" + lon);
	}

	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Provider deshabilitado", Toast.LENGTH_LONG).show();
		if ("GPS".equalsIgnoreCase(provider)) {
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 20,(LocationListener) this);
		}
	}

	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Provider habilitado", Toast.LENGTH_LONG).show();
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}
    
}
