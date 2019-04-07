package cpu;

public class Coordinates {
    private final Location origin;
    private final Vector width, length;

    public Coordinates(Location origin, Vector width, Vector length) {
        this.origin = origin;
        this.width = width;
        this.length = length;
    }

    static Coordinates NW() {
        return new Coordinates(
                new Location(0, 0, 0),
                new Vector(0, 0, -1),
                new Vector(-1, 0, 0));
    }

    Location at(int w, int l, int a) {
        return origin.shifted(width.times(w).plus(length.times(l))).above(a);
    }
}
