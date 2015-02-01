package me.wjwang.notificationlistener;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

class RestTask extends AsyncTask<String, Void, HttpResponse> {

    private Exception exception;

    protected HttpResponse doInBackground(String ... url) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url[0]);
            HttpResponse response = client.execute(get);
            System.out.println(response);
            return response;
        } catch (Exception e) {
            this.exception = e;
            System.out.println(e);
            return null;
        }
    }

    protected void onPostExecute(HttpResponse feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}