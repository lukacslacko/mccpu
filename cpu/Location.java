package cpu;

import java.util.Comparator;

public class Location {
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

    public Location rotatedAround(Location center, int quarters) {
        Vector toCenter = to(center);
        Location rotated = shifted(toCenter);
        while (quarters < 0) { quarters += 4; }
        for (; quarters > 0; --quarters) {
            rotated = new Location(-rotated.getZ(), rotated.getY(), rotated.getX());
        }
        return rotated.shifted(toCenter.times(-1));
    }

    public static class Comparator implements java.util.Comparator<Location> {
        @Override
        public int compare(Location location1, Location location2) {
            int compY = Integer.compare(location1.getY(), location2.getY());
            if (compY != 0) return compY;
            int compZ = Integer.compare(location1.getZ(), location2.getZ());
            if (compZ != 0) return compZ;
            return Integer.compare(location1.getX(), location2.getX());
        }
    }

    public Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    Location(Location location) {
        x = location.x;
        y = location.y;
        z = location.z;
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

    Vector to(Location other) {
        return new Vector(other.x - x, other.y - y, other.z - z);
    }
}
