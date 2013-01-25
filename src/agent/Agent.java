package agent;

import mining.Miner;

public interface Agent {
	
	/*
	 * This method will be called by a miner when it has completed it's processing. The agent will then take the data from the miner, and may process it further
	 */
	
	public void notifyMiner(Miner m);

}
