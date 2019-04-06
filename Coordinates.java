class Coordinates {
    private Vector width;
    private Vector length;

    Coordinates(Vector width, Vector length) {
        this.width = width;
        this.length = length;
    }

    static Coordinates NW() {
        return new Coordinates(new Vector(0, 0, -1), new Vector(-1, 0, 0));
    }

    Coordinates rotate(int quarters) {
        while (quarters < 0) quarters += 4;
        return new Coordinates(width.rotate(quarters), length.rotate(quarters));
    }

    Vector at(int w, int l) {
        return width.times(w).plus(length.times(l));
    }

    Coordinates mirror() {
        return new Coordinates(width.times(-1), length);
    }
}
