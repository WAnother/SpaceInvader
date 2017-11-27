import java.util.ArrayList;

public class Barrier extends Sprite implements Common{
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
