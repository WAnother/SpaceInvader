import java.util.ArrayList;
//class for the barrier
public class Barrier extends Sprite implements Common{
    //barrier consist of a list of boxes
    private ArrayList<Box> boxes;
    public Barrier(int x, int y){
        super(x,y);
        initBarrier();
        boxes = new ArrayList<Box>();
        initBox(boxes);
    }
    private void initBarrier(){
        boxes = new ArrayList<Box>();
    }
    private void initBox(ArrayList<Box> boxes){
        //add boxes to the barrier, and initialize their position
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                Box box = new Box(x_cor + i * Box_Width, y_cor + j * Box_Height);
                boxes.add(box);
            }
        }
    }
    public ArrayList<Box> getBoxes(){
        return boxes;
    }
}
