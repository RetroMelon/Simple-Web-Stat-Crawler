package test;

import java.util.ArrayList;

import agent.TestAgent;
import mining.StandardMiner;
import mining.miningcomponents.LinkMiningComponent;
import database.TestDatabase;
import debug.Debug;
import fetching.TestFetcher;

public class LinkMiningComponentTester {
	
	public static void main(String[] args){
		Debug.println("LINKMININGCOMPONENTTESTER", "LinkMiningComponentTester started.");
		
		try{
			String pagedata = (new TestFetcher()).fetchData("");
			System.out.println(pagedata);
			System.out.print("\n\n\n\n");
			
			LinkMiningComponent m = new LinkMiningComponent(new TestDatabase(), new TestAgent(), new StandardMiner(new TestDatabase(), new TestAgent(), "http://www.gla.ac.uk/lolcats"));
			
			m.processData(pagedata);
			
			ArrayList<String> links = m.getLinks();
			for(int i = 0; i<links.size(); i++){
				System.out.println(links.get(i));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
