package Webside;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		System.out.println(DiskDriveDataAnalyser.getLastTotalUsage());
		return ResponseEntity.ok(DiskDriveDataAnalyser.getLastTotalUsage());
	}
	

}
