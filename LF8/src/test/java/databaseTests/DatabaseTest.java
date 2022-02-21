package databaseTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import DatabaseInteractors.DatabasePusher;
import Gruppe100.LF8.Database.CPUUsage;
import Webside.Application;

@SpringBootTest(classes = Application.class)
public class DatabaseTest {
    
    //@Autowired
    //private TestRestTemplate testRestTemplate;
    
    @BeforeAll
    private static void testSetup() {
    	
    }
    
    @Test
    public void testDatabaseClean() {
    	DatabasePusher.cleanTables();
    	assertEquals(0, DatabasePusher.getCPUUsages().size());
    	assertEquals(0, DatabasePusher.getRAMUsages().size());
    }
    
    @Test
    public void entryPushTest() {
    	// create Expected CPUUsage
    	Instant now = Instant.now();
    	Timestamp timestamp = Timestamp.from(now);
    	int usagePercentage = 99;
    	// clear Database
    	DatabasePusher.cleanTables();
    	// push Expected to Database
    	DatabasePusher.pushCPUUsageToDatabase(usagePercentage, timestamp);
    	// get Database Entries
    	List<CPUUsage> databaseEntries = DatabasePusher.getCPUUsages();
    	// check if it works as expected.
    	assertTrue(databaseEntries.size() == 1, "Too many Database Entries found: "+ databaseEntries.size());
    	CPUUsage databaseEntry = databaseEntries.get(0);
    	assertEquals(usagePercentage,databaseEntry.getUsage(), "Wrong Percentage in Entry");
    	LocalDateTime localTimeExpected = timestamp.toLocalDateTime();
    	LocalDateTime localTimeActual = databaseEntry.getDate().toLocalDateTime();
    	assertEquals(localTimeExpected.getYear(),localTimeActual.getYear());
    	assertEquals(localTimeExpected.getMonth(),localTimeActual.getMonth());
    	assertEquals(localTimeExpected.getDayOfMonth(),localTimeActual.getDayOfMonth());
    	assertEquals(localTimeExpected.getHour(),localTimeActual.getHour());
    	assertEquals(localTimeExpected.getMinute(),localTimeActual.getMinute());
    	
    }
    
}
