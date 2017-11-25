import java.awt.*;
import javax.swing.*;

public class Sprite {
    private Image image;
    protected int x_cor;
    protected int y_cor;
    protected int move_x;
    protected int move_y;
    protected int image_width;
    protected int image_height;

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
    public void setImageDimension(){
        image_width = image.getWidth(null);
        image_height = image.getHeight(null);
    }
    public void resizeImage(int x){
        //resize the image based on the given objective
        switch(x) {
            case 1: image = image.getScaledInstance(1000, 1000, Image.SCALE_DEFAULT);
            break;
        }
    }
}
