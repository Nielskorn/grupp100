package datainterpretation;

import java.util.List;

import DatabaseInteractors.DatabasePusher;
import database.CPUUsage;

public class CPUUsageAnalyser {
	
	public static void analyseDatabase() {
		List<CPUUsage> cpuUsages = DatabasePusher.getCPUUsagesLastMinute();
		int sumOfPercentages = 0;;
		int counter = 0;
		for(CPUUsage cpuUsage : cpuUsages) {
			sumOfPercentages += cpuUsage.getUsage();
			counter++;
			if(cpuUsage.getUsage() > Thresholds.CPUHARDCAP)
				cpuAlert();
		}
		int mean = 0;
		if(counter != 0)
			mean = sumOfPercentages/counter;
		if(mean > Thresholds.CPUSOFTCAP)
			cpuAlert();
	}

	public static void cpuAlert() {
		//TODO
	}
}
