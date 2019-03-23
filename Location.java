public class Location {
    private int x, y, z;

    Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }

    Location above(int dy) {
        return new Location(x, y + dy, z);
    }

    Location shifted(Vector v) {
        return new Location(x + v.getDx(), y + v.getDy(), z + v.getDz());
    }
}
