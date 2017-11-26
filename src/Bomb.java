public class Bomb extends Sprite implements Common{
    private String path = "res//image//Invader_Bomb.png";
    public Bomb(int x, int y){
        super(x,y);
        //Initial Bullet
        initBullet();
    }
    public void initBullet(){
        visible = true;
        loadImage(path);
        resizeImage(2);
        setImageDimension(2);
    }
    public void move(){
        //update the bullet position
        y_cor += Bullet_Speed;
        if(y_cor >= 900){
            visible = false;
        }
    }
}
