package Webside;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import datainterpretation.Threshold;
import datainterpretation.ThresholdList;

@RestController("thresholds")
@RequestMapping("thresholds")
public class ThresholdController {
	
	@PostMapping("/changeThreshold")
	public ResponseEntity<?> changeThreshold(@RequestBody @NonNull Threshold newThreshold) {
		ThresholdList.changeValue(newThreshold.name, newThreshold.value);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/getThresholds")
	public ResponseEntity<ArrayList<Threshold>> getThresholds(){
		return ResponseEntity.ok(ThresholdList.getThresholdsAsList());
	}
	
	
}
