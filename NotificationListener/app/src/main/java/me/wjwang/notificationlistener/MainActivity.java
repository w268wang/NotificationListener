package me.wjwang.notificationlistener;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.content.BroadcastReceiver;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private BroadcastReceiver dataUpdaterReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("in broadcast receiver");
            String message = intent.getStringExtra("message");
            TextView tv = (TextView) findViewById(R.id.textField);
            tv.setText(message);

            try {
                System.out.println(message);
                // Communicate with server
                //String baseUrl = "http://wjwang.me/";
                String baseUrl = "http://10.0.2.2:8080/wood/";
                String url = baseUrl + "rest/ray/notification?content=" + message;
                System.out.println("SEND");
                url = url.replace(" ", "/s");
                url = url.replace("|", "/S");
                HttpResponse response = new RestTask().execute(url).get();
                System.out.println("Post send " + response.getStatusLine().getStatusCode());
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    response.getEntity().getContent().close();
                    throw new IOException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View view) {
        findViewById(R.id.startService).setEnabled(false);
        TextView tv = (TextView) findViewById(R.id.textField);
        tv.setText(NotificationService.MSG);
        LocalBroadcastManager.getInstance(this).registerReceiver(dataUpdaterReceiver,
                new IntentFilter(NotificationService.syncName));
        Intent nsIntent = new Intent(this, NotificationService.class);
        startService(nsIntent);
    }

    public void sendNotification(View view) {
        NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder ncomp = new NotificationCompat.Builder(this);
        ncomp.setContentTitle("My Notification");
        ncomp.setContentText("Notification Listener Service Example");
        ncomp.setTicker("Notification Listener Service Example");
        ncomp.setSmallIcon(R.drawable.ic_launcher);
        ncomp.setAutoCancel(true);
        nManager.notify((int)System.currentTimeMillis(),ncomp.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
