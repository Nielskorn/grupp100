package javatest;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import database.CPUUsage;
import database.RAMUsage;
import Main.MonitorOfPCResorces;

public class TestMonitorOfPCResouces {
@Test
public void Cpuload() {
	CPUUsage cpuUsage;
	MonitorOfPCResorces mon= new MonitorOfPCResorces();
	mon.OsInfo();
	cpuUsage=mon.getCpuload(mon.OsName);
	assertNotNull(cpuUsage.getDate());
	System.out.println(cpuUsage.getUsage());
	assertTrue(cpuUsage.getUsage()>=0);

}
@Test 
void Ramload() {
	RAMUsage ramUsage;
	MonitorOfPCResorces mon=new MonitorOfPCResorces();
	ramUsage= mon.memoryinfo();
	assertNotNull(ramUsage.getDate());
	assertTrue(ramUsage.getUsage()>0);
}
}
