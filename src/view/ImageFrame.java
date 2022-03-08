package view;

import javax.swing.*;

public class ImageFrame extends JFrame {
    private final ImageComponent imageComponent;
    public ImageFrame(int width, int height) {
      //  setResizable(false);
        setTitle("Raytracing");
        setSize(width, height);
        setLocationRelativeTo(null); //Makes the window "spawn" in the middle of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create ImageComponent and add to ImageFrame
        imageComponent = new ImageComponent(width, height);
        add(imageComponent);
        setVisible(true);
    }

    public ImageComponent getImageComponent(){
        return imageComponent;
    }
}
