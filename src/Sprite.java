import java.awt.*;
import javax.swing.*;
//base class for the creatures
public class Sprite implements Common{
    protected Image image;
    protected int x_cor;
    protected int y_cor;
    protected int move_x;
    protected int move_y;
    protected int image_width;
    protected int image_height;
    protected boolean visible;

    public Sprite(){};
    public Sprite(int x, int y){
        x_cor = x;
        y_cor = y;
    }
    public void setXcor(int x){
        //set x coordinate
        x_cor = x;
    }
    public void setYcor(int y){
        //set y coordinate
        y_cor = y;
    }
    public void loadImage(String path){
        ImageIcon icon = new ImageIcon(path);
        setImage(icon.getImage());
    }
    public void setImage(Image image){
        //set the image
        this.image = image;
    }
    public int getXcor(){
        //return x coordinate
        return x_cor;
    }
    public int getYcor() {
        //return y coordinate
        return y_cor;
    }
    public Image getImage(){
        //return the image
        return image;
    }
    public void die(){
        visible = false;
    }
    public void setImageDimension(int x){
        switch(x) {
            case 1:
                image_width = 150;
                image_height = 150;
                break;
            case 2:
                image_width = 30;
                image_height = 30;
                break;
            case 3:
                image_width = Box_Width;
                image_height = Box_Height;
                break;
        }
    }
    public int getWidth(){
        return image_width;
    }
    public int getHeight(){
        return image_height;
    }

    public boolean checkVisible(){
        return visible;
    }
    public void resizeImage(int x){
        //resize the image based on the given objective
        switch(x) {
            case 1: image = image.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            break;
            case 2: image = image.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            break;
        }
    }
}
