package uet.entities.still_objects;


import uet.entities.Entity;
import uet.graphics.Sprite;

public class Grass extends Tile {

    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

//    public Grass(int x, int y) {
//        super(x, y);
//        image = Sprite.getImageFromFile("res\\UI\\image\\sprites\\jungle stage sprites\\GRASS - jungle stage.png");
//    }

    /**
     * Cho bất kì đối tượng khác đi qua
     * @param e
     * @return
     */
    @Override
    public boolean collide(Entity e) {
        return true;
    }
}
