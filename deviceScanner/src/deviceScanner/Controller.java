package deviceScanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Controller {

	public static void main(String[] args) throws UnknownHostException {
		// Use the IP the device running this app is connected to to scan for devices on
		// this network
		String thisIP = InetAddress.getLocalHost().getHostAddress();
		System.out.println(thisIP);//DEBUG: CHekc what IP the code is running on
		String networkIP = thisIP.substring(0, thisIP.lastIndexOf('.'));
		checkHosts(networkIP);// this IP

	}// end main method

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
					System.out.println(host);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}// end check hosts method

	public static void hostDetails(InetAddress host) {
//		Collects all available Info on the device and adds to an object, which is then stored in an arraylist, to be accessed later on for port and socket information
		DeviceDetails device = new DeviceDetails(host.getHostName(), host.getHostAddress(), host.getCanonicalHostName(),
				host.isAnyLocalAddress(), host.isLinkLocalAddress(), host.isLoopbackAddress(),
				host.isSiteLocalAddress(), host.isMulticastAddress(), host.isMCGlobal(), host.isMCLinkLocal(),
				host.isMCNodeLocal(), host.isMCSiteLocal());

		
		device.addToDeviceList(device);

	}// end Host Details Method

}// end Controller Class
