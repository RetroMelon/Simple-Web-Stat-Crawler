package test;

import debug.Debug;
import fetching.SimpleFetcher;
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
		
		System.out.print("\n\n\n\n\n\n");
		Debug.println("FETCHERTESTER", "Testing the SimpleFetcher with URL 'http://www.utopiacomputers.co.uk/'.");
		
		try{
			String pagedata1 = (new SimpleFetcher()).fetchData("http://www.utopiacomputers.co.uk/");
			System.out.println(pagedata1);
		}catch(Exception e){
			Debug.println("FETCHERTESTER", "FetcherTester failed because an exception arose in SimpleTester.");
			e.printStackTrace();
		}
		
	}

}
