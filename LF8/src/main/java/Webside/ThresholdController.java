package Webside;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import datainterpretation.ThresholdList;

@RestController("thresholds")
@RequestMapping("thresholds")
public class ThresholdController {
	
	@PostMapping("/changeThreshold")
	public ResponseEntity<?> changeThreshold(@RequestBody @NonNull HashMap<String,Double> newThreshold) {
		for(String thresholdName : newThreshold.keySet()) {
			ThresholdList.changeValue(thresholdName, newThreshold.get(thresholdName));
		}
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/getThresholds")
	public ResponseEntity<HashMap<String,Double>> getThresholds(){
		return ResponseEntity.ok(ThresholdList.getThresholds());
	}
	
	
}
