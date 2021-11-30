//package uet.entities.moving_objects.A_Star_Path;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.PriorityQueue;
//import java.util.Stack;
//import javafx.util.Pair;
//
//import uet.entities.Entity;
//import uet.level.FieldMap;
//
//public class AStar {
//    public final static int ROW;
//    public final static int COL;
//
//    private static Pair<Integer, Integer> initPosition, destination;
//    private static int[][] antCanGo;
//    private static List<Pair<Integer, Integer>> gBest = new ArrayList<>();
//
//    private List<Pair<Integer, Integer>> pathResult = new ArrayList<>();
//
//    static {
//        List<Entity> what = new ArrayList<Entity>();
//        FieldMap.initObjectPos(what);
//        char[][] tempMap = FieldMap.getFieldMap();
//        ROW = tempMap.length;
//        COL = tempMap[0].length;
//
//        antCanGo = new int[ROW][COL];
//
//        for (int i = 0; i < ROW; i++) {
//            for (int j = 0; j < COL; j++) {
//                if (tempMap[i][j] == ' ') {
//                    antCanGo[i][j] = 1;
//                } else {
//                    antCanGo[i][j] = 0;
//                }
//            }
//        }
//
//        initPosition = new Pair<Integer, Integer>(1, 1);
//        destination = new Pair<Integer, Integer>(10, 29);
//    }
//
//    // Returns true if row number and column number is in range
//    public static boolean isValid(Pair<Integer, Integer> point) {
//        return (point.getKey() >= 0) && (point.getKey() < ROW) && (point.getValue() >= 0) && (point.getValue() < COL);
//    }
//
//    public static boolean isUnBlocked(Pair<Integer, Integer> point) {
//        // Returns true if the cell is not blocked else false
//        return isValid(point) && antCanGo[point.getKey()][point.getValue()] == 1;
//    }
//
//    // A Utility Function to calculate the 'h' heuristics.
//    public static double calculateHValue(Pair<Integer, Integer> neighbor) {
//        // h is estimated with the two points distance formula
//        return Math.abs(neighbor.getKey() - destination.getKey())
//                + Math.abs(neighbor.getValue() - destination.getValue());
//    }
//
//    public static void tracePath(Cell[][] cellDetails) {
//        System.out.println("The Path is ");
//
//        Stack<Pair<Integer, Integer>> Path = new Stack<>();
//
//        int row = destination.getKey();
//        int col = destination.getValue();
//
//        Pair<Integer, Integer> next_node = cellDetails[row][col].parent;
//        do {
//            Path.push(next_node);
//            next_node = cellDetails[row][col].parent;
//            row = next_node.getKey();
//            col = next_node.getValue();
//        } while (cellDetails[row][col].parent != next_node);
//
//        Path.push(new Pair<Integer, Integer>(row, col));
//        while (!Path.empty()) {
//            Pair<Integer, Integer> p = Path.pop();
//            System.out.println("x = " + p.getValue() + ", y = " + p.getKey());
//        }
//    }
//
//    public static void aStarSearch() {
//
//        // Create a closed list, no Cell has been included yet
//        boolean[][] closedList = new boolean[ROW][COL];
//        for (boolean[] eachBool : closedList) {
//            for (boolean each : eachBool) {
//                each = false;
//            }
//        }
//
//        // Declare a 2D List of structure to hold the details of that Cell
//        Cell[][] CellDetails = new Cell[ROW][COL];
//
//        // Initialising the parameters of the starting node
//        int y = initPosition.getKey();
//        int x = initPosition.getValue();
//
//        CellDetails[y][x] = new Cell(0, 0, 0);
//        CellDetails[y][x].parent = new Pair<Integer, Integer>(y, x);
//
//        /*
//         * Create an open list having information as- <f, <i, j>> where f = g + h, and
//         * i, j are the row and column index of that Cell Note that 0 <= i <= ROW-1 & 0
//         * <= j <= COL-1 This open list is implemented as a set of tuple<double, int,
//         * int>.
//         */
//        PriorityQueue<CellDetail> openList = new PriorityQueue<>();
//
//        // Put the starting Cell on the open list and set its 'f' as 0
//        openList.add(new CellDetail(0.0, new Pair<>(y, x)));
//
//        int[] addX = { -1, 0, 1, 0 };
//        int[] addY = { 0, -1, 0, 1 };
//        // We set this boolean value as false as initially the destination is not
//        // reached.
//        while (!openList.isEmpty()) {
//            // System.out.println("While loop...");
//            final Pair<Double, Pair<Integer, Integer>> p = openList.poll().getDetail();
//            y = p.getValue().getKey(); // second element of tuple
//            x = p.getValue().getValue(); // third element of tuple
//
//            closedList[y][x] = true;
//
//            int count = 0;
//            int add_x, add_y;
//            for (; count < 4; count++) {
//                // System.out.println("For loop...");
//                add_x = addX[count];
//                add_y = addY[count];
//                Pair<Integer, Integer> neighbour = new Pair<Integer, Integer>(y + add_y, x + add_x);
//                // Only process this Cell if this is a valid one
//                if (isValid(neighbour)) {
//                    // If the destination Cell is the same as the current successor
//                    if (neighbour.equals(destination)) { // Set the Parent of the destination Cell
//                        CellDetails[neighbour.getKey()][neighbour.getValue()] = new Cell(
//                                new Pair<Integer, Integer>(y, x));
//                        System.out.println("The destination Cell is found!");
//                        tracePath(CellDetails);
//                        return;
//                    }
//                    // If the successor is already on the closed list or if it is blocked, then
//                    // ignore it
//                    else if (!closedList[neighbour.getKey()][neighbour.getValue()] && isUnBlocked(neighbour)) {
//                        double gNew, hNew, fNew;
//                        gNew = CellDetails[y][x].g + 1.0;
//                        hNew = calculateHValue(neighbour);
//                        fNew = gNew + hNew;
//
//                        // If it isn’t on the open list, add it to the open list. Make the current
//                        // square the parent of this square. Record the f, g, and h costs of the
//                        // square Cell
//                        // OR
//                        // If it is on the open list already, check to see if this path to that square
//                        // is better, using 'f' cost as the measure.
//                        if (CellDetails[neighbour.getKey()][neighbour.getValue()] == null) {
//                            CellDetails[neighbour.getKey()][neighbour.getValue()] = new Cell(-1, -1, -1);
//                        }
//                        if (CellDetails[neighbour.getKey()][neighbour.getValue()].f == -1
//                                || CellDetails[neighbour.getKey()][neighbour.getValue()].f > fNew) {
//
//                            openList.add(new CellDetail(fNew, neighbour.getKey(), neighbour.getValue()));
//
//                            // Update the details of this Cell
//                            CellDetails[neighbour.getKey()][neighbour.getValue()].g = gNew;
//                            CellDetails[neighbour.getKey()][neighbour.getValue()].h = hNew;
//                            CellDetails[neighbour.getKey()][neighbour.getValue()].f = fNew;
//                            CellDetails[neighbour.getKey()][neighbour.getValue()].parent = new Pair<Integer, Integer>(y,
//                                    x);
//
//                            // System.out.println("x = " + x + ", y = " + y);
//                        }
//                    }
//                }
//            }
//        }
//
//        System.out.println("Failed to find the Destination Cell\n");
//    }
//
//    public static void main(String[] args) {
//        System.out.println("Starting...");
//        aStarSearch();
//    }
//}