package maman15a;

import javax.swing.JFrame;

public class Maman15a {

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 300;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new TrafficLightJunctionPanel());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
