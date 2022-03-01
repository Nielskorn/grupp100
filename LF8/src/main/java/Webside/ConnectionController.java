package Webside;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("connection")
@RequestMapping("connection")
public class ConnectionController {
	
	@GetMapping("/test")
	public ResponseEntity<?> connectionTest(){
		return ResponseEntity.ok(null);
	}
	
}
