package uet.entities.still_objects;

import uet.Board;
import uet.entities.Entity;
import uet.entities.moving_objects.Bomber;
import uet.graphics.Sprite;

public class Portal extends Tile {
    public Board _board;

    public Portal(int x, int y, Board board, Sprite sprite) {
        super(x, y, sprite);
        _board = board;
    }

//    public Portal(int x, int y) {
//        super(x, y);
//        image = Sprite
//                .getImageFromFile("res\\UI\\image\\sprites\\village stage sprites\\PORTAL (open) - village stage.png");
//    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi Bomber đi vào
        if (e instanceof Bomber) {
            if (_board.detectNoEnemies() == false) {
                return false;
            }
            System.out.println(_board.detectNoEnemies());

            if (e.getXTile() == getX() && e.getYTile() == getY()) {
                if (_board.detectNoEnemies())
                    _board.nextLevel();
            }

            return true;
        }

        return false;
    }
}


