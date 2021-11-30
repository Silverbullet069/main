package uet.level;

import uet.Board;
import uet.Game;
import uet.entities.LayeredEntity;
import uet.entities.moving_objects.Bomber;
//import uet.entities.moving_objects.enemy.Balloon;
//import uet.entities.moving_objects.enemy.Oneal;
import uet.entities.still_objects.Grass;
import uet.entities.still_objects.Portal;
import uet.entities.still_objects.Wall;
import uet.entities.still_objects.destroyable.Brick;
//import uet.entities.still_objects.item.BombItem;
//import uet.entities.still_objects.item.FlameItem;
//import uet.entities.still_objects.item.SpeedItem;
import uet.exceptions.LoadLevelException;
import uet.graphics.Screen;
import uet.graphics.Sprite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

public class FileLevelLoader extends LevelLoader {

    /**
     * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
     * từ ma trận bản đồ trong tệp cấu hình
     */
    private static char[][] _map;

    public FileLevelLoader(Board board, int level) throws LoadLevelException {
        super(board, level);
    }

    @Override
    public void loadLevel(int level) throws LoadLevelException {
        // TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt

        try {
            URL Path = FileLevelLoader.class.getResource("/levels/Level" + level + ".txt");

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(Path.openStream()))) {
                String data = br.readLine();
                String[] a = data.split(" ");

                _level = Integer.parseInt(a[0]);
                _height = Integer.parseInt(a[1]);
                _width = Integer.parseInt(a[2]);
                // TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
                _map = new char[_height][_width];
                for (int i = 0; i < _height; i++) {
                    String _lineMap = br.readLine();
                    for (int j = 0; j < _width; j++) {
                        _map[i][j] = _lineMap.charAt(j);

                    }
                }
            }
        } catch (IOException e) {
            throw new LoadLevelException("Lỗi đường truyền " + e);
        }


    }

    @Override
    public void createEntities() {
        // TODO: tạo các Entity của màn chơi
        // TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

        // TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
        // TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
        // thêm Wall
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                int pos = x + y * getWidth();
//				Sprite sprite = y == 0 || x == 0 || x == 10 || y == 10 ? Sprite.wall : Sprite.grass;
//				_board.addEntity(pos, new Grass(x, y, sprite));
                switch (_map[y][x]) {
                    case '#': {
                        _board.addEntity(pos, new Wall(x, y, Sprite.wall));
//                        _board.addEntity(pos, new Wall(x, y));
                        break;
                    }
                    case 'p': {
                        _board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        Screen.setOffset(0, 0);
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
//                        _board.addEntity(pos, new Grass(x, y));
                        break;
                    }
                    case '*': {
                        _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new Brick(x, y, Sprite.brick)));
//                        _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y), new Brick(x, y)));
                        break;
                    }
//                    case '1' :
//                    {
//                        _board.addCharacter(new Balloon(Coordinates.tileToPixel(x),Coordinates.tileToPixel(y)+Game.TILES_SIZE,_board));
//                        _board.addEntity(pos,new Grass(x,y,Sprite.grass));
//                        break;
//                    }
//                    case '2' :
//                    {
//                        _board.addCharacter(new Oneal(Coordinates.tileToPixel(x),Coordinates.tileToPixel(y)+Game.TILES_SIZE,_board));
//                        _board.addEntity(pos,new Grass(x,y,Sprite.grass));
//                        break;
//
//                    }
                    case 'x': {
                        _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new Portal(x, y, _board, Sprite.portal), new Brick(x, y, Sprite.brick)));
//                        _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y), new Portal(x, y), new Brick(x, y)));
                        break;
                    }
//                    case 'f' :
//                    {
//                        _board.addEntity(pos,new LayeredEntity(x,y,new Grass(x,y,Sprite.grass),new FlameItem(x,y,Sprite.powerup_flames),new Brick(x,y,Sprite.brick)));
//                        break;
//                    }
//                    case 'b' :
//                    {
//                        _board.addEntity(pos,new LayeredEntity(x,y,new Grass(x,y,Sprite.grass),new BombItem(x,y,Sprite.powerup_bombs),new Brick(x,y,Sprite.brick) ));
//                    }
                    default:
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass));
//                        _board.addEntity(pos, new Grass(x, y));
                        break;
                }

            }
        }

//		// thêm Bomber
//		int xBomber = 1, yBomber = 1;
//		_board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
//		Screen.setOffset(0, 0);
//		_board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));
//
//		// thêm Enemy
//		int xE = 2, yE = 1;
//		_board.addCharacter( new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
//		_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
//
//		// thêm Brick
//		int xB = 3, yB = 1;
//		_board.addEntity(xB + yB * _width,
//				new LayeredEntity(xB, yB,
//					new Grass(xB, yB, Sprite.grass),
//					new Brick(xB, yB, Sprite.brick)
//				)
//		);
//		//thêm wall
////		int xW = 4,yW = 1;
////		_board.addEntity(xW + yW * _width,
////				);
//		// thêm Item kèm Brick che phủ ở trên
//		int xI = 1, yI = 2;
//		_board.addEntity(xI + yI * _width,
//				new LayeredEntity(xI, yI,
//					new Grass(xI ,yI, Sprite.grass),
//					new SpeedItem(xI, yI, Sprite.powerup_flames),
//					new Brick(xI, yI, Sprite.brick)
//				)
//		);
    }

}
