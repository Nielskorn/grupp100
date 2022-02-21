package DatabaseInteractors;

import java.sql.SQLException;
import java.sql.Timestamp;


import Gruppe100.LF8.Database.CPUUsage;
import Gruppe100.LF8.Database.DBController;
import Gruppe100.LF8.Database.RAMUsage;

public class DatabasePusher {
	
	public static void pushCPUUsageToDatabase(double usage, Timestamp date) {
		CPUUsage cpuUsage = new CPUUsage(date, usage);
		try {
		DBController.getVoid("INSERT INTO CPUUSAGE (date,timeInString,usage) VALUES "+
				"('"+cpuUsage.getDate()+"','"+cpuUsage.getDay()+","+cpuUsage.getUsage()+"');");
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void pushRAMUsageToDatabase(double usage, Timestamp date) {
		RAMUsage ramUsage = new RAMUsage(date, usage);
		try {
		DBController.getVoid("INSERT INTO RAMUSAGE (date,timeInString,usage) VALUES "+
				"('"+ramUsage.getDate()+"','"+ramUsage.getDay()+","+ramUsage.getUsage()+"');");
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
