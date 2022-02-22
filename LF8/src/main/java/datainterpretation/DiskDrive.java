package datainterpretation;

public class DiskDrive {
	
	private String diskName;
	private double spaceSoftCap;
	private double spaceHardCap;
	private double lastUsage;
	private double totalSpace;
	
	public double getTotalSpace() {
		return totalSpace;
	}

	public void setTotalSpace(double totalSpace) {
		this.totalSpace = totalSpace;
	}

	public String getDiskName() {
		return diskName;
	}

	public double getSpaceSoftCap() {
		return spaceSoftCap;
	}

	public void setSpaceSoftCap(double spaceSoftCap) {
		this.spaceSoftCap = spaceSoftCap;
	}

	public double getSpaceHardCap() {
		return spaceHardCap;
	}

	public void setSpaceHardCap(double spaceHardCap) {
		this.spaceHardCap = spaceHardCap;
	}
	
	public double getLastUsage() {
		return lastUsage;
	}
	
	public void setUsage(double usage) {
		this.lastUsage = usage;
	}

	public DiskDrive(String diskName, double spaceSoftCap, double spaceHardCap) {
		this.diskName = diskName;
		this.spaceSoftCap = spaceSoftCap;
		this.spaceHardCap = spaceHardCap;
		this.lastUsage = 0;
	}
	
	public DiskDrive(String diskName) {
		this.diskName = diskName;
		this.spaceHardCap = Thresholds.DISKSPACEINDIVIDUALDEFAULTHARDCAP;
		this.spaceSoftCap = Thresholds.DISKSPACEINDIVIDUALDEFAULTSOFTCAP;
		this.lastUsage = 0;
	}


}
