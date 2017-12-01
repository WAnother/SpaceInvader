import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Main frame for the game
public class Game extends JFrame implements Common{
    private Board board;
    private Menu menu;
    private JPanel screen;
    private CardLayout cardLayout;
    private boolean start;
    public Game() {
        initGame();
    }
    private void initGame(){
        //create a new frame
        start = false;
        //create a menu panel and cardlayout
        menu = new Menu();
        screen = new JPanel(new CardLayout());
        //create a button in main frame
        JButton jButton = new JButton("Enter");
        jButton.setOpaque(true);
        jButton.setBackground(Color.white);
        jButton.setActionCommand("Nothing");
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton = (JButton) e.getSource();
                menu.check_start(jButton);
                if(jButton.getBackground() == Color.blue){
                    //if the button is pressed, create the game panel and show it
                    Board board = new Board(menu.getRow(), menu.getColumn(),menu.getMove_h(),menu.getMove_v(), menu.getFire_speed(), menu.getNumber_lives());
                    screen.add(board, "Game Screen");
                    cardLayout.show(screen,"Game Screen");
                }
            }
        };
        //let cardlayout show the menu panel first
        jButton.addActionListener(actionListener);
        menu.add(jButton);
        screen.add(menu, "Menu Screen");
        cardLayout= (CardLayout)screen.getLayout();
        cardLayout.show(screen,"Menu Screen");
        add(screen);
        pack();
        setSize(Frame_Width, Frame_Height);
        setTitle("Space Invader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //play background music
        BackgroundMusic backgroundMusic = new BackgroundMusic();
        backgroundMusic.play();
    }
}
