package debug;

/*
 * The debug class provides basic functionality for outputting debug info to the console.
 * It allows the debugger a slightly greater insight as to where the debug message originated from.
 * 
 * All messages from the debugger are tagged with a "name" of the object which sent it. Usually this will simply be the name of the class.
 * 
 * This class also makes use of another class called DebugFile to allow you a debug log to be dumped to a text file.
 */

public final class Debug {
	
	private static final String PREFIX = "DEBUG";
	private static final String SEPARATOR = " :: ";
	
	/*
	 * All debug messages are sent in this fashion:
	 * 
	 * 	"DEBUG :: SENDER :: ARBITRARY MESSAGE"
	 */
	
	public static void println(String Sender, String Message){
		System.out.println(PREFIX+SEPARATOR+Sender+SEPARATOR+Message);
	}
	
	public static void printst(Exception exception){
		System.out.println(PREFIX+SEPARATOR+"PRINTING STACK TRACE"+SEPARATOR);
		exception.printStackTrace();
	}

	public static void setOutputFile(String FileLocation){
		/*
		 * The file output methods will be written once the file input/output system is in place.
		 */
	}
	
	public static void setOutputFile(DebugFile File){
		/*
		 * The file output methods will be written once the file input/output system is in place.
		 */
	}
}
