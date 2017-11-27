import javax.swing.*;
import java.awt.*;

public class Game extends JFrame implements Common{
    public Game() {
        initGame();
    }
    private void initGame(){
        //Create a new frame
        //add(new Board());
        //Board board = new Board();
        add(new Board());
        pack();
        setSize(Frame_Width, Frame_Height);
        setTitle("Space Invader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
     /*   Thread thread = new Thread(board);
        thread.start();*/
    }

    public static void main(String[] args){
        //Game entry point
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game game = new Game();
           }
        });
    }
}
