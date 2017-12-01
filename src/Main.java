import java.awt.*;

public class Main {
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
