package scheduler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@Component
public class ScheduledTasks {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  @Scheduled(fixedRate = 5000)
  public void reportCurrentTime() {
  
    System.out.println("The time is now " + dateFormat.format(new Date()));

	try {

      DefaultHttpClient httpClient = new DefaultHttpClient();
      HttpGet getRequest = new HttpGet("http://192.168.1.9:9200/");
      HttpResponse response = httpClient.execute(getRequest);
	  String responseAsString = EntityUtils.toString(response.getEntity());
      System.out.println("Response: " + responseAsString);
      httpClient.getConnectionManager().shutdown();
   
    } catch (ClientProtocolException e) {
    
      e.printStackTrace();
    
    } catch (IOException e) {

      e.printStackTrace();     

    }
	
  }

}


