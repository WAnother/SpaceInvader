import java.awt.*;
import javax.swing.*;

public class Invader extends Sprite {
    private String path = "res//image//Invader.png";
    private int start_x;
    private int start_y;

    public Invader(){
        //initial invader
        initInvader();
    }
    private void initInvader(){
        loadImage(path);
    }
}
