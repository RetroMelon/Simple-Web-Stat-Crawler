package mining.miningcomponents;

import java.util.ArrayList;

import linkParsing.LinkSanitiser;
import mining.Miner;
import agent.Agent;
import database.Database;

/*
 * the link miner mines for all types of link including links to image files.
 */

public class LinkMiningComponent implements MiningComponent {

	public static final byte ID = 0;

	private Miner miner;
	private Agent agent;
	private Database database;
	
	protected ArrayList<String> links = new ArrayList<String>();
	
	public LinkMiningComponent(Database d, Agent a, Miner m) {
		this.database = d;
		this.agent = a;
		this.miner = m;
	}

	@Override
	public byte getComponentID() {
		return ID;
	}

	@Override
	public void processData(String data) {
		
		String[] splitdata = data.split("\"");
		for(int i = 0; i<splitdata.length; i++){
			if(splitdata[i].contains("href=") || splitdata[i].contains("src=")){
				links.add(splitdata[i+1]);
			}
		}
		
		ArrayList<String> sanitisedlinks = new ArrayList<String>();
		for(int i = 0; i<links.size(); i++){
			String sanitisedlink = LinkSanitiser.sanitiseLink(links.get(i), miner.getPageURL());
			
			if(sanitisedlink != null){//if the url was garbage, do not add it to the sanitisedlinks list
				sanitisedlinks.add(sanitisedlink);
			}
		}
		
		links = sanitisedlinks;
	}
	
	public ArrayList<String> getLinks(){
		return (ArrayList<String>) links.clone();
	}


}
