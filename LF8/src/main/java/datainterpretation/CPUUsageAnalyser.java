package datainterpretation;

import java.util.List;

import DatabaseInteractors.DatabasePusher;
import database.CPUUsage;
import utils.MessagesSource;

public class CPUUsageAnalyser {
	static int alertValue;
	public static void analyseDatabase() {
		List<CPUUsage> cpuUsages = DatabasePusher.getCPUUsagesLastMinute();
		int sumOfPercentages = 0;;
		int counter = 0;
		for(CPUUsage cpuUsage : cpuUsages) {
			sumOfPercentages += cpuUsage.getUsage();
			counter++;
			if(cpuUsage.getUsage() > Thresholds.CPUHARDCAP)
				alertValue=cpuUsage.getUsage();
				cpuAlert();
		}
		int mean = 0;
		if(counter != 0)
			mean = sumOfPercentages/counter;
		if(mean > Thresholds.CPUSOFTCAP)
			cpuAlert();
	}

	public static void cpuAlert() {
		String message;
		message=MessagesSource.getformHashmap("alert")+" ";
		message=message+MessagesSource.getformHashmap("cpu");
		message=message+alertValue+"% \n";
		message=message+MessagesSource.getformHashmap("bye");
	}
}
