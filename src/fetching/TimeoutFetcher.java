package fetching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TimeoutFetcher implements Fetcher {

	/*
	 * This fetcher has a timeout which will stop fetching if we are unable to read for a certain length of time.
	 * The timeout in milliseconds can be set with the "setTimeout" method.
	 * 
	 * If the connection does time out, an empty string will be returned.
	 */
	protected int timeout = 10000;
	
	public String fetchData(String URL) throws Exception {
		HttpURLConnection urlconn = (HttpURLConnection)(new URL(URL)).openConnection();
		
		urlconn.setConnectTimeout(timeout);
		urlconn.setReadTimeout(timeout);
		
		urlconn.setRequestMethod("GET");
		urlconn.connect();
		
		BufferedReader Reader = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
		
		String line = "";
		String ReturnString = "";
		try{
		while((line = Reader.readLine()) != null){
			ReturnString = ReturnString + line + "\n";
		}
		}catch(Exception e){return "";}
		
		return ReturnString;
	}
	
	public void setTimeout(int TimeoutMillis){
		timeout = TimeoutMillis;
	}
	

}
