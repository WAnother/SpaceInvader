import java.awt.*;
import javax.swing.*;

public class Background extends JPanel implements Common{
    private String path = "res//image//Background.jpg";
    private Image image;

    public Background(){
        initBackground();
    }
    private void initBackground(){
        //Load background image
        loadImage();
        setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
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
