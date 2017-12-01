import java.awt.*;
import javax.swing.*;
//class for the background image
public class Background implements Common{
    private String path = "res//image//Background.jpg";
    private Image image;

    public Background(){
        initBackground();
    }
    private void initBackground(){
        //Load background image
        loadImage();
    }
    private void loadImage(){
        ImageIcon icon = new ImageIcon(path);
        image = icon.getImage();
    }
    public Image getImage(){
        //get the image
        return image;
    }
}
