package gui;

import java.awt.*;
import java.awt.event.*;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;

import javax.swing.*;

import servercommunication.Communicator;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 28.02.2022
 * @author 
 */

public class GUI extends JFrame {
  // GUI Visual fields
  // Anfang Attribute
  private JTextField enterThresholdValue = new JTextField();
  private JComboBox<String> chooseThresholdType = new JComboBox<String>();
  private DefaultComboBoxModel<String> modelForThresholdChoice = new DefaultComboBoxModel<String>();
  private JButton confirmThresholdUpdate = new JButton();
  private Canvas ramCanvas = new Canvas();
  private JLabel ramOverLabel = new JLabel();
  private Canvas cpuCanvas = new Canvas();
  private JLabel cpuOverLabel = new JLabel();
  private JLabel cpuUnderLabel = new JLabel();
  private JLabel cpuLeftLabel = new JLabel();
  private JLabel ramLeftLabel = new JLabel();
  private JLabel ramUnderLabel = new JLabel();
  private JLabel individualDiskDriveLabel = new JLabel();
  private JLabel individualDiskDriveUsageLabel = new JLabel();
  private JComboBox<String> individualDiskDriveSelection = new JComboBox<String>();
  private DefaultComboBoxModel<String> modelForIndividualDiskDriveSelection = new DefaultComboBoxModel<String>();
  private JLabel overAllDiskDriveLabel = new JLabel();
  private JLabel overAllDiskDriveUsageLabel = new JLabel();
  private JLabel labelOverDiskDriveSelection = new JLabel();
  private JComboBox<String> dataUpdateFrequencySelector = new JComboBox<String>();
  private DefaultComboBoxModel<String> modelForDataUpdateFrequency = new DefaultComboBoxModel<String>();
  private JLabel dataUpdateFrequencyDescription = new JLabel();
  private JLabel enterThresholdDescription = new JLabel();
  private JLabel thresholdSelectionDescription = new JLabel();
  private JTextField serverUrlInputField = new JTextField();
  private JButton serverUrlConfirm = new JButton();
  private JLabel serverUrlDescription = new JLabel();
  private JLabel connectionErrorLabel = new JLabel();
  // GUI functional fields
  public Communicator serverConnection;
  private HashMap<String, Double> thresholds;
  private HashMap<String, Double> driveUsage;
  private UpdateGUIThread updateThread ;
  // Ende Attribute
  
  public GUI() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 1234; 
    int frameHeight = 731;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("GUI");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    enterThresholdValue.setBounds(48, 32, 150, 20);
    cp.add(enterThresholdValue);
    chooseThresholdType.setModel(modelForThresholdChoice);
    chooseThresholdType.setBounds(224, 32, 266, 20);
    cp.add(chooseThresholdType);
    confirmThresholdUpdate.setBounds(168, 64, 75, 25);
    confirmThresholdUpdate.setText("Confirm");
    confirmThresholdUpdate.setMargin(new Insets(2, 2, 2, 2));
    confirmThresholdUpdate.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        confirmThresholdUpdate_ActionPerformed(evt);
      }
    });
    cp.add(confirmThresholdUpdate);
    ramCanvas.setBounds(696, 160, 300, 300);
    cp.add(ramCanvas);
    ramOverLabel.setBounds(688, 130, 110, 20);
    ramOverLabel.setText("Ram Usage Graph");
    cp.add(ramOverLabel);
    cpuCanvas.setBounds(96, 160, 300, 300);
    cp.add(cpuCanvas);
    cpuOverLabel.setBounds(88, 130, 110, 20);
    cpuOverLabel.setText("CPU Usage Graph");
    cp.add(cpuOverLabel);
    cpuUnderLabel.setBounds(200, 460, 110, 20);
    cpuUnderLabel.setText("Time in Minutes");
    cp.add(cpuUnderLabel);
    cpuLeftLabel.setBounds(8, 270, 78, 20);
    cpuLeftLabel.setText("CPU in %");
    cp.add(cpuLeftLabel);
    ramLeftLabel.setBounds(608, 270, 70, 20);
    ramLeftLabel.setText("RAM in %");
    cp.add(ramLeftLabel);
    ramUnderLabel.setBounds(800, 460, 110, 20);
    ramUnderLabel.setText("Time in Minutes");
    cp.add(ramUnderLabel);
    individualDiskDriveLabel.setBounds(72, 526, 46, 20);
    
    cp.add(individualDiskDriveLabel);
    individualDiskDriveUsageLabel.setBounds(160, 526, 46, 20);
    cp.add(individualDiskDriveUsageLabel);
    individualDiskDriveSelection.setModel(modelForIndividualDiskDriveSelection);
    individualDiskDriveSelection.setBounds(232, 526, 150, 20);
    individualDiskDriveSelection.addItemListener( itemEvent -> {
		differentDiskDriveChosen(itemEvent.getItem().toString());
	});
    cp.add(individualDiskDriveSelection);
    overAllDiskDriveLabel.setBounds(64, 582, 110, 20);
    overAllDiskDriveLabel.setText("TotalDrive Usage:");
    cp.add(overAllDiskDriveLabel);
    overAllDiskDriveUsageLabel.setBounds(232, 582, 110, 20);
    
    cp.add(overAllDiskDriveUsageLabel);
    labelOverDiskDriveSelection.setBounds(240, 502, 110, 20);
    labelOverDiskDriveSelection.setText("Select Disk Drive");
    cp.add(labelOverDiskDriveSelection);
    dataUpdateFrequencySelector.setModel(modelForDataUpdateFrequency);
    dataUpdateFrequencySelector.setBounds(752, 528, 150, 20);
    dataUpdateFrequencySelector.addItemListener( itemEvent -> {
		differentFrequencyChosen(itemEvent.getItem().toString());
	});
    //TODO add time values
    cp.add(dataUpdateFrequencySelector);
   
    dataUpdateFrequencyDescription.setBounds(748, 504, 200, 20);
    dataUpdateFrequencyDescription.setText("Select Data Update Frequency");
    dataUpdateFrequencySelector.addItem("30sec");
    dataUpdateFrequencySelector.addItem("1min");
    dataUpdateFrequencySelector.addItem("5min");
    dataUpdateFrequencySelector.addItem("10min");
    dataUpdateFrequencySelector.addItem("30min");
    dataUpdateFrequencySelector.addItem("60min");

    cp.add(dataUpdateFrequencyDescription);
    enterThresholdDescription.setBounds(64, 8, 160, 20);
    enterThresholdDescription.setText("Enter Threshold Value");
    cp.add(enterThresholdDescription);
    thresholdSelectionDescription.setBounds(248, 8, 110, 20);
    thresholdSelectionDescription.setText("Select Threshold");
    cp.add(thresholdSelectionDescription);
    serverUrlInputField.setBounds(456, 312, 246, 20);
    serverUrlInputField.setText("http://localhost:8080");
    cp.add(serverUrlInputField);
    serverUrlConfirm.setBounds(528, 352, 99, 33);
    serverUrlConfirm.setText("Confirm");
    serverUrlConfirm.setMargin(new Insets(2, 2, 2, 2));
    serverUrlConfirm.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        serverUrlConfirm_ActionPerformed(evt);
      }
    });
    cp.add(serverUrlConfirm);
    connectionErrorLabel.setBounds(428, 400, 400, 20);
    cp.add(connectionErrorLabel);
    serverUrlDescription.setBounds(500, 272, 210, 20);
    serverUrlDescription.setText("Please Input Server Url");
    cp.add(serverUrlDescription);
    // Ende Komponenten
    
    setVisible(true);
    monitoringScreen(false);
    urlInputScreen(true);
  } // end of public GUI

  private void updateIndividualDiskDriveLabels(String driveName, double usage) {
    individualDiskDriveLabel.setText(driveName);
    individualDiskDriveUsageLabel.setText(Math.round(usage)+"%");
  }
  private void updateOverAllDiskDrive(double usage) {
    overAllDiskDriveUsageLabel.setText(Math.round(usage) + "%");
  }
  
  private void urlInputScreen(boolean visible) {
    serverUrlConfirm.setVisible(visible);
    serverUrlDescription.setVisible(visible);
    serverUrlInputField.setVisible(visible);
    connectionErrorLabel.setVisible(visible);
  }
  
  private void monitoringScreen(boolean visible) {
    cpuGraphVisible(visible);
    ramGraphVisible(visible);
    diskDriveMonitorVisible(visible);
    thresholdInputVisible(visible);
    dataUpdateFrequencyVisible(visible);
  }
  
  private void dataUpdateFrequencyVisible(boolean visible) {
    dataUpdateFrequencySelector.setVisible(visible);
    dataUpdateFrequencyDescription.setVisible(visible);
  }
  
  private void thresholdInputVisible(boolean visible) {
    enterThresholdValue.setVisible(visible);
    chooseThresholdType.setVisible(visible);
    confirmThresholdUpdate.setVisible(visible);
    thresholdSelectionDescription.setVisible(visible);
    enterThresholdDescription.setVisible(visible);
  }
  
  private void diskDriveMonitorVisible(boolean visible) {
    individualDiskDriveLabel.setVisible(visible);
    individualDiskDriveUsageLabel.setVisible(visible);
    overAllDiskDriveUsageLabel.setVisible(visible);
    overAllDiskDriveLabel.setVisible(visible);
    individualDiskDriveSelection.setVisible(visible);
    labelOverDiskDriveSelection.setVisible(visible);
  }
  
  private void ramGraphVisible(boolean visible) {
    ramCanvas.setVisible(visible);
    ramOverLabel.setVisible(visible);
    ramUnderLabel.setVisible(visible);
    ramLeftLabel.setVisible(visible);
  }
  
  private void cpuGraphVisible(boolean visible) {
    cpuCanvas.setVisible(visible);
    cpuOverLabel.setVisible(visible);
    cpuUnderLabel.setVisible(visible);
    cpuLeftLabel.setVisible(visible);
  }
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new GUI();
  } // end of main
  
  public void confirmThresholdUpdate_ActionPerformed(ActionEvent evt) {
	  Double newThresholdValue =Double.valueOf(enterThresholdValue.getText());
	  String thresholdName = chooseThresholdType.getSelectedItem().toString();
	  serverConnection.changeThreshold(thresholdName, newThresholdValue);
	  
  } // end of confirmThresholdUpdate_ActionPerformed

  public void serverUrlConfirm_ActionPerformed(ActionEvent evt) {
    
      configureServerUrl(serverUrlInputField.getText());
      boolean couldConnect = testConnection();
      if(couldConnect) {
        urlInputScreen(false);
        updateMonitoringScreen();
        monitoringScreen(true);
        connectionErrorLabel.setText("");
      }else {
        connectionErrorLabel.setText("Could not connect to the entered Url");
      }
  }
 
  
  public void updateMonitoringScreen() {
	  System.out.println("updating");
	  getThresholds();
	  updateDiskDrives();
	  updateGraphs();
  }
  
  private void updateGraphs() {
	  GraphPainter.paintGraph(ramCanvas, serverConnection.getRAMUsage());
	  GraphPainter.paintGraph(cpuCanvas, serverConnection.getCPUUsage());
  }
  
  private void differentDiskDriveChosen(String drive) {
	  updateIndividualDiskDriveLabels(drive, driveUsage.get(drive));
  }
  
  private void updateDiskDrives() {
	  updateOverAllDiskDrive(serverConnection.getOverAllDiskUsage());
	  driveUsage = serverConnection.getIndividualDiskUsages();
	  String selectedItem = null;
	  if( individualDiskDriveSelection.getSelectedItem() != null)
		  selectedItem = individualDiskDriveSelection.getSelectedItem().toString();
	  individualDiskDriveSelection.removeAllItems();
	  for(String key : driveUsage.keySet()) {
		  individualDiskDriveSelection.addItem(key);
	  }
	  if(selectedItem != null)
		  try {
			  individualDiskDriveSelection.setSelectedItem(selectedItem);
		  }catch(Exception e) {
		  
		  }
  }
  
  private void getThresholds() {
	thresholds = serverConnection.getThresholds();
	chooseThresholdType.removeAllItems();
	for(String key : thresholds.keySet()) {
		chooseThresholdType.addItem(key);
	}

  }
  
  private void differentFrequencyChosen(String item) {	 
	 int timeinsec = 0; 
	 switch (item) {
	case"30sec":
		timeinsec=30;
		break;
	case "1min":
		timeinsec=60;
		break;
	case"5min":
		timeinsec=5*60;
		break;
	case"10min":
		timeinsec=10*60;
		break;
	case"30min":
		timeinsec=30*60;
	case"60min":
		timeinsec=60*60;
		break;

	}
	if(updateThread == null) {
		updateThread = new UpdateGUIThread(this,Duration.ofSeconds(timeinsec));
		updateThread.start();
	}
	updateThread.updateFrequency(Duration.ofSeconds(timeinsec));
  }
  
  
  private boolean testConnection() {
	  try {
	      return serverConnection.testConnection();
	     }catch(URISyntaxException e) {
	       connectionErrorLabel.setText("URL Syntax Error. Example URL: http://localhost:8080");
	       return false;
	    }
  }
  
  private void configureServerUrl(String newServerUrl){
	  serverConnection = new Communicator(newServerUrl);
  
  } 
  

  // Ende Methoden
} // end of class GUI

