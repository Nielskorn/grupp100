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

public class MonitorOfPCResorces {
	String cpuReadCommand = "CPU_Percentage.bat";
	static double GB = 1024D * 1024D * 1024D;
	// Format drive space as per your need
	// double MB = 1024D * 1024D;
	// double KB = 1024D;
	Timestamp Cpu_pecentage;
	Timestamp memory;
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
		double Totaloftotals=0;
		double TotaloffreeSpace=0;
		double TotalofUsableSpace=0;
		File[] listDrives = File.listRoots();
		System.out.println("Listing System drives:");
		for (File drive : listDrives) {
			//System.out.printf("Drive: %s\n", drive);
			double totalspace = drive.getTotalSpace() / GB;
			//System.out.printf("Total Space: %8.2f GB\n", totalspace);
			double freeSpace = drive.getFreeSpace() / GB;
			//System.out.printf("Free Space: %8.2f GB\n", freeSpace);
			double UsableSpace = drive.getUsableSpace() / GB;
			//System.out.printf("Usable Space: %8.2f GB\n\n", UsableSpace);
			Totaloftotals=Totaloftotals+totalspace;
			TotaloffreeSpace = TotaloffreeSpace+freeSpace;
			TotalofUsableSpace=TotalofUsableSpace+UsableSpace;
		}
		//System.out.println("Total of all System drives");
		//System.out.printf("Total space: %8.2f GB\n",Totaloftotals);
		//System.out.printf("Free Space : %8.2f GB \n",TotaloffreeSpace);
		//System.out.printf("Usable Space: %8.2f GB \n",TotalofUsableSpace);

		
	}

	public void OsInfo() {
		String Arch = ManagementFactory.getOperatingSystemMXBean().getArch();
		String OsName = ManagementFactory.getOperatingSystemMXBean().getName();
		String OsVersion = ManagementFactory.getOperatingSystemMXBean().getVersion();
		this.OsName = OsName;
	}

	 public RAMUsage memoryinfo() {
		double totalMemory=sunbean.getTotalMemorySize()/GB;
		double freeMemory = sunbean.getFreeMemorySize() / GB;
		double usedMemory= totalMemory-freeMemory;
		//System.out.printf("freeMemorySize:%8.2f GB\n", freeMemory);
		//System.out.printf("TotalMemorySize:%8.2f GB\n",totalMemory);
		Instant now = Instant.now();
		memory=Timestamp.from(now);
		RAMUsage ram=new RAMUsage(memory, usedMemory);
		return ram;
	}

	public CPUUsage getCpuload(String OsName) {
		int cpuloadint=0;
		Process CpuRead = null;
		if (OsName.toLowerCase().contains("windows")) {
		if (new File("CPU_Percentage.bat").exists()) {
			try {
				CpuRead = Runtime.getRuntime().exec(cpuReadCommand);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}

		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(CpuRead.getInputStream()));
		String Cpuloadpercentage = null;
		try {
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				if (!line.isBlank())
					Cpuloadpercentage = line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Cpuloadpercentage = Cpuloadpercentage.strip();
		 cpuloadint = Integer.parseInt(Cpuloadpercentage);
		System.out.println("Cpuload: " + Cpuloadpercentage + " %");
		if (cpuloadint >= 80) {
			System.out.println("Alert: Cpu is over 80%");

		}}
		else {
			sunbean.getCpuLoad();
		}		
		Instant now = Instant.now();
		Cpu_pecentage=Timestamp.from(now);
		CPUUsage CpuUsage=new CPUUsage(Cpu_pecentage,cpuloadint);
		return CpuUsage;
	}
}
