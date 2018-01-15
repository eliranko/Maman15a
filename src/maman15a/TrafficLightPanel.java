package maman15a;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class TrafficLightPanel extends JPanel {
    private final boolean DEFAULT_CARS_LIGHT = false;
    private final Color BACKGROUND_COLOR = Color.WHITE;
    private final Color SHAPE_OFF_COLOR = Color.GRAY;
    private final Color LIGHT_ON_COLOR = Color.GREEN;
    private final Color LIGHT_OFF_COLOR = Color.RED;
    private final int CIRCLE_X = 0;
    private final int CIRCLE_Y = 0;
    private final int CIRCLE_DIAMETER = 100;
    private final int RECTANGULAR_WIDTH = CIRCLE_DIAMETER / 2;
    private final int RECTANGULAR_HEIGHT = CIRCLE_DIAMETER;
    private final int RECTANGULAR_X = CIRCLE_DIAMETER / 4;
    private final int RECTANGULAR_Y = 0;
    
    private boolean carsLightOn;

    public TrafficLightPanel(boolean carsLight) {
        this.carsLightOn = carsLight;
    }

    public TrafficLightPanel() {
        this.carsLightOn = DEFAULT_CARS_LIGHT;
    }
    
    public boolean isCarsLightOn() {
        return carsLightOn;
    }

    public void setCarsLightOn(boolean carsLightOn) {
        this.carsLightOn = carsLightOn;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(BACKGROUND_COLOR);
        int heightOffset = 0;
        
        // Draw the first cars light
        drawCarsLight(g, carsLightOn ? LIGHT_ON_COLOR : SHAPE_OFF_COLOR, heightOffset);
        heightOffset += CIRCLE_DIAMETER;
        
        // Draw the second cars light
        drawCarsLight(g, carsLightOn ? SHAPE_OFF_COLOR : LIGHT_OFF_COLOR, heightOffset);
        heightOffset += CIRCLE_DIAMETER;
        
        // Draw the first pedestrians light
        drawPedestriansLight(g, carsLightOn ? SHAPE_OFF_COLOR : LIGHT_ON_COLOR, heightOffset);
        heightOffset += RECTANGULAR_HEIGHT;
        
        // Draw the second pedestrians light
        drawPedestriansLight(g, carsLightOn ? LIGHT_OFF_COLOR : SHAPE_OFF_COLOR, heightOffset);
    }
    
    private void drawCarsLight(Graphics g, Color color, int heightOffset) {
        // Fill shape
        g.setColor(color);
        g.fillOval(CIRCLE_X, heightOffset + CIRCLE_Y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }
    
    private void drawPedestriansLight(Graphics g, Color color, int heightOffset) {
        // Fill shape
        g.setColor(color);
        g.fillRect(RECTANGULAR_X, heightOffset + RECTANGULAR_Y, RECTANGULAR_WIDTH, RECTANGULAR_HEIGHT);
    }
}
