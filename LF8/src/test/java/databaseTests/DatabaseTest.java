package databaseTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import DatabaseInteractors.DatabasePusher;
import Webside.Application;
import database.CPUUsage;
import database.RAMUsage;

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
    public void entryPushTestCPUUsage() {
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
    
    
    
    @Test
    public void entryPushTestRAMUsage() {
    	// create Expected CPUUsage
    	Instant now = Instant.now();
    	Timestamp timestamp = Timestamp.from(now);
    	double usagePercentage = 99.9;
    	// clear Database
    	DatabasePusher.cleanTables();
    	// push Expected to Database
    	DatabasePusher.pushRAMUsageToDatabase(usagePercentage, timestamp);
    	// get Database Entries
    	List<RAMUsage> databaseEntries = DatabasePusher.getRAMUsages();
    	// check if it works as expected.
    	assertTrue(databaseEntries.size() == 1, "Too many Database Entries found: "+ databaseEntries.size());
    	RAMUsage databaseEntry = databaseEntries.get(0);
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
