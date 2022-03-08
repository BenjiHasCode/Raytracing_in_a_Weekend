package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageComponent extends JComponent {
    private int width;
    private int height;
    private BufferedImage bufferedImage;

    public ImageComponent (int width, int height) {
        this.width = width;
        this.height = height;
        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public void paintComponent (Graphics g) {
        g.drawImage(bufferedImage, 0, 0, this.width, this.height, this);
    }

    public BufferedImage getBufferedImage () {
        return bufferedImage;
    }
}