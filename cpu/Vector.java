package cpu;

public class Vector {
    private int dx, dy, dz;

    public Vector(int dx, int dy, int dz) {
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }

    Vector copy() {
        return new Vector(dx, dy, dz);
    }

    int getDx() { return dx; }
    int getDy() { return dy; }
    int getDz() { return dz; }

    public Vector times(int n) {
        return new Vector(n*dx, n*dy, n*dz);
    }

    Vector rotate() {
        return new Vector(dz, dy, -dx);
    }

    public Vector rotate(int quarters) {
        Vector v = copy();
        for (int i = 0; i < quarters; ++i) {
            v = v.rotate();
        }
        return v;
    }

    public Vector plus(Vector other) {
        return new Vector(dx + other.dx, dy + other.dy, dz + other.dz);
    }
}
