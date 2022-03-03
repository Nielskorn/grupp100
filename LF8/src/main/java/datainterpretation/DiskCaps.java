package datainterpretation;

import java.util.ArrayList;
import java.util.List;

public class DiskCaps {
	
	private static ArrayList<DiskDrive> driveList = new ArrayList<>();
	
	public static List<DiskDrive> getDriveList() {
		return driveList;
	}

	public static void updateIndividualSoftCap(String driveName, double softCap) {
		DiskDrive diskDrives = getDiskCaps(driveName);
		diskDrives.setSpaceSoftCap(softCap);
	}
	
	public static void updateIndividualHardCap(String driveName, double hardCap) {
		DiskDrive diskDrives = getDiskCaps(driveName);
		diskDrives.setSpaceSoftCap(hardCap);
	}
	
	public static double getDiskHardCap(String driveName) {
		DiskDrive diskDrives = getDiskCaps(driveName);
		return diskDrives.getSpaceHardCap();
	}
	
	public static double getDiskSoftCap(String driveName) {
		DiskDrive diskDrives = getDiskCaps(driveName);
		return diskDrives.getSpaceSoftCap();
	}
	
	public static double getLastUsage(String driveName) {
		DiskDrive diskDrives = getDiskCaps(driveName);
		return diskDrives.getLastUsage();
	}
	
	public static void setUsage(String driveName, double lastUsage,double totalSpace) {
		DiskDrive diskDrives = getDiskCaps(driveName);
		diskDrives.setUsage(lastUsage);
		diskDrives.setTotalSpace(totalSpace);
	}
	
	private static DiskDrive getDiskCaps(String driveName) {
		for(DiskDrive diskDrive : driveList) {
			if(diskDrive.getDiskName().contentEquals(driveName)) {
				System.out.println("Disk Drive found");
				return diskDrive;
			}
		}
		DiskDrive diskDrive = new DiskDrive(driveName);
		driveList.add(diskDrive);
		return diskDrive;
	}

}
