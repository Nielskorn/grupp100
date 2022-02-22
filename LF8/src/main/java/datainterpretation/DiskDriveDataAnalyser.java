package datainterpretation;

import java.util.List;

public class DiskDriveDataAnalyser {
	
	public static void analyseData() {
		double totalSpace = 0;
		double usedSpace = 0;
		List<DiskDrive> drives = DiskCaps.getDriveList();
		for(DiskDrive drive : drives) {
			totalSpace += drive.getTotalSpace();
			usedSpace += drive.getTotalSpace()*drive.getLastUsage()/100;
		}
		if(totalSpace == 0)
			return;
		double totalUsage = usedSpace / totalSpace *100;
		if(totalUsage > Thresholds.DISKSPACEOVERALLHARDCAP) {
			diskTotalHardAlert();
			return;
		}
		if(totalUsage > Thresholds.DISKSPACEOVERALLSOFTCAP) {
			diskTotalSoftAlert();
		}
		
	}

	private static void diskTotalSoftAlert() {
		// TODO Auto-generated method stub
		
	}

	private static void diskTotalHardAlert() {
		// TODO Auto-generated method stub
		
	}

}
