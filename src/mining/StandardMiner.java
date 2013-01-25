package mining;

import agent.Agent;
import database.Database;

public class StandardMiner extends Miner{

	public StandardMiner(Database database, Agent agent, String pageURL) {
		super(database, agent, pageURL);
	}

	/*
	 * The standardMiner has standard components for mining data such as:
	 * 
	 * links + link-sanitiser
	 * checksum
	 * word count 
	 */
	protected void setupMiningComponents() {
		
	}

	@Override
	protected void notifyAgent() {
		// TODO Auto-generated method stub
		
	}

}
