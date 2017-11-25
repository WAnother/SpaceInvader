import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Defender extends Sprite{
    private String path = "res//image//Defender.png";
    private int start_x = 600;
    private int start_y = 650;
    public Defender(){
        initDefender();
    }
    private void initDefender(){
        //Load the defender image and set coordinates
        setXcor(start_x);
        setYcor(start_y);
        loadImage(path);
        resizeImage(1);
    }
}
