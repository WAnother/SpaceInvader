import javax.swing.*;
import java.awt.*;

public class Game extends JFrame implements Common{
    public Game() {
        initGame();
    }
    private void initGame(){
        //Create a new frame
        add(new Board());
        setSize(Frame_Width, Frame_Height);
        setTitle("Space Invader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args){
        //Game entrance point
        Game game = new Game();
    }
}