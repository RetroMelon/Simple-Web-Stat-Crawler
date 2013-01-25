package test;

import debug.Debug;
import fetching.TestFetcher;

public class FetcherTester {
	
	public static void main(String args[]){
		Debug.println("FETCHERTESTER", "FetcherTester started.");

		
		Debug.println("FETCHERTESTER", "Testing the TestFetcher.");
		
		try{
			String pagedata = (new TestFetcher()).fetchData("");
			System.out.println(pagedata);
		}catch(Exception e){
			Debug.println("FETCHERTESTER", "FetcherTester failed because an exception arose in TestFetcher.");
			e.printStackTrace();
		}
		
	}

}
