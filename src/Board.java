import java.awt.*;
import javax.swing.*;

public class Board extends JPanel implements Common{
    private Background background;
    private Defender defender;
    private boolean in_progress;
    public Board(){
        initBoard();
    }
    private void initBoard(){
        //Load necessary parameter
        background = new Background();
        defender = new Defender();
        in_progress = true;
    }
    public void drawBackground(Graphics g){
        //Draw the background
        g.drawImage(background.getImage(),0,0,null );
    }
    public void drawDefender(Graphics g){
        //Draw the defender
        g.drawImage(defender.getImage(), defender.getXcor(), defender.getYcor(), null);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
       // if(in_progress) {
            drawDefender(g);
       // }
    }
}
