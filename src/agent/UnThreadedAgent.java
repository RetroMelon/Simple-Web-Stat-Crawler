package agent;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import database.Database;
import database.TestDatabase;
import fetching.TimeoutFetcher;

import sun.misc.Queue;

import mining.Miner;
import mining.StandardMiner;
import mining.miningcomponents.LinkMiningComponent;
import mining.miningcomponents.MiningComponent;

public class UnThreadedAgent implements Agent{

	//contains a list of URLS and the number of their inbound links
	Map<String, Integer> linksmap = new HashMap<String, Integer>(1000);
	int uniquelinks = 0;
	int totallinkages = 0;
	
	LinkedList<String> linksqueue = new LinkedList<String>();
	
	String startlink = "";
	
	public UnThreadedAgent(String startlink){
		this.startlink = startlink;
	}
	
	public void start(){

		System.out.println("UnThreadedAgent started...");
		(new StandardMiner(new TestDatabase(), this, startlink)).run();
		

	}

	@Override
	public void notifyMiner(Miner m) {
		try{
			ArrayList<String> links = ((LinkMiningComponent)m.getMiningComponent(LinkMiningComponent.ID)).getLinks();
			
			totallinkages+=links.size();
			
			for(int i = 0; i<links.size(); i++){
				if(linksmap.get(links.get(i)) == null){//url must be new
					uniquelinks++;
					linksqueue.add(links.get(i));
					linksmap.put(links.get(i), 0);
				}
				linksmap.put(links.get(i), linksmap.get(links.get(i))+1);
			}
		}catch(Exception e){
			
		}
		
		//exitpoint
		if(uniquelinks>=200000){
			
			try{
				FileOutputStream fos = new FileOutputStream("C:\\Users\\Joe\\Desktop\\100000 links.txt"); 
				OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
			
			//this array is an array of entries. In order to get the actual strings, we must cast them to an entry, then call getValue()
			Object[] EntrySet = linksmap.entrySet().toArray();
			
			for(int i = 0; i<EntrySet.length; i++){
				int numberofinboundlinks = (Integer) ((Entry)EntrySet[i]).getValue();
				out.write(((Entry)EntrySet[i]).getKey() + "\t----\tInboundLinks: "+numberofinboundlinks + System.getProperty("line.separator"));
			}
			
			System.out.println("\n\nDUMP OF LINKS SAVED ON DESKTOP... \n");
			
			out.close();
			fos.close();
			
			}catch(Exception e){}
			return;
		}
		
		System.out.println("totallinkages = "+totallinkages+"\t\t uniquelinks = "+uniquelinks);
		makenewminer();
	}
	
	public void makenewminer(){
		String link = linksqueue.poll();
		while(true){
			if(link.endsWith(".css") || link.endsWith(".js") || link.endsWith(".jpg") || link.endsWith(".gif") || link.endsWith(".ico") || link.endsWith(".png")){
				link = linksqueue.poll();
			}else{
				break;
			}
		}
		
		System.out.println("Scanning Link: "+link);
		try{Thread.sleep(40);}catch(Exception e){}
		StandardMiner m = (new StandardMiner(new TestDatabase(), this, link));
		m.setFetcher(new TimeoutFetcher());
		m.run();
	}

}
