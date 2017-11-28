import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Menu extends JPanel implements ActionListener {
    private Background background;
    private ArrayList<JButton> jButtons;
    private ArrayList<JLabel> jLabels;
    // private JLabel jLabel;
    private String path = "res//image//Defender_Missile.png";
    private boolean start;
    private int row, column, move_h, move_v, fire_speed, number_lives;
    public Menu() {
        initMenu();
    }

    private void initMenu() {
        start = false;
        background = new Background();
        jButtons = new ArrayList<JButton>();
        jLabels = new ArrayList<JLabel>();
        initjLabel();
        initjButton();
        locateInterface();
    }
    private void locateInterface() {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            JLabel jLabel = jLabels.get(i);
            add(jLabel);
            for (int j = 0; j < 3; j++) {
                JButton jButton = jButtons.get(count);
                add(jButton);
                count++;
            }
        }
    }
    private void initjLabel() {
        for (int i = 0; i < 6; i++) {
            JLabel jLabel = null;
            if (i == 0) {
                jLabel = new JLabel("Number of invaders in each row");
            }
            if (i == 1) {
                jLabel = new JLabel("Number of invaders in each column");
            }
            if (i == 2) {
                jLabel = new JLabel("Speed of moving left and right for invader");
            }
            if (i == 3) {
                jLabel = new JLabel("Speed of moving up and down for invader");
            }
            if (i == 4) {
                jLabel = new JLabel("Speed of firing for invader");
            }
            if (i == 5) {
                jLabel = new JLabel("Number of lives for defender");
            }
            jLabel.setForeground(Color.white);
            jLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
            jLabels.add(jLabel);
        }
    }
    private void initjButton() {
        for (int i = 0; i < 18; i++) {
            JButton jButton = null;
            if (i < 3) {
                jButton = new JButton(String.valueOf(i + 4));
            }
            if (i >= 3 && i < 6) {
                jButton = new JButton(String.valueOf(i + 1));
            }
            if (i >= 6 && i < 9) {
                jButton = new JButton(diffSel(i));
            }
            if (i >= 9 && i < 12) {
                jButton = new JButton(diffSel(i));
            }
            if (i >= 12 && i < 15) {
                jButton = new JButton(diffSel(i));
            }
            if (i >= 15 && i < 18) {
                jButton = new JButton(String.valueOf(i - 14));
            }
            if (i == 18) {
                jButton = new JButton("Start");
            }
            jButton.addActionListener(this);
            jButton.setOpaque(true);
            jButton.setBackground(Color.white);
            jButton.setActionCommand("Nothing");
            jButtons.add(jButton);
        }
    }
    private String diffSel(int x) {
        String message = null;
        switch (x % 3) {
            case 0:
                message = new String("Slow");
                break;
            case 1:
                message = new String("Medium");
                break;
            case 2:
                message = new String("Fast");
                break;
        }
        return message;
    }
    public void paintComponent(Graphics g) {
        //Draw the background
        g.drawImage(background.getImage(), 0, 0, null);
    }
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object.getClass() == jButtons.get(1).getClass()) {
            JButton jButton = (JButton) object;
            int i = jButtons.indexOf(jButton);
            boolean check = false;
            if (i < 3) {
                check_button(i,0,3);
                row = i + 4;
            }
            if (i >= 3 && i < 6) {
                check_button(i,3,6);
                column = i + 1;
            }
            if (i >= 6 && i < 9) {
                check_button(i,6,9);
                move_h = i - 5;
            }
            if (i >= 9 && i < 12) {
                check_button(i,9,12);
                move_v = i - 8;
            }
            if (i >= 12 && i < 15) {
                check_button(i,12,15);
                fire_speed = i - 11;
            }
            if (i >= 15 && i < 18) {
                check_button(i,15,18);
                number_lives = i - 14;
            }
          /*  if (i == 18) {
                check_start(i);
                if(jButton.getBackground() == Color.blue){
                    start = true;
                }
            }*/
        }
    }
    private void check_button(int x, int start, int end) {
        JButton jButton = jButtons.get(x);
        for (int i = start; i < end; i++) {
            JButton jButton1 = jButtons.get(i);
            if (jButton1.getBackground() == Color.blue) {
                jButton1.setBackground(Color.white);
                break;
            }
        }
        jButton.setBackground(Color.blue);
    }
    public void check_start(JButton jButton){
     //   JButton jButton = jButtons.get(x);
        boolean decide = true;
        for(int i = 0; i < 6;i++){
            boolean check = check_start_helper(i + i * 2, i + i * 2 + 3);
            if(!check){
                decide = false;
                break;
            }
        }
        if(decide == true){
            jButton.setActionCommand("Start");
            jButton.setBackground(Color.blue);
        }
    }
    private boolean check_start_helper(int start, int end){
        boolean check = false;
        for(int i = start; i < end; i++){
            JButton jButton = jButtons.get(i);
            if(jButton.getBackground() == Color.blue){
                check = true;
                break;
            }
        }
        return check;
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public int getMove_h(){
        return move_h;
    }
    public int getMove_v(){
        return move_v;
    }
    public int getFire_speed(){
        return fire_speed;
    }
    public int getNumber_lives(){
        return number_lives;
    }
}