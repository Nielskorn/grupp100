package datainterpretation;

import java.util.List;

import service.EMailSenderService;
import utils.MessagesSource;

public class DiskDriveDataAnalyser {
	static double alertValue;
	static EMailSenderService diskSender= new EMailSenderService();
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
		if(totalUsage > ThresholdList.getValue(ThresholdList.DISKSPACEOVERALLHARDCAP)) {
			diskTotalHardAlert();
			return;
		}
		if(totalUsage > ThresholdList.getValue(ThresholdList.DISKSPACEOVERALLSOFTCAP)) {
			diskTotalSoftAlert();
		}
		
	}

	private static void diskTotalSoftAlert() {
		// TODO Auto-generated method stub
		String message;
		String subject= "Soft Disk Alert";
		message=MessagesSource.getformHashmap("alert")+" ur system";
		message=message+MessagesSource.getformHashmap("softcapDisk");
		message=message+alertValue+"\n";
		message=message+MessagesSource.getformHashmap("bye");
		diskSender.sendAlert(subject, message);
		
	}

	private static void diskTotalHardAlert() {
		// TODO Auto-generated method stub
		String message;
		String subject= "Hard Disk Alert";
		message=MessagesSource.getformHashmap("alert")+" ur system";
		message=message+MessagesSource.getformHashmap("hardcapDisk");
		message=message+alertValue+"\n";
		message=message+MessagesSource.getformHashmap("bye");
		diskSender.sendAlert(subject, message);
	}

}
