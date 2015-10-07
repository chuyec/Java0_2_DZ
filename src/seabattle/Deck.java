package seabattle;

/**
 * Created by Chuyec on 06.10.2015.
 */
public class Deck extends Point {
    public static final char WHOLE_DECK = 'X';
    public static final char DESTROYED_DEC = '@';
    private boolean isDestroyed;

    public Deck(int x, int y, boolean isDestroyed) {
        super(x, y, WHOLE_DECK, Color.GREEN);
        this.isDestroyed = isDestroyed;
    }
}
