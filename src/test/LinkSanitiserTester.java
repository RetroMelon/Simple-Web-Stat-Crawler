package test;

import linkParsing.LinkSanitiser;

public class LinkSanitiserTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String parentlink = "http://www.gla.ac.uk/testpage/lol/";
		String[] testlinks = {
				"/about/interact/",
				"/media/media_221003_en.gif",
				"/about/itunesu/",
				"#",
				"#hi",
				"http://www.gla.ac.uk/about/contact/hello#2",
				"http://www.gla.ac.uk/about/maps/",
				"http://www.gla.ac.uk/about/contact/",
				"http://www.gla.ac.uk/about/accessibility/",
				"http://www.gla.ac.uk/legal/disclaimer/"
		};
		
		for(int i = 0; i<testlinks.length; i++){
			System.out.println(testlinks[i] + "\t\t => \t\t" + LinkSanitiser.SanitiseLink(testlinks[i], parentlink));
		}

	}

}
