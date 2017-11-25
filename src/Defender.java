import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class Defender extends Sprite{
    private String path = "res//image//Defender.png";
    private int start_x = 600;
    private int start_y = 650;
    public Defender(){
        //Initial defender
        initDefender();
    }
    private void initDefender(){
        //Load the defender image and set coordinates
        setXcor(start_x);
        setYcor(start_y);
        loadImage(path);
        setImageDimension();
        //resizeImage(1);
    }
    public void move(){
        x_cor += move_x;
        y_cor += move_y;
        if(x_cor < 1){
            x_cor = 1;
        }
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D){
            move_x = 2;
        }
        if(key == KeyEvent.VK_A){
            move_x = -2;
        }
    }
    public void keyReleased(KeyEvent e){
        //if key is released then stop moving
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D){
            move_x = 0;
        }
        if(key == KeyEvent.VK_A){
            move_x = 0;
        }
    }

}
