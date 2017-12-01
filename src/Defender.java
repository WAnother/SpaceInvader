import java.awt.event.KeyEvent;
import java.util.ArrayList;
//class for the defender
public class Defender extends Sprite{
    private String path = "res//image//Defender.png";
    private int start_x = 600;
    private int start_y = 700;
    private ArrayList<Bullet> bullets;
    private boolean released = false;
    public int shoot_count;
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
        shoot_count = 0;
        bullets = new ArrayList<Bullet>();

    }
    public ArrayList<Bullet> getBullet(){
        //get the bullet Arraylist
        return bullets;
    }
    public void move(){
        //Defender's action
        x_cor += move_x;
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
        if(key == KeyEvent.VK_SPACE){
            //if key is released earlier, then shoot, otherwise wait
            if(!released) {
                if(bullets.size() == 0) {
                    shoot();
                }
            }
        }
        released = true;
    }
    public void keyReleased(KeyEvent e){
        //if key is released then stop moving
        int key = e.getKeyCode();
        released = true;
        if(key == KeyEvent.VK_RIGHT){
            move_x = 0;
        }
        if(key == KeyEvent.VK_LEFT){
            move_x = 0;
        }
        released = false;
    }
    public void shoot(){
        //play music when shoot bullet
        ActionMusic actionMusic = new ActionMusic(3);
        actionMusic.play();
        bullets.add(new Bullet(x_cor + 60, y_cor));
    }
}
