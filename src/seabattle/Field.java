package seabattle;

import java.util.ArrayList;

/**
 * Created by Chuyec on 05.10.2015.
 */
public class Field {
    private Point[][] points;
    private int size;
    private ArrayList<Ship> shipArrayList;

    public Field(int size) {
        this.size = size;
        points = new Point[size][size];

        for (int i = 0; i < points.length; i++) {
            Point[] point = points[i];
            for (int j = 0; j < point.length; j++) {
                points[i][j] = new Point(j, i, '.', Point.Color.AQUA);
            }
        }

        shipArrayList = new ArrayList<>();
    }

    public void print() {
        System.out.printf("   ");
        for (int i = 1; i < size + 1; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < points.length; i++) {
            Point[] point = points[i];
            for (int j = 0; j < point.length; j++) {
                if (j == 0) {
                    System.out.printf("%2d ", i + 1);
                }
                Point cell = point[j];
                cell.print();
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Установить все корабли на поле рандомно
     */
    void fill() {
        shipArrayList.add(new Ship(4));
        for (int i = 0; i < 2; i++) {
            shipArrayList.add(new Ship(3));
        }
        for (int i = 0; i < 3; i++) {
            shipArrayList.add(new Ship(2));
        }
        for (int i = 0; i < 4; i++) {
            shipArrayList.add(new Ship(1));
        }

        for (int i = 0; i < shipArrayList.size(); i++) {
            Ship ship;
            do {
                ship = shipArrayList.get(i);
                ship.createRandom(size);
            } while(!trySetShip(ship));
            print();
            System.out.println();
        }
    }

    /**
     * Попробовать установить один корабль
     * @param ship Корабль
     * @return true, если удалось, иначе false
     */
    private boolean trySetShip(Ship ship) {
        for (int i = 0; i < ship.size; i++) {
            Deck deck = ship.decks[i];
            if (!checkPoint(deck)) {
//                System.out.printf("FALSE: X = %d, Y = %d, %s\n", deck.x, deck.y, ship.orientation.name());
                return false;
            }
//            System.out.printf("TRUE: X = %d, Y = %d, %s\n", deck.x, deck.y, ship.orientation.name());
        }

        for (int i = 0; i < ship.size; i++) {
            Deck deck = ship.decks[i];
            points[deck.y][deck.x].symbol = Deck.WHOLE_DECK;
            points[deck.y][deck.x].color = Point.Color.GREEN;
        }
        return true;
    }

    /**
     * Проверить точку на поле на возможность поставить туда палубу корабля
     * @param deck Палуба
     * @return true, если палубу возможно поставить, иначе false
     */
    private boolean checkPoint(Deck deck) {
        if (points[deck.y][deck.x].symbol != '.') {
            System.out.printf("FALSE: Y = %d, X = %d, point = %c\n", deck.y, deck.x, points[deck.y][deck.x].symbol);
            return false;
        }

        for (int i = deck.y - 1; i <= deck.y + 1; i++) {
            for (int j = deck.x - 1; j <= deck.x + 1; j++) {
                if (i < 0 || j < 0) {
                    System.out.println("Continue");
                    continue;
                }
                if (i > size - 1 || j > size - 1) {
                    System.out.println("Continue");
                    continue;
                }
                System.out.printf("Check. Y = %d, X = %d, point = %c\n", i, j, points[i][j].symbol);
                if (points[i][j].symbol != '.') {
                    System.out.printf("FALSE: Y = %d, X = %d, point[%d][%d] = %c\n", deck.y, deck.x, i, j, points[i][j].symbol);
                    return false;
                }
            }
        }
        System.out.printf("TRUE: Y = %d, X = %d\n", deck.y, deck.x);
        return true;
    }
}
