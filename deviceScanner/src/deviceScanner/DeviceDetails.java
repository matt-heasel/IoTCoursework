package deviceScanner;

import java.util.ArrayList;

import java.io.Serializable;

public class DeviceDetails implements Serializable	 {
	
	   //default serialVersion id
    private static final long serialVersionUID = 1L;
    
	private String hostName, hostAddr, FQDN;
	private boolean wildCard, linkLocal, loopBack, siteLocal, multiCast, mcGlobal, mcLinkLocal, mcNodeLocal,
			mcSiteLocal;

	public DeviceDetails(String hostName, String hostAddr, String fQDN, boolean wildCard, boolean linkLocal,
			boolean loopBack, boolean siteLocal, boolean multiCast, boolean mcGlobal, boolean mcLinkLocal,
			boolean mcNodeLocal, boolean mcSiteLocal) {
		super();
		this.hostName = hostName;
		this.hostAddr = hostAddr;
		this.FQDN = fQDN;
		this.wildCard = wildCard;
		this.linkLocal = linkLocal;
		this.loopBack = loopBack;
		this.siteLocal = siteLocal;
		this.multiCast = multiCast;
		this.mcGlobal = mcGlobal;
		this.mcLinkLocal = mcLinkLocal;
		this.mcNodeLocal = mcNodeLocal;
		this.mcSiteLocal = mcSiteLocal;
	}

	public DeviceDetails(String hostName, String hostAddr) {
		super();
		this.hostName = hostName;
		this.hostAddr = hostAddr;
	}


	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostAddr() {
		return hostAddr;
	}

	public void setHostAddr(String hostAddr) {
		this.hostAddr = hostAddr;
	}

	public String getFQDN() {
		return FQDN;
	}

	public void setFQDN(String fQDN) {
		FQDN = fQDN;
	}

	public boolean isWildCard() {
		return wildCard;
	}

	public void setWildCard(boolean wildCard) {
		this.wildCard = wildCard;
	}

	public boolean isLinkLocal() {
		return linkLocal;
	}

	public void setLinkLocal(boolean linkLocal) {
		this.linkLocal = linkLocal;
	}

	public boolean isLoopBack() {
		return loopBack;
	}

	public void setLoopBack(boolean loopBack) {
		this.loopBack = loopBack;
	}

	public boolean isSiteLocal() {
		return siteLocal;
	}

	public void setSiteLocal(boolean siteLocal) {
		this.siteLocal = siteLocal;
	}

	public boolean isMultiCast() {
		return multiCast;
	}

	public void setMultiCast(boolean multiCast) {
		this.multiCast = multiCast;
	}

	public boolean isMcGlobal() {
		return mcGlobal;
	}

	public void setMcGlobal(boolean mcGlobal) {
		this.mcGlobal = mcGlobal;
	}

	public boolean isMcLinkLocal() {
		return mcLinkLocal;
	}

	public void setMcLinkLocal(boolean mcLinkLocal) {
		this.mcLinkLocal = mcLinkLocal;
	}

	public boolean isMcNodeLocal() {
		return mcNodeLocal;
	}

	public void setMcNodeLocal(boolean mcNodeLocal) {
		this.mcNodeLocal = mcNodeLocal;
	}

	public boolean isMcSiteLocal() {
		return mcSiteLocal;
	}

	public void setMcSiteLocal(boolean mcSiteLocal) {
		this.mcSiteLocal = mcSiteLocal;
	}

	@Override
	public String toString() {
		return "DeviceDetails:\nhostName: " + hostName + "\n hostAddr: " + hostAddr + "\n FQDN: " + FQDN + "\n wildCard: "
				+ wildCard + "\n linkLocal: " + linkLocal + "\n loopBack: " + loopBack + "\n siteLocal: " + siteLocal
				+ "\n multiCast: " + multiCast + "\n mcGlobal: " + mcGlobal + "\n mcLinkLocal: " + mcLinkLocal
				+ "\n mcNodeLocal: " + mcNodeLocal + "\n mcSiteLocal: " + mcSiteLocal;
	}
//	public String toString() {
//		return new StringBuffer("\n Host Name: ").append(hostName).append("\n Host Address: ").append(hostAddr).append("\n FQDN: ").append(FQDN).append("\n Wild Card: ").append(wildCard).append("\n Link Local").append(linkLocal).append("\n Loopback: ").append(loopBack).append("\n Site Local: ").append(siteLocal).toString();
//	}
	
//	public String toString() {
//        return new StringBuffer(" First Name: ").append(this.first_name)
//                .append(" Last Name : ").append(this.last_name).append(" Age : ").append(this.age).toString();
//    }

//	public String toString() {
//		return "DeviceDetails [hostName=" + hostName + ", hostAddr=" + hostAddr + "]";
//	}

}// end class
