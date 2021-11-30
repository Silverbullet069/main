package uet.entities.still_objects.destroyable;


import uet.entities.Entity;
import uet.entities.bomb.Flame;
import uet.graphics.Screen;
import uet.graphics.Sprite;
import uet.level.Coordinates;

public class Brick extends DestroyableTile {

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

//    public Brick(int x, int y) {
//        super(x, y);
//        image = Sprite.getImageFromFile("res\\UI\\image\\sprites\\jungle stage sprites\\BRICK - jungle stage.png");
//    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Screen screen) {
        int x = Coordinates.tileToPixel(_x);
        int y = Coordinates.tileToPixel(_y);

        if(_destroyed) {
            _sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);

            screen.renderEntityWithBelowSprite(x, y, this, _belowSprite);
        }
        else
            screen.renderEntity( x, y, this);
    }
    public boolean collide(Entity e) {

        if(e instanceof Flame)
            destroy();
        return false;
    }

}
