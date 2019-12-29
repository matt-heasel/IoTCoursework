package deviceScanner;

import java.io.IOException;
import java.net.InetAddress;

public class IPscanner {
	

	public static void checkHosts(String subnet) {// subnet is is a number that defines a range
//		of IP addresses available within a network
		int timeout = 1000;
		for (int i = 1; i < 255; i++) {
			String host = subnet + "." + i;
			try {
				if (InetAddress.getByName(host).isReachable(timeout)) {
					System.out.println("\n" + host + " is reachable");
					InetAddress address = InetAddress.getByName(host);
					hostDetails(address);

				} else {
					System.out.println(i);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}// end check hosts method

	public static void hostDetails(InetAddress host) {
		System.out.println("Host Name: "+ host.getHostName());
		System.out.println("Fully Qualified Domain Name (if possible): "+host.getCanonicalHostName().toString());
		System.out.println("Is a Wildard Address? "+host.isAnyLocalAddress());
		System.out.println("Is an Link Local Address? "+host.isLinkLocalAddress());
		System.out.println("Is a Loopback Address? "+host.isLoopbackAddress());
		System.out.println("Is this a Unicast Address?"+ host.isSiteLocalAddress());
		if(host.isMulticastAddress()) {
			System.out.println(host+" is a Multicast Address");
			System.out.println("Does Multicast Address have Global Scope? "+host.isMCGlobal());
			System.out.println("Does Multicast Address have Link Scope? "+host.isMCLinkLocal());
			System.out.println("Does Multicast Address have node-local Scope? "+host.isMCNodeLocal());
			System.out.println("Does Multicast Address have Site Scope? "+host.isMCSiteLocal());
			
		}
		else {
			System.out.println("This host is not Multicast");
		}
	}//end Host Details Method
	
	
}// end class
