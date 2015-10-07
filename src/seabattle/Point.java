package seabattle;

/**
 * Created by Chuyec on 05.10.2015.
 */
public class Point {
    enum Color {
        BLACK,
        RED,
        GREEN,
        YELLOW,
        BLUE,
        VIOLET,
        AQUA,
        GRAY,
    }

    private static final String COLOR_PREFIX = "\033[3";
    private static final String COLOR_POSTFIX = "m";
    private static final String COLOR_RESET = "\033[0m";
    public int x;
    public int y;
    public char symbol;
    public Color color;

    public Point() {
    }

    public Point(int x, int y, char symbol, Color color) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.color = color;
    }

    public void print() {
        char color = (char)(0x30 + this.color.ordinal());
        String colorStr = COLOR_PREFIX + color + COLOR_POSTFIX;

        System.out.print(colorStr + symbol + COLOR_RESET);
    }


}
