package deviceScanner;

import java.util.ArrayList;

public class DeviceList {
	ArrayList<DeviceDetails> deviceList = new ArrayList<DeviceDetails>();

	public void addToArrayList(DeviceDetails device) {
		deviceList.add(device);
	}

	public void showArrayList() {
		for (DeviceDetails item : deviceList) {
			System.out.println("ArrayList: "+item.toString());
		}
	}
	public DeviceDetails returnDevices() {
		for(DeviceDetails item : deviceList) {
			System.out.println("ArrayList: "+item.toString());
			return item;
		}
		return null;
	}
}// end class
