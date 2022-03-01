package javatest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Main.MonitorOfPCResorces;
import database.CPUUsage;
import javatestThread.CpuLoadThread;

class TestCPUIncrease {

	@Test
	public void cpuIncrease() {
		CPUUsage cpuValue1;
		CPUUsage cpuValue2=null;
		MonitorOfPCResorces mon= new MonitorOfPCResorces();
		mon.OsInfo();
		cpuValue1=mon.getCpuload(mon.OsName);
		CpuLoadThread last = null;
		for(int i=0; i < 10; i++) {
			last = new CpuLoadThread();
			last.start();
		}
		while(!last.started) {
			
		}
		cpuValue2=mon.getCpuload(mon.OsName);	
		System.out.println(cpuValue1.getUsage() + " " + cpuValue2.getUsage());
		assertTrue(cpuValue1.getUsage() < cpuValue2.getUsage());		
	}

}
