package mining;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mining.miningcomponents.LinkMiningComponent;
import mining.miningcomponents.MiningComponent;
import mining.miningcomponents.MiningComponentClass;

import agent.Agent;
import database.Database;
import debug.Debug;
import fetching.*;

/*
 * The miner is responsible for processing the page data.
 * 
 * The miner requires  parameters in it's constructor.
 * Firstly, it requires a database to store the data in.
 * Secondly, an agent to notify when it is complete.
 * Thirdly, a URL to scan.
 * 
 * The miner will then do the following things:
 * 
 * download the page
 * set up the processors
 * process the data
 * notify the agent that it is complete.
 */

public abstract class Miner {
	
	protected Database database;
	protected Agent agent;
	protected String pageURL;
	
	protected Fetcher fetcher;
	protected String pageData = "";
	
	private Map miningComponents = new HashMap(10);
	
	private boolean running = false;
	
	public Miner(Database database, Agent agent, String pageURL){
		this.database = database;
		this.agent = agent;
		this.pageURL = pageURL;
		
		//setting up the fetcher
		fetcher = new SimpleFetcher();
	}
	
	/*
	 * This method gives the option to specify a custom type of fetcher, should the fetcher requirements change
	 */
	
	public void setFetcher(Fetcher fetcher){
		this.fetcher = fetcher;
	}
	
	public void run(){
		if(running)return;
		
		(new Thread(new MiningThread())).start();
	}
	
	protected abstract void setupMiningComponents();
	
	protected void addMiningComponent(MiningComponentClass m){
		miningComponents.put(m.getComponentID(), m);
	}
	
	//returns false if it fails
	private boolean fetchData(){
		try {
			System.out.print("Fetching...");
			pageData = fetcher.fetchData(pageURL);
			System.out.print("Fetched...");
		} catch (Exception e) {
			return false;
		}
		
		if(pageData.equals(""))return false;
		
		return true;
	}
	
	private void processData(){
		//((LinkMiningComponent) miningComponents.get(LinkMiningComponent.ID)).processData(pageData);
		
		//this array is an array of entries. In order to get the actual mining components, we must cast them to an entry, then call getValue()
		Object[] MiningComponentSet = miningComponents.entrySet().toArray();
		
		for(int i = 0; i<MiningComponentSet.length; i++){
			MiningComponent m = (MiningComponent) ((Entry)MiningComponentSet[i]).getValue();
			m.processData(pageData);
		}
		
	}
	
	protected abstract void notifyAgent();
	
	private class MiningThread implements Runnable{

		@Override
		public void run() {
			running = true;
			if(fetchData()){//only if the fetching of the data was successful do we bother processing it.
				System.out.print("Preparing to process...");
				setupMiningComponents();
				System.out.print("Processing...");
				processData();
				System.out.print("Processed...");
			}
			System.out.print("notifying agent then quitting...\n");
			notifyAgent();

			running = false;
		}
		
	}
	
	//all get and set methods
	
	public String getPageURL(){
		return pageURL;
	}
	
	public MiningComponent getMiningComponent(byte ID){
		return (MiningComponent) miningComponents.get(ID);
	}
}
