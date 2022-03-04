package DatabaseInteractors;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;

import database.DBController;
import datainterpretation.CPUUsageAnalyser;
import datainterpretation.DiskDriveDataAnalyser;
import datainterpretation.RAMUsageAnalyser;

public class DatabaseMaintenanceThread extends Thread {
	
	public DatabaseMaintenanceThread() {
		
	}
	private final int MILISECONDS = 1;
	private final int SECONDS = 1000 * MILISECONDS;
	private final int MINUTE = 60 * SECONDS;
	private final int HOUR = 60 * MINUTE;
	
	@Override
	public void run() {
		int minuteCounter = 0;
		while(true) {
			CPUUsageAnalyser.analyseDatabase();
			RAMUsageAnalyser.analyseDatabase();
			try {
				sleep(MINUTE);
				minuteCounter++;
			}catch(InterruptedException e) {
				break;
			}
			if( minuteCounter % 10 == 0 )
				DiskDriveDataAnalyser.analyseData();
			if((minuteCounter * MINUTE) > HOUR ) {
				minuteCounter = 0;
				deleteIrrelevantDatabaseData();
			}
		}
	}
	
	public static void deleteIrrelevantDatabaseData() {
		// this method should delete all data older then one day before today.
		Instant yesterday = Instant.now().minus(Duration.ofHours(12));
		try {
			DBController.getVoid("DELETE FROM CPUUSAGE WHERE date < '"+ Timestamp.from(yesterday)+"';");
			DBController.getVoid("DELETE FROM RAMUSAGE WHERE date < '"+ Timestamp.from(yesterday)+"';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}