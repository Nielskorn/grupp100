package datainterpretation;

import java.util.List;

import DatabaseInteractors.DatabasePusher;
import database.RAMUsage;

public class RAMUsageAnalyser {
	
	public static void analyseDatabase() {
		List<RAMUsage> ramUsages = DatabasePusher.getRAMUsagesLastMinute();
		double sumOfPercentages = 0;;
		int counter = 0;
		for(RAMUsage ramUsage : ramUsages) {
			sumOfPercentages += ramUsage.getUsage();
			counter++;
			if(ramUsage.getUsage() > Thresholds.RAMHARDCAP)
				ramAlert();
		}
		double mean = 0;
		if(counter != 0)
			mean = sumOfPercentages/counter;
		if(mean > Thresholds.RAMSOFTCAP)
			ramAlert();
	}

	public static void ramAlert() {
		//TODO
	}

}
