package Interface_Testing;

import java.util.ArrayList;
import Interface_Testing.Int.ScanResult; 

public class ScanResultList {
	ArrayList<ScanResult> scanResultList = new ArrayList<ScanResult>();
	
	public void addToArrayList(ScanResult result) {
		scanResultList.add(result);
	}

	public void showArrayList() {
		for (ScanResult item : scanResultList) {
			System.out.println("\n\nArrayList: "+item.toString());
		}
	}

	
	public ArrayList<ScanResult> getscanResultList() {
		return scanResultList;
	}
	public ScanResult iterator() {
		while(scanResultList != null) {
			for(ScanResult item : scanResultList) {
				return item;
			}
		}

		return null;
	}


}//end class
