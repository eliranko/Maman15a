package maman15a;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class TrafficLightJunctionPanel extends JPanel implements Runnable {
    private final int DEFAULT_TRAFFIC_LIGHTS_TIMER = 2000;
    
    private TrafficLightPanel leftTrafficLight;
    private TrafficLightPanel rightTrafficLight;
    private TrafficLightPanel upperTrafficLight;
    private TrafficLightPanel lowerTrafficLight;
    private int lightsCycleTime;

    public TrafficLightJunctionPanel(TrafficLightPanel leftTrafficLight, TrafficLightPanel rightTrafficLight,
            TrafficLightPanel upperTrafficLight, TrafficLightPanel lowerTrafficLight, int lightsCycleTime) {
        this();
        this.leftTrafficLight = leftTrafficLight;
        this.rightTrafficLight = rightTrafficLight;
        this.upperTrafficLight = upperTrafficLight;
        this.lowerTrafficLight = lowerTrafficLight;
        this.lightsCycleTime = lightsCycleTime;
        synchronizeTrafficLights();
    }

    public TrafficLightJunctionPanel(int lightsCycleTime) {
        this();
        this.lightsCycleTime = lightsCycleTime;
    }
    
    public TrafficLightJunctionPanel() {
        // Set traffic lights
        this.leftTrafficLight = new TrafficLightPanel();
        this.rightTrafficLight = new TrafficLightPanel();
        this.upperTrafficLight = new TrafficLightPanel();
        this.lowerTrafficLight = new TrafficLightPanel();
        this.lightsCycleTime = DEFAULT_TRAFFIC_LIGHTS_TIMER;
        synchronizeTrafficLights();
        
        // Add components
        addTrafficsLightPanels();
    }

    public TrafficLightPanel getLeftTrafficLight() {
        return leftTrafficLight;
    }

    public void setLeftTrafficLight(TrafficLightPanel leftTrafficLight) {
        this.leftTrafficLight = leftTrafficLight;
        synchronizeTrafficLights();
    }

    public TrafficLightPanel getRightTrafficLight() {
        return rightTrafficLight;
    }

    public void setRightTrafficLight(TrafficLightPanel rightTrafficLight) {
        this.rightTrafficLight = rightTrafficLight;
        synchronizeTrafficLights();
    }

    public TrafficLightPanel getUpperTrafficLight() {
        return upperTrafficLight;
    }

    public void setUpperTrafficLight(TrafficLightPanel upperTrafficLight) {
        this.upperTrafficLight = upperTrafficLight;
        synchronizeTrafficLights();
    }

    public TrafficLightPanel getLowerTrafficLight() {
        return lowerTrafficLight;
    }

    public void setLowerTrafficLight(TrafficLightPanel lowerTrafficLight) {
        this.lowerTrafficLight = lowerTrafficLight;
        synchronizeTrafficLights();
    }
    
    public void stop() {
        
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(this.lightsCycleTime);
                this.leftTrafficLight.setCarsLight(!this.leftTrafficLight.getCarsLight());
                synchronizeTrafficLights();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void addTrafficsLightPanels() {
        int gridLayoutRows = 3;
        int gridLayoutColumn = 3;
        
        // Set layout
        setLayout(new GridLayout(gridLayoutRows, gridLayoutColumn));
        
        add(new JPanel());
        add(this.leftTrafficLight);
        add(new JPanel());
        add(this.upperTrafficLight);
        add(new JPanel());
        add(this.lowerTrafficLight);
        add(new JPanel());
        add(this.rightTrafficLight);
        add(new JPanel());
        
        // Add place holders
        JPanel[][] panelHolder = new JPanel[gridLayoutRows][gridLayoutColumn];
        for(int i = 0; i < gridLayoutRows; i++) {
            for (int j = 0; j < gridLayoutColumn; j++) {
                panelHolder[i][j] = new JPanel();
                //add(panelHolder[i][j]);
            }
        }
        
        //panelHolder[0][1].add(this.leftTrafficLight);
        //panelHolder[1][0].add(this.upperTrafficLight);
        //panelHolder[1][2].add(this.lowerTrafficLight);
        //panelHolder[2][1].add(this.rightTrafficLight);
    }
    
    private void synchronizeTrafficLights() {
        if(this.leftTrafficLight == null ||
                this.rightTrafficLight == null ||
                this.upperTrafficLight == null ||
                this.lowerTrafficLight == null)
        {
            return; // Don't sync uncompleted junction
        }
        
        // Synchronize the traffic lights by the left traffic light
        boolean leftTrafficCarsLight = this.leftTrafficLight.getCarsLight();
        this.rightTrafficLight.setCarsLight(leftTrafficCarsLight);
        this.upperTrafficLight.setCarsLight(!leftTrafficCarsLight); // Opposite light
        this.lowerTrafficLight.setCarsLight(!leftTrafficCarsLight); // Opposite light
    }
}
