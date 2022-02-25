package datainterpretation;

import java.util.List;

import utils.MessagesSource;

public class DiskDriveDataAnalyser {
	static double alertValue;
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
		String message;
		message=MessagesSource.getformHashmap("alert")+" ur system";
		message=message+MessagesSource.getformHashmap("softcapDisk");
		message=message+alertValue+"\n";
		message=message+MessagesSource.getformHashmap("bye");
	}

	private static void diskTotalHardAlert() {
		// TODO Auto-generated method stub
		String message;
		message=MessagesSource.getformHashmap("alert")+" ur system";
		message=message+MessagesSource.getformHashmap("hardcapDisk");
		message=message+alertValue+"\n";
		message=message+MessagesSource.getformHashmap("bye");
	}

}
