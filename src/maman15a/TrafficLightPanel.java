package maman15a;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TrafficLightPanel extends JPanel implements ActionListener, Runnable {
    private final int FLICKERING_DELAY = 200;
    private final boolean DEFAULT_CARS_LIGHT = false;
    private final Color BACKGROUND_COLOR = Color.BLACK;
    private final Color SHAPE_OFF_COLOR = Color.GRAY;
    private final Color LIGHT_ON_COLOR = Color.GREEN;
    private final Color LIGHT_OFF_COLOR = Color.RED;
    private final int DEFAULT_CARS_TRAFFIC_LIGHTS_ON_TIMER = 2000;
    private final int DEFAULT_CARS_TRAFFIC_LIGHTS_OFF_TIMER = 2000;
    
    private Timer pedestriansLightOnTimer;
    private boolean flickerOn;
    private boolean isCarsLightOn;
    private int carsLightOnDelay;
    private int carsLightOffDelay;

    /**
     * Constructor
     * @param carsLightOnDelay cars light on delay
     * @param carsLightOffDelay cars light off delay
     */
    public TrafficLightPanel(int carsLightOnDelay, int carsLightOffDelay) {
        this();
        this.carsLightOnDelay = carsLightOnDelay;
        this.carsLightOffDelay = carsLightOffDelay;
    }
    
    /**
     * Constructor
     * @param carsLight boolean indication if the cars light should be on when the traffic light starts
     * @param carsLightOnDelay cars light on delay
     * @param carsLightOffDelay cars light off delay
     */
    public TrafficLightPanel(boolean carsLight, int carsLightOnDelay, int carsLightOffDelay) {
        this(carsLight);
        this.carsLightOnDelay = carsLightOnDelay;
        this.carsLightOffDelay = carsLightOffDelay;
    }
    
    /**
     * Constructor
     * @param carsLight boolean indication if the cars light should be on when the traffic light starts
     */
    public TrafficLightPanel(boolean carsLight) {
        this();
        this.isCarsLightOn = carsLight;
    }

    /**
     * Constructor
     */
    public TrafficLightPanel() {
        this.isCarsLightOn = DEFAULT_CARS_LIGHT;
        this.flickerOn = false;
        this.carsLightOnDelay = DEFAULT_CARS_TRAFFIC_LIGHTS_ON_TIMER;
        this.carsLightOffDelay = DEFAULT_CARS_TRAFFIC_LIGHTS_OFF_TIMER;
        
        // Set timer
        this.pedestriansLightOnTimer = new Timer(FLICKERING_DELAY, this);
        this.pedestriansLightOnTimer.start();
    }

    /**
     * Get cars light on delay
     * @return integer representing the delay
     */
    public int getCarsLightOnDelay() {
        return carsLightOnDelay;
    }

    /**
     * Set cars light on delay
     * @param carsLightOnDelay integer representing the delay
     */
    public void setCarsLightOnDelay(int carsLightOnDelay) {
        this.carsLightOnDelay = carsLightOnDelay;
    }

    /**
     * Get cars light off delay
     * @return integer representing the delay
     */
    public int getCarsLightOffDelay() {
        return carsLightOffDelay;
    }

    /**
     * Set cars light off delay
     * @param carsLightOnDelay integer representing the delay
     */
    public void setCarsLightOffDelay(int carsLightOffDelay) {
        this.carsLightOffDelay = carsLightOffDelay;
    }
    
    /**
     * Get current cars light
     * @return true if the light is on, false otherwise
     */
    public boolean getCarsLight() {
        return isCarsLightOn;
    }

    /**
     * Set current cars light
     * @param carsLightOn true if the light is on, false otherwise
     */
    public void setCarsLight(boolean carsLightOn) {
        this.isCarsLightOn = carsLightOn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.flickerOn = !this.flickerOn; // Set the opposite value to make the light flicker
        repaint(); // Repaint the panel after the change, so the flickering takes effect
    }
    
    /**
     * Run the traffic light.
     * The traffic light will change value on its own thread, using the given times.
     */
    @Override
    public void run() {
        while(true) {
            try {
                // Sleep the required delay time
                Thread.sleep(this.isCarsLightOn ? this.carsLightOnDelay : this.carsLightOffDelay);
                this.isCarsLightOn = !this.isCarsLightOn;
                
                // Stop/Start the pedestrians timer if necessary - so it doesn't waste run time!
                if(this.isCarsLightOn) {
                    this.pedestriansLightOnTimer.stop();
                }
                else if(!this.pedestriansLightOnTimer.isRunning()) {
                    this.pedestriansLightOnTimer.restart();
                }
                
                repaint(); // Repaint the changes
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int circleDiameter = getHeight() / 4; // 4 components with equal height
        int circleWidthOffset = (getWidth() - circleDiameter) / 2; // Center the circle
        int rectWidth = circleDiameter / 2; // Pedestrians light should equal half the cars light width
        int rectWidthOffset = (getWidth() - rectWidth) / 2; // Center the rect
        int heightOffset = 0;
        
        setBackground(BACKGROUND_COLOR);
        
        // Draw the first cars light
        drawCarsLight(g, this.isCarsLightOn ? LIGHT_ON_COLOR : SHAPE_OFF_COLOR, circleDiameter, heightOffset, circleWidthOffset);
        heightOffset += circleDiameter;
        
        // Draw the second cars light
        drawCarsLight(g, this.isCarsLightOn ? SHAPE_OFF_COLOR : LIGHT_OFF_COLOR, circleDiameter, heightOffset, circleWidthOffset);
        heightOffset += circleDiameter;
        
        // Draw the first pedestrians light
        drawPedestriansLight(g, this.isCarsLightOn ? LIGHT_OFF_COLOR : SHAPE_OFF_COLOR,
                rectWidth, circleDiameter, heightOffset, rectWidthOffset);
        heightOffset += circleDiameter;
        
        // Draw the second pedestrians light
        drawPedestriansLight(g, !this.isCarsLightOn && !this.flickerOn ? LIGHT_ON_COLOR : SHAPE_OFF_COLOR ,
                rectWidth, circleDiameter, heightOffset, rectWidthOffset);
    }
    
    private void drawCarsLight(Graphics g, Color color, int diameter, int heightOffset, int widthOffset) {
        // Fill shape
        g.setColor(color);
        g.fillOval(widthOffset, heightOffset, diameter, diameter);
    }
    
    private void drawPedestriansLight(Graphics g, Color color, int width, int height,
            int heightOffset, int widthOffset) {
        // Fill shape
        g.setColor(color);
        g.fillRect(widthOffset, heightOffset, width, height);
    }
}
