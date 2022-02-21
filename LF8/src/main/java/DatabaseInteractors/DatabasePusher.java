package DatabaseInteractors;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Gruppe100.LF8.Database.CPUUsage;
import Gruppe100.LF8.Database.DBController;
import Gruppe100.LF8.Database.RAMUsage;

public class DatabasePusher {
	
	public static void pushCPUUsageToDatabase(int usage, Timestamp date) {
		CPUUsage cpuUsage = new CPUUsage(date, usage);
		try {
		DBController.getVoid("INSERT INTO CPUUSAGE (date,timeInString,usage) VALUES "+
				"('"+cpuUsage.getDate()+"','"+cpuUsage.getDay()+"','"+cpuUsage.getUsage()+"');");
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void pushRAMUsageToDatabase(double usage, Timestamp date) {
		RAMUsage ramUsage = new RAMUsage(date, usage);
		try {
		DBController.getVoid("INSERT INTO RAMUSAGE (date,timeInString,usage) VALUES "+
				"('"+ramUsage.getDate()+"','"+ramUsage.getDay()+"','"+ramUsage.getUsage()+"');");
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<CPUUsage> getCPUUsages(){
		ArrayList<CPUUsage> answer = new ArrayList<>();
		try {
		 answer = DBController.getCPUUsage("SELECT * FROM CPUUSAGE;");
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	public static List<RAMUsage> getRAMUsages(){
		ArrayList<RAMUsage> answer = new ArrayList<>();
		try {
		 answer = DBController.getRAMUsage("SELECT * FROM RAMUSAGE;");
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	public static void cleanTables() {
		try {
		DBController.getVoid("DELETE FROM CPUUSAGE;");
		DBController.getVoid("DELETE FROM RAMUSAGE;");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
