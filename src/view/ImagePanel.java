package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private final BufferedImage image;

    public ImagePanel(int width, int height, BufferedImage image) {
        this.image = image;
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public void setRGB(int x, int y, int r, int g, int b) {
        try {
            image.setRGB(x, y, new Color(r, g, b).getRGB());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(x + ", " + y);
        }

    }

    public BufferedImage getImage() {
        return image;
    }
}