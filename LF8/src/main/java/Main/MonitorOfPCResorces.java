package Main;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.time.Instant;

import com.sun.management.OperatingSystemMXBean;

import database.CPUUsage;
import database.RAMUsage;
import datainterpretation.DiskSpaceDataAcceptor;

/*** @author niels
 * {@summary Class zu monitoring of Pc resorces}
 **/
public class MonitorOfPCResorces {
	// datei pfad für batchfile zum auslessen der cpu last in percentage
	String cpuReadCommand = "CPU_Percentage.bat";
	static double GB = 1024 * 1024 * 1024;
	// Format drive space as per your need
	// double MB = 1024D * 1024D;
	// double KB = 1024D;
	Timestamp cpuUsageTimestamp;
	Timestamp memoryUsageTimestamp;
	
	//Osname field zum weitergeben des betriebssystems
	public String OsName;
	static OperatingSystemMXBean sunbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
			.getOperatingSystemMXBean();

	
//methode zum auslesen Hard drives und übergab an dbclass
	 public void drivespace() {
		 //var zum speichern des gesamtsystem Speicher
		double totalOfTotals=0;
		double totalOfFreeSpace=0;
		double totalOfUsableSpace=0;
		File[] listDrives = File.listRoots();
		//System.out.println("Listing System drives:");
		for (File drive : listDrives) {
			//System.out.printf("Drive: %s\n", drive);
			String name=drive.getPath();
			double totalSpace = drive.getTotalSpace() / GB;
			
			double freeSpace = drive.getFreeSpace() / GB;
			
			double UsableSpace = drive.getUsableSpace() / GB;
			
			//adding of singel drives to whole system
			totalOfTotals=totalOfTotals+totalSpace;
			totalOfFreeSpace = totalOfFreeSpace+freeSpace;
			totalOfUsableSpace=totalOfUsableSpace+UsableSpace;
 
			DiskSpaceDataAcceptor.acceptDiskData(name, totalSpace, freeSpace);
		}

	}
	 // methode um infos über das betriebssystems zubekommen
	 //sets osname
	public void OsInfo() {
		String arch = ManagementFactory.getOperatingSystemMXBean().getArch();
		String OsName = ManagementFactory.getOperatingSystemMXBean().getName();
		String osVersion = ManagementFactory.getOperatingSystemMXBean().getVersion();
		this.OsName = OsName;
	}
		//methode um usedMemory in percentage zu errechenen und dies an db zu übergeben
	 public RAMUsage memoryinfo() {
		double totalMemory=sunbean.getTotalPhysicalMemorySize()/GB;
		double freeMemory = sunbean.getFreePhysicalMemorySize() / GB;
		double usedMemory=totalMemory-freeMemory;
		double memoryOnePercentage=totalMemory/100;
		double memoryInPercentage= usedMemory/memoryOnePercentage;
		Instant now = Instant.now();
		memoryUsageTimestamp=Timestamp.from(now);
		RAMUsage ram=new RAMUsage(memoryUsageTimestamp, memoryInPercentage);
		return ram;
	}
	 //methode to get cpu load für angebens betriebssystem
	 //und push the load as int to db
	 public CPUUsage getCpuload(String OsName) {
			double cpuUsageAsDoubleForLinux = 0;
			int cpuUsageAsInt=0;
			

			if (OsName.toLowerCase().contains("windows")) {	
				cpuUsageAsInt= getCpuLoadWindows();	
				
			}else {		
				cpuUsageAsDoubleForLinux = sunbean.getSystemCpuLoad();
				System.out.println("CPU LOAD :" + cpuUsageAsDoubleForLinux);
				cpuUsageAsDoubleForLinux = cpuUsageAsDoubleForLinux * 100;
				cpuUsageAsInt = (int) Math.round(cpuUsageAsDoubleForLinux);
				System.out.println("Usage as Int: "+cpuUsageAsInt);
			}		
			Instant now = Instant.now();
			cpuUsageTimestamp=Timestamp.from(now);
			CPUUsage CpuUsage=new CPUUsage(cpuUsageTimestamp,cpuUsageAsInt);
			return CpuUsage;
		}
		//methode do get cpuload in windows
	 	// über batch dartei dies funkuniert leider nicht in windows 11
		private int getCpuLoadWindows() {
			String cpuUsagePercentageAsString = null;
			Process cpuRead = null;
			if (new File("CPU_Percentage.bat").exists()) {
				try {
					cpuRead = Runtime.getRuntime().exec(cpuReadCommand);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}

			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(cpuRead.getInputStream()));
		
			try {
				for (String line = reader.readLine(); line != null; line = reader.readLine()) {
					if (!line.isBlank())
						cpuUsagePercentageAsString = line;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			cpuUsagePercentageAsString = cpuUsagePercentageAsString.strip();
			return Integer.parseInt(cpuUsagePercentageAsString);
			
		}


			}
			
	 


	



