package monitoring;

import Main.MonitorOfPCResorces;

public class monitorThread extends Thread {
		@Override
		public void run() {
			MonitorOfPCResorces pc = new MonitorOfPCResorces();
			pc.OsInfo();
			while (true) {	
				pc.getCpuload(pc.OsName);
				pc.memoryinfo();
				pc.drivespace();	
			}
			
		}
}

