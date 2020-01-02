package deviceScanner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Controller {
	 private static final String filepath="C:\\Users\\Matthew\\Desktop\\LJMU\\CompSec\\EncryptThisFile.docx";//location of file to save to

	public static void main(String[] args) throws UnknownHostException, InterruptedException, ExecutionException {
		DeviceList deviceList = new DeviceList();
		boolean startPorts = false;
		
		 Controller objectIO = new Controller();
		
		// Use the IP the device running this app is connected to to scan for devices on
		// this network
		InetAddress iP = InetAddress.getLocalHost();
		System.out.println(iP.getHostName());
		String thisIP = iP.getHostAddress();
		System.out.println(thisIP);// DEBUG: CHekc what IP the code is running on
		String subnet = thisIP.substring(0, thisIP.lastIndexOf('.'));

		int timeout = 1000;
//		for (int i = 0; i < 49; i++) {// replace with 0 to 255
//			String hoster = subnet + "." + i;
//			try {
//				if (InetAddress.getByName(hoster).isReachable(timeout)) {
////					System.out.println("\n" + hoster + " is reachable");
//					InetAddress host = InetAddress.getByName(hoster);
//
//					DeviceDetails device = new DeviceDetails(host.getHostName(), host.getHostAddress(),
//							host.getCanonicalHostName(), host.isAnyLocalAddress(), host.isLinkLocalAddress(),
//							host.isLoopbackAddress(), host.isSiteLocalAddress(), host.isMulticastAddress(),
//							host.isMCGlobal(), host.isMCLinkLocal(), host.isMCNodeLocal(), host.isMCSiteLocal());
//					System.out.println(device);
//					deviceList.addToArrayList(device);
//					
//					
//					DeviceDetails testDevice = new DeviceDetails("test", "test");
//					objectIO.WriteObjectToFile(testDevice);
//
//				} else {
//					System.out.println(hoster);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		deviceList.showArrayList();
//		deviceList.returnDevices();
//		startPorts = true;
		
		DeviceDetails testDevice = new DeviceDetails("test", "test");
		objectIO.WriteObjectToFile(testDevice);
		objectIO.ReadObjectFromFile(testDevice);
		
//		***************START PORTSCANNER CODE*********************
//		final ExecutorService es = Executors.newFixedThreadPool(20);
//		final int timeoutPorts = 200;
//		final List<Future<ScanResult>> futures = new ArrayList<>();
//
//		while (startPorts == true) {
//			for (int port = 1; port <= 65535; port++) {// this will show open ports
////				 for (int port = 1; port <= 80; port++) {
//				futures.add(portIsOpen(es, thisIP, port, timeoutPorts));
//			}
//			es.awaitTermination(200L, TimeUnit.MILLISECONDS);
//			int openPorts = 0;
//			for (final Future<ScanResult> f : futures) {
//				if (f.get().isOpen()) {// is port open
//					openPorts++;
////					System.out.println("For loop in main gives: " + f.get().getPort());
////					System.out.println("Port Is Open: " + f.get().isOpen);
//					System.out.println(f.get().toString());
//
//				}
//			}
//			System.out.println("There are " + openPorts + " open ports on host " + thisIP
//					+ " (probed with a timeout of " + timeoutPorts + "ms)");
//			startPorts = false;
//		}

	}// end main method
	
	public void WriteObjectToFile(DeviceDetails serObj) {
		 
        try {
 
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            
            
            objectOut.writeObject(serObj);
            objectOut.close();
            fileOut.close();
            System.out.println("The Object  was succesfully written to a file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//end write file
	
	public void ReadObjectFromFile(DeviceDetails item) {
		try {
			FileInputStream fileIn = new FileInputStream(new File(filepath));
			ObjectInputStream objOut = new ObjectInputStream(fileIn);
			
			DeviceDetails d1 = (DeviceDetails) objOut.readObject();
			
			System.out.println("Test Device: "+d1.toString());
			fileIn.close();
			objOut.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end read object

	public static Future<ScanResult> portIsOpen(final ExecutorService es, final String ip, final int port,
			final int timeout) {
		return es.submit(new Callable<ScanResult>() {
			@Override
			public ScanResult call() {
				try {
					Socket socket = new Socket();// creates a socket , thereby connecting to the server
					
					socket.connect(new InetSocketAddress(ip, port), timeout);// read from socket?
//					System.out.println("Socket RemoteAddress:"+ socket.getRemoteSocketAddress());
//					System.out.println("Socket get Local Port: "+ socket.getLocalPort());
//					System.out.println("Port number to which this socket is connected:"+ socket.getPort());
					
					InetAddress localAddr = socket.getLocalAddress();
					InetAddress remoteAddr = socket.getInetAddress();
					String remoteDomainName = remoteAddr.getCanonicalHostName();
					String localDomainName = localAddr.getCanonicalHostName();
					int localPort = socket.getLocalPort();
					
//					System.out.println("Connected to " + socket.getInetAddress() + " on port " + socket.getPort()
//							+ " from port " + socket.getLocalPort() + " of " + socket.getLocalAddress());
					socket.close();// close socket
					return new ScanResult(port, localPort, remoteAddr, localAddr, true, remoteDomainName, localDomainName);
				} catch (Exception ex) {
					return new ScanResult(port, false);
				}
			}
		});
	}

	public static class ScanResult {
		private int port;// the remote port number this socket is connected to
		private int localPort;// the local port number this socket is bound to
		private InetAddress remoteAddress;// the remote ip address this socket is connected to
		private InetAddress localAddress;// the local address to which the socket is bound, the loopback address
//		if the operation is denied by the security manager or the wildcard address
//		if the socket is not yet bound
		private boolean isOpen;
		private String remoteAddressDomainName, localAddressDomainName;

		public ScanResult(int port, int localPort, InetAddress remoteAddress, InetAddress localAddress,
				boolean isOpen, String remoteAddressDomainName, String localAddressDomainName) {
			super();
			this.port = port;
			this.localPort = localPort;
			this.remoteAddress = remoteAddress;
			this.localAddress = localAddress;
			this.isOpen = isOpen;
			this.remoteAddressDomainName = remoteAddressDomainName;
			this.localAddressDomainName = localAddressDomainName;
		}

		public ScanResult(int port, boolean isOpen) {
			super();
			this.port = port;
			this.isOpen = isOpen;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public boolean isOpen() {
			return isOpen;
		}

		public void setOpen(boolean isOpen) {
			this.isOpen = isOpen;
		}

		public int getLocalPort() {
			return localPort;
		}

		public void setLocalPort(int localPort) {
			this.localPort = localPort;
		}

		public InetAddress getRemoteAddress() {
			return remoteAddress;
		}

		public void setRemoteAddress(InetAddress remoteAddress) {
			this.remoteAddress = remoteAddress;
		}

		public InetAddress getLocalAddress() {
			return localAddress;
		}

		public void setLocalAddress(InetAddress localAddress) {
			this.localAddress = localAddress;
		}

		public String getRemoteAddressDomainName() {
			return remoteAddressDomainName;
		}

		public void setRemoteAddressDomainName(String remoteAddressDomainName) {
			this.remoteAddressDomainName = remoteAddressDomainName;
		}

		public String getLocalAddressDomainName() {
			return localAddressDomainName;
		}

		public void setLocalAddressDomainName(String localAddressDomainName) {
			this.localAddressDomainName = localAddressDomainName;
		}

		@Override
		public String toString() {
			return "\nScanResult: Remote port=" + port + ", localPort=" + localPort + ", remoteAddress=" + remoteAddress
					+ ", localAddress=" + localAddress + ", isOpen=" + isOpen+", Remote Address DomainName: "+ remoteAddressDomainName+", Local Address Domain Name"+localAddressDomainName;
		}
	}

}// end Controller Class
//Commom Vulnerable windows ports
//•42WIINS (Host Name Server)
//•80HTTP (IIS or Apache vulnerability)
//•135RPC (Remote Procedure Call)
//•137NetBIOS Name Service
//•139NetBIOS Session Service
//•1025Windows Messenger  
//•1433Microsoft-SQL-Server 
//•2745Bagle worm backdoor  
//•3127MyDoom worm backdoor  
//•3306MySQL UDR
//•5000UPnP
