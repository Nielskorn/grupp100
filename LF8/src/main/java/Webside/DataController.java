package Webside;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DatabaseInteractors.DatabasePusher;
import database.CPUUsage;
import database.RAMUsage;
import datainterpretation.DiskCaps;
import datainterpretation.DiskDrive;
import datainterpretation.DiskDriveDataAnalyser;

@RestController("database")
@RequestMapping("database")
public class DataController {
	
	@GetMapping("/diskUsage")
	public ResponseEntity<HashMap<String, Double>> getDiskUsages(){
		List<DiskDrive> diskDrives = DiskCaps.getDriveList();
		HashMap<String, Double> diskUsages = new HashMap<>();
		for(DiskDrive drive : diskDrives) {
			diskUsages.put(drive.getDiskName(), drive.getLastUsage());
		}
		return ResponseEntity.ok(diskUsages);
	}
	
	@GetMapping("/overAllDiskUsage")
	public ResponseEntity<Double> getOverAllDiskUsage() {
		return ResponseEntity.ok(DiskDriveDataAnalyser.getLastTotalUsage());
	}
	
	@GetMapping("/getRAMData")
	public ResponseEntity<Integer[]> getRAMData(){
		List<RAMUsage> datalist = DatabasePusher.getRAMUsagesStandardTimeFrame();
		Integer[] dataArray = new Integer[datalist.size()];
		for(int i = 0 ; i < dataArray.length; i++) {
			dataArray[i] = (int)Math.round(datalist.get(i).getUsage());
			dataArray[i] = i;
		}
		return ResponseEntity.ok(dataArray);
	}
	
	@GetMapping("/getCPUData")
	public ResponseEntity<Integer[]> getCPUData(){
		List<CPUUsage> datalist = DatabasePusher.getCPUUsagesStandardTimeFrame();
		Integer[] dataArray = new Integer[datalist.size()];
		for(int i = 0 ; i < dataArray.length; i++) {
			dataArray[i] = (int)Math.round(datalist.get(i).getUsage());
			dataArray[i] = i;
		}
		return ResponseEntity.ok(dataArray);
	}
	

}
