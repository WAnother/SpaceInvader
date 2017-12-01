//class for the bomb from invader
public class Bomb extends Sprite implements Common{
    private String path = "res//image//Invader_Bomb.png";
    public Bomb(int x, int y){
        super(x,y);
        //Initial Bul
        initBomb();
    }
    private void initBomb(){
        visible = true;
        loadImage(path);
        resizeImage(2);
        setImageDimension(2);
    }
    public void move(){
        //update the bullet position
        y_cor += Bomb_Drop_Speed;
        if(y_cor >= 900){
            visible = false;
        }
    }
}
