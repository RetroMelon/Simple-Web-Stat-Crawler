package fetching;


/*
 * The fetcher is an incredibly simple class which simply downloads the data from a webpage and returns it to the miner.
 * 
 * If the file does not load for any reason the fetcher will throw an IOException.
 * This method should never really return null. it should always throw an exception.
 */

public interface Fetcher {
	
	public String fetchData(String URL) throws Exception;

}
