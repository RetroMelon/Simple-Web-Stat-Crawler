package mining.miningcomponents;

import mining.Miner;
import agent.Agent;
import database.Database;

/*
 * The mining component is used to separate all of the different types of processing tasks that can be performed on the page data in to separate components.
 * Each component has a Component ID. This means that any class wishing to access a specific type of data, they can use the component ID when searching through the map of components.
 */

public interface MiningComponent {
	
	public byte getComponentID();
	
	public void processData(String data);

}
