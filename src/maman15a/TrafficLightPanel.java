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
    private final int RECTANGULAR_Y = 0;
    
    private boolean isCarsLightOn;

    public TrafficLightPanel(boolean carsLight) {
        this.isCarsLightOn = carsLight;
    }

    public TrafficLightPanel() {
        this.isCarsLightOn = DEFAULT_CARS_LIGHT;
    }
    
    public boolean getCarsLight() {
        return isCarsLightOn;
    }

    public void setCarsLight(boolean carsLightOn) {
        this.isCarsLightOn = carsLightOn;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // ADD COMMENTSSSSSSSSSSSSSS!@#!@#@!
        int circleDiameter = getHeight() / 4;
        int widthOffset = (getWidth() - circleDiameter) / 2;
        int rectWidth = circleDiameter / 2;
        int rectX = circleDiameter / 4;
        
        setBackground(BACKGROUND_COLOR);
        int heightOffset = 0;
        
        // Draw the first cars light
        drawCarsLight(g, isCarsLightOn ? LIGHT_ON_COLOR : SHAPE_OFF_COLOR, circleDiameter, heightOffset, widthOffset);
        heightOffset += circleDiameter;
        
        // Draw the second cars light
        drawCarsLight(g, isCarsLightOn ? SHAPE_OFF_COLOR : LIGHT_OFF_COLOR, circleDiameter, heightOffset, widthOffset);
        heightOffset += circleDiameter;
        
        // Draw the first pedestrians light
        drawPedestriansLight(g, isCarsLightOn ? SHAPE_OFF_COLOR : LIGHT_ON_COLOR,
                rectWidth, circleDiameter, rectX, heightOffset, widthOffset);
        heightOffset += circleDiameter;
        
        // Draw the second pedestrians light
        drawPedestriansLight(g, isCarsLightOn ? LIGHT_OFF_COLOR : SHAPE_OFF_COLOR,
                rectWidth, circleDiameter, rectX, heightOffset, widthOffset);
    }
    
    private void drawCarsLight(Graphics g, Color color, int diameter, int heightOffset, int widthOffset) {
        // Fill shape
        g.setColor(color);
        g.fillOval(widthOffset + CIRCLE_X, heightOffset + CIRCLE_Y, diameter, diameter);
    }
    
    private void drawPedestriansLight(Graphics g, Color color, int width, int height,
            int rectX, int heightOffset, int widthOffset) {
        // Fill shape
        g.setColor(color);
        g.fillRect(widthOffset + rectX, heightOffset + RECTANGULAR_Y, width, height);
    }
}
