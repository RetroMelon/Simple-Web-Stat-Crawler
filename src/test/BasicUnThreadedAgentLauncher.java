package test;

import agent.UnThreadedAgent;

public class BasicUnThreadedAgentLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		(new UnThreadedAgent("http://en.wikipedia.org/wiki/Ryan_M-1")).start();

	}

}
