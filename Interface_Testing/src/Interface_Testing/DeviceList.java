package Interface_Testing;


import java.util.ArrayList;

public class DeviceList {
	ArrayList<DeviceDetails> deviceList = new ArrayList<DeviceDetails>();

	public ArrayList<DeviceDetails> getDeviceList() {
		return deviceList;
	}

	public void addToArrayList(DeviceDetails device) {
		deviceList.add(device);
	}

	@Override
	public String toString() {
		return "DeviceList [deviceList=" + deviceList + "]";
	}

	public void showArrayList() {
		for (DeviceDetails item : deviceList) {
			System.out.println("\n\nArrayList: "+item.toString());
		}
	}
	public DeviceDetails returnDevices() {
		for(DeviceDetails item : deviceList) {
			System.out.println("\n\nArrayList: "+item.toString());
			return item;
		}
		return null;
	}
}// end class
