import java.awt.*;
import javax.swing.*;

public class Invader extends Sprite {
    private String path = "res//image//Invader.png";

    public Invader(){
        initInvader();
    }
    private void initInvader(){
        loadImage(path);
    }
}
