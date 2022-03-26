package view;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImageFrame extends JFrame {
    private final ImagePanel panel;
    public ImageFrame(int width, int height, BufferedImage image) {
        setSize(width, height);
        setResizable(false);
        setTitle("Ray Tracing in a Weekend");
        setLocationRelativeTo(null);
        panel = new ImagePanel(width, height, image);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
    }

    public void setRGB(int x, int y, double r, double g, double b) {
        panel.setRGB(x, y, (int)r, (int)g, (int)b);
    }

    public BufferedImage getImage() {
        return panel.getImage();
    }
}
