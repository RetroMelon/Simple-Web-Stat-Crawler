package mining;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mining.miningcomponents.MiningComponent;

import agent.Agent;
import database.Database;
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
	
	protected void addMiningComponent(MiningComponent m){
		miningComponents.put(m.getComponentID(), m);
	}
	
	private void fetchData(){
		try {
			pageData = fetcher.fetchData(pageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void processData(){
		Iterator<MiningComponent> iterator = miningComponents.entrySet().iterator();
		while(iterator.hasNext()){
			((MiningComponent) iterator.next()).processData(pageData);
		}
	}
	
	protected abstract void notifyAgent();
	
	private class MiningThread implements Runnable{

		@Override
		public void run() {
			running = true;
			fetchData();
			if(pageData.equals("")){return;}
			setupMiningComponents();
			processData();
			notifyAgent();
			running = false;
		}
		
	}

}
