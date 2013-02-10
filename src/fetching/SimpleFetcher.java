package fetching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/*
 * The simple fetcher is a basic fetcher that simply downloads the webpage from the desired URL.
 */

public class SimpleFetcher implements Fetcher{

	@Override
	public String fetchData(String pageURL) throws Exception {
		
		String pageData = "";
		
		URL url = new URL(pageURL);
		
		try{
			BufferedReader Reader = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String line;
			while((line = Reader.readLine()) != null){
				pageData = pageData + line + "\n";
			}
		}catch(Exception e){
			return "";
		}
		
		return pageData;
	}

}
