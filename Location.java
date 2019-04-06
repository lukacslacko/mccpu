class Location {
    private int x, y, z;

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;
        if (!(obj instanceof  Location)) return false;
        Location other = (Location) obj;
        return x == other.x && y == other.y && z == other.z;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    Location(Location location) {
        x = location.x;
        y = location.y;
        z = location.z;
    }

    int getX() { return x; }
    int getY() { return y; }
    int getZ() { return z; }

    Location above(int dy) {
        return new Location(x, y + dy, z);
    }

    Location shifted(Vector v) {
        return new Location(x + v.getDx(), y + v.getDy(), z + v.getDz());
    }

    Vector to(Location other) {
        return new Vector(other.x - x, other.y - y, other.z - z);
    }
}
