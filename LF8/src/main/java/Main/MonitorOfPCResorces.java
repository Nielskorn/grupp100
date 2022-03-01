package Main;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.sun.management.OperatingSystemMXBean;

import database.CPUUsage;
import database.RAMUsage;
import datainterpretation.DiskSpaceDataAcceptor;

public class MonitorOfPCResorces {
	String cpuReadCommand = "CPU_Percentage.bat";
	static double GB = 1024 * 1024 * 1024;
	// Format drive space as per your need
	// double MB = 1024D * 1024D;
	// double KB = 1024D;
	Timestamp cpuUsageTimestamp;
	Timestamp memoryUsageTimestamp;
	
	
	public String OsName;
	static OperatingSystemMXBean sunbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
			.getOperatingSystemMXBean();

	public static void main(String[] args) {
		MonitorOfPCResorces mon = new MonitorOfPCResorces();
		mon.memoryinfo();
		mon.OsInfo();
		mon.getCpuload(mon.OsName);
		mon.drivespace();
	}

	 void drivespace() {
		double totalOfTotals=0;
		double totalOfFreeSpace=0;
		double totalOfUsableSpace=0;
		File[] listDrives = File.listRoots();
		//System.out.println("Listing System drives:");
		for (File drive : listDrives) {
			//System.out.printf("Drive: %s\n", drive);
			String name=drive.getName();
			double totalSpace = drive.getTotalSpace() / GB;
			//System.out.printf("Total Space: %8.2f GB\n", totalSpace);
			double freeSpace = drive.getFreeSpace() / GB;
			//System.out.printf("Free Space: %8.2f GB\n", freeSpace);
			double UsableSpace = drive.getUsableSpace() / GB;
			//System.out.printf("Usable Space: %8.2f GB\n\n", UsableSpace);
			totalOfTotals=totalOfTotals+totalSpace;
			totalOfFreeSpace = totalOfFreeSpace+freeSpace;
			totalOfUsableSpace=totalOfUsableSpace+UsableSpace;
			//TODO Adding Alert 
			DiskSpaceDataAcceptor.acceptDiskData(name, totalSpace, freeSpace);
		}
		//System.out.println("Total of all System drives");
		//System.out.printf("Total space: %8.2f GB\n",totalOfTotals);
		//System.out.printf("Free Space : %8.2f GB \n",totalOfFreeSpace);
		//System.out.printf("Usable Space: %8.2f GB \n",totalOfUsableSpace);
		//TODO Adding ALERT for System Arlert
		
	}

	public void OsInfo() {
		String arch = ManagementFactory.getOperatingSystemMXBean().getArch();
		String OsName = ManagementFactory.getOperatingSystemMXBean().getName();
		String osVersion = ManagementFactory.getOperatingSystemMXBean().getVersion();
		this.OsName = OsName;
	}

	 public RAMUsage memoryinfo() {
		double totalMemory=sunbean.getTotalPhysicalMemorySize()/GB;
		double freeMemory = sunbean.getFreePhysicalMemorySize() / GB;
		double usedMemory=totalMemory-freeMemory;
		double memoryOnePercentage=totalMemory/100;
		double memoryInPercentage= usedMemory/memoryOnePercentage;
		
		System.out.printf("usedMemorySize:%8.2f\n" ,usedMemory);
		System.out.printf("freeMemorySize:%8.2f GB\n", freeMemory);
		System.out.printf("TotalMemorySize:%8.2f GB\n",totalMemory);
		Instant now = Instant.now();
		memoryUsageTimestamp=Timestamp.from(now);
		RAMUsage ram=new RAMUsage(memoryUsageTimestamp, memoryInPercentage);
		
		return ram;
	}
	 public CPUUsage getCpuload(String OsName) {
			double cpuUsageAsDoubleForLinux = 0;
			int cpuUsageAsInt=0;
			

			if (OsName.toLowerCase().contains("windows")) {
				cpuUsageAsInt = getCpuLoadWindows();
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
			
	 


	



