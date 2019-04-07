abstract class Renderer {
    private Location origin;
    Coordinates coordinates;

    Renderer(Location origin, Coordinates coordinates) {
        this.origin = origin;
        this.coordinates = coordinates;
    }

    void rotate(int quarters) {
        coordinates = coordinates.rotate(quarters);
    }

    void shift(Vector v) {
        origin = origin.shifted(v);
    }

    Vector pos(int w, int l, int a) {
        return coordinates.at(w, l).plus(new Vector(0, a, 0));
    }

    Location loc(int w, int l, int a) {
        return origin.shifted(pos(w, l, a));
    }

    abstract void render(RenderTarget target);
}
