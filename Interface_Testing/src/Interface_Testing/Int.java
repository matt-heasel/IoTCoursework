package Interface_Testing;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Int {

	protected Shell shell;
	private Text text;
	private Table table;
	private static final String filepath = "C:\\Users\\Matthew\\Desktop\\LJMU\\CompSec\\EncryptThisFile.txt";

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws UnknownHostException, InterruptedException, ExecutionException {
		try {
			Int window = new Int();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// end main method

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		DeviceList deviceList = new DeviceList();
		ScanResultList scanResultList = new ScanResultList();

		shell = new Shell();
		shell.setSize(638, 402);
		shell.setText("Network Scanners");
		shell.setLayout(new GridLayout(2, false));
		
		
		
		Button btnLoadTable = new Button(shell, SWT.NONE);
		btnLoadTable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnLoadTable.setText("Load Table");
				ArrayList<DeviceDetails> arr = deviceList.getDeviceList();
				ArrayList<ScanResult> arr2 = scanResultList.getscanResultList();

				if (arr.size() >= 1) {


					for (DeviceDetails item : arr) {
						TableItem tableItem = new TableItem(table, SWT.NONE);
						tableItem.setText(item.toString());

					} // end for loop
					arr.clear();;

				} else if(arr2.size() >=1){
					for (ScanResult item2 : arr2) {
						TableItem tableItem2 = new TableItem(table, SWT.NONE);
						tableItem2.setText(item2.toString());

					} // end for loop
					arr2.clear();
				}else {
					btnLoadTable.setText("NoData");
				}

			}
		});
		btnLoadTable.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnLoadTable.setText("Load Table");
		
		

		Button btnScanForDevices = new Button(shell, SWT.NONE);
		btnScanForDevices.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnScanForDevices.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					runDeviceScanner(deviceList);
					btnLoadTable.setText("Load Table");
				} catch (UnknownHostException e1) {
					// 
					e1.printStackTrace();
				}
			}
		});
		btnScanForDevices.setText("Scan For Devices");
		Button btnScanPortssocket = new Button(shell, SWT.NONE);
		btnScanPortssocket.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnScanPortssocket.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					runPortScanner(scanResultList);
					btnLoadTable.setText("Load Table");
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} catch (ExecutionException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnScanPortssocket.setText("Scan Ports/Socket");



		text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnSaveToFile = new Button(shell, SWT.NONE);
		btnSaveToFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Save pressed");
				Int objectIO = new Int();
				ArrayList<DeviceDetails> arr = deviceList.getDeviceList();
				ArrayList<ScanResult> arr2 = scanResultList.getscanResultList();
				deviceList.getDeviceList();
			  PrintWriter write;
			try {
				write = new PrintWriter(filepath);
				  write.println(scanResultList.getscanResultList().toString());   
				  write.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   

//				if(arr.size() >= 1 || arr2.size() >=1) {
//					
//					btnSaveToFile.setText("Saved to target file");
//					for (DeviceDetails item : arr) {
//						System.out.println("Save pressed");
//
//						objectIO.WriteObjectToFile(item);
//					} // end for loop
//					arr.clear();
//					
//					for (ScanResult item : arr2) {
//						objectIO.WriteSRObjectToFile(item);
//					} // end for loop
//					arr2.clear();;
//					
//				}
//				else{
//					btnSaveToFile.setText("No Saved");
//
//				}
			}
		});
		btnSaveToFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnSaveToFile.setText("Save To File");

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

	}// end create content method

	public void runDeviceScanner(DeviceList deviceList) throws UnknownHostException {
		Int objectIO = new Int();

		// Use the IP the device running this app is connected to to scan for devices on
		// this network
		InetAddress iP = InetAddress.getLocalHost();
		System.out.println("Local Host: " + iP);
		System.out.println("Host Name for this IP: " + iP.getHostName());
		String thisIP = iP.getHostAddress();
		System.out.println("This IP: " + thisIP);// DEBUG: CHekc what IP the code is running on
		String subnet = thisIP.substring(0, thisIP.lastIndexOf('.'));

		// Enter IP Address:
		// IP address variable

		// 1-scan for devices
		// 2-scan port and socket information
		// 3-encrypt file save
		// 4-decrypt file save
		// if password = correct

		int timeout = 1000;
		for (int i = 0; i < 55; i++) {// replace with 0 to 255
			String hoster = subnet + "." + i;
			try {
				if (InetAddress.getByName(hoster).isReachable(timeout)) {
//					System.out.println("\n" + hoster + " is reachable");
					InetAddress host = InetAddress.getByName(hoster);
//					DeviceDetails device = new DeviceDetails(host.getHostName(), host.getHostAddress(),
//							host.getCanonicalHostName(), host.isAnyLocalAddress(), host.isLinkLocalAddress(),
//							host.isLoopbackAddress(), host.isSiteLocalAddress(), host.isMulticastAddress(),
//							host.isMCGlobal(), host.isMCLinkLocal(), host.isMCNodeLocal(), host.isMCSiteLocal());
					DeviceDetails device = new DeviceDetails(host.getHostName(), host.getHostAddress(),
							host.getCanonicalHostName());
					// System.out.println(device);
					deviceList.addToArrayList(device);

					System.out.println("Checking... " + device.getHostAddr());

					objectIO.WriteObjectToFile(device);// writes to ENcryptThis.docx

				} else {
					System.out.println(hoster);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end for loop to search for devices

//		objectIO.ReadObjectFromFile();

		deviceList.showArrayList();
	}

	public void runPortScanner(ScanResultList scanResultList)
			throws UnknownHostException, InterruptedException, ExecutionException {

		InetAddress iP = InetAddress.getLocalHost();
		System.out.println("Local Host: " + iP);
		System.out.println("Host Name for this IP: " + iP.getHostName());
		String thisIP = iP.getHostAddress();

		boolean startPorts = true;
		final ExecutorService es = Executors.newFixedThreadPool(20);
		final int timeoutPorts = 200;
		final List<Future<ScanResult>> futures = new ArrayList<>();

		while (startPorts == true) {
			for (int port = 1; port <= 655; port++) {// this will show open ports - replace with 1 to 65535
//				 for (int port = 1; port <= 80; port++) {
				futures.add(portIsOpen(es, thisIP, port, timeoutPorts));
			}
			es.awaitTermination(200L, TimeUnit.MILLISECONDS);
			int openPorts = 0;
			for (final Future<ScanResult> f : futures) {
				if (f.get().isOpen()) {// is port open
					openPorts++;
//					System.out.println("For loop in main gives: " + f.get().getPort());
//					System.out.println("Port Is Open: " + f.get().isOpen);
					System.out.println(f.get().toString());
					scanResultList.addToArrayList(f.get());

				}
			}
			System.out.println("There are " + openPorts + " open ports on host " + thisIP
					+ " (probed with a timeout of " + timeoutPorts + "ms)");
			scanResultList.showArrayList();
			startPorts = false;
		}
	}

	public void WriteObjectToFile(DeviceDetails serObj) {

		try {

			FileOutputStream fileOut = new FileOutputStream(filepath);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

			objectOut.writeObject(serObj);
			objectOut.close();
			fileOut.close();
			System.out.println(serObj.getHostName() + " was succesfully written to " + filepath);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}// end write file
	public void WriteSRObjectToFile(ScanResult serObj) {

		try {

			FileOutputStream fileOut = new FileOutputStream(filepath);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

			objectOut.writeObject(serObj);
			objectOut.close();
			fileOut.close();
			System.out.println(serObj.getLocalAddressDomainName() + " was succesfully written to " + filepath);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}// end write file

	public void ReadObjectFromFile() {

		try {
			FileInputStream fileIn = new FileInputStream(new File(filepath));
			ObjectInputStream objOut = new ObjectInputStream(fileIn);

			DeviceDetails d1 = (DeviceDetails) objOut.readObject();
			ScanResult d2 = (ScanResult) objOut.readObject();
			
			System.out.println("\n\nReading from file: " + d1.toString() + "\n\n");
			System.out.println("\n\nReading from file: " + d2.toString() + "\n\n");

			fileIn.close();
			objOut.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}// end read object

	public static Future<ScanResult> portIsOpen(final ExecutorService es, final String ip, final int port,
			final int timeout) {
		return es.submit(new Callable<ScanResult>() {
			@Override
			public ScanResult call() {
				try {
					Socket socket = new Socket();// creates a socket , thereby connecting to the server

					socket.connect(new InetSocketAddress(ip, port), timeout);// read from socket?
//					System.out.println("Socket Remote Address:"+ ((InetSocketAddress)socket.getRemoteSocketAddress()).getAddress());
//					System.out.println("Socket get Local Port: "+ socket.getLocalPort());
//					System.out.println("Port number to which this socket is connected:"+ socket.getPort());

					InetAddress localAddr = socket.getLocalAddress();
					InetAddress remoteAddr = socket.getInetAddress();
//					int i1 = socket.getReceiveBufferSize();
//					SocketAddress i2 = socket.getRemoteSocketAddress();
//					System.out.println("Receive buffer size: " + i1 + "Remote Socket address: " + i2);
					String remoteDomainName = remoteAddr.getCanonicalHostName();
					String localDomainName = localAddr.getCanonicalHostName();
					int localPort = socket.getLocalPort();

//					System.out.println("Connected to " + socket.getInetAddress() + " on port " + socket.getPort()
//							+ " from port " + socket.getLocalPort() + " of " + socket.getLocalAddress());
					socket.close();// close socket
					return new ScanResult(port, localPort, remoteAddr, localAddr, true, remoteDomainName,
							localDomainName);
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

		public ScanResult(int port, int localPort, InetAddress remoteAddress, InetAddress localAddress, boolean isOpen,
				String remoteAddressDomainName, String localAddressDomainName) {
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
			return "\nScanResult: Sockets Remote Port:" + port + "\n Sockets Local Port: " + localPort
					+ "\n Sockets Remote Address: " + remoteAddress + "\n Sockets Local Address: " + localAddress
					+ "\n Is Port Open: " + isOpen + "\n Remote Address Domain Name: " + remoteAddressDomainName
					+ "\n Local Address Domain Name: " + localAddressDomainName;
		}
	}
}// end class
