package com.example.administrator.fanikiwapeertopeerlending;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import static com.example.administrator.fanikiwapeertopeerlending.R.layout.xamarinandroid;


public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private final static String SERVICE_URI = "http://10.0.2.2:32444/Service1.svc";
    TextView textView;
    Button btnShow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(xamarinandroid);

        textView = (TextView) findViewById(R.id.latText);
        btnShow = (Button) findViewById(R.id.getWeatherButton);

        btnShow.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0){

                try {

                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet request = new HttpGet(SERVICE_URI + "/GetMessage");

                    request.setHeader("Accept", "application/xml");
                    request.setHeader("Content-type", "application/xml");

                    HttpResponse response = httpClient.execute(request);

                    HttpEntity responseEntity = response.getEntity();
                    String output = EntityUtils.toString(responseEntity);

                    //Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
                    textView.setText(output);


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}