package datainterpretation;

public class DiskSpaceDataAcceptor {
	
	public static void acceptDiskData(String diskDriveName, double totalSpace, double freeSpace) {
		if(totalSpace == 0)
			return;
		double usage = (1 - ( freeSpace / totalSpace )) * 100;
		if( usage > DiskCaps.getDiskHardCap(diskDriveName)) {
			individualDriveHardAlert();
		}else if( usage > DiskCaps.getDiskSoftCap(diskDriveName)) {
			individualDriveSoftAlert();
		}
		DiskCaps.setUsage(diskDriveName, usage,totalSpace);
		
	}

	private static void individualDriveSoftAlert() {
		// TODO Auto-generated method stub
		
	}

	private static void individualDriveHardAlert() {
		// TODO Auto-generated method stub
		
	}

}
