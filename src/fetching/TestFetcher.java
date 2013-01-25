package fetching;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import debug.Debug;

/*
 * The test fetcher does not actually download anything from the web. Instead it simply returns the contents of a webpage from a txt document.
 * 
 * If the
 */

public class TestFetcher implements Fetcher{

	@Override
	public String fetchData(String URL) throws Exception{
		Debug.println("TESTFETCHER", "Returning page data from glasgow university.");
		
		/*
		 * Fetching text from txt document.
		 */
		InputStream s = getClass().getResourceAsStream("glasgow.txt");
		BufferedReader input = new BufferedReader(new InputStreamReader(s));
		
		String returnstring = "";
		
		String line;
		
		while((line = input.readLine()) != null){
			returnstring = returnstring + line + "\n";
		}
		
		
		return returnstring;
	}

}
