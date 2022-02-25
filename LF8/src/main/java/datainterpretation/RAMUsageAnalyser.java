package datainterpretation;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import DatabaseInteractors.DatabasePusher;
import database.RAMUsage;

import service.EMailSenderService;
import utils.MessagesSource;

public class RAMUsageAnalyser {
	static double alertValue;
	
	public static void analyseDatabase() throws UnsupportedEncodingException, MessagingException {
		List<RAMUsage> ramUsages = DatabasePusher.getRAMUsagesLastMinute();
		double sumOfPercentages = 0;;
		int counter = 0;
		for(RAMUsage ramUsage : ramUsages) {
			sumOfPercentages += ramUsage.getUsage();
			counter++;
			if(ramUsage.getUsage() > Thresholds.RAMHARDCAP)
				alertValue= ramUsage.getUsage();
				ramAlert(alertValue);
		}
		double mean = 0;
		if(counter != 0)
			mean = sumOfPercentages/counter;
		if(mean > Thresholds.RAMSOFTCAP)
			ramAlert(alertValue);
	}

	public static void ramAlert(double alertValue ) throws UnsupportedEncodingException, MessagingException {
		//TODO
		String message;
		String subject="Ram Alert";
		message=MessagesSource.getformHashmap("alert")+" ";
		message=message+MessagesSource.getformHashmap("ram");
		message=message+alertValue+"% \n";
		message=message+MessagesSource.getformHashmap("bye");
		System.out.println(message);
		EMailSenderService sender=new EMailSenderService();
		sender.sendAlert(subject,message);
	}

}
