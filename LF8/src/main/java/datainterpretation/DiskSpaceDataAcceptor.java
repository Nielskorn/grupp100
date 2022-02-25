package datainterpretation;

import service.EMailSenderService;
import utils.MessagesSource;

public class DiskSpaceDataAcceptor {
	
	public static void acceptDiskData(String diskDriveName, double totalSpace, double freeSpace) {
		if(totalSpace == 0)
			return;
		double usage = (1 - ( freeSpace / totalSpace )) * 100;
		if( usage > DiskCaps.getDiskHardCap(diskDriveName)) {
			individualDriveHardAlert(diskDriveName, usage);
		}else if( usage > DiskCaps.getDiskSoftCap(diskDriveName)) {
			individualDriveSoftAlert(diskDriveName,usage);
		}
		DiskCaps.setUsage(diskDriveName, usage,totalSpace);
		
	}

	private static void individualDriveSoftAlert(String diskDriveName,double usage) {
		// TODO Auto-generated method stub
		try {
			String message;
			String subject="Diskspace alert for"+diskDriveName;
			message=MessagesSource.getformHashmap("alert")+" ";
			message=message+MessagesSource.getformHashmap("softcapDisk");
			message=message+usage+"% \n";
			message=message+MessagesSource.getformHashmap("bye");
			System.out.println(message);
			EMailSenderService sender=new EMailSenderService();
			sender.sendAlert(subject,message);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Could not send mail");
		}
	}
		
		
	

	private static void individualDriveHardAlert(String diskDriveName,double usage) {
		// TODO Auto-generated method stub
		try {
			String message;
			String subject="Diskspace alert for"+diskDriveName;
			message=MessagesSource.getformHashmap("alert")+" ";
			message=message+MessagesSource.getformHashmap("softcapDisk");
			message=message+usage+"% \n";
			message=message+MessagesSource.getformHashmap("bye");
			System.out.println(message);
			EMailSenderService sender=new EMailSenderService();
			sender.sendAlert(subject,message);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Could not send mail");
		}
	}
	

}
