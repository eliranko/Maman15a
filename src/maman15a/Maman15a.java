package maman15a;

import javax.swing.JFrame;

public class Maman15a {
    private static final int DELAY_PARAMETER_MULTIPLIER = 1000; // convert to milliseconds
    private static final int DEFAULT_LIGHT_DELAY = 2000;
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;
    
    public static void main(String[] args) {
        TrafficLightJunctionPanel junction = getJunction(args);
        junction.execute();
        
        // Create frame
        JFrame frame = new JFrame();
        frame.add(junction);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private static TrafficLightJunctionPanel getJunction(String[] args) {
        String lightOnDelay = "";
        String lightOffDelay = "";
        
        // get parameters
        if(args.length > 1) {
            lightOnDelay = args[0];
            lightOffDelay = args[1];
        }
        
        // Create junction
        return new TrafficLightJunctionPanel(getLightDelay(lightOnDelay), getLightDelay(lightOffDelay));
    }
    
    private static int getLightDelay(String str) {
        int delay;
        try {
            delay = Integer.parseInt(str) * DELAY_PARAMETER_MULTIPLIER;
        }
        catch(NumberFormatException e) {
            delay = DEFAULT_LIGHT_DELAY;
        }
        
        return delay;
    }
}
