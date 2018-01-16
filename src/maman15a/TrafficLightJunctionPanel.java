package maman15a;

import java.awt.GridLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;

public class TrafficLightJunctionPanel extends JPanel {
    private ExecutorService executor;
    private TrafficLightPanel leftTrafficLight;
    private TrafficLightPanel rightTrafficLight;
    private TrafficLightPanel upperTrafficLight;
    private TrafficLightPanel lowerTrafficLight;

    /**
     * Constructor
     * @param leftTrafficLight left traffic light
     * @param rightTrafficLight right traffic light
     * @param upperTrafficLight upper traffic light
     * @param lowerTrafficLight lower traffic light
     */
    public TrafficLightJunctionPanel(TrafficLightPanel leftTrafficLight, TrafficLightPanel rightTrafficLight,
            TrafficLightPanel upperTrafficLight, TrafficLightPanel lowerTrafficLight) {
        // Set traffic lights
        this.leftTrafficLight = leftTrafficLight;
        this.rightTrafficLight = rightTrafficLight;
        this.upperTrafficLight = upperTrafficLight;
        this.lowerTrafficLight = lowerTrafficLight;
        synchronizeTrafficLights();
        
        // Add components
        addTrafficsLightPanels();
    }
    
    /**
     * Constructor
     * @param lightOnCycleTime cars light on cycle time
     * @param lightOffCycleTime cars light off cycle time
     */
    public TrafficLightJunctionPanel(int lightOnCycleTime, int lightOffCycleTime) {
        // Set traffic lights
        this.leftTrafficLight = new TrafficLightPanel(lightOnCycleTime, lightOffCycleTime);
        this.rightTrafficLight = new TrafficLightPanel(lightOnCycleTime, lightOffCycleTime);
        this.upperTrafficLight = new TrafficLightPanel(lightOffCycleTime, lightOnCycleTime); // opposite times
        this.lowerTrafficLight = new TrafficLightPanel(lightOffCycleTime, lightOnCycleTime);
        synchronizeTrafficLights();
        
        // Add components
        addTrafficsLightPanels();
    }
    
    /**
     * Empty constructor
     */
    public TrafficLightJunctionPanel() {
        // Set traffic lights
        this.leftTrafficLight = new TrafficLightPanel();
        this.rightTrafficLight = new TrafficLightPanel();
        this.upperTrafficLight = new TrafficLightPanel();
        this.lowerTrafficLight = new TrafficLightPanel();
        synchronizeTrafficLights();
        
        // Add components
        addTrafficsLightPanels();
    }

    /**
     * get left traffic light
     * @return TrafficLightPanel object
     */
    public TrafficLightPanel getLeftTrafficLight() {
        return leftTrafficLight;
    }

    /**
     * set left traffic light
     * @param leftTrafficLight TrafficLightPanel object
     */
    public void setLeftTrafficLight(TrafficLightPanel leftTrafficLight) {
        this.leftTrafficLight = leftTrafficLight;
    }

    /**
     * get right traffic light
     * @return TrafficLightPanel object
     */
    public TrafficLightPanel getRightTrafficLight() {
        return rightTrafficLight;
    }

    /**
     * set right traffic light
     * @param leftTrafficLight TrafficLightPanel object
     */
    public void setRightTrafficLight(TrafficLightPanel rightTrafficLight) {
        this.rightTrafficLight = rightTrafficLight;
    }

    /**
     * get upper traffic light
     * @return TrafficLightPanel object
     */
    public TrafficLightPanel getUpperTrafficLight() {
        return upperTrafficLight;
    }

    /**
     * set upper traffic light
     * @param leftTrafficLight TrafficLightPanel object
     */
    public void setUpperTrafficLight(TrafficLightPanel upperTrafficLight) {
        this.upperTrafficLight = upperTrafficLight;
    }

    /**
     * get lower traffic light
     * @return TrafficLightPanel object
     */
    public TrafficLightPanel getLowerTrafficLight() {
        return lowerTrafficLight;
    }

    /**
     * set lower traffic light
     * @param leftTrafficLight TrafficLightPanel object
     */
    public void setLowerTrafficLight(TrafficLightPanel lowerTrafficLight) {
        this.lowerTrafficLight = lowerTrafficLight;
    }
    
    /**
     * Execute the traffic lights operations in the junction
     */
    public void execute() {
        if(this.executor != null) return; // Executor already running
        
        this.executor = Executors.newCachedThreadPool(); // add threads to thread pool
        this.executor.execute(this.leftTrafficLight);
        this.executor.execute(this.rightTrafficLight);
        this.executor.execute(this.upperTrafficLight);
        this.executor.execute(this.lowerTrafficLight);
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
        this.lowerTrafficLight.setCarsLight(!leftTrafficCarsLight);
    }
}
