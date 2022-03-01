package datainterpretation;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import DatabaseInteractors.DatabasePusher;
import database.RAMUsage;

import service.EMailSenderService;
import utils.MessagesSource;

public class RAMUsageAnalyser {
	
	public static void analyseDatabase() {
		List<RAMUsage> ramUsages = DatabasePusher.getRAMUsagesLastMinute();
		double sumOfPercentages = 0;;
		int counter = 0;
		for(RAMUsage ramUsage : ramUsages) {
			sumOfPercentages += ramUsage.getUsage();
			counter++;
			if(ramUsage.getUsage() > ThresholdList.getValue(ThresholdList.RAMHARDCAP))
				ramAlert(ramUsage.getUsage());
		}
		double mean = 0;
		if(counter != 0)
			mean = sumOfPercentages/counter;
		if(mean > ThresholdList.getValue(ThresholdList.RAMSOFTCAP))
			ramAlert(mean);
	}


	public static void ramAlert(double alertValue )  {
		try {
			String message;
			String subject="Ram Alert";
			message=MessagesSource.getformHashmap("alert")+" ";
			message=message+MessagesSource.getformHashmap("ram");
			message=message+alertValue+"% \n";
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
