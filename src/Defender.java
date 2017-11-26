 import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Defender extends Sprite{
    private String path = "res//image//Defender.png";
    private int start_x = 600;
    private int start_y = 650;
    private ArrayList<Bullet> bullets;

    public Defender(){
        //Initial defender
        initDefender();
    }
    private void initDefender(){
        //Load the defender image and set coordinates
        visible = true;
        setXcor(start_x);
        setYcor(start_y);
        loadImage(path);
        resizeImage(1);
        setImageDimension(1);
        bullets = new ArrayList<Bullet>();

    }
    public ArrayList<Bullet> getBullet(){
        //get the bullet Arraylist
        return bullets;
    }
    public void move(){
        //Defender's action
        x_cor += move_x;
      //  y_cor += move_y;
        if(x_cor < 1){
            x_cor = 1;
        }
        if(x_cor > 1300){
            x_cor = 1300;
        }
    }
    public void keyPressed(KeyEvent e){
        //If key is pressed then start moving
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT){
            move_x = 2;
        }
        if(key == KeyEvent.VK_LEFT){
            move_x = -2;
        }
        if(key == KeyEvent.VK_UP){
            shoot();
        }
    }
    public void keyReleased(KeyEvent e){
        //if key is released then stop moving
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT){
            move_x = 0;
        }
        if(key == KeyEvent.VK_LEFT){
            move_x = 0;
        }
    }
    private void shoot(){
        //add bullet to bullet list
        bullets.add(new Bullet(x_cor + 50, y_cor));
    }

}
