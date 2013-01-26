package linkParsing;

import debug.Debug;

/*
 * The link sanitiser does all of the following things:
 * 
 * Ensures all links have "http://" (or "https://") in front of them
 * Ensures that links with a # are split, and only the first part is kept.
 * Ensures that any relative links (ones without a server address) are changed to absolute addresses.
 * Ensures the link never ends in a trailing "/"
 * 
 * 
 * If the link is garbage, eg. in the case of a "#", the Link Sanitizer can return Null.
 */

public class LinkSanitiser {
	
	public static String sanitiseLink(String link, String parentpagelink){
		String sanitisedlink = link;
		
		//splitting the link in case it has a "#"
		try{
			sanitisedlink = sanitisedlink.split("#")[0];
		}catch(Exception e){
			return null;
		}
		
		if(sanitisedlink.equals("")){
			return null;
		};
		
		//if the string starts with http, all we need to do is check that we don't have a trailing / and end.
		if(sanitisedlink.startsWith("http")){
			
		}else{//the link must be a relative one, so we remove a leading /, and then add it on to the rest of the url.
			if(sanitisedlink.startsWith("/")){
				sanitisedlink = sanitisedlink.substring(1);
			}
			sanitisedlink = getDomainURL(parentpagelink) + "/" + sanitisedlink;
		}
			
		//checking that the sanitised link does not have a trailing slash
		while(sanitisedlink.endsWith("/")){
			sanitisedlink = sanitisedlink.substring(0, sanitisedlink.length()-1);
		}
	
		return sanitisedlink;
		
	}
	
	/*
	 * this method returns the domain url given a link to a page. eg for "http://www.google.co.uk/search/query.html" it would return "http://www.google.co.uk"
	 * it is always preceded by "http://" or "https://" and is never followed by a trailing "/"
	 * 
	 * if this method is passed an invalid URL it will return null.
	 */
	
	public static String getDomainURL(String pageURL){
		try{
			String[] splitpageurl = pageURL.split("/");
			return splitpageurl[0] + "//" + splitpageurl[2];
			
		}catch(Exception e){
			Debug.println("LINKSANITISER:GETDOMAINURL", "failed to get domain url from given url. probably bad url provided");
			return null;
		}
	}

}
