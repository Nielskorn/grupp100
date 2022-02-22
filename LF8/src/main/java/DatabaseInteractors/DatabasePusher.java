package DatabaseInteractors;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import database.CPUUsage;
import database.DBController;
import database.RAMUsage;

public class DatabasePusher {
	
	private static final String TIMECONDITION = "WHERE date < "; 
	
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
		return getCPUUsages("");
	}
	
	public static List<CPUUsage> getCPUUsages(String condition){
		ArrayList<CPUUsage> answer = new ArrayList<>();
		try {
		 answer = DBController.getCPUUsage("SELECT * FROM CPUUSAGE "+condition+";");
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	public static List<RAMUsage> getRAMUsagesLastMinute(){
		return getRAMUsages(TIMECONDITION + "'"+getTimeOfLastMinute()+"'" );
	}
	
	public static List<CPUUsage> getCPUUsagesLastMinute(){
		return getCPUUsages(TIMECONDITION + "'"+getTimeOfLastMinute()+"'" );
	}
	
	private static Timestamp getTimeOfLastMinute () {
		return Timestamp.from(Instant.now().minus(Duration.ofMinutes(1)));
	}
	
	public static List<RAMUsage> getRAMUsages(){
		return getRAMUsages("");
	}
	
	public static List<RAMUsage> getRAMUsages(String condition){
		ArrayList<RAMUsage> answer = new ArrayList<>();
		try {
		 answer = DBController.getRAMUsage("SELECT * FROM RAMUSAGE "+condition+";");
		
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
